# EBANX Take Home Assignment
## Table of Contents
- [About this Project](#about-this-project)
- [About the Proposed Solution](#about-the-proposed-solution)
  - [Package Structure](#package-structure)
  - [Notes on Business Rules and Validations](#notes-on-business-rules-and-validations)
- [Instructions](#instructions)
  - [Cloning the Project](#cloning-the-project)
  - [Building the Application](#building-the-application)
  - [Running the Application on a Local Mahcine](#running-the-application-on-a-local-machine)

## About this Project
This project was created as a solution for EBANX's technical evaluation.
The proposed problem is a simple _API_ that is composed by two _endpoints_, **GET /balance** and **POST /event**.
The solution should be tested using an online automated test suite.

The objective of this evaluation is to set up a baseline for admission process.

**Extra info**:
- There is no hidden agenda, if you code passes the tests, and you are happy about it:  you are done;
- Durability **IS NOT** a requirement, that is, you don’t need to use a database or persistence mechanism;
- Pay attention to the package/directory structure, naming and encapsulation;
- Separate your business logic from the HTTP transport layer;
- Keep your code simple, do not try to anticipate anything that is not part of the spec;
- Keep your code malleable, we may ask for modifications;
- AGAIN, Keep your code malleable, we may ask for modifications;
- Use version control, we would love to see your step-by-step process;
- Take your time, don’t rush it;

## About the Proposed Solution
The solution is implemented in the [Java](https://www.oracle.com/java/) Programming Language, using the [Spring Boot](https://spring.io/projects/spring-boot) Framework and [Maven](https://maven.apache.org/) as a dependency management tool.

The main goal of this application is to simulate a simple _API_ that handles some basic financial operations, such as:
- Deposits;
- Withdraws;
- Transfers.

This _API_ also supports 

The Project uses best practices such as the use of [SOLID Principles](https://www.baeldung.com/solid-principles), use of interfaces single purpose implementations as well as [Design Patterns](https://refactoring.guru/design-patterns) to facilitate code maintainability.

### Package Structure
The project itself is based on a [Package-by-Feature](http://www.javapractices.com/topic/TopicAction.do?Id=205) organization structure, dividing the project into functional domains and subdomains, each with their own layers.

Since the idea of the project is to be simple and malleable it was decided to keep the communication between (sub)domains simpler, using _Dependency Injection_ of _Services_ into _orchestrator_ structures called _Executors_.
As the project grows, it could be easily refactored into a _Port_-_Adapter_ structure, closer to a more standard _Hexagonal Architecture_ if needed.

### Notes on Business Rules and Validations
- It was not specified if the balance should be properly validated **before** the _withdrawal_ and _transfer_ events. 
Since negative balance is something that is used by some banks and other financial institutions (which may charge overdraft fees), it was decided that such validation was not going to be added at the current moment.
However, it is really simple to be added, if required in the future. 

## Instructions
### Cloning the Project
To clone the repository, simply run the following command on a terminal session:

```bash
git clone git@github.com:lucian-augusto/ebanx-take-home-assignment.git
```

then navigate to the repository's folder using:

```bash
cd ebanx-take-home-assignment
```

### Building the Application
Since this project uses [Maven](https://maven.apache.org/) as its dependency and build management tool, make sure to [download](https://maven.apache.org/download.cgi) and [install](https://maven.apache.org/install.html) before moving on.
After [Maven](https://maven.apache.org/) is installed, just run the following command on a terminal session while in the project's root folder:

```bash
mvn clean install
```

This command will also execute all the automated tests implemented as well. If any test fail, the build will also fail. Note that this document has a badge that shows the status of the previous automated build.

### Running the Application on a Local Machine
To run this application, simply configure the _Spring Boot Runner_ on your _IDE_ of choice and execute the runner.

If your _IDE_ does not support any _Spring Boot Runner_ (or if you are not using a full-fledged _IDE_), you can run the following command on a terminal session (after building the project):

```bash
java -Xmx512m -jar target/ebanx-assignment-0.0.1.jar
```

Then call the endpoints **GET /balance** and **POST /event** to start testing te application.