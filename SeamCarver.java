/* *****************************************************************************
 *  Name:    Shashank Rao
 *  NetID:   aturing
 *  Precept: P00
 *
 *  Partner Name:    Ada Lovelace
 *  Partner NetID:   alovelace
 *  Partner Precept: P00
 *
 *  Description:  Prints 'Hello, World' to the terminal window.
 *                By tradition, this is everyone's first program.
 *                Prof. Brian Kernighan initiated this tradition in 1974.
 *
 **************************************************************************** */

import edu.princeton.cs.algs4.Picture;
import edu.princeton.cs.algs4.StdOut;

import java.awt.Color;

public class SeamCarver {

    /**
     * Instance Variables
     *
     * aPicture representing the main picture being analyzed in the SeamCarver class
     * RGBArr represents the RGB values of each pixel in the array
     */
    private Picture aPicture;
    private int[][][] RGBArr;

    /**
     * The SeamCarver constructor takes in a picture for input, and creates an empty seam carver
     * object based on the given picture
     *
     * @param None
     * @return A empty SeamCarver object.
     */
    public SeamCarver(Picture picture) {
        aPicture = picture;
        RGBArr = new int[aPicture.width()][aPicture.height()][3];
        for (int i = 0; i < aPicture.width(); i++) {
            for (int j = 0; j < aPicture.height(); j++) {
                Color color = aPicture.get(i, j);
                RGBArr[i][j][0] = color.getRed();
                RGBArr[i][j][1] = color.getGreen();
                RGBArr[i][j][2] = color.getBlue();
            }
        }

    }

    /**
     * The picture method takes  no input and returns a picture object representing the current
     * picture
     *
     * @param None
     * @return Picture
     */
    public Picture picture() {
        return aPicture;
    }

    /**
     * The width method takes  no input and returns an int representing the width of the current
     * picture
     *
     * @param None
     * @return Integer
     */
    public int width() {
        return aPicture.width();
    }

    /**
     * The height method takes  no input and returns an int representing the height of the current
     * picture
     *
     * @param None
     * @return Integer
     */
    public int height() {
        return aPicture.height();
    }

    /**
     * The energy method takes in two Integers for input and returns an double representing the
     * calculated energy of the pixel at column x and row y
     *
     * @param Integer x, y
     * @return double
     */
    public double energy(int x, int y) {
        if (x >= aPicture.width() || y >= aPicture.height()) {
            throw new IllegalArgumentException("The x or y is out of bounds");
        }
        double output = 0;
        int left = (x + aPicture.width() - 1) % aPicture.width();
        int right = (x + 1) % aPicture.width();
        int top = (y + aPicture.height() - 1) % aPicture.height();
        int bottom = (y + 1) % aPicture.height();

        int[] lArr = RGBArr[left][y];
        int[] rArr = RGBArr[right][y];
        int[] tArr = RGBArr[x][top];
        int[] bArr = RGBArr[x][bottom];

        double xGradSq = Math.pow(lArr[0] - rArr[0], 2);
        double yGradSq = Math.pow(tArr[0] - bArr[0], 2);
        output = Math.sqrt(xGradSq + yGradSq);
        return output;
    }

    /**
     * The findHorizontalSeam method takes no input and returns an array of type integer
     * representing the sequence of indices for horizontal seam
     *
     * @param None
     * @return int[]
     */
    public int[] findHorizontalSeam() {
        int[] output = findSeam(false);
        return output;
    }

    /**
     * The findVerticalSeam method takes no input and returns an array of type integer representing
     * the sequence of indices for vertical seam
     *
     * @param None
     * @return Integer[]
     */
    public int[] findVerticalSeam() {
        int[] output = findSeam(true);
        return output;
    }

    /**
     * The removeHorizontalSeam method takes an array of type integer for input and returns nothing.
     * It removes horizontal seam from current picture
     *
     * @param Integer[]
     * @return None
     */
    public void removeHorizontalSeam(int[] seam) {
        if (aPicture.height() == 1) {
            throw new IllegalArgumentException("The Height of the picture is 1");
        }
        int width = aPicture.width();
        int height = aPicture.height();
        Picture output = new Picture(width, height - 1);
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (seam[j] == i) {
                    continue;
                }

                if (i < seam[j]) {
                    output.set(j, i, aPicture.get(j, i));
                }
                else {
                    output.set(j, i - 1, aPicture.get(j, i));
                    RGBArr[j][i - 1] = RGBArr[j][i];
                }


            }
        }

        aPicture = output;
    }

    /**
     * The removeVerticalSeam method takes an array of type integer for input and returns nothing.
     * It removes vertical seam from current picture
     *
     * @param Integer[]
     * @return None
     */
    public void removeVerticalSeam(int[] seam) {
        if (aPicture.width() == 1) {
            throw new IllegalArgumentException("The Width of the picture is 1");
        }
        int width = aPicture.width();
        int height = aPicture.height();
        Picture output = new Picture(width - 1, height);
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (seam[i] == j) {
                    continue;
                }

                if (j < seam[i]) {
                    output.set(j, i, aPicture.get(j, i));
                }
                else {
                    output.set(j - 1, i, aPicture.get(j, i));
                    RGBArr[j - 1][i] = RGBArr[j][i];
                }


            }
        }

        aPicture = output;
    }
    /**
     * The private method findSeam takes in a boolean for input and returns an array of type integer representing
     * the sequence of indices for the seam
     *
     * @param boolean
     * @return Integer[]
     */
    private int[] findSeam(boolean isVertical) {

        int width = aPicture.width();
        int height = aPicture.height();
        double[][] energyArr = new double[aPicture.width()][aPicture.height()];

        for (int i = 0; i < aPicture.width(); i++) {
            for (int j = 0; j < aPicture.height(); j++) {
                energyArr[i][j] = energy(i, j);
            }
        }
        if (!isVertical) {
            double[][] tempArr = new double[height][width];
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    tempArr[i][j] = energyArr[j][i];
                }
            }
            energyArr = tempArr;
            width = height();
            height = width();
        }


        double[][] shortestDist = new double[width][height];
        int[][] shortestPath = new int[width][height];


        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (j == 0) {
                    shortestDist[i][j] = energyArr[i][j];
                }
                else {
                    shortestDist[i][j] = Double.MAX_VALUE;
                }
            }
        }


        for (int row = 1; row < height; row++) {
            for (int col = 0; col < width; col++) {

                double minEnergy = shortestDist[col][row - 1];
                int column = col;


                if (col != width - 1) {
                    if (shortestDist[col + 1][row - 1] < minEnergy) {
                        minEnergy = shortestDist[col + 1][row - 1];
                        column = col + 1;
                    }
                }


                if (col != 0) {
                    if (shortestDist[col - 1][row - 1] < minEnergy) {
                        minEnergy = shortestDist[col - 1][row - 1];
                        column = col - 1;
                    }
                }

                if (shortestDist[col][row] > (energyArr[col][row] + minEnergy)) {
                    shortestDist[col][row] = energyArr[col][row] + minEnergy;
                    shortestPath[col][row] = column;
                }
            }

        }


        double min = Double.MAX_VALUE;
        int minInd = 0;
        for (int col = 0; col < width; col++) {
            if (shortestDist[col][height - 1] < min) {
                min = shortestDist[col][height - 1];
                minInd = col;
            }
        }

        int[] seam = new int[height];
        for (int row = height - 1; row >= 0; row--) {
            seam[row] = minInd;
            minInd = shortestPath[minInd][row];
        }

        return seam;
    }

    /**
     * The main method tests all of the functions in the PointST class.
     *
     * @param String array for all of the arguments.
     * @return none.
     */
    public static void main(String[] args) {
        String[] singleTestArr = new String[1];
        singleTestArr[0] = "diagonals.png";
        String[] testArr = new String[3];
        testArr[0] = "diagonals.png";
        testArr[1] = "3";
        testArr[2] = "3";

        StdOut.println("The following test clients call all the methods in the SeamCarver class");

        PrintEnergy.main(singleTestArr);
        ShowSeams.main(singleTestArr);
        ResizeDemo.main(testArr);

    }

}
