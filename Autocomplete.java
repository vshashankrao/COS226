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

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Quick3way;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

public class Autocomplete {
    // Instance array aTerms which represents the
    private final Term[] aTerms;

    /**
     * The Autocomplete constructor takes a term array as input, and creates an empty Autocomplete
     * object of type term
     *
     * @param Term[]
     * @return An empty Autocomplete object.
     */
    public Autocomplete(Term[] terms) {
        if (terms == null || !nullChecker(terms)) {
            throw new java.lang.IllegalArgumentException("There are null terms in the array");
        }
        this.aTerms = new Term[terms.length];
        for (int i = 0; i < terms.length; i++) {
            this.aTerms[i] = terms[i];
        }
        Quick3way.sort(aTerms);
    }


    /**
     * The allMatches method takes a String as input, and returns a Term array. It Returns all terms
     * that start with the given prefix, in descending order of weight.
     *
     * @param String
     * @return Term[].
     */
    public Term[] allMatches(String prefix) {
        if (prefix == null) {
            throw new java.lang.IllegalArgumentException("The input is null");
        }
        int firstTerm = BinarySearchDeluxe
                .firstIndexOf(aTerms, new Term(prefix, 0), Term.byPrefixOrder(prefix.length()));
        if (firstTerm == -1) {
            return new Term[0];
        }

        int lastTerm = BinarySearchDeluxe
                .lastIndexOf(aTerms, new Term(prefix, 0), Term.byPrefixOrder(prefix.length()));
        Term[] output = new Term[lastTerm - firstTerm + 1];

        for (int i = 0; i < output.length; i++) {
            output[i] = aTerms[firstTerm];
            firstTerm += 1;
        }
        Arrays.sort(output, Term.byReverseWeightOrder());

        return output;


    }

    /**
     * The numberOfMatches method takes a String as input, and returns a Term array. It Returns the
     * number of terms that start with the given prefix.
     *
     * @param String
     * @return Integer.
     */
    public int numberOfMatches(String prefix) {
        if (prefix == null) {
            throw new java.lang.IllegalArgumentException("The input is null");
        }
        int firstTerm = BinarySearchDeluxe
                .firstIndexOf(aTerms, new Term(prefix, 0), Term.byPrefixOrder(prefix.length()));
        int lastTerm = BinarySearchDeluxe
                .lastIndexOf(aTerms, new Term(prefix, 0), Term.byPrefixOrder(prefix.length()));
        return lastTerm - firstTerm + 1;
    }

    /**
     * The private nullChecker method takes a Term array as input, and returns a boolean. It checks
     * to see if any of the terms in the inputted array are null
     *
     * @param Term[]
     * @return boolean.
     */
    private boolean nullChecker(Term[] checker) {
        for (int i = 0; i < checker.length; i++) {
            if (checker[i] == null) {
                return false;
            }
        }
        return true;
    }

    /**
     * The main method tests all of the functions in the Autocomplete class.
     *
     * @param String array for all of the arguments.
     * @return none.
     */
    public static void main(String[] args) {
        /*Term[] testArr = {
                new Term("Hello", 3), new Term("abcdef", 5), new Term("term", 8),
                new Term("m550", 4)
        };
        Autocomplete testAuto = new Autocomplete(testArr);
        StdOut.println(testAuto.numberOfMatches("e"));
        Term[] allTest = testAuto.allMatches("a");
        for (int i = 0; i < allTest.length; i++) {
            StdOut.println(allTest[i]);
        }*/
        // read in the terms from a file
        String filename = args[0];
        In in = new In(filename);
        int n = in.readInt();
        Term[] terms = new Term[n];
        for (int i = 0; i < n; i++) {
            long weight = in.readLong();           // read the next weight
            in.readChar();                         // scan past the tab
            String query = in.readLine();          // read the next query
            terms[i] = new Term(query, weight);    // construct the term
        }

        // read in queries from standard input and print out the top k matching terms
        int k = Integer.parseInt(args[1]);
        Autocomplete autocomplete = new Autocomplete(terms);
        while (StdIn.hasNextLine()) {
            String prefix = StdIn.readLine();
            Term[] results = autocomplete.allMatches(prefix);
            StdOut.printf("%d matches\n", autocomplete.numberOfMatches(prefix));
            for (int i = 0; i < Math.min(k, results.length); i++)
                StdOut.println(results[i]);
        }

    }
}
