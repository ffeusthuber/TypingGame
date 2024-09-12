<a id="readme-top"></a>

# Typing Game Application

<details>
  <summary>Table of Contents</summary>
  <ol>
    <li>
      <a href="#about-the-project">About The Project</a>
      <ul>
        <li><a href="#built-with">Built With</a></li>
      </ul>
    </li>
    <li>
      <a href="#getting-started">Getting Started</a>
      <ul>
        <li><a href="#prerequisites">Prerequisites</a></li>
        <li><a href="#setup">Setup</a></li>
        <li><a href="#running-tests">Running Tests</a></li>
      </ul>
    </li>
    <li><a href="#license">License</a></li>
  </ol>
</details>

<div style="text-align:center">
   <img src="src/main/resources/images/logo.png" alt="Logo" width="80" height="80">
</div>

## About the project

This project is a Java-based typing game application built with JavaFX. The main goal of the project was to practice several key software development principles and techniques, including:

- **Test-Driven Development (TDD)**: Guiding design by writing tests first before implementing functionality.
- **Object-Oriented Programming (OOP)**: Applying OOP principles for a modular and reusable codebase.
- **Hexagonal Architecture**: Applying the Hexagonal Architecture (Ports and Adapters) to ensure the separation of concerns and maintainable code.
- **JavaFX**: Building the user interface with the JavaFX framework.

In this game, the player must type words before they reach the bottom of the screen. The objective is to type for as long as possible without running out of lives.

<p style="text-align: right">(<a href="#readme-top">back to top</a>)</p>

### Built With
- **Java**: The core programming language.
- **JavaFX**: Framework for building the user interface
- **Maven**:  Dependency management and project structure.
- **JUnit**: Framework for writing and running unit tests.
- **Mockito**: Library for creating mock objects in tests.

<p style="text-align: right">(<a href="#readme-top">back to top</a>)</p>

## Getting Started

To get a local copy of the project up and running, follow these steps:

### Prerequisites

- **JDK 17** or higher
- **Maven 3.6.0** or higher

### Setup

1. Clone the repository:
    ```sh
    git clone https://github.com/ffeusthuber/typing-game.git
    cd TypingGame
    ```

2. Build the project using Maven:
    ```sh
    mvn clean install
    ```

3. Run the application:
    ```sh
    mvn javafx:run
    ```

### Running Tests

To run the tests and ensure the application functions as intended, use the following Maven command:
```sh
mvn test
```

<p style="text-align: right">(<a href="#readme-top">back to top</a>)</p>

## License

Distributed under the MIT License. See `LICENSE.txt` for more information.

## Attribution
<ul>
<li><a href="https://www.flaticon.com/free-icons/typing" title="typing icons">Typing icons created by Freepik - Flaticon</a></li>
</ul>

<p style="text-align: right">(<a href="#readme-top">back to top</a>)</p>

