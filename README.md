# Spotify Desktop Management System

## Introduction

Spotify Desktop Management System is a Java Swing desktop application used to manage Spotify tracks and playlists efficiently.

The system supports CRUD operations, search & filtering, pagination, charts visualization, Excel export, role-based access, and MySQL database integration.

---

## Features

- Login Authentication
- CRUD Tracks Management
- Search & Filter
- Pagination
- Export Excel
- Charts Visualization
- Role-Based Access
- Concurrency with SwingWorker
- JUnit Testing

---

## Technologies

- Java Swing
- JDBC
- MySQL
- Apache POI
- JFreeChart
- JUnit 5

---

## Database

Tables used in the system:

- users
- artists
- albums
- tracks
- playlists
- playlist_tracks

---

## Project Structure

```text
src/
├── chart
├── dao
├── export
├── factory
├── main
├── model
├── service
├── test
├── ui
└── util
```

---

## Accounts

| Role   | Username | Password |
|--------|----------|----------|
| ADMIN  | admin    | 123      |
| STAFF  | staff    | 123      |
| VIEWER | viewer   | 123      |

---

## JUnit Testing

Implemented test cases:

- testFindAll()
- testPopularTracks()
- testSearchTracks()
- testPagination()
- testExportExcel()

---

## Concurrency

This project uses SwingWorker to load data in background threads without freezing the UI.

---

## Excel Export

Tracks can be exported to Excel using Apache POI.

Generated file:

```text
tracks.xlsx
```

---

## GitHub Repository

Public GitHub Repository:

https://github.com/nguyenthihuynhnhuk18-maker/spotify-management-system

---

## How to Run

1. Clone the repository
2. Open project in Eclipse IDE
3. Import MySQL database
4. Configure database connection
5. Run MainFrame.java

---

## Screenshots

### Main Interface

_Add screenshots here_

---

## Future Improvements

- Dark Mode UI
- Music Recommendation System
- REST API Integration
- User Activity Analytics

---

## Author

Nguyen Thi Huynh Nhu## Future Improvements

- Dark mode UI
- REST API integration
- Music recommendation system## Future Improvements

- Dark mode UI
- REST API integration
- Music recommendation system## Future Improvements

- Dark mode UI
- REST API integration
- Music recommendation system