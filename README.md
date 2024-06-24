# Assignment-EITC du

## Overview
The project is an Android application written in Kotlin that adheres to MVVM architecture with Clean Architecture principles. It utilizes various libraries and tools for efficient development and testing.

## Key Components and Technologies Used
1. **Kotlin**: Primary programming language used for Android app development.
2. **MVVM Architecture**: Model-View-ViewModel architectural pattern for separation of concerns and maintainability.
3. **Dagger**: Dependency injection framework for managing dependencies and promoting modularization.
4. **Retrofit with Gson**: Networking library for making HTTP requests and handling JSON responses.
5. **RxJava and LiveData**: Reactive programming components for asynchronous data streams and UI updates.
6. **DataBinding**: Android Jetpack library used for declaratively binding UI components in XML layouts to data sources.
7. **Binding Collection Adapter**: Simplifies RecyclerView setup by binding collections to item views.
8. **Room Database**: Android's SQLite object mapping library used for offline storage of posts and favorite posts.
9. **Mockito**: Mocking framework for writing unit tests and mocking dependencies.
10. **MockWebServer**: Library for mocking HTTP server responses to simulate network operations during testing.

## What is Clean Architecture?

Clean Architecture is a software design approach that emphasizes separation of concerns, testability, and independence from external frameworks. It structures applications into layers with clear responsibilities and boundaries, ensuring flexibility, maintainability, and scalability over time.

## Screens Overview

### Screen1: Login Form
1. Contains two text fields: one for email and another for password.
2. Validates that the email is a valid email address.
3. Validates that the password length is between 8 to 15 characters.
4. Enables the submit button only when both email and password are valid.
5. On clicking the submit button, navigates to the next screen without making any remote API calls.

### Screen2: Posts and Favorites Tabs
#### Posts Tab
1. Displays a list of posts fetched from a network API (`https://jsonplaceholder.typicode.com/posts`).
2. Provides offline availability of the list even without network connectivity.
3. Allows users to mark any post as favorite by clicking on it.

#### Favorites Tab
1. Lists all posts that have been marked as favorites by the user.
2. Provides persistent storage using Room Database to store favorite posts locally.

## Future Enhancements

### Enhanced UX/UI
- Navigation Component can be used to navigate between screens.
- Eliminate XML and Data binding with Jetpack Compose.
- Search within posts can be implemented.

### General
- Increase unit test coverage by writing test cases for remianing classes other than View Classes.
- Implementation of UI testing with **Espresso** to ensure robustness and reliability.
- Use of version control (e.g., Git) effectively with branching strategies and clear commit messages.
- Continuous integration (CI) pipelines can be implemented to automate builds, tests, and deployments.
- Implement logging mechanisms to track app events and debug issues efficiently.
- Ensuring that the app is accessible to users with disabilities by following accessibility guidelines.

## API References
- **Base URL**: `https://jsonplaceholder.typicode.com`
- **Posts Endpoint**: `/posts`