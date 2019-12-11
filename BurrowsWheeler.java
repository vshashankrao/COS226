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

import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.BinaryStdOut;
import edu.princeton.cs.algs4.StdOut;

public class BurrowsWheeler {

    // Constant representing the ASCII characters size
    private static final int R = 256;


    /**
     * The transform method applies the Burrows-Wheeler transform, reading from standard input and
     * writing to standard output
     *
     * @param None
     * @return None.
     */
    public static void transform() {
        String in = BinaryStdIn.readString();
        CircularSuffixArray start = new CircularSuffixArray(in);
        for (int i = 0; i < start.length(); i++) {
            if (start.index(i) == 0) {
                BinaryStdOut.write(i);
                break;
            }
        }
        for (int i = 0; i < start.length(); i++) {
            BinaryStdOut.write(in.charAt((start.index(i) + start.length() - 1) % start.length()));
        }
        BinaryStdOut.close();

    }

    /**
     * The inverseTransform method applies the Burrows-Wheeler inverseTransform, reading from
     * standard input and writing to standard output
     *
     * @param None
     * @return None.
     */
    public static void inverseTransform() {
        int in = BinaryStdIn.readInt();
        String asciIn = BinaryStdIn.readString();
        char[] t = new char[asciIn.length()];
        int[] indexArr = new int[asciIn.length()];
        int[] counts = new int[R];
        int[] next = new int[asciIn.length()];
        int[] diff = new int[R];
        int counter = 0;
        int prev = in;
        int comparator = asciIn.length() - 1;
        for (int i = 0; i < asciIn.length(); i++) {
            char aChar = asciIn.charAt(i);
            t[i] = aChar;
            indexArr[i] = counts[aChar];
            counts[aChar] += 1;
        }

        for (int i = 1; i < R; i++) {
            counter += counts[i - 1];
            diff[i] = counter;
        }
        while (comparator >= 0) {
            next[comparator] = prev;
            char x = t[prev];
            prev = diff[x] + indexArr[prev];
            comparator -= 1;
        }


        for (int i = 0; i < asciIn.length(); i++) {
            BinaryStdOut.write(t[next[i]]);
        }
        BinaryStdOut.close();
    }

    /**
     * The main method tests all of the functions in the MoveToFront class.
     *
     * @param String array for all of the arguments.
     * @return none.
     */
    public static void main(String[] args) {
        if (args[0].equals("+")) {
            inverseTransform();
        }
        else if (args[0].equals("-")) {
            transform();
        }
        else {
            StdOut.println("Please type '+' or '-' for the first argument");
        }

    }

}
