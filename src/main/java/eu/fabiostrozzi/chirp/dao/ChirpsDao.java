// ChirpsDao.java, created on Jan 17, 2013
package eu.fabiostrozzi.chirp.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

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

    public static final RowMapper<ChirpData> CHIRP_MAPPER = new RowMapper<ChirpData>() {
        @Override
        public ChirpData mapRow(ResultSet rs, int line) throws SQLException {
            ChirpData c = new ChirpData();
            c.setId(rs.getLong("id"));
            c.setUserId(rs.getLong("user_id"));
            c.setContent(rs.getString("content"));
            c.setCreated(rs.getTimestamp("created"));
            return c;
        }
    };

}
