# TaskMaster

TaskMaster is a simple yet powerful task management app built with Android's latest architecture components. It allows users to create, update, and manage their tasks seamlessly. This repository contains the source code for TaskMaster, showcasing how to implement a modern Android application with MVVM architecture, Room database, LiveData, and Coroutines.

## Features

- **Task Creation**: Users can add new tasks with a name, description, and priority.
- **Task Editing**: Users can update existing tasks.
- **Task Listing**: All tasks are displayed in a RecyclerView with real-time updates.
- **MVVM Architecture**: Separation of concerns with ViewModel and Repository patterns.
- **Room Database**: Local data storage using Room for handling tasks.
- **Coroutines**: Asynchronous operations for database interactions.

## Getting Started

### Prerequisites

- Android Studio
- Kotlin

### Installation

1. Clone the repository:
   
2. Open the project in Android Studio.
3. Build the project to download the required dependencies.

### Running the App

1. Connect an Android device or start an emulator.
2. Run the app from Android Studio.

## Project Structure

```
com.example.taskmaster
├── Adapter
│   └── TaskAdapter.kt
├── database
│   ├── Task.kt
│   ├── TaskDao.kt
│   ├── TaskDatabase.kt
│   └── TaskRepository.kt
├── TaskPageDataViewModel.kt
└── TaskListPage.kt
```

### Key Components

- **TaskListPage.kt**: The main activity displaying the list of tasks.
- **TaskPageDataViewModel.kt**: ViewModel for managing UI-related data.
- **TaskAdapter.kt**: RecyclerView Adapter for displaying tasks.
- **TaskDatabase.kt**: Room database instance.
- **TaskRepository.kt**: Repository for handling data operations.

## Usage

### Adding a Task

- Click on the add button to open the dialog.
- Enter task details (name, description, priority) and click "OK".

### Editing a Task

- Click on a task item to open the edit dialog.
- Update task details and click "OK".

## Contributing

Contributions are welcome! Please open an issue or submit a pull request.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

---
