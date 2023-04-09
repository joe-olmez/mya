# About

I created this project to keep my basic full-stack skills fresh and to use new technologies and test them from time to time.

This project receives currency data from an external source using an API key (https://api.getgeoapi.com/v2/currency) daily (Spring schedule was used) and stores them in the database.
To use the user interface, the user must signup once. Later, he can access the relevant data as a regular user (not an admin).

There are 2 user dashboards admin and regular user.
Regular users have some restrictions. For example, they cannot access the user list, see users other than themselves, or make any changes to them.

Accounts will be added for each user soon.

# Used Tech

• Backend : Java-Spring Boot
• Database : MySQL and H2 database (as a test database)
• Frontend : Angular
• Other : JWT, Lombok

# Repository

• Private access repository: https://bitbucket.org/josephpersonal/mya/src/main/

# Design Pattern

· Data Access Object (DAO) Design Pattern was used
· The pattern focuses on decoupling the service layer from the data access layer. With the DAO design pattern,

- The model is transferred from one layer to the other (e.g. CurrencyRate class).
- The interfaces provide a flexible design (e.g. CurrencyRateService interface).
- The interface implementation is a concrete implementation of the persistence logic (e.g. CurrencyRateServiceImpl class).
