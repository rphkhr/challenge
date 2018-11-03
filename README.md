# SQ Coding Challenge

One Paragraph of project description goes here

## Getting Started

MySQL
SpringBoot v1.5.9
Using Flyway to initialize the database (scripts can be found here)
Gradle
Wildfly

### Prerequisites

Connecting to the DB:
1- Create a schema called 'sqchallenge'
2- In file application.properties, input username-password

Build the war, deploy in AS of your choice (Wildfly or Tomcat), with application-context 'challenge'
Postman collection can be found [here](https://www.getpostman.com/collections/db5884a72d44bc41472d)

## Endpoints available

### Person

Create Person: Creates a person and returns his unique ID

Get Person: Retrieves a person's accounts, and list of transactions for these accounts (where they are the source account)

### Accounts

Create Account: Creates an account for this person and returns its DB ID

Get Account: Retrieves an account's balance, currency and list of transactions

### Transaction

CashIn: Adds funds to an account

CashOut: Removes funds from an account

Transfer: Transfer funds between 2 accounts (can be same person's account)


## Notes

Tests have not been completed due to lack of time, encountered an issue testing the integration tests.

Time tracked using the commit messages (4h10mn). Last commit was for writing the readme file.
 
## Bugs

In GetAccount, should return account instead of accountId :(
