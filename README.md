# JEE and Spring application: Online cinema
## Preface
The main purpose of this project is to learn and demonstrate various JEE/Spring features. Application shows simple movie database and will offer many demonstrative functionalities like inserting and updating movie informations, sharing on facebook and twitter, offering web services etc. The app is work in progress!

## Getting Started


If you wish to try this example, please download source code from repository clicking on [link](https://github.com/almirpehratovic/cinema/archive/master.zip). Extract the project somewhere on disk and turn on Spring STS. Inside Spring STS import project by choosing File - Import - Maven - Existing Maven Project and navigating to extracted folder. Spring STS will import project and download all java dependencies configured in pom.xml file.

This project is using various Java anntotations that are not compatible with older java versions so we have to change compatibility to Java 1.6. In project properties (right click on project name) in Java Compiler section choose Compiler compliance level as 1.6, and in Java Build Path section on Libraries tab remove JRE 1.5 library and add your installed Java sdk version greater than 1.5. Spring STS will rebuild the project.

Finally, to run the example, right click on the project and choose Run as - Maven build. As a goal type tomcat7:run. First time Spring STS will download tomcat7 web server and at the end run the app. To try application, in favorite web browser type http://localhost:8080/cinema/movies and that's it. 

## List of used technologies and features

* Internal (in-memory) H2 database
* Hibernate mapping to domain objects
* Web login authentication (spring-security)
* Internationalization (bosnian + italian)
* Spring MVC
* Apache Tiles templating
* JavaServer Pages
* JQuery scripts
* CSS


