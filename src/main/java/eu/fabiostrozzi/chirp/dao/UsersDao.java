// UsersDao.java, created on Jan 17, 2013
package eu.fabiostrozzi.chirp.dao;

import static java.util.Collections.singletonMap;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * Database access layer for users.
 * 
 * @author fabio
 */
@Repository
public class UsersDao {

    @Autowired
    @Qualifier("jdbcTemplate")
    private NamedParameterJdbcTemplate jdbcTemplate;

    private static final RowMapper<UserData> USER_MAPPER = new RowMapper<UserData>() {
        @Override
        public UserData mapRow(ResultSet rs, int line) throws SQLException {
            UserData ud = new UserData();
            ud.setId(rs.getLong("id"));
            ud.setFirstName(rs.getString("first_name"));
            ud.setLastName(rs.getString("last_name"));
            ud.setUsername(rs.getString("username"));
            ud.setToken(rs.getString("token"));
            return ud;
        }
    };

    /**
     * Gets the only user that has the given token.
     * <p>
     * If none is found, returns null.
     * 
     * @param token
     * @return
     */
    public UserData getByToken(String token) {
        List<UserData> users = jdbcTemplate.query("select * from users where token = :token",
                singletonMap("token", token), USER_MAPPER);
        return users.size() == 1 ? users.get(0) : null;
    }

    /**
     * Gets the only user that has the given username.
     * <p>
     * If none is found, returns null.
     * 
     * @param user
     * @return
     */
    public UserData getByUsername(String user) {
        List<UserData> users = jdbcTemplate.query("select * from users where username = :user",
                singletonMap("user", user), USER_MAPPER);
        return users.size() == 1 ? users.get(0) : null;
    }

    /**
     * Makes {@code actor} follows a new user.
     * <p>
     * If {@code actor} already follows {@code friend}, then nothing changes.
     * 
     * @param actor
     * @param friend
     */
    public void follow(String actor, String friend) {
        Map<String, String> params = new HashMap<>();
        params.put("actor", actor);
        params.put("friend", friend);

        long count = jdbcTemplate.queryForLong("select count(1) from followers where "
                + "user_id = (select id from users where username = :actor) and "
                + "followed_id = (select id from users where username = :friend)", params);
        if (count == 0)
            jdbcTemplate.update("insert into followers(user_id, followed_id) values ("
                    + "(select id from users where username = :actor), "
                    + "(select id from users where username = :friend))", params);
    }

    /**
     * Makes {@code actor} stop following a user.
     * 
     * @param actor
     * @param who
     */
    public void unfollow(String actor, String who) {
        Map<String, String> params = new HashMap<>();
        params.put("actor", actor);
        params.put("who", who);

        jdbcTemplate.update("delete from followers where "
                + "user_id = (select id from users where username = :actor) and "
                + "followed_id = (select id from users where username = :who)", params);
    }
}
