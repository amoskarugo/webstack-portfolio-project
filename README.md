# webstack-portfolio-project
# API Documentation - Banking Portal

**Introduction:**
The Banking Portal API allows users to perform various banking operations, including managing accounts, fund transfers, and transactions. This document provides details on each API endpoint, its request parameters, response structure, and authentication requirements. It also includes information on error handling and status codes.

**Authentication:**
The API endpoints require bearer token authentication. Users must obtain a valid access token and include it in the "Authorization" header with the "Bearer" scheme to access protected endpoints.

**Base URL:**
`http://localhost:8180`

### 1. Account Endpoints

#### 1.1. Register a new Customer
- **Endpoint:** `/api/v1/register`
- **Method:** `PUT`
- **Authorization:** None
- **Request Body:**
  ```json
  {

    "first_name": "doe",
    "last_name": "john",
    "email": "johndoe@gmail.com",
    "password": "123456",
    "dob": "2000-01-07",
    "role": "USER"
  }
  ```
  
- **Description:** Registers the user in the system.
- **Response:**
  - Status Code: 201 CREATED or 400 BAD REQUEST
  - Body:
    ```json
    {
    "httpStatusCode": 201,
    "message": "customer created successfully!",
    "data": {
        "customer_id": 62862965,
        "first_name": "doe",
        "last_name": "john",
        "email": "johndoe2@gmail.com"
    }
    ```
     or
    
    ```json
    {
    "httpStatusCode": 401,
    "message": "email already exists!",
    "data": null
    }
   ```

**Possible Status Codes:**
- 201 CREATED: The User has been created successfully.
- 400 BAD REQUEST: Invalid request body.

#### 1.2. Login
- **Endpoint:** `/api/v1/login`
- **Method:** `POST`
- **Authorization:** None
- **Request Body:**
```json
  {
    "username": "johndoe@gmail.com",
    "password": "123456"
  }
  ```
- **Description:** Intiates login into the system.
- **Response:**
  - Status Code: 200 OK
  - Body:
    ```json
    {
    "httpStatusCode": 200,
    "message": "login success!",
    "data": {
        "access_token": "[JWT_TOKEN]",
        "token_type": "Bearer"
      }
    }
    ```

**Possible Status Codes:**
- 200 OK: Login succesful.
- 400 Bad Request: The request body is missing or invalid.
- 401 Unauthorized: The user email or the provided password is incorrect.
- 404 Not Found: The user's account was not found.

#### 1.3. Create a new Account
- **Endpoint:** `/api/v1/account/create-account`
- **Method:** `POST`
- **Authorization:** Bearer Token
- **Request Body:**
  ```json
  {

    "currency": "EUR",
    "branch_name": "NAIROBI",
    "accountType": "SAVING",
    "account_status": "ACTIVE",
    "pin": 2068
  }
  ```
- **Description:** Creates a new bank account in the system.
- **Response:**
  - Status Code: 201 CREATED
  - Body:
    ```json
    {
    "httpStatusCode": 201,
    "message": "account created successfully!",
    "data": {
        "account_balance": 0,
        "currency": "EUR",
        "account_no": 634176595
      }
    }
    ```
**Possible Status Codes:**
- 201 OK: Account created successfully!.
- 400 Bad Request: The request body is missing or invalid.
- 401 Unauthorized: The user is not authenticated, Please ensure you include a valid bearer token in the request header.

#### 1.4. Deposit
- **Endpoint:** `/api/v1/account/deposit`
- **Method:** `POST`
- **Authorization:** Bearer Token
- **Request Body:**
  ```json
  {

    "accountNumber": 923307938,
    "amount": 1000000
    }
  ```
- **Description:** Deposits the specified amount into the customer's account.
- **Response:**
  - Status Code: 200 OK
  - Body:
    ```json
    {
    "httpStatusCode": 200,
    "message": "deposit successful!",
    "data": {
        "transaction_id": 9,
        "date": "2024-08-28T21:24:10.482774363",
        "bal": 1000000.00
      }
    }
  ```

**Possible Status Codes:**
- 200 OK: The cash Deposit was successful.
- 400 Bad Request: The request body is missing or invalid, or the account does not have sufficient balance.
- 401 Unauthorized: The user is not authenticated, Please ensure you include a valid bearer token in the request header.
- 404 Not Found: The customers's account number was not found.

#### 1.5. Deposit
- **Endpoint:** `/api/account/withdraw`
- **Method:** `POST`
- **Authorization:** Bearer Token
- **Request Body:**
  ```json
  {

    "accountNumber": 430831346,
    "amount": 500,
    "pin": 2068
    }
  ```
- **Description:** Withdraw the specified amount from the customer's account.
- **Response:**
  - Status Code: 200 OK
  - Body:
    ```json
    {
    "httpStatusCode": 201,
    "message": "withdraw success!",
    "data": {
        "date": "2024-08-28T22:08:06.076574723",
        "amount": 500,
        "bal": 999500.00,
        "transactionID": 10
     }
    }
    ```

**Possible Status Codes:**
- 200 OK: The cash withdraw was successful.
- 400 Bad Request: The request body is missing or invalid.
- 401 Unauthorized: The user is not authenticated or the provided PIN is incorrect. Please ensure you include a valid bearer token in the request header.
- 404 Not Found: The user's account was not found.

#### 1.6. Fund Transfer
- **Endpoint:** `/api/v1/account/transfer`
- **Method:** `POST`
- **Authorization:** Bearer Token
- **Request Body:**
  ```json
  {
    "fromAccount": 430831346,
    "toAccount": 923307938,
    "pin": 2068,
    "amount": 1011
  }
  ```
- **Description:** Transfers the specified amount to another Customer's account.
- **Response:**
  - Status Code: 200 OK
  - Body:
    ```json
    {
    "httpStatusCode": 201,
    "message": "transfer successful",
    "data": {
        "amount": 1011,
        "balance": 2017678.00,
        "recipient": 923307938,
        "transactionID": 11
      }
    }
    ```

**Possible Status Codes:**
- 200 OK: The fund transfer was successful.
- 400 Bad Request: The request body is missing or invalid, or the account does not have sufficient balance.
- 401 Unauthorized: The user is not authenticated or the provided PIN is incorrect. Please ensure you include a valid bearer token in the request header.
- 404 Not Found: The user's source or target account was not found.

#### 1.6. Borrow Loan
- **Endpoint:** `/api/v1/loan/borrow`
- **Method:** `POST`
- **Authorization:** Bearer Token
- **Request Body:**
  ```json
  {
    "loan_type": "PERSONAL",
    "amount": 20200,
    "account_number": 430831349
  }
  ```
- **Description:** Borrow a loan of the specified amount.
- **Response:**
  - Status Code: 200 OK
  - Body:
    ```json
    {
    "httpStatusCode": 200,
    "message": "loan successfully  credited to account 634176595",
    "data": {
        "amount": 20200,
        "load_id": 2,
        "date_issued": "2024-08-28T22:34:28.73476611",
        "due_date": "2025-09-28",
        "payment_start_date": "2024-09-27"
      }
    }
    ```

**Possible Status Codes:**
- 200 OK: Loan request was successful.
- 400 Bad Request: The request body is missing or invalid, or the account does not have sufficient balance.
- 401 Unauthorized: The user is not authenticated or the provided PIN is incorrect. Please ensure you include a valid bearer token in the request header.
- 404 Not Found: The user's target account was not found.
#### 1.6. Borrow Loan
- **Endpoint:** `/api/v1/loan/pay_loan`
- **Method:** `POST`
- **Authorization:** Bearer Token
- **Request Body:**
  ```json
  {
    "loan_id": 1,
    "amount": 10000,
    "account_number": 430831346
  }
  ```
- **Description:** Borrow a loan of the specified amount.
- **Response:**
  - Status Code: 200 OK
  - Body:
    ```json
    {
    "httpStatusCode": 200,
    "message": "loan paid successfully!",
    "data": {
        "transaction_id": 14,
        "loan_bal": 200.00,
        "amount paid": 10000
      }
    }
    ```

**Possible Status Codes:**
- 200 OK: Loan payment request was successful.
- 400 Bad Request: The request body is missing or invalid, or the account does not have sufficient balance.
- 401 Unauthorized: The user is not authenticated or the provided PIN is incorrect. Please ensure you include a valid bearer token in the request header.
- 404 Not Found: The user's target account was not found.

#### 1.7. Transactions
- **Endpoint:** `/api/account/transactions`
- **Method:** `GET`
- **Authorization:** Bearer Token
- **Description:** Retrieves the list of transactions for the user's account.
- **Response:**
  - Status Code: 200 OK
  - Body: List of transaction objects.

**Possible Status Codes:**
- 200 OK: The list of transactions was successfully retrieved.
- 401 Unauthorized: The user is not authenticated. Please ensure you include a valid bearer token in the request header.

#### 1.8. Account Details
- **Endpoint:** `/api/dashboard/account/{account_no}`
- **Method:** `GET`
- **Authorization:** Bearer Token
- **Description:** Retrieves details of the Customers's account.
- **Response:**
  - Status Code: 200 OK
  - Body: Account details object.

**Possible Status Codes:**
- 200 OK: The account details were successfully retrieved.
- 401 Unauthorized: The user is not authenticated. Please ensure you include a valid bearer token in the request header.
- 404 Not Found: The user's account was not found.



#### 2.2. Get User Details
- **Endpoint:** `/api/dashboard/user`
- **Method:** `GET`
- **Authorization:** Bearer Token
- **Description:** Retrieves details of the authenticated user.
- **Response:**
  - Status Code: 200 OK
  - Body: User details object.

**Possible Status Codes:**
- 200 OK: The user details were successfully retrieved.
- 401 Unauthorized: The user is not authenticated. Please ensure you include a valid bearer token in the request header.
- 404 Not Found: The user's account was not found.


**Error Handling:**
The API implements global exception handling for the following scenarios:
- NotFoundException: Returns 404 Not Found with an error message.
- UnauthorizedException: Returns 401 Unauthorized with an error message.
- InsufficientBalanceException: Returns 400 Bad Request with an error message.

Please note that this API documentation provides an overview of the available endpoints, request parameters, and response structures, along with additional details on error handling and status codes. For a comprehensive understanding of the API, further details such as possible response data and detailed error messages should be included in the final API documentation.
