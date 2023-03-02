import java.util.Scanner;

public class Main {
    Scanner sc = new Scanner(System.in);

    // If want random grid change GRID_SIZE TO 0
    int GRID_SIZE = 10;
    int NUM_GENERATIONS = 0;
    int SPEED_MS = 0;
    private boolean[][] grid;

    /**
     *  In here have the parameters : Size of Grid, Number of generations and the Speed to print the generations
     */
    public Main() {
        System.out.println("The size of grid is default 10x10");

        System.out.println("Put number of generations: ");
        NUM_GENERATIONS = sc.nextInt();

        System.out.println("Put Speed to print in milliseconds: ");
        SPEED_MS = sc.nextInt();

        grid = new boolean[GRID_SIZE][GRID_SIZE];
    }

    // Class to initialize with the specified grid
    public void setInitialPopulation(boolean[][] initialPopulation) {
        for (int i = 0; i < initialPopulation.length; i++) {
            for (int j = 0; j < initialPopulation[i].length; j++) {
                grid[i][j] = initialPopulation[i][j];
            }
        }
    }

    // Class to initialize the random grid
    /* public void initializeGrid() {
        // randomly initialize the grid
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                grid[i][j] = Math.random() < 0.5;
            }
        }
    } */

    // Class to print the grid
    public void printGrid() {
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                System.out.print(grid[i][j] ? "X " : ". ");
            }
            System.out.println();
        }
    }

    public void runGame() throws InterruptedException {
        for (int generation = 0; generation < NUM_GENERATIONS; generation++) {
            System.out.println("Generation " + (generation + 1));
            printGrid();

            // for calculate the next generation
            boolean[][] nextGrid = new boolean[GRID_SIZE][GRID_SIZE];
            for (int i = 0; i < GRID_SIZE; i++) {
                for (int j = 0; j < GRID_SIZE; j++) {
                    int numNeighbors = countNeighbors(i, j);
                    if (grid[i][j]) {
                        // if cell is alive
                        if (numNeighbors == 2 || numNeighbors == 3) {
                            nextGrid[i][j] = true;
                        }
                    } else {
                        // if cell is dead
                        if (numNeighbors == 3) {
                            nextGrid[i][j] = true;
                        }
                    }
                }
            }

            // to update the grid
            grid = nextGrid;

            // to wait for the specified time before displaying the next generation
            Thread.sleep(SPEED_MS);
        }
    }

    // Class to the rules of the game
    private int countNeighbors(int x, int y) {
        int count = 0;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                int neighborX = x + i;
                int neighborY = y + j;
                if (neighborX >= 0 && neighborX < GRID_SIZE && neighborY >= 0 && neighborY < GRID_SIZE && !(i == 0 && j == 0)) {
                    if (grid[neighborX][neighborY]) {
                        count++;
                    }
                }
            }
        }
        return count;
    }

    public static void main(String[] args) throws InterruptedException {
        Main main = new Main();
        // Set the initial population here to specified grid
        boolean[][] initialPopulation = {
                {false, false, false, false, false, false, false, false, false, false},
                {false, false, false, false, false, false, false, false, false, false},
                {true, false, true, false, true, false, false, false, false, false},
                {true, false, true, false, false, false, false, false, false, false},
                {false, true, false, false, false, false, false, false, false, false},
                {false, false, false, false, false, false, false, false, false, false},
                {false, false, false, false, false, false, false, false, false, false},
                {false, false, false, false, false, false, false, false, false, false},
                {false, false, false, false, false, false, false, false, false, false},
                {false, false, false, false, false, false, false, false, false, false}
        };
        // to specified grid
        main.setInitialPopulation(initialPopulation);

        // to random grid
        // main.initializeGrid();

        main.runGame();
    }
}

