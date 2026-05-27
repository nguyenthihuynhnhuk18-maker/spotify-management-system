# Spotify Desktop Management System

## Introduction

Spotify Desktop Management System is a Java Swing desktop application developed for managing Spotify tracks, playlists, artists, and albums efficiently.

This project was developed for the Object-Oriented Programming Final Exam (ITE23005) at The Saigon International University.

The application demonstrates modern Java programming practices, Object-Oriented Programming principles, JDBC database integration, data visualization, concurrency, Excel export, and role-based authentication.

---

# Project Objectives

The objectives of this project are:

- Apply Object-Oriented Programming concepts in Java
- Build a desktop management application using Java Swing
- Connect and manage MySQL databases using JDBC
- Implement CRUD operations
- Visualize data using charts
- Apply Design Patterns
- Implement authentication and role-based access
- Use modern Java features
- Perform unit testing using JUnit 5

---

# Features

## Authentication & Authorization

- Login / Logout system
- SHA-256 password hashing
- Session management using HashMap
- Role-based access control
- Roles:
  - ADMIN
  - STAFF
  - VIEWER

---

## Track Management

- Add tracks
- Update tracks
- Delete tracks
- View tracks
- Search tracks
- Filter tracks
- Pagination support

---

## Playlist Management

- Create playlists
- Add tracks to playlists
- Delete playlists

---

## Data Visualization

Implemented charts using JFreeChart:

- Genre Distribution Pie Chart
- Popularity Bar Chart

Charts update dynamically from database queries.

---

## Excel Export

Export JTable data to Excel using Apache POI.

Generated output file:

```text
tracks.xlsx
```

---

## Concurrency

Implemented SwingWorker to load data in background threads without freezing the UI.

---

## Unit Testing

JUnit 5 test cases implemented for:

- DAO methods
- Pagination
- Search functionality
- Export functionality
- Validation logic

---

# Technologies Used

| Technology | Purpose |
|---|---|
| Java Swing | Desktop UI |
| JDBC | Database connection |
| MySQL | Database |
| Apache POI | Excel export |
| JFreeChart | Data visualization |
| JUnit 5 | Unit testing |
| SwingWorker | Concurrency |
| GitHub | Version control |

---

# Modern Java Features

This project uses several modern Java features:

- Java Records
- Stream API
- Optional<T>
- Enhanced Switch Expressions
- var keyword
- Lambda Expressions

Example:

```java
record UserDTO(int id, String username, String role) {}
```

---

# Design Patterns

The following design patterns were implemented:

| Pattern | Purpose |
|---|---|
| Singleton | Database connection management |
| DAO Pattern | Database abstraction |
| Factory Pattern | DAO object creation |
| MVC Architecture | UI separation |

---

# Database Design

## Database Name

```sql
spotify_management
```

---

## Tables

The system contains the following tables:

- users
- artists
- albums
- tracks
- playlists
- playlist_tracks

---

## Relationships

- artists → albums
- albums → tracks
- users → playlists
- playlists → playlist_tracks
- tracks → playlist_tracks

---

# Database Import Instructions

## Step 1 — Open MySQL Workbench

Launch MySQL Workbench and connect to your local MySQL server.

---

## Step 2 — Create Database

Execute the following SQL:

```sql
CREATE DATABASE spotify_management;
USE spotify_management;
```

---

## Step 3 — Import SQL File

1. Open file:

```text
mysqlSpotify.sql
```

2. Execute all SQL scripts.

3. Verify all tables are created successfully.

---

# Project Structure

```text
Spotify-Management-System/
│
├── database/
│   ├── mysqlSpotify.sql
│   └── ERD.png
│
├── report/
│   └── ProjectReport.pdf
│
├── presentation/
│   └── Slides.pptx
│
├── src/
│   ├── chart/
│   ├── dao/
│   ├── export/
│   ├── factory/
│   ├── main/
│   ├── model/
│   ├── service/
│   ├── test/
│   ├── ui/
│   └── util/
│
├── screenshots/
│
├── README.md
│
└── pom.xml
```

---

# User Accounts

| Role | Username | Password |
|---|---|---|
| ADMIN | admin | 123 |
| STAFF | staff | 123 |
| VIEWER | viewer | 123 |

---

# How to Run the Project

## Requirements

- Java JDK 17+
- MySQL 8+
- Eclipse IDE or IntelliJ IDEA
- Maven (optional)

---

## Setup Steps

### Step 1 — Clone Repository

```bash
git clone https://github.com/nguyenthihuynhnhuk18-maker/spotify-management-system
```

---

### Step 2 — Open Project

Import the project into Eclipse IDE or IntelliJ IDEA.

---

### Step 3 — Configure Database

Open:

```text
DatabaseConnection.java
```

Update:

```java
url
username
password
```

according to your MySQL configuration.

---

### Step 4 — Run Application

Run:

```text
MainFrame.java
```

---

# Screenshots

## Login Screen

_Add login screenshot here_

---

## Main Dashboard

_Add dashboard screenshot here_

---

## Tracks Management

_Add tracks management screenshot here_

---

## Charts Visualization

_Add charts screenshot here_

---

## Excel Export

_Add export screenshot here_

---

# JUnit Testing

Implemented test methods:

- testFindAll()
- testFindById()
- testSearchTracks()
- testPagination()
- testPopularTracks()
- testExportExcel()

---

# Test Coverage

_Add JUnit coverage screenshot here_

---

# Concurrency

This project uses SwingWorker for background processing.

Example operations:

- Loading tracks
- Refreshing charts
- Exporting Excel files

This prevents the UI from freezing during long-running tasks.

---

# GitHub Repository

GitHub Repository:

https://github.com/nguyenthihuynhnhuk18-maker/spotify-management-system

---

# Future Improvements

Future enhancements planned:

- Dark Mode UI
- REST API integration
- Music recommendation system
- Analytics dashboard
- Real-time Spotify API integration
- PDF report export

---

# Challenges & Lessons Learned

During this project, several challenges were encountered:

- Designing relational database structures
- Managing JDBC connections
- Handling Swing UI complexity
- Implementing charts dynamically
- Managing concurrency with SwingWorker

This project improved understanding of:

- OOP principles
- Java Swing development
- Database management
- Software architecture
- Testing and debugging

---

# Student Information

| Information | Details |
|---|---|
| Student Name | Nguyen Thi Huynh Nhu |
| Course | ITE23005 Object-Oriented Programming |
| University | The Saigon International University |
| Semester | HK252 |
| Academic Year | 2025 – 2026 |

---

# References

- Java Documentation
- MySQL Documentation
- Apache POI Documentation
- JFreeChart Documentation
- JUnit 5 Documentation
- Oracle JDBC Documentation

---

# Author

Nguyen Thi Huynh Nhu

SIU – Faculty of Engineering & Computer Science