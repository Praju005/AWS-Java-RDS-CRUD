🏆 AWS Java JDBC CRUD Project

Author: Prajakta Pandaram

A 2-tier AWS project connecting a Java CLI app running on EC2 to an RDS MySQL database. Perform Create, Read, Update, Delete (CRUD) operations on users. Perfect for demonstrating Java + AWS cloud skills.

📌 Project Architecture
      +-------------+               +---------------+
      |             |   JDBC        |               |
      |  EC2 (Java  |  --------->   |  RDS MySQL    |
      | CLI App)    |               |  Database     |
      +-------------+               +---------------+


Tier 1: EC2 hosts Java application
Tier 2: RDS MySQL stores all user data

⚡ Features

Add new users (Name, Address, Contact)

View all users

Secure RDS connection from EC2

Simple, beginner-friendly AWS 2-tier setup

Perfect for interviews and cloud demonstrations

🛠 Prerequisites

AWS account with EC2 and RDS access

Java 11 or higher installed on EC2

MySQL client installed on EC2

🚀 Setup Instructions
1️⃣ Create RDS MySQL Database

AWS Console → RDS → Create database → MySQL

DB name: userdb

Note the endpoint, e.g., mydb.abc123xyz.eu-north-1.rds.amazonaws.com

Open port 3306 in your EC2 Security Group

Create DB user (appuser) or use master user

2️⃣ Update Java Code
static final String DB_URL = "jdbc:mysql://<RDS-ENDPOINT>:3306/userdb";
static final String DB_USER = "appuser";
static final String DB_PASSWORD = "AppStrongPass123!";

3️⃣ Install Java & MySQL Driver on EC2
sudo yum update -y
sudo amazon-linux-extras install java-openjdk11 -y
java -version

wget https://dev.mysql.com/get/Downloads/Connector-J/mysql-connector-j-8.4.0.tar.gz
tar -xvzf mysql-connector-j-8.4.0.tar.gz


Keep the .jar file (e.g., mysql-connector-j-8.4.0.jar)

4️⃣ Compile & Run Java Program
scp -i mykey.pem UserDatabaseApp.java ec2-user@<EC2_PUBLIC_IP>:/home/ec2-user/
javac -cp .:mysql-connector-j-8.4.0/mysql-connector-j-8.4.0.jar UserDatabaseApp.java
java -cp .:mysql-connector-j-8.4.0/mysql-connector-j-8.4.0.jar UserDatabaseApp

5️⃣ Create Database & Table on RDS
sudo yum install -y mysql
mysql -h <RDS-ENDPOINT> -P 3306 -u <DB_USER> -p


Run SQL:

CREATE DATABASE userdb;
USE userdb;

CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100),
    address VARCHAR(200),
    contact VARCHAR(15)
);

6️⃣ Test the App

Run the program → menu appears:

--- MENU ---
1. Add User
2. View Users
3. Exit
Choose option:


Check users in RDS:

SELECT * FROM users;

✅ Your 2-tier AWS Java + RDS project is now fully functional!
