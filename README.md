Multi module approach for test case automation
==============================================
- This framework allows you to automate API test cases with reusable libraries

## Requirements
- [Maven](https://maven.apache.org/download.cgi)
- [Java 8](https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)

## Features

1. `helpers` package acts as a wrapper over assertions
2. `readers` package allows user to read from different file formats
3. `utils` package comes handy when you test case verification
4. `rest` package provides helper methods for rest api automation
5. `config.properties` allows you to specify test execution configuration, don't use it to provide test data
6. `apis` contains API definitions
7. `base` contains base test definition, will be used as a parent class for test suites
8. `tests` contains testng test suite files
9. `dataproviders` contains generic data providers, can be used across test suites
10. `pojos` contains object to store request and responses
11. `utils` contains common code that can be used across framework
12. `runner.xml` contains test suites to be executed

## How to use it?
1. Clone this repo using `git clone` command
2. Goto project directory

### Execute test cases
1. Run command `mvn clean test` to execute test cases
2. Run command `mvn allure:report` to generate HTML report

## Reporting and logs
- `target/automation.out` file: contains detailed log for debugging purpose
- `target/site/allure-maven-plugin/index.html` directory: contains execution report in HTML format

## Author
- [Vikas Sanap](https://www.linkedin.com/in/vikassanap/)