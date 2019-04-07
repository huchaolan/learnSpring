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

DBCMP数据源

依赖Jakarta Commons-pool对象池机制的数据库连接池

事务属性

|属性|默认值|说明|
|----|-----|----|
|defaultAutoCommit|true|连接池创建的连接的默认auto-commit状态|
|defaultReadOnly|驱动默认|连接池创建的连接的默认read-only状态。如果没有设置，则setReadOnly()方法将不会被调用|
|defaultTransactionIsolation|驱动默认|连接池创建的连接的默认的TransactionIsolation状态|

数据源连接数量

|属性|默认值|说明|
|----|-----|----|
|initalSize|0|初始化连接|
|maxActive|8|最大活动连接，同一时间能够分配的最大活动连接数量，负数表示不限制|
|maxIdle|8|最大空闲连接，允许保持空闲状态的最大连接数量，超过的空闲连接将被释放。|
|maxWait|无限|最大等待时间,当没有可用连接时，连接池等待连接被归还的最大时间(以毫秒计数),超过时间则抛出异常。如果设置-1，表示无限等待|

连接健康情况维护和间长

|属性|默认值|说明|
|----|-----|----|
|validationQuery|无默认值|sql查询语句，连接返回调用者前用于验证连接是否可用。它必须返回一行记录|
|testOnBorrow|true|当检验失败后从连接池中去除该连接尝试取出另一个新的连接|
|testOnReturn|false|是否在归还到连接池中前进行检验。
|testWhileIdle|false|是否被空闲连接回收期(如果有)进行检验。如果检测失败，从连接池中移除|
|timeBetweenEvictionRunsMillis|-1|空闲连接器线程运行的周期，以毫秒为单位，负数为不允许|
|numTestsPerEvictionRun|3|每次空闲连接回收器线程运行时检查的连接数量|
|minEvictableIdleTimeMillis|1000*60*30|连接在可被空闲连接回收器回收前已经在连接池中的空闲时间|

缓存语句

|属性|默认值|说明|
|----|-----|----|
|poolPpreparedStatements|false|开启连接池的prepared statement池功能，会缓存CallableStatements和PreparedStatements
|maxOpenPreparedStatements|无限制|分配的打开的statements的最大数量。

连接泄露回收

|属性|默认值|说明|
|----|-----|----|
|removeAbandoned|false|标记是否删除泄露的链接,机制:idle<2 and active>(maxactive-3) 才会触发，但是只有活动连接的未被使用的时间超过removeAbandonedTimeout时才会被回收(默认300s)。
|removeAbandonedTimeout|300|泄露的链接可以被回收的超时值。
|logAbandoned|false|标记昂Statement或连接被泄露时是否打印堆栈。

