

CREATE SCHEMA IF NOT EXISTS bankportal;
SET NAMES 'UTF8MB4';
USE bankportal;

DROP TABLE IF EXISTS customers;

CREATE TABLE IF NOT EXISTS customers (

    customer_id BIGINT NOT NULL UNIQUE PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(256) NOT NULL,
    dob DATE NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

DROP TABLE IF EXISTS branch;

CREATE TABLE IF NOT EXISTS branch (
    branch_code BIGINT NOT NULL PRIMARY KEY,
    branch_name VARCHAR(30) NOT NULL
);


DROP TABLE IF EXISTS accounts;



CREATE TABLE accounts (

    account_no BIGINT NOT NULL UNIQUE PRIMARY KEY,
    balance  DECIMAL(18, 2) UNSIGNED DEFAULT 0.00,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    customer_id BIGINT NOT NULL,
    account_status VARCHAR(30) DEFAULT("active") NOT NULL,
    currency VARCHAR(20) NOT NULL,
    account_type VARCHAR(50) NOT NULL,
    branch_id BIGINT NOT NULL,

    FOREIGN KEY(branch_id) REFERENCES branch(branch_code),
    FOREIGN KEY(customer_id) REFERENCES customers(customer_id)
);

DROP TABLE IF EXISTS transactions;

CREATE TABLE IF NOT EXISTS transactions (
    transaction_id BIGINT NOT NULL PRIMARY KEY UNIQUE,
    amount DECIMAL(18, 2) NOT NULL,
    date_issued TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    from_account BIGINT NOT NULL,
    to_account BIGINT NOT NULL,
    transaction_type VARCHAR(20) NOT NULL,
    FOREIGN KEY(from_account) REFERENCES accounts(account_no)
);

DROP TABLE IF EXISTS loans;

CREATE TABLE IF NOT EXISTS loans(
    loan_id BIGINT NOT NULL PRIMARY KEY UNIQUE,
    account_no BIGINT NOT NULL,
    start_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    end_date TIMESTAMP NOT NULL,
    amount DECIMAL(18, 2) NOT NULL,
    branch_code BIGINT NOT NULL,
    loan_type VARCHAR(50) NOT NULL,
    loan_status VARCHAR(20) NOT NULL,
    FOREIGN KEY(branch_id) REFERENCES branch(branch_code),
    FOREIGN KEY(account_no) REFERENCES accounts(account_no)
);

DROP TABLE IF EXISTS loan_payment;

CREATE TABLE IF NOT EXISTS loan_payment(
    payment_id  BIGINT UNSIGNED NOT NULL PRIMARY KEY,
    loan_id BIGINT NOT NULL,
    payment_amount DECIMAL (18, 2) NOT NULL,
    payment_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    balance_remaining DECIMAL(18, 2) NOT NULL,
    FOREIGN KEY (loan_id) REFERENCES loans(loan_id)
);
