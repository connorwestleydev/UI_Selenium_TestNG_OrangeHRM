# Web UI automation project using Selenium WebDriver 4 and TestNG in Java

## Overview
This is a UI test automation project built using Selenium WebDriver 4 and TestNG in Java with Maven as the build tool.
I have written a [blog post](https://connorwestleytesting.wordpress.com/2024/04/09/web-ui-automation-project-using-selenium-webdriver-4-and-testng-in-java/) 
discussing some of the key features of the project as well as discussion about 
the challenges and lessons learned while creating it.

In summary, the project demonstrates various aspects of web UI automation, such as utilising the Page Object Model (POM),
using appropriate element locators, interacting with different types of web elements and using TestNG annotations. The project uses the 
[OrangeHRM demo site](https://opensource-demo.orangehrmlive.com/web/index.php/auth/login) as the application under test.

There is an accompanying [GitHub Actions workflow](https://github.com/connorwestleydev/UI_Selenium_TestNG_OrangeHRM/blob/main/.github/workflows/test.yml) 
which has a manual trigger event.

## Running tests locally
### Pre-requisites
- [Java Development Kit (JDK) 21 or higher](https://www.oracle.com/uk/java/technologies/downloads/)
- [Maven 3.9.7](https://maven.apache.org/download.cgi)

### Installation
1. Clone the repository:
    ```bash
    git clone https://github.com/connorwestleydev/UI_Selenium_TestNG_OrangeHRM
    cd UI_Selenium_TestNG_OrangeHRM
    ```
2. Install the dependencies:
    ```bash
    mvn clean install
    ```

### Running Tests
The tests can be run using Maven.

- To run all tests:
    ```bash
    mvn test
    ```
  
- To run a specific test class:
    ```bash
  mvn -Dtest=LoginTests test
  ```
  
## Contact
Constructive feedback is welcomed. You can contact me via LinkedIn - [Connor Westley](https://www.linkedin.com/in/connor-westley-1a309713b/)
