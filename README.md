# Java practice EPAM Lab Winter 2022 (01/01/2022 - 06/01/2022)


## Prerequisites
* [Java 17](https://jdk.java.net/archive/)
* [Apache Tomcat 9](https://tomcat.apache.org/download-90.cgi)
* [MySQL server 8](https://dev.mysql.com/downloads/mysql/)


## Installing

Download and Install Java 17.
Ensure that your Java installation is acceptable by executing the following command in the command prompt: `java -version`

Download and Install Apache Tomcat 9.
Navigate to `com.epam.task11.controller.ContextListener` and set convenient image storage directories for the `ABSOLUTE_PATH_STORAGE_USER_AVATARS` and `ABSOLUTE_PATH_STORAGE_PRODUCT_PICTURES` constants. Make sure these directories exist, otherwise, you will encounter an error. You can also use test media data from `src/main/resources/media`.

Download and Install MySQL Server 8.
After the MySQL Server installation is complete, create the database using the following scripts:
Creating script: `src/main/resources/db/creation.sql`
Fill test data script: `src/main/resources/db/fill_test_data.sql`


## Tasks

************************************Task1************************************

1. Create a class hierarchy describing a certain subject area. The depth of the inheritance hierarchy should be three levels (ancestor, its descendant, descendant of the descendant).
   1.1. Implement empty and parameterized constructors, get and set methods for each field, toString() method, and equals() method in the classes.<br><br>

2. Create your own implementation of a container for storing a list of goods. The container should implement the List interface. 
- Implement the container based on an array and use the methods of the Arrays class.
- Consider whether to use a scheme with pre-allocated memory. Identify potential challenges in each case.
- ListIterator and sublist implementation can be omitted.<br>
2.1. Write an iterator that selects only the elements from the container that satisfy a specific condition. Consider how to make the condition assignment as flexible as possible.<br>
2.2. Write tests to verify if the created classes comply with the contracts of the List and Iterator interfaces. For detailed information about them, refer to the help and articles.
  Ensure to provide coverage for the following methods:
- add(int index, E element)
- add(E element)
- get(int index)
- remove(int index)
- remove(Object obj)
- iterator.


************************************Task2************************************

1. Examine how protection against distortion of returned data is implemented if the container's contents are changed externally during iteration.
   1.1. Devise and implement an iterator that is resistant to external data changes using the copy-on-write scheme. Create the collection and iterator based on the container from Task 1 Part 2. (PS. This should be a separate class; the old container should remain as it was.)<br><br>

2. Consider the purpose of read-only (unmodifiable) wrappers for collections.
   2.1. Implement a list consisting of an unmodifiable and a modifiable list. Remember to write an iterator to view its contents.
   Copying data from these two lists is not allowed.


************************************Task3************************************

1. Implement a list (List) that stores only one instance of each object. Make it a subclass of ArrayList. Which methods in ArrayList need to be overridden for this? (For the sake of simplicity, assume that creating a ListIterator is prohibited.)<br>
   1.1. Determine under what circumstances it would be possible to have two identical objects within the container.<br><br>

2. Override the hash code generation mechanism for string keys (use two different generation methods: use the length of the string and the sum of the first four characters as the hash code).
   For both cases, populate the table with pairs of article numbers (strings) and values (product objects).
   Examine the order of element retrieval using the built-in iterator for both hash code generation cases when using HashMap and LinkedHashMap.


************************************Task4************************************

1. Implement the storage of a shopping cart in a hash table. Use product information as the key (think about what exactly should be used as the key), and the quantity of the product in the cart as the value.<br>
   1.1. Implement a console interface for an online store. The user should be able to:
   - display a list of products;
   - add a product to the cart;
   - view the contents of the cart;
   - purchase all the products in the cart (place an order). Display the total order amount.
   - view information about the last 5 products that were added to the cart in all purchase sessions. (One list for all purchases. Implement using a LinkedHashMap.)<br><br>

2. Add the ability to place orders to the store. If a user places an order, the order information is recorded in a TreeMap. Use the date and time of the order as the key (for now, input from the console), and the list of ordered products as the value.<br>
   2.1. Implement the viewing of the list of orders for a specified time period.<br>
   2.2. Implement the selection of an order by the nearest date (for example, the user specifies the current year, month, day, and zeroes for hours and minutes when the order was placed).


************************************Task5************************************

1. Implement a wrapper class for viewing a text file. The class constructor should take the file name as a parameter. It should provide the ability to view the contents of the file line by line using a for loop.<br><br>

2. Implement a program that searches for files based on a given set of parameters in the specified directory and its subdirectories. Use the "Chain of Responsibility" pattern to create the search filter.
   The search parameters should include the file name, extension, file size range, and file modification date range.
   Enter the search parameter types and constraints from the console.
   It should look like this:
> Search by file name? (0\1)<br>
0<br>
> Search by file extension? (0\1)<br>
1<br>
> Enter the extension:<br>
gif<br>
...... and so on......


************************************Task6************************************

1. Implement reading/saving of a container with products. Saving the container should occur when exiting the application, and reading should occur when starting it.<br><br>

2. Create a method that writes the container to a serialization stream (ObjectOutputStream) connected to a file write stream a specified number of times. Observe how the file size changes as the number of records increases and explain the observed effect.<br>
   2.1. Implement saving to disk in compressed GZip format. Observe the changes.<br>
   (This is a separate task that can be done in unit tests.)<br><br>

3. Implement the ability to add products to the catalog from the console. Implement inputting product information in two ways: from the console and using a random number generator. When using the random number generator, you can use templates for text strings like "Company 0342323," where the number will be generated by the random number generator.<br>
   3.1. Choose the input method for product information once when the application starts. After that, the input mechanism cannot be changed. Think about whether it's better to use a template class or a strategy pattern here.<br>
   3.2. Also, think about possible extension of the inheritance hierarchy for products. How can console input be implemented to accommodate this flexibility most effectively?


************************************Task7************************************

1. Using reflection and annotations, implement the input of product information.<br>
   a. The get/set methods or fields should be annotated.<br>
   b. The annotation should specify user-friendly field names in string resources. For example, the annotation can set the key value for the field as "TOVAR_NAME," and in the resources, it will correspond to "item name" for the English language and "название товара" for Russian.<br>
   c. Values should only be set for annotated fields.<br>
   d. When entering values from the keyboard, the user-friendly field name should be displayed in the prompt for the user.<br>
   e. When entering values using a random number generator, the field type (parameter type of the set method) should be taken into account.<br>
   f. This task should be done in the store project. As part of the task, create additional class(es) for inputting products from the console. The existing classes made in task 6 should not be removed.<br><br>

2. Add localization only for product input.<br><br>

   --- The following tasks should be done in separate projects:<br>
3. Write an interface for one of the product classes.<br><br>

4. Write a factory method to create an immutable representation of a product object using dynamic Proxy classes.<br>
   a. The representation should throw an exception when attempting to call the "setXXX" method.<br>
   b. Calls to other methods should be forwarded to the product object.<br><br>

5. Use dynamic Proxy classes to implement the product interface based on a map (Map).<br>
   a. Calls to the get/set methods of the product interface should be converted into get/put calls on the map, where the field name serves as the key.


************************************Task8************************************

1. Using multiple execution threads (Thread), implement the search for prime numbers within a given range.<br>
   a. Input the search interval (e.g., from 1 to 10000) and the number of threads from the keyboard.<br>
   b. Think about how to divide the search interval among the threads.<br>
   c. Implement the primality test using the simplest method: a loop from 2 to number/2.<br>
   d. The found numbers should be stored in a single shared collection.<br>
   - Check what is faster: storing numbers in the shared collection immediately upon finding them.<br>
   - Or storing the found numbers in separate collections for each thread and then adding their contents to the shared collection.<br><br>

2. Implement the same functionality using Executors.<br><br>

3. Write a program that finds the longest repeating byte sequence in a file.<br>
   - Enter the file name from the keyboard.<br>
   - The entire file can be loaded into memory.<br>
   - The sequence search should be implemented in a separate execution thread.<br>
     - The thread should start when the program starts.<br>
     - After entering the file name, the main thread should notify the additional thread that it can start the search.<br>
     - The additional thread should provide information about the progress of the search, and the main thread should display this information on the screen. For example, the current length of the sequence can be printed.<br>
     - After completing the search, the main thread should output the number of bytes in the longest repeating sequence and the indices of its first and second occurrences.<br>
     - After completing the search, the additional thread should enter a waiting state, and the main thread should prompt for the name of the next file.<br>
   - Additional task (optional): think about whether it's possible to speed up the search using multiple additional threads and how to do it best.


************************************Task9************************************

1. Implement a TCP server that implements a text-based protocol for collecting information about product prices.<br>
   - Use port 3000.
   - Each connection should handle one command.
   - The server should support the following commands:
     - Get the number of products in the store.
       - Request: "get count" as a string.
       - Response: the number of products in text format.
   - Get the name of a product.
     - Request: "get item=item_number" as a string. (If the store uses an identifier for the product instead of a number, the identifier should be provided here.)
     - Response: the name of the product, separated by the "|" symbol, and the product price.
   - Debug the server and client parts using JUnit and Mockito.
   - Yes, you will need to write a client application to demonstrate the server's functionality.
   - Only connect to the store after writing tests and debugging!<br><br>

2. Implement a simple HTTP server that returns store information in JSON format.
   - Two commands should be implemented:
     - Get the number of products.
       - Request: GET, URL: /shop/count.
       - Response: {count:123}.
     - Get information about a product.
       - Request: GET, URL: /shop/item?get_info=item_number. 
       - Response: {name: product_name, price: 123}.
   - JSON responses can be generated using formatted output.
   - Consider the possibility of adding new commands during development.<br><br>
3. Both tasks should be included in the store project.<br><br>

4. The operation of both servers should not block the console interface, meaning the user should be able to enter new products, and these changes should be reflected in the network calls.<br><br>

5. Use the "abstract factory" design pattern to create service threads.


************************************Task10************************************

1. Implement an HTML/CSS/jQuery template for an online store.
   - Find a _free_ website template on the Internet.
   - Implement the template for the main homepage of the website.
   - Implement a user registration page and perform client-side validation of the entered information.
     - Implement two types of validation: using jQuery and pure JavaScript.
     - You are not allowed to use jQuery validation plugins, as I want you to practice accessing DOM elements using both jQuery and plain JS.
   - Implement a static page with information about the product list.
   - Include in the project the link to the website where the template was obtained and a link to the template's license agreement confirming its use in commercial (corporate) projects.<br><br>
2. Create a web application using the static website template (later, we will replace the static pages with JSP/servlets).<br><br>
 
3. Use the Maven/Gradle build system to build the application.<br><br>

P.S.: We are not focused on web design, so the task should be completed without excessive emphasis on website styling.


************************************Task11************************************

1. Implement user registration on the website with protection against robots (CAPTCHA).
   - Implement user data validation on the client-side and server-side.
     - On the server-side, check for the existence of a user with the entered login.
     - Define the list of existing users as constants.
   - Ensure that the client doesn't need to re-enter all the registration information in case of an error.
     - Use at least the following registration information: user's first name, last name, password, email address, desired subscriptions.
   - Use an image with printed numbers as the CAPTCHA.
     - Use a pseudorandom number generator with a specified seed as a source of numbers.
     - Adding dots to complicate automated recognition is optional.
     - The image should be generated by a servlet. External libraries can be used for drawing the CAPTCHA.
   - Devise a mechanism for storing the generated number between the image generation and input validation stages.
     - It is recommended to draw a sequence diagram reflecting the interaction between the client and the server and the data transfer paths.
     - Ensure that the generated number is not explicitly sent to the client anywhere except in the image.
     - Encryption cannot be used.
     - Codes for the hidden number can be used.
   - Implement two variants of data storage for the hidden number: session attributes and application context.
     - For the second variant, store the identifier of the generated number on the client-side using cookies and hidden form fields.
   - Implement a configuration option to select the CAPTCHA data storage mode through web application initialization parameters.
     - Consider how to make the CAPTCHA module interface independent of the mechanism used to store the generated number.
   - Implement a custom tag to display the CAPTCHA image and related fields in a JSP page.
     - The tag should generate HTML code to display the image.
     - When storing the identifier of the number in a hidden form field, the tag should include this field.
   - Test the system in different modes. Create unit tests for the registration servlet to demonstrate that it meets the requirements.
     - Fix any errors. If there are no errors, fix the tests.<br><br>

2. Implement classes for working with users.<br>
   a. Create a repository class and one or more service classes.<br>
   b. Determine where it would be appropriate to place these classes and what minimal set of methods they should contain.<br><br>

3. Implement a timeout for registration processing.
   - Registration should not be processed if the time elapsed from page generation to receiving the form data from the user exceeds a specified interval.
     - Configure the interval in the CAPTCHA settings.
   - Expired CAPTCHA data should be deleted from memory. Two options are possible.
     - Deletion occurs when requesting the CAPTCHA or processing registration data.
     - Deletion occurs in an additional thread at regular intervals.
   - Justify the choice and implement the selected option.


************************************Task12************************************

1. Implement a data access layer and services using MySQL and JDBC.<br>
   a. Write corresponding classes for managing connection and transactions.<br>
   b. Write DAO classes and services for working with users.<br><br>

2. Implement user login on the website.<br>
   a. Implement a user login form.<br>
   b. Implement a custom tag that will be placed on the page within a fixed-size block and will contain:
     - User login form if the user is not logged in.
     - User's name and a logout button if the user is logged in.<br><br>

3. File uploading to the server.<br>
   a. Add a field for uploading an avatar to the registration page.<br>
   b. Implement the uploading of the avatar to the server and store it in a designated server directory.<br>
   c. Upon subsequent user logins, their avatar should be displayed on the login page.


************************************Task13************************************

1. Implement the display of a list of products in an online store.<br><br>

2. Implement a product storage class (repository layer).
   - The class should provide filtering of products by product name, categories, manufacturers, and price range - for all parameters available in the user interface.
     - Each product is associated with one category and one manufacturer.
   - It should also provide partial pagination of the list with the corresponding sorting mode.<br><br>

3. The page displaying the list of products should implement the following functionality:
   - For each product, basic information about it (name, price, description with key features) and its image (if available) should be displayed.
   - The page should display no more than a certain number of products.
     - Implement the ability for the user to choose the number of products per page.
   - If there are more products than can fit on one page, a menu should be displayed to select the displayed page (a set of links to pages).
   - Implement a menu for filtering products by categories, manufacturers, product name, and price.
     - Display a filtering panel on the screen where the user can enter restrictions on the product properties.
     - Filtering restrictions should be applied together, i.e., find a product manufactured by "AAA" company with a price between 2 and 10 units.
   - Implement sorting of products by multiple fields (price, name). Sorting should be performed on one selected field in both directions.
   - If no products satisfying the conditions are found, instead of the product list, a message should be displayed: "No products found."
   - Provide a button or hyperlink to add a product to the shopping cart.


************************************Task14************************************

1. Implement the ability to select products in the shopping cart and proceed with purchasing the items.<br><br>

2. Write a class that implements the shopping cart.<br>
   a. The cart can be implemented as a map, where the key is the product and the value is the quantity of the product.<br>
   b. The class should contain methods for:
      - Adding a product to the cart,
      - Removing a product from the cart,
      - Retrieving the list of selected products in the cart,
      - Retrieving the number of products in the cart,
      - Calculating the total purchase amount for the cart.<br><br>

3. Write a class for storing information about ordered products.<br>
   a. The class should store information about the product, the quantity of the product, and the unit price of the product at the time of purchase.<br>
   b. The class should be immutable.<br><br>

4. Write a class describing an order.<br>
   a. The order object should contain:
     - An order identifier,
     - Order status (accepted, confirmed, processing, dispatched, completed, canceled),
     - Detailed status information (reasons for the current state, e.g., "order canceled at customer's request"),
     - Date and time of the order,
     - Information about the user who placed the order,
     - A list of ordered products (see point 3).<br><br>

5. Write repository layer classes for storing orders.<br>
   a. The class should contain a method for adding an order.<br><br>

6. Create a page for working with the shopping cart.<br>
   a. The page should display the list of products in the cart.<br>
   b. For each product, the ability to change the quantity of the product in the cart should be implemented.<br>
   c. Functions for removing items from the cart and clearing the cart should be implemented.<br>
   d. Ordering products from the cart should be implemented.
      - If the user is not logged in, prompt them to register before placing the order.
      - During the ordering process, inquire about the user's preferred payment/delivery method.
      - Obtain the user's payment details.
      - After a successful order, the cart should be cleared.


************************************Task15************************************

1. Implement a filter that provides localization for a web application.
   - The filter should determine the current application locale as follows:
     - If the selected locale is specified in the storage (session or cookie), use it.
     - If there is no selected locale in the storage, check the list of locales that the browser is willing to accept. You can use the getLocales() method of the HttpServletRequest class, which returns locales in descending order of acceptability for the client. Find the most acceptable locale supported by the application and set its value in the session.
     - If none of the locales passed by the browser is supported by the application, set the default locale as the selected locale.<br>
   
     P.S.: You can configure the desired localization in the browser. In Firefox, go to the Tools->Options->Content tab->Languages menu item.
   - The list of locales supported by the application and the default locale are specified as parameters of the filter in the deployment descriptor.<br>
   P.S.: Consider what needs to be done if the deployment sets incorrect localization parameters. i.e., how to verify and handle it.
   - The filter should override the request locales (getLocales() and getLocale()) with the selected locale.
   - In the web application, the value returned by the HttpServletRequest.getLocale() method should be used as the selected locale.<br><br>

2. Write a set of tests to verify the correct functioning of the localization filters.<br>
   a. In the tests, try to consider as many scenarios as possible.<br><br>

3. Write a custom tag for language switching in the web application.<br>
   a. The tag should display language names or their icons.<br>
   b. The tag should be used in all application pages.<br>
   c. Language selection is done by adding a lang=NM parameter to the current page URL, where NM is the language name.<br>
   d. The parameter processing should be performed in the filter from the first task.<br><br>

4. Implement storing localization information in cookies and sessions.<br>
   a. The choice of storage option should be set in the initialization parameters of the localization filter.<br>
   b. The cookie expiration should be based on the last visit to the website page.<br>
   c. The cookie expiration should be set in the application parameters.<br><br>

5. Implement a filter that compresses the response body for text pages.
   - The filter should perform compression only if:
     - The response content type (Content-type) is set to text.
     - The client is capable of decompression (Accept-encoding: gzip is set).
   - The fact that the response is compressed should be indicated in the response headers.
   - Consider the best way to apply the filter to the application resources - through web.xml or annotations.<br><br>

6. Implement a filter that disables caching.


************************************Task16************************************

1. Implement access control for parts of the project.<br><br>

2. Complete the user management service classes.<br>
   a. Add necessary functionality to the UserService. Most likely, it will require checking the existence of a user login and performing user login.<br>
   b. Ideally (but not mandatory if you don't have enough time), store information about the number of failed authentication attempts and the time/date when the account lock is lifted, if the user is locked.
      - In this case, automatic user blocking (ban) should be implemented if they exceed a certain number of failed login attempts.<br><br>

3. Implement a filter that restricts access to website URLs based on specified constraints.<br>
   a. The constraints should be defined in an XML file, the path to which will be specified in the servlet initialization parameters.<br>
   b. The XML file format should be similar to the following:<br>
   ```
   <security>
     <constraint>
       <url-pattern>/admin/.*</url-pattern>
       <role>admin</role>
     </constraint>
     <constraint>
       <url-pattern>/user.*.do</url-pattern>
       <role>admin</role>
     </constraint>
     <constraint>
       <url-pattern>/internal/.*</url-pattern>
       <role>user</role>
       <role>admin</role>
     </constraint>
   </security>
   ```
   In the url-pattern tag, you can specify either the exact resource paths with restricted access or regular expressions (as shown in the example).<br>
   I recommend extracting the check for user access based on role into a separate class and debug its functionality outside the web application.<br>

   c. The file should be read during application startup, and after that, access control settings should not change.<br>
   d. The filter should intercept all incoming requests from clients (except static content). For each request, the filter should perform the following checks:
      - Is the requested page in the list of restricted access pages?
      - NO - Pass the request to the next filter (chain.doFilter).
      - YES - Is the user logged in?
        - NO - Redirect the user to the login page.
        - YES - Does the user's role have access to the requested page?
          - NO - Redirect the user to the access error pages (sendRedirect).
          - YES - Pass the request to the next filter (chain.doFilter).<br>
   
   e. Consider how to return to the requested page after displaying the login page (using the HTTP Referer header or URL parameter).<br><br>

4. Implement user login, logout, and access error pages.

