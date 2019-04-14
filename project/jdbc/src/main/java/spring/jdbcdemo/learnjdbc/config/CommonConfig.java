package spring.jdbcdemo.learnjdbc.config;

import javax.sql.DataSource;

import com.zaxxer.hikari.HikariDataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import spring.jdbcdemo.learnjdbc.connectiondb.jdbcdao.JDBCDao;
import spring.jdbcdemo.learnjdbc.service.AOPDemoServcie;

/**
 * SpringConfig
 */
@Configurable
@PropertySource("classpath:dbconfig/test.properties")
@EnableAspectJAutoProxy
//@ComponentScan(basePackages="spring.jdbcdemo.learnjdbc",
//	includeFilters=@Filter(type=FilterType.ANNOTATION,value=Aspect.class))
@ImportResource(locations="classpath:tx-spring.xml")
public class CommonConfig {

	private static final String CLOSE = "close";

	@Value("${dbusername}")
	private String dbusername;

	@Value("${password}")
	private String password;

	@Bean
	public AOPDemoServcie aopDemoServcie() {
		AOPDemoServcie result = new AOPDemoServcie();
		return result;
	}

	@Bean
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer(){
		return new PropertySourcesPlaceholderConfigurer();
	}

	@Bean
	public TransactionTemplate transactionTemplate() {
		TransactionTemplate txtemplate = new TransactionTemplate();
		txtemplate.setTransactionManager(transactionManager());
		return txtemplate;
	}

	@Bean
	public DataSourceTransactionManager transactionManager() {
		DataSourceTransactionManager txmanager = new DataSourceTransactionManager();
		txmanager.setDataSource(hiDataSource());
		return txmanager;
	}

	@Bean
	public JDBCDao jdbcDao() {
		return new JDBCDao();
	}

	@Bean
	public JdbcTemplate jdbcTemplate() {
		return new JdbcTemplate(hiDataSource());
	}

	//@Bean(destroyMethod = CLOSE)
	// public DataSource dataSource() {
	// 	BasicDataSource ds = new BasicDataSource();
	// 	ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
	// 	ds.setUrl("jdbc:mysql://localhost:3306/springcloud_spell?characterEncoding=utf-8&userSSL=false&serverTimezone=Hongkong");
	// 	ds.setUsername(dbusername);
	// 	ds.setPassword(password);
	// 	ds.setInitialSize(15);
	// 	ds.setMaxTotal(20);
	// 	return ds;
	// }

	@Bean
	public DataSource hiDataSource() {
		HikariDataSource ds = new HikariDataSource();
		ds.setJdbcUrl("jdbc:mysql://localhost:3306/springcloud_spell?characterEncoding=utf-8&userSSL=false&serverTimezone=Hongkong");
		ds.setUsername(dbusername);
		ds.setPassword(password);
		ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
		return ds;
	}

	@Bean
	public DataSourceTransactionManager txManager(){
		DataSourceTransactionManager txmanager = new DataSourceTransactionManager();
		txmanager.setDataSource(hiDataSource());
		return txmanager;
	}
}