提供基础api，提供dubbo服务接口和实体类型
### 事务
#### @GlobalTransactional
使用seata作为分布式事务管理，service层的方法上添加@GlobalTransactional注解，该注解会把服务之间的调用加入事务中。
#### @Transactional
使用spring的事务管理来处理单个服务内部事务，service层使用该注解。
