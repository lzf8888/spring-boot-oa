# spring-boot-oa
It's a template to rapidly develop an enterprise backend application,such as OA 

Project Overview:
OaHttpRequestInterceptor to generate traceId

How to config:
add 
-Ddb.schema.name=TEST 
as VM args to set the DB schema, if you want change schema to another name,please also change it in BaseTest.java.
To turn on security feature(this aims to when your application is under SSO, all data requests are pre-authenticated and you have security method to get access principal), you can add
-Dspring.profiles.active=secure
you'll need custom improvements for SecurityConfig.java

View:
Type http://localhost:8082 to view h2;
JDBC URL:jdbc:h2:~/.h2/testoa
Username:sa
Password:
