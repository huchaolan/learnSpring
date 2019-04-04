# Spring对DAO的支持

## 提供DAO层的抽象可以带来一些好处

1. 容易的构造模拟对象，方便单元测试的开展
2. 使用切面时会有更多的选择，既可以使用JDK动态代理，又可以使用CGLib动态代理

## 统一的异常体系

### Spring的DAO异常体系

Spring提供了完备的DAO异常体系，这些异常都继承与`DataAccessException`，而它继承NestedRuntimeException。可以用getCause方法获取原始的异常信息。SQLException要通过getErrorCode和getSQLState获取

### 统一数据访问模板

Spring将相同的数据访问流程固化到模板类章，并将数据访问中固定和变化的部分分开，同事保证模板类时线程安全的，以便多个数据访问线程共享同一个模板实例。固定的部分在模板类中已经准备好，而变化的部分通过回调接口开放出来，用于定义具体数据访问和结果返回的操作。

#### Spring为不同持久化技术提供了模板类

|ORM技术|模板类|
|-------|-----|
|JDBC|JdbcTemplate|
|HibernateX.0|HibernateTemplate|
|JPA|JpaTemplate|
|JDO|JdoTemplate|

每个模板类都有对应的Support类,它都继承DaoSupport类，它实现了InitializingBean接口，在afterPropertiesSet接口方法中检查模板对象和数据源是否被正确设置，否则将抛出异常，每个都是抽象的类需要被实现，
|ORM技术|支持类|
|-------|-----|
|JDBC|JdbcDaoSupport|
|HibernateX.0|HibernateDaoSupport|
|JPA|JpaDaoSupport|
|JDO|JdoDaoSupport|

### 数据源

Spring中数据链接时通过数据源获取的，数据源可以JNDI获取，通过配置据获取，还可以通过代码创建。
