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

    public static final RowMapper<UserEntity> USER_MAPPER = new RowMapper<UserEntity>() {
        @Override
        public UserEntity mapRow(ResultSet rs, int line) throws SQLException {
            UserEntity u = new UserEntity();
            u.setId(rs.getLong("user_id"));
            u.setFirstName(rs.getString("first_name"));
            u.setLastName(rs.getString("last_name"));
            u.setUsername(rs.getString("username"));
            u.setToken(rs.getString("token"));
            return u;
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
    public UserEntity getByToken(String token) {
        List<UserEntity> users = jdbcTemplate.query("select * from users where token = :token",
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
    public UserEntity getByUsername(String user) {
        List<UserEntity> users = jdbcTemplate.query("select * from users where username = :user",
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
                + "following_id = (select user_id from users where username = :actor) and "
                + "followed_id = (select user_id from users where username = :friend)", params);
        if (count == 0)
            jdbcTemplate.update("insert into followers(following_id, followed_id) values ("
                    + "(select user_id from users where username = :actor), "
                    + "(select user_id from users where username = :friend))", params);
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
                + "following_id = (select user_id from users where username = :actor) and "
                + "followed_id = (select user_id from users where username = :who)", params);
    }

    /**
     * @param user
     * @return
     */
    public List<UserEntity> getFollowedBy(String user) {
        List<UserEntity> users = jdbcTemplate.query("select u.* from users u, followers f "
                + "where u.user_id = f.followed_id and f.following_id = (select user_id from users where username = :user)",
                singletonMap("user", user), USER_MAPPER);
        return users;
    }

    /**
     * @param user
     * @return
     */
    public List<UserEntity> getFollowersOf(String user) {
        List<UserEntity> users = jdbcTemplate.query("select u.* from users u, followers f "
                + "where u.user_id = f.following_id and f.followed_id = (select user_id from users where username = :user)",
                singletonMap("user", user), USER_MAPPER);
        return users;
    }
}
