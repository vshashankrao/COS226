/* *****************************************************************************
 *  Name:    Shashank Rao
 *  NetID:   aturing
 *  Precept: P00
 *
 *  Description:  Prints 'Hello, World' to the terminal window.
 *                By tradition, this is everyone's first program.
 *                Prof. Brian Kernighan initiated this tradition in 1974.
 *
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    // Instance variable grid which stores the boolean values of the main grid in a 2d array
    private boolean[][] grid;
    // Instance variable size which stores the size of the grid in the form of an int
    private int size;
    // Instance variable search which stores the data from the Weighted Quick Union Find algorithm
    private WeightedQuickUnionUF search;
    // Instance variable that the number of available cells in the system
    private int availableCells;
    // Instance variable that represents the end of the grid
    private int end;

    /**
     * The Percolation constructor takes in a size n for input, and creates a Percolation object
     * with an n-by-n grid, with all sites initially blocked
     *
     * @param Integer n.
     * @return A empty Percolation object.
     */
    public Percolation(int n) {
        if (n <= 0) {
            throw new java.lang.IllegalArgumentException("n must be greater than 0");
        }
        size = n;
        grid = new boolean[size + 1][size];
        for (int i = 0; i < n; i++) {
            grid[0][i] = true;
        }

        search = new WeightedQuickUnionUF((size * size) + size);
        for (int j = 0; j < size - 1; j++) {
            search.union(j, j + 1);
        }
        availableCells = 0;
        end = n * n + 1;


    }

    /**
     * The open method takes two integers row and column for input, and opens the site at coordinate
     * (row, col) if it is not open already in the Percolation grid
     *
     * @param Integer row, Integer col
     * @return None.
     */
    public void open(int row, int col) {
        if (row >= size || col >= size) {
            throw new java.lang.IllegalArgumentException("row or column is out of bounds");
        }
        if (!grid[row + 1][col]) {
            grid[row + 1][col] = true;
            availableCells += 1;
            // checks top cell
            if (row >= 0 && isOpen(row - 1, col)) {
                search.union(getSearchIndex(row, col), getSearchIndex(row - 1, col));
            }
            // checks bottom cell
            if (row < (size - 1) && isOpen(row + 1, col)) {
                search.union(getSearchIndex(row, col), getSearchIndex(row + 1, col));
            }
            // checks right cell
            if (col < (size - 1) && isOpen(row, col + 1)) {
                search.union(getSearchIndex(row, col), getSearchIndex(row, col + 1));
            }
            // checks left cell
            if (col > 0 && isOpen(row, col - 1)) {
                search.union(getSearchIndex(row, col), getSearchIndex(row, col - 1));
            }
        }

    }

    /**
     * The isOpen method takes two integers row and column for input, and checks if the site at
     * (row, col) is open in the Percolation grid
     *
     * @param Integer row, Integer col
     * @return boolean.
     */
    public boolean isOpen(int row, int col) {
        if (row >= size || col >= size) {
            throw new java.lang.IllegalArgumentException("row or column is out of bounds");
        }
        return grid[row + 1][col];
    }

    /**
     * The isFull method takes two integers row and column for input, and checks if the site at
     * (row, col) is full in the Percolation grid
     *
     * @param Integer row, Integer col
     * @return boolean.
     */
    public boolean isFull(int row, int col) {
        if (row >= size || col >= size) {
            throw new java.lang.IllegalArgumentException("row or column is out of bounds");
        }
        if (isOpen(row, col)) {
            if (search.connected(getSearchIndex(row, col), 0))
                return true;
            return false;
        }
        return false;
    }

    /**
     * The numberOfOpenSites method takes no input, and returns the number of open sites in the
     * Percolation grid
     *
     * @param none
     * @return Integer.
     */
    public int numberOfOpenSites() {
        return availableCells;
    }

    /**
     * The percolates method takes no input, and returns a boolean which represents whether or not
     * the Percolation grid percolates
     *
     * @param none
     * @return Boolean.
     */
    public boolean percolates() {
        for (int i = 0; i < size; i++) {
            if (isFull(size - 1, i)) {
                return true;
            }
        }
        return false;
    }

    /**
     * The private method getSearchIndex takes two integers x and y for input, and returns the
     * search index at the specified (x, y) location on the grid
     *
     * @param Integer x, Integer y
     * @return Integer.
     */
    private int getSearchIndex(int x, int y) {
        return ((x + 1) * size) + y;
    }

    /**
     * The main method tests all of the functions in the Percolation class.
     *
     * @param String array for all of the arguments.
     * @return none.
     */
    public static void main(String[] args) {
        Percolation testPerc = new Percolation(4);
        testPerc.open(2, 2);
        Boolean openBool = testPerc.isOpen(2, 2);
        Boolean fullBool = testPerc.isFull(2, 2);
        int numSites = testPerc.numberOfOpenSites();
        Boolean yesOrNo = testPerc.percolates();

        StdOut.println("The isOpen method should return true and returns " + openBool);
        StdOut.println("The isFull method should return false and returns " + fullBool);
        StdOut.println("The numSites method should 1 and returns " + numSites);
        StdOut.println("The percolates method should return false and returns " + yesOrNo);
    }

}
