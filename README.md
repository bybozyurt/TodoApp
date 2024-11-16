# NoteApp

**NoteApp** is a simple, cross-platform note-taking app developed with **Kotlin Multiplatform (KMP)**. It allows users to create, manage, and organize their notes efficiently, with synchronization across multiple devices.

## Features

- **Create Notes**: Add new notes with a title and description.
- **Edit Notes**: Update the title or description of existing notes.
- **Delete Notes**: Remove unwanted notes.
- **Cross-Platform**: Built using Kotlin Multiplatform, the app works on both Android and iOS devices.

## Technologies Used

- **Kotlin Multiplatform (KMP)**: Share code between Android and iOS, enabling a cross-platform approach for building mobile apps.
- **Jetpack Compose**: A modern UI toolkit for building native Android UIs in a declarative style.
- **Room Database**: A local database for storing notes on Android. Provides an abstraction layer over SQLite to simplify database operations.
- **Voyager**: A modern, declarative navigation library for Kotlin Multiplatform, simplifying navigation logic across platforms.
- **Koin**: A lightweight dependency injection framework for Kotlin, used to manage dependencies in a clean and efficient manner across both Android and iOS.
- **KSP (Kotlin Symbol Processing)**: A tool for generating code using annotations in Kotlin, utilized to streamline tasks like creating database schemas and API clients.
- **Coroutines**: For handling asynchronous operations in a more readable and efficient manner.
- **Kotlin Flow**: A reactive programming library that helps manage state and data streams, enabling the app to respond to changes in data in real-time.

## License

This project is licensed under the MIT License.
