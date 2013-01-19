// ChirpsDao.java, created on Jan 17, 2013
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
 * Database access layer for chirps.
 * 
 * @author fabio
 */
@Repository
public class ChirpsDao {
    @Autowired
    @Qualifier("jdbcTemplate")
    private NamedParameterJdbcTemplate jdbcTemplate;

    public static final RowMapper<ChirpEntity> CHIRP_MAPPER = new RowMapper<ChirpEntity>() {
        @Override
        public ChirpEntity mapRow(ResultSet rs, int line) throws SQLException {
            ChirpEntity c = new ChirpEntity();
            c.setId(rs.getLong("chirp_id"));
            c.setUserId(rs.getLong("user_id"));
            c.setContent(rs.getString("content"));
            c.setCreated(rs.getTimestamp("created"));
            return c;
        }
    };

    public static final RowMapper<ChirpUserEntity> CHIRP_USER_MAPPER = new RowMapper<ChirpUserEntity>() {
        @Override
        public ChirpUserEntity mapRow(ResultSet rs, int line) throws SQLException {
            ChirpUserEntity c = new ChirpUserEntity();
            c.setChirp(CHIRP_MAPPER.mapRow(rs, line));
            c.setUser(UsersDao.USER_MAPPER.mapRow(rs, line));
            return c;
        }
    };

    /**
     * @param user
     * @return
     */
    public List<ChirpUserEntity> findByUsername(String user) {
        List<ChirpUserEntity> data = jdbcTemplate.query("select c.chirp_id, c.created, c.content, u.* "
                + "from chirps c, users u where c.user_id = u.user_id and "
                + "(c.user_id = (select user_id from users where username = :user) or "
                + "c.user_id in (select followed_id from followers "
                + "where following_id = (select user_id from users where username = :user))) order by c.created",
                singletonMap("user", user), CHIRP_USER_MAPPER);
        return data;
    }

    /**
     * @param user
     * @return
     */
    public List<ChirpUserEntity> findByUsernameAndKey(String user, String key) {
        Map<String, String> params = new HashMap<>();
        params.put("user", user);
        params.put("key", "%" + key + "%");

        List<ChirpUserEntity> data = jdbcTemplate.query("select c.chirp_id, c.created, c.content, u.* "
                + "from chirps c, users u where c.user_id = u.user_id and c.content like :key and "
                + "(c.user_id = (select user_id from users where username = :user) or "
                + "c.user_id in (select followed_id from followers "
                + "where following_id = (select user_id from users where username = :user))) order by c.created",
                params, CHIRP_USER_MAPPER);

        return data;
    }
}
