[English](/README.md) |  中文

<p align="center">
	<a href="#"><img src="https://images.gitee.com/uploads/images/2019/1009/235538_73450b95_1468963.png" width="700"></a>
</p>
<p align="center">
	<a target="_blank" href="https://search.maven.org/search?q=M-PasS">
		<img src="https://img.shields.io/badge/Maven Central-1.12.0-blue.svg" ></img>
	</a>
        <a target="_blank" href="https://gitee.com/ibyte/M-Pass">
		<img src="https://img.shields.io/badge/Spring%20Boot-2.0.9.RELEASE-blue" alt="Downloads"/>
	</a>
        <a target="_blank" href="https://gitee.com/ibyte/M-Pass">
		<img src="https://img.shields.io/badge/Spring%20Cloud-Finchley.SR4-blue" alt="Downloads"/>
	</a>
	<a target="_blank" href="https://www.oracle.com/technetwork/java/javase/downloads/index.html">
		<img src="https://img.shields.io/badge/JDK-1.8+-green.svg" ></img>
	</a>
	<a target="_blank" href="https://gitee.com/ibyte/M-Pass" title="API文档">
		<img src="https://img.shields.io/badge/Api Docs-1.12.0-orange.svg" ></img>
	</a>
</p>

基于SpringBoot2.x、SpringCloud并采用前后端分离的企业级微服务,多租户系统架构微服务开发平台 mPaaS（Microservice PaaS）为租户业务开发、测试、运营及运维开源框架，能有效降低技术门槛、减少研发成本、提升开发效率，协助企业快速搭建稳定高质量的微服务应用;同时还集合各种微服务治理功能和监控功能。模块包括:企业级的认证系统、开发平台、应用监控、慢sql监控、统一日志、单点登录、Redis分布式高速缓存、配置中心、分布式任务调度、接口文档、代码生成等等

    ______  ___     ________                      
    ___   |/  /     ___  __ \_____ _______________
    __  /|_/ /________  /_/ /  __ `/_  ___/_  ___/
    _  /  / /_/_____/  ____// /_/ /_(__  )_(__  )
    /_/  /_/        /_/     \__,_/ /____/ /____/  
## 项目开发更新进度
### 如果您觉得有帮助，请点右上角 "Star" 支持一下谢谢

## 项目总体架构图
![项目架构图](https://oss-weslie.oss-cn-shanghai.aliyuncs.com/data/github_content_pic/020143_0d434b4a_1468963.jpeg "mPass_Springcloud微服务架构.jpg")

 :anger:  :facepunch:   _系统处于开发阶段_

**核心功能**：
- **快速开发**：工程化的开发框架可以自动生成初始化代码，框架还提供模块化开发模式，适用于多人协作开发。
- **性能优化**：支持运营活动投放一站式全流程创建管理，加载智能化投放能力，最大可能提升运营效率和转化效果，助力业务增长。
- **数字化运营闭环**：所有组件都经历了高并发，大流量的检验，对弱网，保活，容器等都有深度的优化，能够兼容复杂的客户端情况
- **使用方式灵活**：框架与组件并没有强依赖，可分可合，灵活机动。各组件可以独立的提供强大的功能，也可以互相配合优化使用体验，发挥更大的作用

### 基础业务模块
- [x] 注册配置服务 mPaaS服务注册、配置中心服务
- [x] 聚合基础服务 业务聚合服务,可自由服务聚合、独立部署
- [x] 监控系统服务 监控系统基础业务调整(包含：日志等级调整、基础服务状态与服务使用状态)
- [x] 对象存储服务 业务服务附件存储(包含：本地，oss、bos、obs、odo、tos)
- [x] 组织架构服务 组织架构元素（包含：机构、部门、岗位、群组、人员）
- [ ] 任务调度服务 任务集中调度服务

## 运维架构图
![运维架构图](https://oss-weslie.oss-cn-shanghai.aliyuncs.com/data/github_content_pic/005728_9d45ec29_1468963.png "ops.png")

## 项目详细部署图
![项目详细部署图](https://oss-weslie.oss-cn-shanghai.aliyuncs.com/data/github_content_pic/005728_9d45ec29_1468963.png "deploy.png")

## 服务简述
#### 对象存储服务
![存储机制](https://oss-weslie.oss-cn-shanghai.aliyuncs.com/data/github_content_pic/200848_8ac7f86d_1468963.png "mpaas 存储机制.png")
#### 组织架构服务
![组织架构基础数据模型](https://oss-weslie.oss-cn-shanghai.aliyuncs.com/data/github_content_pic/173721_27c0e789_1468963.png "组织架构基础模型.png")