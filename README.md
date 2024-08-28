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
        "access_toke": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJqb2huZG9lMUBnbWFpbC5jb20iLCJpYXQiOjE3MjQ4Njg3OTAsImV4cCI6MTcyNDk1NTE5MH0.uPHFLquSFEUQj5SXVjhzgNdH2tbsVbF8m5E7qnzMFkxuCp9zWFW8DIrev6HnEyPNxOOWSTDdCdb0zPqAQ5_0JQ",
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
- **Endpoint:** `/api/account/deposit`
- **Method:** `POST`
- **Authorization:** Bearer Token
- **Request Body:**
  ```json
  {
      "amount": 100.0,
      "pin": "4321"
  }
  ```
- **Description:** Deposits the specified amount into the user's account.
- **Response:**
  - Status Code: 200 OK
  - Body:
    ```json
    {
        "msg": "Cash deposited successfully"
    }
    ```

**Possible Status Codes:**
- 200 OK: The cash deposit was successful.
- 400 Bad Request: The request body is missing or invalid.
- 401 Unauthorized: The user is not authenticated or the provided PIN is incorrect. Please ensure you include a valid bearer token in the request header.
- 404 Not Found: The user's account was not found.

#### 1.6. Fund Transfer
- **Endpoint:** `/api/account/fund-transfer`
- **Method:** `POST`
- **Authorization:** Bearer Token
- **Request Body:**
  ```json
  {
      "amount": 10.0,
      "pin": "4321",
      "targetAccountNumber": "556704"
  }
  ```
- **Description:** Transfers the specified amount to another user's account.
- **Response:**
  - Status Code: 200 OK
  - Body:
    ```json
    {
        "msg": "Fund transferred successfully"
    }
    ```

**Possible Status Codes:**
- 200 OK: The fund transfer was successful.
- 400 Bad Request: The request body is missing or invalid, or the account does not have sufficient balance.
- 401 Unauthorized: The user is not authenticated or the provided PIN is incorrect. Please ensure you include a valid bearer token in the request header.
- 404 Not Found: The user's source or target account was not found.

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
- **Endpoint:** `/api/dashboard/account`
- **Method:** `GET`
- **Authorization:** Bearer Token
- **Description:** Retrieves details of the user's account.
- **Response:**
  - Status Code: 200 OK
  - Body: Account details object.

**Possible Status Codes:**
- 200 OK: The account details were successfully retrieved.
- 401 Unauthorized: The user is not authenticated. Please

 ensure you include a valid bearer token in the request header.
- 404 Not Found: The user's account was not found.

### 2. User Endpoints

#### 2.1. Register User
- **Endpoint:** `/api/users/register`
- **Method:** `POST`
- **Request Body:**
  ```json
  {
      "name": "Abhishek a",
      "password": "secretpassword2",
      "email": "jonoe@gmail.com",
      "address": "123 Main Street",
      "phone_number": "134566690"
  }
  ```
- **Description:** Registers a new user with the provided details.
- **Response:**
  - Status Code: 200 OK
  - Body:
    ```json
    {
        "name": "Abhishek a",
        "email": "jonoe@gmail.com",
        "accountNumber": "236480",
        "IFSC_code": "[IFSC_CODE]",
        "branch": "[BRANCH]",
        "account_type": "[ACCOUNT_TYPE]"
    }
    ```

**Possible Status Codes:**
- 200 OK: The user was successfully registered.
- 400 Bad Request: The request body is missing or invalid.
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

#### 2.3. Login
- **Endpoint:** `/api/users/login`
- **Method:** `POST`
- **Request Body:**
  ```json
  {
      "accountNumber": "236480",
      "password": "secretpassword2"
  }
  ```
- **Description:** Logs in the user with the provided account number and password.
- **Response:**
  - Status Code: 200 OK
  - Body:
    ```json
    {
        "token": "[JWT_TOKEN]"
    }
    ```

**Possible Status Codes:**
- 200 OK: The login was successful, and a JWT token is provided in the response body.
- 400 Bad Request: The request body is missing or invalid.
- 401 Unauthorized: The provided account number or password is incorrect.
- 404 Not Found: The user's account was not found.

**Error Handling:**
The API implements global exception handling for the following scenarios:
- NotFoundException: Returns 404 Not Found with an error message.
- UnauthorizedException: Returns 401 Unauthorized with an error message.
- InsufficientBalanceException: Returns 400 Bad Request with an error message.

Please note that this API documentation provides an overview of the available endpoints, request parameters, and response structures, along with additional details on error handling and status codes. For a comprehensive understanding of the API, further details such as possible response data and detailed error messages should be included in the final API documentation.
