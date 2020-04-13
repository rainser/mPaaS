English | [中文](/README.cn.md)

<p align="center">
	<a href="#"><img src="https://images.gitee.com/uploads/images/2019/1009/235538_73450b95_1468963.png" width="700"></a>
</p>
<p align="center">
	<a target="_blank" href="https://search.maven.org/search?q=M-PasS">
		<img src="https://img.shields.io/badge/Maven Central-1.12.0-blue.svg" ></img>
	</a>
        <a target="_blank" href="https://github.com/lihangqi/mPaaS">
		<img src="https://img.shields.io/badge/Spring%20Boot-2.0.9.RELEASE-blue" alt="Downloads"/>
	</a>
        <a target="_blank" href="https://github.com/lihangqi/mPaaS">
		<img src="https://img.shields.io/badge/Spring%20Cloud-Finchley.SR4-blue" alt="Downloads"/>
	</a>
	<a target="_blank" href="https://www.oracle.com/technetwork/java/javase/downloads/index.html">
		<img src="https://img.shields.io/badge/JDK-1.8+-green.svg" ></img>
	</a>
	<a target="_blank" href="https://github.com/lihangqi/mPaaS" title="API文档">
		<img src="https://img.shields.io/badge/Api Docs-1.12.0-orange.svg" ></img>
	</a>
</p>

Based on SpringBoot2.x and SpringCloud, the front-end and back-end separated multi-tenant system architecture microservice development platform mPaaS (Microservice PaaS) is used to develop, test, operate, and maintain an open source framework for tenant business, which can effectively reduce the technical threshold, reduce research and development costs, and improve Development efficiency.

    ______  ___     ________                      
    ___   |/  /     ___  __ \_____ _______________
    __  /|_/ /________  /_/ /  __ `/_  ___/_  ___/
    _  /  / /_/_____/  ____// /_/ /_(__  )_(__  )
    /_/  /_/        /_/     \__,_/ /____/ /____/ 


## Project development update progress
### If you find it helpful, please click "Star" in the upper right corner for support

## Project overall architecture diagram
![Project overall architecture diagram](https://oss-weslie.oss-cn-shanghai.aliyuncs.com/data/github_content_pic/020143_0d434b4a_1468963.jpeg "mPass_Springcloud微服务架构.jpg")

:anger: :facepunch:  _The system is under development.

**Core functions**:
- **Quick Development**: The engineering development framework can automatically generate initialization code. The framework also provides a modular development mode, which is suitable for collaborative development by multiple people.
- **Performance optimization**: Supports one-stop, full-process creation and management of operational activities, loading intelligent delivery capabilities, which can maximize operational efficiency and conversion effects, and help business growth.
- **Closed loop of digital operation**: All components have undergone high concurrency, high traffic inspection, and have been deeply optimized for weak networks, keep-alives, containers, etc., and are compatible with complex client situations
- **Flexible use mode**: The framework and components do not have strong dependencies, can be divided and combined, and are flexible and maneuverable. Each component can provide powerful functions independently, and can also cooperate with each other to optimize the user experience and play a greater role.

### Basic Business Module
- [x] Registration configuration service mPaaS service registration, configuration center service
- [x] Aggregation basic service Business aggregation service, free service aggregation, independent deployment
- [x] Monitoring system service Monitoring system basic business adjustment (including: log level adjustment, basic service status and service usage status)
- [x] Object storage service Business service attachment storage (including: local, oss, bos, obs, odo, tos)
- [x] Organizational structure services Organizational structure elements (including: institutions, departments, positions, groups, personnel)
- [ ] Task scheduling service Task centralized scheduling service

## Operation and maintenance architecture diagram
![Operation and maintenance architecture diagram](https://oss-weslie.oss-cn-shanghai.aliyuncs.com/data/github_content_pic/005728_9d45ec29_1468963.png "ops.png")

## Project detailed deployment diagram
![Project detailed deployment diagram](https://oss-weslie.oss-cn-shanghai.aliyuncs.com/data/github_content_pic/005737_ba969737_1468963.png "deploy.png")

## Service Brief
#### Object storage service
![Storage mechanism](https://oss-weslie.oss-cn-shanghai.aliyuncs.com/data/github_content_pic/200848_8ac7f86d_1468963.png "mpaas storage mechanism.png")
#### Organizational Structure Service
![Organization Structure Basic Data Model](https://oss-weslie.oss-cn-shanghai.aliyuncs.com/data/github_content_pic/173721_27c0e789_1468963.png "Organization Structure Basic Model.png")