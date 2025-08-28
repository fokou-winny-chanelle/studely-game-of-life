package com.studely.gameoflife;

/**
 * Represents a 5x5 grid for Conway's Game of Life.
 * Manages the state of cells and provides methods for game logic.
 */
public class Grid {
    private static final int SIZE = 5;
    private boolean[][] cells;
    
    /**
     * ASCII symbols for displaying cells (compatible with all consoles)
     */
    public static final String ALIVE_CELL = "█";
    public static final String DEAD_CELL = "░";
    
    /**
     * Default pattern from the test document
     */
    private static final boolean[][] DEFAULT_PATTERN = {
        {false, false, true,  false, false},
        {false, true,  false, true,  false},
        {false, false, true,  false, false},
        {false, false, false, false, false},
        {false, false, false, false, false}
    };
    
    /**
     * Creates a new grid with the default pattern
     */
    public Grid() {
        this.cells = new boolean[SIZE][SIZE];
        setDefaultPattern();
    }
    
    /**
     * Creates a new grid with a custom pattern
     * @param pattern the initial pattern (must be 5x5)
     */
    public Grid(boolean[][] pattern) {
        if (pattern == null || pattern.length != SIZE || pattern[0].length != SIZE) {
            throw new IllegalArgumentException("Pattern must be 5x5");
        }
        this.cells = new boolean[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            System.arraycopy(pattern[i], 0, this.cells[i], 0, SIZE);
        }
    }
    
    /**
     * Sets the default pattern from the test document
     */
    private void setDefaultPattern() {
        for (int i = 0; i < SIZE; i++) {
            System.arraycopy(DEFAULT_PATTERN[i], 0, this.cells[i], 0, SIZE);
        }
    }
    
    /**
     * Gets the state of a cell at the specified position
     * @param row the row index (0-4)
     * @param col the column index (0-4)
     * @return true if the cell is alive, false if dead
     */
    public boolean getCell(int row, int col) {
        if (row < 0 || row >= SIZE || col < 0 || col >= SIZE) {
            return false; // Fixed boundaries - cells outside grid are dead
        }
        return cells[row][col];
    }
    
    /**
     * Sets the state of a cell at the specified position
     * @param row the row index (0-4)
     * @param col the column index (0-4)
     * @param alive true to make the cell alive, false to make it dead
     */
    public void setCell(int row, int col, boolean alive) {
        if (row >= 0 && row < SIZE && col >= 0 && col < SIZE) {
            cells[row][col] = alive;
        }
    }
    
    /**
     * Counts the number of live neighbors for a given cell
     * @param row the row index of the cell
     * @param col the column index of the cell
     * @return the number of live neighbors (0-8)
     */
    public int countNeighbors(int row, int col) {
        int count = 0;
        
        // Check all 8 neighbors
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                // Skip the cell itself
                if (i == 0 && j == 0) continue;
                
                // Check if neighbor is alive (using direct array access for efficiency)
                int neighborRow = row + i;
                int neighborCol = col + j;
                
                // Check bounds first
                if (neighborRow >= 0 && neighborRow < SIZE && neighborCol >= 0 && neighborCol < SIZE) {
                    if (cells[neighborRow][neighborCol]) {
                        count++;
                    }
                }
            }
        }
        
        return count;
    }
    
    /**
     * Applies the Game of Life rules to determine the next state of a cell
     * @param row the row index of the cell
     * @param col the column index of the cell
     * @return true if the cell will be alive in the next generation
     */
    public boolean getNextState(int row, int col) {
        boolean currentState = getCell(row, col);
        int neighbors = countNeighbors(row, col);
        
        // Rule 1: Any live cell with 2-3 neighbors survives
        if (currentState && (neighbors == 2 || neighbors == 3)) {
            return true;
        }
        
        // Rule 2: Any dead cell with exactly 3 neighbors becomes alive
        if (!currentState && neighbors == 3) {
            return true;
        }
        
        // Rule 3: All other cells die
        return false;
    }
    
    /**
     * Creates the next generation of the grid
     * @return a new Grid representing the next generation
     */
    public Grid nextGeneration() {
        boolean[][] nextCells = new boolean[SIZE][SIZE];
        
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                nextCells[i][j] = getNextState(i, j);
            }
        }
        
        return new Grid(nextCells);
    }
    
    /**
     * Displays the current grid state
     * @param generation the current generation number
     */
    public void display(int generation) {
        System.out.println("Generation " + generation + ":");
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                System.out.print(cells[i][j] ? ALIVE_CELL : DEAD_CELL);
            }
            System.out.println();
        }
        System.out.println();
    }
    
    /**
     * Checks if this grid is equal to another grid
     * @param other the grid to compare with
     * @return true if the grids are identical
     */
    public boolean equals(Grid other) {
        if (other == null) return false;
        
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (cells[i][j] != other.cells[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }
    
    /**
     * Gets the size of the grid
     * @return the grid size (5)
     */
    public static int getSize() {
        return SIZE;
    }
}
