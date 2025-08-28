package com.studely.gameoflife;

import java.util.Scanner;

/**
 * Main class for Conway's Game of Life.
 * Handles user interaction, input validation, and game execution.
 */
public class GameOfLife {
    private static final Scanner scanner = new Scanner(System.in);
    private static final int DELAY_MS = 1000; // 1 second delay between generations
    
    public static void main(String[] args) {
        System.out.println("=== Conway's Game of Life ===");
        System.out.println("Welcome to the cellular automaton simulation!\n");
        
        try {
            // Get initial pattern
            Grid grid = getInitialPattern();
            
            // Get number of generations
            int generations = getNumberOfGenerations();
            
            // Run the simulation
            runSimulation(grid, generations);
            
        } catch (Exception e) {
            System.err.println("An error occurred: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
    
    /**
     * Gets the initial pattern from the user
     * @return the initial grid
     */
    private static Grid getInitialPattern() {
        System.out.println("Choose your initial pattern:");
        System.out.println("1. Use default pattern (from test document)");
        System.out.println("2. Enter custom pattern manually");
        System.out.print("Enter your choice (1 or 2): ");
        
        String choice = scanner.nextLine().trim();
        
        if ("1".equals(choice)) {
            System.out.println("Using default pattern...\n");
            return new Grid();
        } else if ("2".equals(choice)) {
            return getCustomPattern();
        } else {
            System.out.println("Invalid choice. Using default pattern.\n");
            return new Grid();
        }
    }
    
    /**
     * Gets a custom pattern from the user
     * @return the custom grid
     */
    private static Grid getCustomPattern() {
        System.out.println("\nEnter your 5x5 pattern:");
        System.out.println("Use '1' for alive cells and '0' for dead cells");
        System.out.println("Enter each row as 5 characters (e.g., 00100):\n");
        
        boolean[][] pattern = new boolean[5][5];
        
        for (int i = 0; i < 5; i++) {
            boolean validInput = false;
            while (!validInput) {
                System.out.print("Row " + (i + 1) + ": ");
                String input = scanner.nextLine().trim();
                
                if (isValidPatternRow(input)) {
                    for (int j = 0; j < 5; j++) {
                        pattern[i][j] = input.charAt(j) == '1';
                    }
                    validInput = true;
                } else {
                    System.out.println("Invalid input. Please enter exactly 5 characters (0s and 1s only).");
                }
            }
        }
        
        System.out.println("\nCustom pattern accepted!\n");
        return new Grid(pattern);
    }
    
    /**
     * Validates a pattern row input
     * @param input the input string
     * @return true if the input is valid
     */
    private static boolean isValidPatternRow(String input) {
        return input != null && input.length() == 5 && input.matches("[01]+");
    }
    
    /**
     * Gets the number of generations from the user
     * @return the number of generations
     */
    private static int getNumberOfGenerations() {
        while (true) {
            System.out.print("How many generations to run? (1-50): ");
            String input = scanner.nextLine().trim();
            
            try {
                int generations = Integer.parseInt(input);
                if (generations > 0 && generations <= 50) {
                    return generations;
                } else {
                    System.out.println("Please enter a number between 1 and 50.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }
    
    /**
     * Runs the Game of Life simulation
     * @param initialGrid the initial grid state
     * @param generations the number of generations to run
     */
    private static void runSimulation(Grid initialGrid, int generations) {
        System.out.println("\nStarting simulation...\n");
        
        Grid currentGrid = initialGrid;
        
        for (int gen = 0; gen < generations; gen++) {
            // Display current generation
            currentGrid.display(gen);
            
            // Wait before next generation (except for the last one)
            if (gen < generations - 1) {
                try {
                    Thread.sleep(DELAY_MS);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
            
            // Calculate next generation
            currentGrid = currentGrid.nextGeneration();
        }
        
        System.out.println("Simulation completed!");
    }
}
