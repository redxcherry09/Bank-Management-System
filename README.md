🏧 Bank Management & ATM System (Java + Swing + MySQL)
📌 Project Overview

The Bank Management System is a comprehensive desktop application designed to digitalize banking operations. This project handles the entire user lifecycle, from a multi-stage account registration process to secure ATM transactions and real-time financial tracking, all managed through a robust Graphical User Interface (GUI).

🚀 Features

🔐 Secure Authentication – Features a login system utilizing 16-digit Card Numbers and 4-digit PIN verification.
📝 Three-Step Registration – A modular onboarding process capturing personal details, identification (PAN/Aadhar), and banking service preferences.
💳 ATM Operations – Functional Cash Deposit and Withdrawal modules with automated balance validation.
⚡ Fast Cash – Provides a quick-access interface for withdrawing standard denominations instantly.
🔑 PIN Management – A secure utility to update the user's security PIN across all database records.
📊 Transaction History – Generates real-time transaction logs and mini-statements for user transparency.

🛠 Technologies Used

Java (Swing & AWT) – Used for crafting the professional Desktop GUI and event handling.

MySQL – Acts as the relational database management system for persistent data storage.

JDBC (Java Database Connectivity) – Facilitates the bridge between the Java application and the SQL backend.

NetBeans / IntelliJ IDEA – The primary Integrated Development Environments used for construction.

🧩 Project Structure

login.java – The secure entry point for authenticated users.

Signup.java, Signup2.java, Signup3.java – The multi-frame registration pipeline.

main_Class.java – The central navigation dashboard (ATM Menu).

Deposit.java & Withdrawl.java – Core financial transaction logic.

FastCash.java – Specialized quick-withdrawal module.

Pin.java – Secure credential management utility.

mini.java – Module for fetching and displaying transaction history.

Conn.java – Centralized database connection configuration.

java1.java, java2.java, ruby.java – Database initialization and connectivity test scripts.

🗄 Database Setup

To initialize the project, create a MySQL database named bankSystem. Ensure the following tables are configured: login (for credentials), bank (for transaction ledgers), and signup / signuptwo / signupthree (for user profiling).

▶️ How to Run

Import the project into your preferred IDE (NetBeans or IntelliJ).

Setup the MySQL database and execute the necessary table creation queries.

Add External Libraries: Include the mysql-connector-java.jar and jcalendar.jar in your project build path.

Configure Credentials: Update the database URL and password within the Conn.java file.

Launch the application by running the login.java file.

💡 Key Functionalities

🏨 Modular User Onboarding
The system uses a three-frame architecture to ensure data integrity, linking user information across multiple tables via a unique Form Number.

💳 Dynamic Balance Calculation
Instead of storing a static balance, the system scans the entire bank table for a user's transaction history to calculate the current balance in real-time, preventing overdrafts.

🛠 Automated Credential Generation
Upon successful completion of the signup process, the system algorithmically generates a unique 16-digit Card Number and a 4-digit PIN for the user.

🎯 Future Enhancements

📅 Temporal Transaction Filtering – Sorting history by specific date ranges.
📄 Automated Invoice Generation – Exporting mini-statements as PDF documents.
🎨 UI/UX Optimization – Implementation of a modern Dark Mode interface.
👥 Administrative Dashboard – A dedicated panel for bank staff to manage user accounts.

👨– Author

Name: Ruby Singh
Project Type: Academic / Full Stack Portfolio Project
Technology Stack: Java Swing + MySQL + JDBC

⭐ Conclusion

This Bank Management System is a complete end-to-end solution simulating real-world ATM and banking scenarios. It effectively demonstrates the practical application of database integration, complex business logic, and modular GUI design in Java.
