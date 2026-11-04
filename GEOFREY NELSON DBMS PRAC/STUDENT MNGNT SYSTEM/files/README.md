# Student Management System (Java + MySQL)

A console-based Student Management System built with core Java (JDBC) and MySQL.
Demonstrates OOP principles (encapsulation, abstraction, polymorphism), the DAO
design pattern, and relational database CRUD operations via SQL.

## Project Structure

```
StudentManagementSystem/
├── database.sql                     # Creates the database, table, and sample data
├── db.properties                    # DB connection credentials (edit this)
└── src/
    ├── Student.java                 # Model / entity class
    ├── DBConnection.java            # Singleton JDBC connection handler
    ├── StudentDAO.java              # DAO interface (abstraction)
    ├── StudentDAOImpl.java          # DAO implementation (JDBC + SQL)
    └── StudentManagementSystem.java # Main class / console menu
```

## Features

- Add a new student
- View all students
- Search a student by ID
- Search students by name (partial match)
- Update an existing student's details
- Delete a student
- Uses `PreparedStatement` everywhere to prevent SQL injection

## Prerequisites

1. **Java JDK 17+** installed (`java -version` to check)
2. **MySQL Server** installed and running
3. **MySQL Connector/J** (JDBC driver) — download the `.jar` from:
   https://dev.mysql.com/downloads/connector/j/

## Setup Instructions

### 1. Create the database

Open a terminal (or MySQL Workbench) and run:

```bash
mysql -u root -p < database.sql
```

This creates the `student_management` database, the `students` table, and
inserts 3 sample rows.

### 2. Configure your credentials

Edit `db.properties` and set your MySQL username/password:

```properties
db.url=jdbc:mysql://localhost:3306/student_management
db.username=root
db.password=your_actual_password
```

### 3. Compile the project

From the `StudentManagementSystem` folder, with `mysql-connector-j-x.x.x.jar`
placed in a `lib/` folder next to `src/`:

```bash
javac -d bin src/*.java
```

### 4. Run the application

**Windows:**
```bash
java -cp "bin;lib/mysql-connector-j-9.0.0.jar" StudentManagementSystem
```

**Mac/Linux:**
```bash
java -cp "bin:lib/mysql-connector-j-9.0.0.jar" StudentManagementSystem
```

Make sure `db.properties` is in the same directory you run the `java` command from
(or adjust the path in `DBConnection.java`).

## Notes

- If you're using an IDE (IntelliJ / Eclipse / NetBeans), just add the
  MySQL Connector/J `.jar` to your project's build path/libraries instead of
  using the `-cp` flag manually.
- The `students` table auto-increments the `id` column, so you don't need to
  supply it when adding a new student.
- To reset all data, simply re-run `database.sql`.

## OOP & Database Concepts Applied

| Concept | Where it appears |
|---|---|
| Encapsulation | `Student.java` — private fields, public getters/setters |
| Abstraction | `StudentDAO.java` interface hides implementation details |
| Polymorphism | `StudentDAOImpl` can be swapped for another `StudentDAO` implementation |
| Singleton Pattern | `DBConnection.java` reuses a single DB connection |
| DAO Pattern | Separates database logic from business/UI logic |
| SQL / RDBMS | `database.sql` — table design, constraints, CRUD via JDBC |
