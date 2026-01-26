# 🚀 Task Tracker CLI

A simple, command-line interface (CLI) task management application built in Java. It allows users to log and track their daily tasks with the ability to update their statuses in a straightforward manner.

## ✨ Features

- **Create Tasks:** Add new tasks with auto-incrementing IDs
- **Update Tasks:** Modify descriptions of existing tasks
- **Delete Tasks:** Permanently remove tasks
- **List Tasks:** List all tasks of filter by status: todo, in-progress, done
- **Update Status:** Update the status of existing tasks
- **Data Persistence:** Automatically saves and loads tasks so data is never lost

## 💻 Prerequisites

Ensure you have the `Java (JDK 17+)` and `Git` installed on your machine.

## 💻 How to Run

1. Clone the repository:
   
    ```
    git clone https://github.com/alayaaaa/Task-Tracker
    ```
    
2. Navigate to the project directory:
   
    ```
    cd Task-Tracker
    ```
    
3. Build and Run using Maven:
   
    ```
    .\mvnw.cmd clean compile exec:java -Dexec.mainClass="Application"
    ```

## 📖 Usage Example

```
# Adding a new task
add Buy Groceries
# Output: Task added successfully (ID: 1)

# Updating an existing task
update 1 Wash Dishes
# Output: Task updated successfully (ID: 1)
```

## 📂 Project Structure

The project follows a clean separation of concerns:
```
src
├── cli
│   └── TaskCLI.java
├── manager
│   └── TaskManager.java
├── repository
│   ├── LocalDateTimeAdapter.java
│   ├── TaskDAO.java
│   └── TaskDAOImpl.java
├── task
│   ├── Task.java
│   └── TaskStatus.java
└── Application.java
```

## 🛠️ Tech Stack

* **Language:** Java
* **Build Tool:** Maven
* **Libraries:** Gson

## 🚧 Future Improvements

- Sort tasks by Last Updated or Date Created
- Add priority levels
- Add an option to set deadline dates to tasks
