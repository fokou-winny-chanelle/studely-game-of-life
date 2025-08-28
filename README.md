# Conway's Game of Life

A Java implementation of Conway's Game of Life, a cellular automaton simulation that demonstrates emergent behavior from simple rules.

## Overview

Conway's Game of Life is a mathematical simulation where the state of each cell in a grid is determined by the state of its neighbors according to specific rules. This implementation features:

- **5x5 fixed-size grid** as specified in the requirements
- **Console-based interface** with Unicode display
- **User-friendly input validation**
- **Comprehensive unit tests**
- **Professional code documentation**

## Game Rules

The simulation follows Conway's original rules:

1. **Survival**: Any live cell with 2 or 3 live neighbors survives to the next generation
2. **Birth**: Any dead cell with exactly 3 live neighbors becomes alive
3. **Death**: All other cells die or remain dead

## Features

- **Default Pattern**: Pre-configured pattern from the test document
- **Custom Patterns**: Manual input of 5x5 patterns using 0s and 1s
- **Generation Control**: User-specified number of generations (1-50)
- **Visual Display**: Unicode symbols (█ for alive, ░ for dead)
- **Fixed Delays**: 1-second pause between generations for better visualization
- **Input Validation**: Robust error handling for user inputs

## Requirements

- **Java 17** or higher
- **Maven 3.6** or higher

## Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/your-username/studely-game-of-life.git
   cd studely-game-of-life
   ```

2. Build the project:
   ```bash
   mvn clean package
   ```

## Usage

### Running the Application

```bash
java -jar target/game-of-life-1.0.0.jar
```

### Running Tests

```bash
mvn test
```

### Building the Project

```bash
mvn clean compile
```

## Project Structure

```
studely-game-of-life/
├── src/
│   ├── main/java/com/studely/gameoflife/
│   │   ├── GameOfLife.java      # Main application class
│   │   └── Grid.java            # Grid logic and game rules
│   └── test/java/com/studely/gameoflife/
│       └── GridTest.java        # Unit tests
├── pom.xml                      # Maven configuration
└── README.md                    # This file
```

## Class Documentation

### Grid Class

The `Grid` class manages the 5x5 game board and implements the core Game of Life logic:

- **Grid Management**: Cell state tracking and boundary handling
- **Neighbor Counting**: Efficient neighbor calculation with fixed boundaries
- **Rule Application**: Implementation of Conway's Game of Life rules
- **Generation Evolution**: Creation of next generation grids
- **Display**: Console output with Unicode symbols

### GameOfLife Class

The main application class handles user interaction and simulation orchestration:

- **User Interface**: Pattern selection and generation input
- **Input Validation**: Robust error handling for user inputs
- **Simulation Control**: Generation execution with timing
- **Error Handling**: Graceful exception management

## Testing

The project includes comprehensive unit tests covering:

- Grid initialization and pattern setting
- Neighbor counting accuracy
- Game rule application
- Boundary condition handling
- Pattern evolution (blinker, still life)
- Input validation
- Grid equality comparison

Run tests with:
```bash
mvn test
```

## Example Output

```
=== Conway's Game of Life ===
Welcome to the cellular automaton simulation!

Choose your initial pattern:
1. Use default pattern (from test document)
2. Enter custom pattern manually
Enter your choice (1 or 2): 1
Using default pattern...

How many generations to run? (1-50): 5

Starting simulation...

Generation 0:
░░█░░
░█░█░
░░█░░
░░░░░
░░░░░

Generation 1:
░░█░░
░█░█░
░░█░░
░░░░░
░░░░░

...

Simulation completed!
```

## Technical Details

- **Language**: Java 
- **Build Tool**: Maven
- **Testing Framework**: JUnit 5
- **Display**: Unicode console output
- **Architecture**: Object-oriented with clear separation of concerns
