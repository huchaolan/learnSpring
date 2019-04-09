package spring.jdbcdemo.learnjdbc.connectiondb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import spring.jdbcdemo.learnjdbc.bean.ProductInfo;
import spring.jdbcdemo.learnjdbc.config.CommonConfig;
import spring.jdbcdemo.learnjdbc.connectiondb.jdbcdao.JDBCDao;

/**
 * ConntionDBDemo
 */
public class ConntionDBDemo {

	public static void main(String[] args) throws Exception {
		txSupport();
	}

	/**
	 * 事务的支持
	 */
	private static void txSupport() throws Exception{
		ApplicationContext context = new AnnotationConfigApplicationContext(CommonConfig.class);
		DataSource ds = context.getBean("hiDataSource", DataSource.class);
		Connection conn = ds.getConnection();
		System.out.println("supportsTransactions"+conn.getMetaData().supportsTransactions()); 
		System.out.println("supportsTransactionIsolationLevel0："+
		conn.getMetaData().supportsTransactionIsolationLevel(0)); 
		System.out.println("supportsTransactionIsolationLevel1："+
		conn.getMetaData().supportsTransactionIsolationLevel(1)); 
		System.out.println("supportsTransactionIsolationLevel2："+
		conn.getMetaData().supportsTransactionIsolationLevel(2)); 
		System.out.println("supportsTransactionIsolationLevel3："+
		conn.getMetaData().supportsTransactionIsolationLevel(3)); 
		System.out.println("supportsTransactionIsolationLevel4："+
		conn.getMetaData().supportsTransactionIsolationLevel(4)); 
	}

	/**
	 * 编程式事务
	 */
	private static void txDemo() {
		ApplicationContext context = new AnnotationConfigApplicationContext(CommonConfig.class);
		TransactionTemplate tt = context.getBean("transactionTemplate", TransactionTemplate.class);
		tt.execute(new TransactionCallbackWithoutResult() {

			@Override
			protected void doInTransactionWithoutResult(TransactionStatus status) {
				JDBCDao dao = context.getBean("jdbcDao", JDBCDao.class);
				try{
					int i = dao.update("update product_info f set f.product_stock = 50 where f.product_stock = 67");
					System.out.println("i>>>"+i);
				}catch(Exception e){
					e.printStackTrace();
					status.setRollbackOnly();//调用status对象的setRollbackOnly()方法告知事务管理器当前事务需要回滚
				}
			}
			
		});
	}
	/**
	 * 使用jdbcTemplate查询
	 */
	private static void jdbcTemplate() {
		ApplicationContext context = new AnnotationConfigApplicationContext(CommonConfig.class);
		JDBCDao dao = context.getBean("jdbcDao", JDBCDao.class);
		String sql = "select * from product_info";
		//List<Map<String,Object>> list = dao.test(sql);
		//System.out.println(list);
		for(int i=0;i<100;i++){
			List<ProductInfo> list2 = dao.test1(sql, (rs,rowNum)->{
				ProductInfo p = new ProductInfo();
				p.setProductId(rs.getString("product_id"));
				p.setProductName(rs.getString("product_name"));
				p.setProductPrice(rs.getBigDecimal("product_price"));
				p.setProductStock(rs.getInt("product_stock"));
				p.setProductDescription(rs.getString("product_description"));
				p.setProductIcon(rs.getString("product_icon"));
				p.setProductStatus(rs.getInt("product_status"));
				p.setCategoryType(rs.getInt("category_type"));
				p.setCreateTime(rs.getTimestamp("create_time"));
				p.setUpdateTime(rs.getTimestamp("update_time"));
				return p;
			});
			System.out.println(list2);
		}

	}

	/**
	 * 使用一般的方式查询数据库
	 * @throws SQLException
	 */
	private static void normalSelect() throws SQLException {
		ApplicationContext context = new AnnotationConfigApplicationContext(CommonConfig.class);
		DataSource ds = context.getBean("dataSource", DataSource.class);
		System.out.println(ds);
		String sql = "select * from product_info";
		try (Connection con = ds.getConnection();
				PreparedStatement prep = con.prepareStatement(sql);
				ResultSet rs = prep.executeQuery();) {
			while (rs.next()) {
				System.out.println(rs.getString("product_id"));
			}
		}
	}
}