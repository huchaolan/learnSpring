package spring.jdbcdemo.learnjdbc.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import spring.jdbcdemo.learnjdbc.connectiondb.jdbcdao.JDBCDao;
import spring.jdbcdemo.learnjdbc.service.AOPDemoServcie;

import com.zaxxer.hikari.HikariDataSource;

/**
 * SpringConfig
 */
@Configuration
@PropertySource("classpath:dbconfig/test.properties")
@EnableAspectJAutoProxy
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

	/**
	 * 加载属性配置文件
	 * @return
	 */
	@Bean
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer(){
		return new PropertySourcesPlaceholderConfigurer();
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
        System.out.println("hiDataSource:"+ds);
        ds.setJdbcUrl("jdbc:mysql://localhost:3306/springcloud_spell?characterEncoding=utf-8&userSSL=false&serverTimezone=Hongkong");
        ds.setUsername(dbusername);
        ds.setPassword(password);
        ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
        ds.setAutoCommit(false);
	    ds.setMaximumPoolSize(20);
	    ds.setMinimumIdle(5);
        return ds;
	}

	@Bean
	public DataSourceTransactionManager txManager(){
		DataSourceTransactionManager txmanager = new DataSourceTransactionManager();
		txmanager.setDataSource(hiDataSource());
		return txmanager;
	}
}