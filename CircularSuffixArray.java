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

import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

public class CircularSuffixArray {

    /**
     * Instance variables
     * <p>
     * n represents the abstraction of a sorted array of the n circular suffixes indexArr is the
     * array that holds all of the indices
     */
    private int n;
    private int[] indexArr;

    /**
     * The private class Circular implements the Comparable<Circular> interface
     */
    private class Circular implements Comparable<Circular> {
        /**
         * Instance variables
         * <p>
         * aString represents the string being analyzed in the CircularSuffixArray count represents
         * the value of the distance from the original string
         */
        private String aString;
        private int count;

        /**
         * The Circular constructor takes in a String and Integer for input, and returns a Circular
         * object.
         *
         * @param String, Integer
         * @return Circular object
         */
        public Circular(String string, int number) {
            aString = string;
            count = number;
        }

        /**
         * The compareTo method takes in a Circular object for input, and returns an integer.
         * returns the comparison of two circular objects
         *
         * @param Circular circle
         * @return Integer representing comparison result
         */
        public int compareTo(Circular circle) {
            if (this == circle) {
                return 0;
            }
            for (int i = 0; i < n; i++) {
                if ((this.aString.charAt((this.count + i) % this.aString.length()))
                        > (circle.aString.charAt((circle.count + i) % circle.aString.length()))) {
                    return 1;
                }
                if ((this.aString.charAt((this.count + i) % this.aString.length()))
                        < (circle.aString.charAt((circle.count + i) % circle.aString.length()))) {
                    return -1;
                }
            }
            return 0;
        }


        /**
         * The toString method takes in no input, and returns a String representation of the
         * Circular object
         *
         * @param None
         * @return String representation
         */
        public String toString() {
            return aString.substring(count, aString.length()) + aString.substring(0, count);
        }
    }

    /**
     * The CircularSuffixArray constructor takes in a String for input, and returns a
     * CircularSuffixArray object with String s
     *
     * @param String
     * @return CircularSuffixArray object
     */
    public CircularSuffixArray(String s) {
        if (s == null) {
            throw new IllegalArgumentException("String s can't be null");
        }
        n = s.length();
        Circular[] subStr = new Circular[n];

        for (int i = 0; i < n; i++) {
            subStr[i] = new Circular(s, i);
        }
        Arrays.sort(subStr);
        indexArr = new int[n];
        for (int i = 0; i < n; i++) {
            indexArr[i] = subStr[i].count;
        }


    }

    /**
     * The length method takes no input, and simply returns an integer representing the length of
     * string s
     *
     * @param None
     * @return Integer.
     */
    public int length() {
        return n;
    }

    /**
     * The index method takes in an integer for input, and simply returns index of ith sorted
     * suffix
     *
     * @param Integer i
     * @return Integer.
     */
    public int index(int i) {
        if (i >= n || i < 0) {
            throw new IllegalArgumentException("index i has to between 0 and n - 1 inclusive");
        }
        return indexArr[i];
    }


    /**
     * The main method tests all of the functions in the CircuarSuffixArray class.
     *
     * @param String array for all of the arguments.
     * @return none.
     */
    public static void main(String[] args) {
        String testerStr = "RGBSCART";
        CircularSuffixArray tester = new CircularSuffixArray(testerStr);
        StdOut.println("index test: This should print 5 and it prints " + tester.index(0));
        StdOut.println("length test: This should print 8 and it prints " + tester.length());
    }
}
