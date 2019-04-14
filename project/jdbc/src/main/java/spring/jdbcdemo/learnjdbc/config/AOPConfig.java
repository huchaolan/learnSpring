package spring.jdbcdemo.learnjdbc.config;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

/**
 * AOPConfig
 */
@Aspect
public class AOPConfig {

	@Autowired
	private DataSourceTransactionManager transactionManager;

	// @Around("execution(* spring.jdbcdemo.learnjdbc.service.*.update*(..))")
	// public Object updateAround(ProceedingJoinPoint pjp) throws Throwable{
	// 	DefaultTransactionDefinition defaulttx = new DefaultTransactionDefinition();
	// 	defaulttx.setIsolationLevel(TransactionDefinition.ISOLATION_READ_COMMITTED);
	// 	defaulttx.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
	// 	TransactionStatus  txstatus = transactionManager.getTransaction(defaulttx);
	// 	Object result = null;
	// 	try{
	// 		Object[] args = pjp.getArgs();
	// 		result = pjp.proceed(args);
	// 		txstatus.setRollbackOnly();
	// 	}catch(Throwable e){
	// 		throw e;
	// 	}finally{
	// 		transactionManager.commit(txstatus);
	// 	}
	// 	return result;
	// }

	// @Around("execution(* spring.jdbcdemo.learnjdbc.service.*.getProduct(..))")
	// public Object around(ProceedingJoinPoint pjp) throws Throwable{
	// 	DefaultTransactionDefinition defaulttx = new DefaultTransactionDefinition();
	// 	defaulttx.setReadOnly(true);
	// 	TransactionStatus  txstatus = transactionManager.getTransaction(defaulttx);
	// 	Object result = null;
	// 	Throwable t = null;
	// 	try{
	// 		Object[] args = pjp.getArgs();
	// 		result = pjp.proceed(args);
	// 	}catch(Throwable e){
	// 		e.printStackTrace();
	// 		txstatus.setRollbackOnly();
	// 		t = e;
	// 	}
	// 	transactionManager.commit(txstatus);
	// 	if(t != null) {
	// 		throw t;
	// 	}
	// 	return result;
	// }
}