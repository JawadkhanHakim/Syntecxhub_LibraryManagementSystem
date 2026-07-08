# 📚 Library Management System

A console-based Library Management System built in Java, using JDBC to connect with a MySQL database. Developed as part of the Syntecxhub Java Programming Internship.

## Features
- Add new books to the library
- Remove books using ISBN
- Search books by title
- View all books currently in the library
- Full input validation (empty fields, negative numbers, invalid input)
- Handles duplicate ISBN entries gracefully

## Tech Stack
- Java
- JDBC
- MySQL

## How It Works
The application runs as a command-line menu. Users can add, remove, search, and view books, with all data stored and retrieved from a MySQL database in real time.

## Project Structure
- `Main.java` – Handles the CLI menu and user interaction
- `Book.java` – Represents a single book (model class)
- `LibraryDAO.java` – Handles all database operations (Add, Remove, Search, View)
- `DBConnection.java` – Manages the MySQL database connection

## Setup
1. Create a MySQL database named `library_db`
2. Create a `books` table with columns: `id`, `title`, `author`, `isbn`, `quantity`
3. Update the password in `DBConnection.java` with your local MySQL password
4. Run `Main.java`

## Author
Mohammad Jawadkhan Hakim
