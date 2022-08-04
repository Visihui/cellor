# **Project Name ：Callor SSO**



### Catalogue

[TOC]



##### Introduction of Project

This project is a information system which is using for company's management. It include mutiple functions  , such as user management, application management and system setup function. 



##### Configration

System - windows

JDK - jdk1.8

Database - mysql 8.0

Nosql - redis 3.2.100

Project Dependencies Management - Apache Maven 3.5.4 



##### Effect Diagram

![](.\resources\1.png)

![](.\resources\2.png)

![](.\resources\3.png)

![](.\resources\4.png)



##### Installation Instruction

1. Use Code management tool (GIT) download all the source code.

   **The front-end code**

2. when you 1st time to run the code, you need to do:

   - install npm and execute npm command to start.

   - make sure the IP address and server port number can be matched with the back-end code setting.

   **The back-end code**

3. when you 1st time to run the code, you need to do:

    - setup applicatoin document (sso/src/main/resources/config/application.yml)

      ​	-- change server port number.

      ​	-- check IP address and edit url for mysql connection.

##### Project Structure

​	**Front-end**

| No.  | Module  | Description                                                  |
| ---- | ------- | ------------------------------------------------------------ |
| 1    | config  | this package is using for saving configuration files         |
| 2    | public  | it include some public files, such as public module, icon, etc |
| 3    | scripts | it is using for save javascript documents                    |
| 4    | src     | resouces code document: api document, assets package - using for saving picture documents, components package - using for save public module, i18n package - using for mulitiple langulage, utils package - using for save basic functions |

​	**Back-end**

| No.  | Module      | Description                                                  |
| ---- | ----------- | ------------------------------------------------------------ |
| 1    | accountInfo | 1. Structure: controller - receive data from the front end and response, transfer data to service; Service - process business logic; Dao - extend BaseMapper(MybatisPlus) to implement all basic CRUD operations; Entity - defining the entity class.  2. function: using for account management |
| 2    | menu        | 1. Structure: same as module of accountInfo; 2. function: using for menu management. |
| 3    | sysInfo     | 1. Structure: same as module of accountInfo; 2. function: using for login and main page information management. |
| 4    | constants   | using for redis to setup key and token expire                |
| 5    | common      | this package is using for defining some configration document, for example, the mybatisplus configration document, the redis configration document, etc. It also can be used to save some entity classes and process filter problem. |