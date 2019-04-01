package spring.jdbcdemo.learnjdbc.config;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import spring.jdbcdemo.learnjdbc.connectiondb.jdbcdao.JDBCDao;

/**
 * SpringConfig
 */
@Configurable
public class CommonConfig {

	@Bean
	public TransactionTemplate  transactionTemplate () {
		TransactionTemplate txtemplate =  new TransactionTemplate();
		txtemplate.setTransactionManager(transactionManager());
		return txtemplate;
	}
	@Bean
	public DataSourceTransactionManager transactionManager(){
		DataSourceTransactionManager txmanager = new DataSourceTransactionManager();
		txmanager.setDataSource(dataSource());
		return txmanager;
	}
	@Bean
	public JDBCDao jdbcDao() {
		return new JDBCDao();
	}

	@Bean
	public JdbcTemplate jdbcTemplate() {
		return new JdbcTemplate(dataSource());
	}

	@Bean
	public DataSource dataSource() {
		BasicDataSource ds = new BasicDataSource();
		ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
		ds.setUrl("jdbc:mysql://localhost:3306/springcloud_spell?characterEncoding=utf-8&userSSL=false&serverTimezone=Hongkong");
		ds.setUsername("root");
		ds.setPassword("wtadmin");
		ds.setInitialSize(15);
		ds.setMaxTotal(20);
		return ds;
	}
}