package spring.jdbcdemo.learnjdbc.connectiondb.jdbcdao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import spring.jdbcdemo.learnjdbc.bean.ProductInfo;

/**
 * JDBCDao
 */
public class JDBCDao {

	@Autowired
	@Qualifier("jdbcTemplate")
	private JdbcOperations jdbcop;

	public List<Map<String, Object>> test(String sql) {
		return jdbcop.queryForList(sql);
	}

	public <T> List<T> test1(String sql, RowMapper<T> rowMapper) {
		return jdbcop.query(sql, rowMapper);
	}

	public int update(String sql) {
		return jdbcop.update(sql);
	}

	public void forTest(List<ProductInfo> list)  {
		StringBuilder str = new StringBuilder();
		str.append("INSERT INTO `springcloud_spell`.`product_info`");
		str.append("(`product_id`,");
		str.append("`product_name`,");
		str.append("`product_price`,");
		str.append("`product_stock`,");
		str.append("`product_description`,");
		str.append("`product_icon`,");
		str.append("`product_status`,");
		str.append("`category_type`,");
		str.append("`create_time`,");
		str.append("`update_time`)");
		str.append("VALUES");
		str.append("(?,");
		str.append("?,");
		str.append("?,");
		str.append("?,");
		str.append("?,");
		str.append("?,");
		str.append("?,");
		str.append("?,");
		str.append("?,");
		str.append("?)");
		jdbcop.batchUpdate(str.toString(), new BatchPreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				ProductInfo p = list.get(i);
				ps.setString(1, p.getProductId());
				ps.setString(2, p.getProductName());
				ps.setBigDecimal(3, p.getProductPrice());
				ps.setInt(4, p.getProductStock());
				ps.setString(5, p.getProductDescription());
				ps.setString(6, p.getProductIcon());
				ps.setInt(7, p.getProductStatus());
				ps.setInt(8, p.getCategoryType());
				ps.setTimestamp(9,new Timestamp(System.currentTimeMillis()));
				ps.setTimestamp(10,new Timestamp(System.currentTimeMillis()));
			}

			@Override
			public int getBatchSize() {
				return list.size();
			}
		});
	} 
	

}