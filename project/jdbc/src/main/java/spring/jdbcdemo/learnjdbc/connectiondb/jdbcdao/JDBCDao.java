package spring.jdbcdemo.learnjdbc.connectiondb.jdbcdao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;

/**
 * JDBCDao
 */
public class JDBCDao {

	@Autowired
	@Qualifier("jdbcTemplate")
	private JdbcOperations jdbcop;

	public List<Map<String, Object>> test(String sql){
		return jdbcop.queryForList(sql);
	}

	public <T> List<T> test1(String sql,RowMapper<T> rowMapper) {
		return jdbcop.query(sql, rowMapper);
	}
	

}