# Java practice EPAM Lab Winter 2022 (01/01/2022 - 06/01/2022)

### Project Description
The internship focused on crucial concepts of Java engineering, providing hands-on practice and guidance from experienced mentors.

### Achievements
- Enhancing understanding of architectural and design patterns, particularly N-layer architecture, GoF patterns, CDI;
- Implementing a web service based on Java EE;
- Implementing registration with protection against robots (CAPTCHA);

### Key technologies
- Core: Java 17 (SE);
- Libraries: Apache Commons;
- Testing: JUnit, Mockito;
- Backend: Java EE, Java Servlet API (Jakarta Servlet), Apache Tomcat;
- Frontend: JavaScript, jQuery, AJAX, JSP, JSTL, HTML, CSS, Bootstrap;
- Persistence: JDBC, SQL, MySQL;
- Other: git, Apache Maven;

### Database
[MySQL Community Server 8.4.0 LTS](https://dev.mysql.com/downloads/mysql/)

### Additional information
Link to ER-Diagram: [here](src/main/resources/db/ER-diagram.png)

### Tools used during development
- IntelliJ IDEA Community (main development environment);
- MySQL Workbench 8.0 CE (MySQL DBMS for managing database);
- Postman and Web browsers (Google Chrome, Mozilla Firefox, ...) (for testing and documentation);
- Microsoft Teams, Skype (for communicate with mentors);
- GitLab (for version control).

### Demo
Video on YouTube:
- WEB version: [here](https://youtu.be/raf8fo8wuKg);
- Console version: [here](https://youtu.be/hUW3t6befE8).

### Additional description of course (preview)
The project is divided into 3 modules and includes 16 tasks, which were completed under the guidance of mentors and covered various aspects of Java development. These topics include: working with Git, unit and integration testing, code documentation, working with collections, working with files, multithreading (my favorite task 😄: search for prime numbers within a given range (parallel implementation)), practical implementation of design patterns such as "chain of responsibility", "strategy", "command", "abstract factory", "template method", "iterator", "dynamic proxies" and so on. Important concepts such as 'context' and 'dependency injection' were also covered.

### Tasks
Description of tasks [here](tasks/tasks.md#task1)

Assignments in original (source) form [here](tasks/tasks-src.zip)

### Installing

#### Prerequisites
* [Java 17](https://jdk.java.net/archive/);
* [Apache Tomcat 9](https://tomcat.apache.org/download-90.cgi);
* [MySQL Community Server 8.4.0 LTS](https://dev.mysql.com/downloads/mysql/).

Download and Install Java 17.
Ensure that your Java installation is acceptable by executing the following command in the command prompt: `java -version`

Download and Install Apache Tomcat 9.
Navigate to `com.epam.task11.controller.ContextListener` and set convenient image storage directories for the `ABSOLUTE_PATH_STORAGE_USER_AVATARS` and `ABSOLUTE_PATH_STORAGE_PRODUCT_PICTURES` constants. Make sure these directories exist, otherwise, you will encounter an error. You can also use test media data from `src/main/resources/media`.

Download and Install MySQL Server 8.
After the MySQL Server installation is complete, create the database using the following scripts:
Creating script: `src/main/resources/db/creation.sql`
Fill test data script: `src/main/resources/db/fill_test_data.sql`
