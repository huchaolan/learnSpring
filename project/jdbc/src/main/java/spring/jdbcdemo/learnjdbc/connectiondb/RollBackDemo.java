package spring.jdbcdemo.learnjdbc.connectiondb;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.transaction.interceptor.TransactionInterceptor;

import spring.jdbcdemo.learnjdbc.config.CommonConfig;
import spring.jdbcdemo.learnjdbc.service.AOPDemoServcie;

/**
 * 测试回滚
 */
public class RollBackDemo {

	public static void main(String[] args) {
		txupdate();
	}

	private static void txupdate() {
		TransactionInterceptor t = null;
		ApplicationContext context = new AnnotationConfigApplicationContext(CommonConfig.class);
		AOPDemoServcie aopDemoService = context.getBean("aopDemoServcie",AOPDemoServcie.class);
		aopDemoService.testRollBack();
	}
}