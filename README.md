# Personal Utility

Introduction
============
<p>Personal Utility is a web application that has utilities to track, organize and execute our day-to-day activities. To start with it comes with a To-Do list utility, where the users can add a list of activities (<b>we call it 'Tasks'</b>) that they are planning to do in the future.</p>

Features
--------
<p>Logged in user can access the To-Do list by clicking the TO-DO option under <b>Utilities</b> tab.To-Do list comes with an option to add new Task to list with a target date. Each item added will be displayed in a list on the right hand side of the page. This list is <b>sorted</b>  with an increasing order of target date.This way the user can easily notify the immediate tasks to be achieved.</p>

<p><b>Color coding</b> is applied for easy identify the tasks whose target date is over and the one that falls today. Tasks whose target date are in past are indicated in <span style="color:red"><b>RED</b></span> color and those that are today are indicated in <span style="color:green"><b>GREEN</b></span> and <b>BOLD</b>.It allows the user to <b>delete</b> the tasks that  are finished. It shows a maximum of seven tasks at a time and the rest are paginated. The paginated tasks can be viewed by clicking the appropriate page number.</p>

<p>User need to <b>login</b> to create and access the To-Do list. The registration process is done through an ADMIN account. Admin login has a <b>settings</b> option that allows the admin user to add new users and admins. It also has option to delete an existing user. Saying that, the application does not allow the deletion of logged in user. <b>User name is unique</b> and it cannot be reused.</p>

Application deployment
-----------------------
Follow the instructions given below to deploy the application.
<ol>
<li> Download the executable jar file. [<a href="https://github.com/ranju4u6/PersonalUtilityAssignment/blob/master/backend/target/backend-0.0.1-SNAPSHOT.jar">Executable Jar</a>]</li>
<li> Navigate to the download folder.</li>
<li> Execute the jar using command <b>java -jar backend-0.0.1-SNAPSHOT.jar</b></li>
<li>Access the application at http://localhost:8080/PersonalUtility/ </li>
<li>Application comes with a default admin and user. Default Admin account can be accessed with 'admin' as user name and password. And for the user it is 'user'.</li>
<li>Optionally the database can be accessed from the deployed machine using the url given below<br>
http://localhost:8080/PersonalUtility/h2-console<br>
with username as 'sa' and and an empty password.
</li>
</ol>

Contribution
------------------
Software enthusiasts can contribute to the application development. Following are the software requirements and instructions to check out and build the application.

<h3>Software requirements</h3>
<ul>
<li>Node JS</li>
<li>NPM</li>
<li>Visual Studio Code</li>
<li>Eclipse</li>
<li>Java 1.8 or above</li>
</ul>

<h3>Application architecture</h3>

           |User Interface [Angular 6, Bootstrap 4]|
           |---------------------------------------|
         |   REST API [Spring boot, Spring REST]     |
         |-------------------------------------------|
       |     DATA ACCESS [Spring DATA, HIBERNATE[JPA]]  |
       |------------------------------------------------|
     |       DATABASE [H2-DB (Embedded Database)]         |
     |----------------------------------------------------|  
     
Application employs <b>Angular 6</b> and <b>Twitter Bootstrap 4</b> for the development of User Interface. Angular makes REST requests to the back end which is handled by a REST api supported by <b>Spring REST</b>. Data is stored in an Embedded <a href="http://www.h2database.com/">H2-Database</a>, which is accessed through a combination of <b>Spring DATA</b> layer and <b>Hibernate [JPA]</b>. The entire application is bundled using <b>Spring Boot</b>. Spring Boot comes us with an <b>Embedded Tomcat</b> web server, where it deploys the application. By default the application is hosted to the 8080 port of Tomcat. If 8080 port is not accessible in your machine, feel free to change it by making the following entry in the Spring Boot's application.properties file. <br><br>

|server.port = 'new-port-number'.|

<h3>Technology Stack</h3>
<ol>
<li><b>Angular 6:</b>Angular 6 is a JavaScript framework for building web applications and applications in JavaScript, html, and TypeScript, which is a superset of JavaScript. Angular provides built-in features for animation, http service, and materials which in turn has features such as auto-complete, navigation, toolbar, menus, etc. Angular 6 was selected for UI development as the core of Angular 6, the Typescript is much easier for people who are familiar with Object Oriented languages like Java. So that certainly boots the development speed.</li>

<li><b>Twitter Bootstrap:</b>Twitter Bootstrap is a CSS framework that helps in designing web applications. It is one of the easiest CSS frameworks to use in the Industry today. It assumes you have no designing knowledge and all you want to do is write some HTML as per the Bootstrap specifications.</li>

<li><b>Spring Boot:</b>Spring Boot is a rapid application development platform. It uses various components of Spring, but has additional niceties like the ability to package your application as a runnable jar, which includes an embedded tomcat (or jetty) server. So we dont have to depend of external webserver for deployment and testing of the application. It also gives in-built support for H2 DB, there by making the Database integration much easier.</li>

<li><b>Spring REST:</b>Spring’s annotation based MVC framework simplifies the process of creating RESTful web services. The key difference between a traditional Spring MVC controller and the RESTful web service controller is the way the HTTP response body is created. While the traditional MVC controller relies on the View technology, the RESTful web service controller simply returns the object and the object data is written directly to the HTTP response as JSON/XML.</li>

<li><b>Spring DATA JPA:</b>The Java Persistence API, sometimes referred to as JPA, is a Java framework managing relational data in applications using the Java Platform, Standard Edition (JavaSE) and Java Platform, Enterprise Edition(JavaEE).Spring Data JPA is part of the umbrella Spring Data project that makes it easier to implement JPA based repositories. Using Spring DATA JPA reduces the chances of making mistakes by a non-sql developer,  while developing SQL queries</li>

<li><b>Hibernate:</b>Hibernate is an ORM (Object-Relational Mapping) framework. It maps your database tables to the Java classes, i.e., the entities. It internally uses JDBC. It provides you with the flexibility of changing the database, if needed. More than that it reduces the complexity of writing SQl queries. Hibernate comes up with its own query language (HQL). Using it along with Spring data will completely eliminate the possibility of writing SQL queries for a non-sql developer.</li>

<li><b>H2-DataBase:</b>H2 is an open-source lightweight Java database. It can be embedded in Java applications or run in the client-server mode. Mainly, H2 database can be configured to run as inmemory database, which means that data will not persist on the disk. To retain the data, it can be configured to persist the data to file, which is what is used by this application.</li>

<li><b>Cucumber:</b>A cucumber is a tool based on Behavior Driven Development (BDD) framework which is used to write acceptance tests for the web application. It allows automation of functional validation in easily readable and understandable format (like plain English) to Business Analysts, Developers, Testers, etc. Cucumber uses Gherkin language to develop the test cases (also called feature files). Each steps in this feature file is mapped to java methods, which contains the Selenium implementation for these steps.</li>

</ol>

<h3>Security</h3> 
Application employs basic username-password authentication. Password is encoded with Base64 format every time it is passed across the network. Also a Base64 encoded password is stored in the database. For REST api security, a sessionid is generated each time a user logs in. Same has to be passed by the user every time he makes a REST call. Sessionid is created using the UUID class of java.util package.<br><br>
<q>String sessionId = UUID.randomUUID().toString();</q>

<h3>Database Schema</h3> 
     
     
     |  TBL_USER_TYPE  |
     |-----------------|
     |  USER_TYPE_ID(PK)|USER_TYPE(VARCHAR(255))|
     |-----------------|------------------------|

     |  TBL_USER       |
     |-----------------|
     |  USER_ID(PK)|USER_TYPE_ID(FK)|USER_NAME(VARCHAR(255))|PASSWORD(VARCHAR(255))|ACTIVE(CHAR(1))|SESSION_ID(VARCHAR(255))|
     |-------------|----------------|-----------------------|----------------------|---------------|------------------------|
     
    |  TBL_TODO       |
    |-----------------|
    | TASK_ID(PK)|USER_ID(FK)|TASK(VARCHAR(255))|TARGET_DATE(TIMESTAMP)|ACTIVE(CHAR(1))|
    |------------|-----------|------------------|----------------------|---------------|
     
     
<q>TBL_USER & TBL_TODO contains additional common fields UPDATED_BY & UPDATED_TIME</q>
     
<h3>How to build?</h3> 
<ol>    
<li>To begin with, check out the project from github using command, <br/><br/>
<q> git clone https://github.com/ranju4u6/PersonalUtilityAssignment.git </q><br><br> </li>
<li>Issue command <b>mvn clean install</b> to build the project.<br><br>
This command will clean the target folder and install new deployable jar file inside backend/target folder. <br> Before creating the jar file, it will trigger the <span style="color:green"><b>Cucumber</b></span> based integration test cases. <br><br>
<q><span style="color:green"><b>Cucumber</b></span> utilizes Selenium internally and it requires a selenium Chrome driver to be placed in the src/test/resources folder. By default it has a Chrome driver for linux 64-bit machines for Chrome version 69. Make sure that you are using the one that matches your system specification.</q></li><br>
<li>Finally, Import it as a Maven project to Eclipse by selecting the root POM file</li>
</ol>

<h3>FAQ</h3>
<ol>

<li>How to recover from application failure due to database related issues while deployment?<br><br>
<q>Hibernate is asked to update the existing schema on deployment. So it possible to fail in the initial deployment. This can be corrected by asking the Hibernate to create tables by updating the following entry in application.properties with a value 'CREATE' instead of 'UPDATE' given there.<br><br>
spring.jpa.hibernate.ddl-auto = update <br><br>
And it is important to revert it back to 'UPDATE' before next deployment, as 'CREATE' will wipe out all the data from the tables.
</q> 
</li>
<li>Application is not allowing to access using the default user name and password?<br><br>
<q>This can happen in some cases, where the application fails to put default data into the database or it gets deleted by mistake. In that case, execute the following steps to get it updated. <br>
<ul>
	<li>Connect to database through url: http://localhost:8080/PersonalUtility/h2-console (it allows only localhost connection). Enter the database url as 'jdbc:h2:file:~/H2Database/personalUtility'.Then key in username as 'sa' and empty password. Finally execute the following query.</li>
</ul>

<br><br>

		DELETE from TBL_USER;
		DELETE from TBL_USER_TYPE;
		
		INSERT INTO TBL_USER_TYPE(user_type_id, user_type) values
		('UTID_1', 'ADMIN'),
		('UTID_2', 'USER');
		
		INSERT INTO TBL_USER(user_id, user_name, password, active, user_type_id, updated_by, updated_time) VALUES
		('UID_1', 'admin', 'YWRtaW4=', 'Y', 'UTID_1', 'UID_1', null),
		('UID_2', 'user', 'dXNlcg==', 'Y', 'UTID_2', 'UID_1', null);

</q>
</li>

<li>How do we prevent unauthorized user from accessing the REST api's?<br><br>
<q>Each time a user logs in to the application, a unique session id is generated in the server side an is passed to the UI layer.Same session id is updated to the TBL_USER against that particular user. So every time that user makes a REST call, this session id should be a part of that call and it is validated at server side. If the session id do not match with that in the database, an UNAUTHORIZED error is thrown.</q>
</li

<br><br><br><br>

<h2>Thats all !!. Happy Coding.<h2>


</ol>



