-- =========================================================
-- Shopping Mall Management System - Sprint 1
-- PostgreSQL Database Setup Script (ALL MODULES)
-- =========================================================
-- Run this in pgAdmin 4 Query Tool or psql CLI
-- =========================================================

-- =============================================
-- STEP 1: Create the database
-- =============================================
-- Run this FIRST in the default 'postgres' database:
CREATE DATABASE shopping_mall_db;

-- =============================================
-- STEP 2: Connect to the new database
-- =============================================
-- In psql CLI:    \c shopping_mall_db
-- In pgAdmin 4:   Right-click shopping_mall_db → Query Tool


-- =============================================
-- TABLE 1: employees (Employee Module)
-- =============================================
CREATE TABLE IF NOT EXISTS employees (
    id          SERIAL PRIMARY KEY,
    name        VARCHAR(100)   NOT NULL,
    dob         DATE,
    salary      FLOAT,
    address     VARCHAR(255),
    designation VARCHAR(100)   NOT NULL,
    shop_id     BIGINT
);

-- =============================================
-- TABLE 2: shop_owners (ShopOwner Module)
-- =============================================
CREATE TABLE IF NOT EXISTS shop_owners (
    id            SERIAL PRIMARY KEY,
    name          VARCHAR(100)   NOT NULL,
    email         VARCHAR(150)   UNIQUE,
    phone         VARCHAR(20),
    shop_name     VARCHAR(150)   NOT NULL,
    shop_category VARCHAR(100),
    address       VARCHAR(255)
);

-- =============================================
-- TABLE 3: admins (Admin Module)
-- =============================================
CREATE TABLE IF NOT EXISTS admins (
    id       SERIAL PRIMARY KEY,
    name     VARCHAR(100)   NOT NULL,
    email    VARCHAR(150)   UNIQUE NOT NULL,
    phone    VARCHAR(20),
    role     VARCHAR(50)    NOT NULL,
    password VARCHAR(255)   NOT NULL
);

-- =============================================
-- TABLE 4: orders (Order Module)
-- =============================================
CREATE TABLE IF NOT EXISTS orders (
    id           SERIAL PRIMARY KEY,
    customer_id  BIGINT         NOT NULL,
    shop_id      BIGINT         NOT NULL,
    order_date   DATE,
    total_amount DOUBLE PRECISION,
    status       VARCHAR(50)    NOT NULL,
    description  VARCHAR(500)
);

-- =============================================
-- TABLE 5: customers (Customer Module)
-- =============================================
CREATE TABLE IF NOT EXISTS customers (
    id             SERIAL PRIMARY KEY,
    name           VARCHAR(100)   NOT NULL,
    email          VARCHAR(150)   UNIQUE,
    phone          VARCHAR(20),
    address        VARCHAR(255),
    loyalty_points INTEGER        DEFAULT 0
);

-- =============================================
-- TABLE 6: users (User Module)
-- =============================================
CREATE TABLE IF NOT EXISTS users (
    id        SERIAL PRIMARY KEY,
    username  VARCHAR(100)   UNIQUE NOT NULL,
    password  VARCHAR(255)   NOT NULL,
    email     VARCHAR(150)   UNIQUE NOT NULL,
    role      VARCHAR(50)    NOT NULL,
    full_name VARCHAR(150),
    active    BOOLEAN        DEFAULT true
);


-- =========================================================
-- SAMPLE DATA: employees
-- =========================================================
INSERT INTO employees (name, dob, salary, address, designation, shop_id)
VALUES
    ('Ravi Kumar',    '1995-06-15', 35000.00, '12, Anna Nagar, Chennai',     'Cashier',           1),
    ('Priya Sharma',  '1990-03-22', 55000.00, '5, T Nagar, Chennai',         'Shop Manager',      1),
    ('Arjun Mehta',   '1998-11-10', 28000.00, '22, Velachery, Chennai',      'Sales Executive',   2),
    ('Sneha Iyer',    '1993-08-05', 48000.00, '9, Adyar, Chennai',           'Inventory Manager', 2),
    ('Kiran Nair',    '2000-01-30', 22000.00, '3, Tambaram, Chennai',        'Intern',            3);

-- =========================================================
-- SAMPLE DATA: shop_owners
-- =========================================================
INSERT INTO shop_owners (name, email, phone, shop_name, shop_category, address)
VALUES
    ('Suresh Kumar',   'suresh@mail.com',  '9876543210', 'Tech World',       'Electronics',  'Ground Floor, Shop 101'),
    ('Lakshmi Devi',   'lakshmi@mail.com', '9876543211', 'Fashion Hub',      'Clothing',     'First Floor, Shop 201'),
    ('Ramesh Babu',    'ramesh@mail.com',  '9876543212', 'Food Court King',  'Food',         'Third Floor, Shop 301'),
    ('Anitha Raja',    'anitha@mail.com',  '9876543213', 'Book Paradise',    'Books',        'Second Floor, Shop 205'),
    ('Vijay Prakash',  'vijay@mail.com',   '9876543214', 'Sports Zone',      'Sports',       'Ground Floor, Shop 105');

-- =========================================================
-- SAMPLE DATA: admins
-- =========================================================
INSERT INTO admins (name, email, phone, role, password)
VALUES
    ('Super Admin',    'superadmin@mall.com',  '9000000001', 'SUPER_ADMIN',    'admin123'),
    ('Mall Manager',   'manager@mall.com',     '9000000002', 'MALL_MANAGER',   'manager123'),
    ('Floor Manager',  'floor1@mall.com',      '9000000003', 'FLOOR_MANAGER',  'floor123');

-- =========================================================
-- SAMPLE DATA: orders
-- =========================================================
INSERT INTO orders (customer_id, shop_id, order_date, total_amount, status, description)
VALUES
    (1, 1, '2026-04-20', 15999.00, 'DELIVERED',  'Laptop charger and mouse'),
    (2, 2, '2026-04-21', 3500.00,  'CONFIRMED',  'Summer collection dress'),
    (3, 3, '2026-04-22', 850.00,   'PENDING',    'Lunch combo meal x3'),
    (1, 4, '2026-04-23', 1200.00,  'CONFIRMED',  'Programming books x2'),
    (4, 1, '2026-04-24', 45000.00, 'PENDING',    'Gaming keyboard and headset');

-- =========================================================
-- SAMPLE DATA: customers
-- =========================================================
INSERT INTO customers (name, email, phone, address, loyalty_points)
VALUES
    ('Arun Kumar',     'arun@mail.com',    '8765432100', '15, Mylapore, Chennai',    150),
    ('Divya Rajan',    'divya@mail.com',   '8765432101', '8, Guindy, Chennai',       320),
    ('Manoj Krishnan', 'manoj@mail.com',   '8765432102', '20, Porur, Chennai',       75),
    ('Shalini Priya',  'shalini@mail.com', '8765432103', '11, Chromepet, Chennai',   500),
    ('Naveen Raj',     'naveen@mail.com',  '8765432104', '6, Vadapalani, Chennai',   200);

-- =========================================================
-- SAMPLE DATA: users
-- =========================================================
INSERT INTO users (username, password, email, role, full_name, active)
VALUES
    ('admin',       'admin123',   'admin@mall.com',     'ADMIN',      'System Admin',    true),
    ('shopowner1',  'shop123',    'owner1@mall.com',    'SHOP_OWNER', 'Suresh Kumar',    true),
    ('customer1',   'cust123',    'cust1@mall.com',     'CUSTOMER',   'Arun Kumar',      true),
    ('employee1',   'emp123',     'emp1@mall.com',      'EMPLOYEE',   'Ravi Kumar',      true),
    ('inactive1',   'inactive',   'inactive@mall.com',  'CUSTOMER',   'Old Customer',    false);


-- =========================================================
-- VERIFY ALL DATA
-- =========================================================
SELECT 'employees'  AS module, COUNT(*) AS records FROM employees
UNION ALL
SELECT 'shop_owners', COUNT(*) FROM shop_owners
UNION ALL
SELECT 'admins',      COUNT(*) FROM admins
UNION ALL
SELECT 'orders',      COUNT(*) FROM orders
UNION ALL
SELECT 'customers',   COUNT(*) FROM customers
UNION ALL
SELECT 'users',       COUNT(*) FROM users;
