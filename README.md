# 📦 TodoApp - A Clean Architecture Compose Multiplatform Project

![Kotlin](https://img.shields.io/badge/Kotlin-Multiplatform-orange.svg?style=flat-square)
![Jetpack Compose](https://img.shields.io/badge/Jetpack%20Compose-%F0%9F%8C%88-blue)
![Koin](https://img.shields.io/badge/Koin-DI-brightgreen)
![Room](https://img.shields.io/badge/Room-Database-blue)
![Multiplatform](https://img.shields.io/badge/Multiplatform-Android%20%7C%20iOS-blueviolet)

🚀 **TodoApp** is a **Compose Multiplatform** application built using **Clean Architecture** and **Jetpack Compose**. It supports **Android & iOS** while following **modern best practices** for UI, state management, and dependency injection. The app provides a modularized architecture ensuring scalability, testability, and maintainability.

---

## 🔥 **Technologies Used**
- **Compose Multiplatform (KMP)** - Enables shared business logic for Android & iOS with shared UI
- **Jetpack Compose** - Google’s modern UI toolkit
- **Koin Dependency Injection** - DI for shared & platform-specific modules
- **Room Database** - Local data persistence
- **Navigation Compose** - Type-safe navigation for Compose
- **Coroutines & Flows** - Asynchronous programming for smooth execution
- **Serialization** - Uses `kotlinx.serialization` for JSON handling

---

## 🏗 **Project Structure**
The project follows **Clean Architecture** with modularization to separate concerns effectively.

```plaintext
TodoApp
├── core              # Core utilities and shared logic
│   ├── common        # Provides shared utilities
│   ├── ui            # Contains reusable UI components and theme definitions.
│
├── feature           # Feature-based modularization (Independent screens)
│   ├── home          # Home Screen Independent feature modules for managing UI & ViewModels
│   ├── taskeditor    # Task Editor Screen Independent feature modules for managing UI & ViewModels
│
├── data              # Data layer (Repositories, Database handling)
├── domain            # Domain layer (Use Cases, Business Logic)
├── navigation        # Navigation module (Type-safe routing)
├── composeApp        # Entry point for Jetpack Compose UI
├── shared            # Shared module for resources (Android & iOS)
```
---

### 🎯 **Why This Modularization?**
✅ **Separation of Concerns** - UI, Data, and Business Logic remain independent.  
✅ **Scalability** - New features can be added without breaking existing code.  
✅ **Improved Maintainability** - Well-structured layers simplify debugging.  
✅ **Kotlin Multiplatform Ready** - Shared code for Android & iOS.  

