# cloud-demo 
按模块分层

## cloud-demo-api 
对外api和请求/返回回DTO对象DTO对象

## cloud-demo-dao
数据库层,存放实体entity(po),mapper,handler,enum,po(联表查询返回对象/复杂查询对象),
联表查询时建议不要超过三张表join操作

## cloud-demo-business
业务层,业务service方法层和其他常量,配置等,依赖cloud-demo-api和cloud-demo-dao

## cloud-demo-service
service服务, 对外提供服务能力,依赖cloud-demo-business

## cloud-demo-web
web服务, 对展示端提供接口,依赖cloud-demo-business

## 目前只使用cloud-demo-web项目, cloud-demo-service项目不用理会