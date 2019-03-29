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
server.port = 'new-port-number'.
     
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
<br><br><br><br>

<h2>Thats all !!. Happy Coding.<h2>


</ol>



