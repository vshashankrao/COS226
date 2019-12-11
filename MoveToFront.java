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

import java.util.LinkedList;

public class MoveToFront {

    // Constant representing the ASCII characters size
    private static final int R = 256;

    /**
     * The encode method applies the move-to-front encoding, reading from standard input and writing
     * to standard output
     *
     * @param None
     * @return None.
     */
    public static void encode() {
        LinkedList<Character> vals = new LinkedList<Character>();
        for (int i = 0; i < R; i++) {
            vals.add((char) i);
        }

        while (!BinaryStdIn.isEmpty()) {
            char aChar = BinaryStdIn.readChar();
            int key = vals.indexOf(aChar);
            BinaryStdOut.write(key, 8);
            vals.remove(key);
            vals.addFirst(aChar);
        }

        BinaryStdOut.close();
    }

    /**
     * The decode method applies the move-to-front decoding, reading from standard input and writing
     * to standard output
     *
     * @param None
     * @return None.
     */
    public static void decode() {
        LinkedList<Character> vals = new LinkedList<Character>();
        for (int i = 0; i < R; i++) {
            vals.add((char) i);
        }

        while (!BinaryStdIn.isEmpty()) {
            char aChar = BinaryStdIn.readChar();
            char key = vals.get(aChar);
            BinaryStdOut.write(key);
            vals.remove(aChar);
            vals.addFirst(key);
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
            decode();
        }
        else if (args[0].equals("-")) {
            encode();
        }
        else {
            StdOut.println("Please type '+' or '-' for the first argument");
        }


    }


}
