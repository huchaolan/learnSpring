package spring.jdbcdemo.learnjdbc.config;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;

import spring.jdbcdemo.learnjdbc.connectiondb.jdbcdao.JDBCDao;

/**
 * SpringConfig
 */
@Configurable
public class CommonConfig {

	@Bean
	public JDBCDao jdbcDao() {
		return new JDBCDao();
	}

	@Bean
	public JdbcTemplate jdbcTemplate(DataSource ds) {
		return new JdbcTemplate(ds);
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