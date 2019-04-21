package spring.jdbcdemo.learnjdbc.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;

import spring.jdbcdemo.learnjdbc.bean.ProductInfo;
import spring.jdbcdemo.learnjdbc.connectiondb.jdbcdao.JDBCDao;

/**
 * AOPDemoServcie
 */
public class AOPDemoServcie {

	@Autowired
	private JDBCDao jdao;

	public List<ProductInfo> getProduct() {
		return jdao.test1("select * from product_info", new RowMapper<ProductInfo>() {

			@Override
			public ProductInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
				ProductInfo p = new ProductInfo();
				p.setProductId(rs.getString(1));
				p.setProductName(rs.getString(2));
				return p;
			}
		});
	}

	public int updateProductById(String sql){
		return jdao.update(sql);
	}

	public void init(List<ProductInfo> list) {
		jdao.forTest(list);
	}

	public void testRollBack() {
		jdao.update("update product_info f set f.product_name = 'rrr' where f.product_id = 'e64fbbdeca234bbd8e47133c6a2b3fa9' ");
		jdao.update("update product_info f set f.product_price = 'aaa' where f.product_price = aaa");
	}
}