/* *****************************************************************************
 *  Name:    Shashank Rao
 *  NetID:   svr38
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
import java.util.Comparator;

public class Term implements Comparable<Term> {
    // Instance variable aQuery which represents the Query of the term object as a String
    private String aQuery;
    // Instance variable aWeight which represents the weight of the term object as a long
    private long aWeight;

    /**
     * The Term constructor takes a String and a long as input, and creates an empty object of type
     * term
     *
     * @param String, long
     * @return An empty Term object.
     */


    public Term(String query, long weight) {
        if (query == null) {
            throw new java.lang.IllegalArgumentException("Query is null");
        }
        if (weight < 0) {
            throw new java.lang.IllegalArgumentException("Weight is negative");
        }
        aQuery = query;
        aWeight = weight;
    }

    /**
     * The byReverseWeightOrder function takes no input, and returns a Comparator which Compares the
     * two terms in descending order by weight.
     *
     * @param None.
     * @return Comparator<Term>
     */
    public static Comparator<Term> byReverseWeightOrder() {
        return new ReverseWeightOrder();
    }

    /**
     * The byPrefixOrder function takes in an Integer for input, and returns a Comparator which
     * Compares the two terms in lexicographic order by weight but using only the first r characters
     * of each query.
     *
     * @param None.
     * @return Comparator<Term>
     */
    public static Comparator<Term> byPrefixOrder(int r) {
        if (r < 0) {
            throw new java.lang.IllegalArgumentException("r is negative");
        }
        return new PrefixOrder(r);
    }


    /**
     * The compareTo function takes in Term object for input, and returns an Integer which
     * represents the comparison of the two terms in lexicographic order by query.
     *
     * @param Term that.
     * @return Integer.
     */
    public int compareTo(Term that) {
        String x = aQuery;
        String y = that.aQuery;
        return x.compareTo(y);

    }

    /**
     * The toString function takes in no paramters, and returns the string representation of Term
     * object in the following format the weight, followed by a tab, followed by the query.
     *
     * @param None.
     * @return String representation of Term object.
     */
    public String toString() {
        return aWeight + "\t" + aQuery;
    }

    /**
     * The private class ReverseWeightOrder implements the logic of the byReverseWeightOrder
     * function
     */
    private static class ReverseWeightOrder implements Comparator<Term> {
        public int compare(Term x, Term y) {
            if (x.aWeight == y.aWeight) {
                return 0;
            }
            else if (x.aWeight > y.aWeight) {
                return -1;
            }
            else {
                return 1;
            }
        }
    }

    /**
     * The private class ReverseWeightOrder implements the logic of the byPrefixOrder function
     */
    private static class PrefixOrder implements Comparator<Term> {
        private int anR;

        public PrefixOrder(int input) {
            anR = input;
        }

        public int compare(Term x, Term y) {
            String first;
            String second;
            if (x.aQuery.length() < anR) {
                first = x.aQuery;
            }
            else {
                first = x.aQuery.substring(0, anR);
            }
            if (y.aQuery.length() < anR) {
                second = y.aQuery;
            }
            else {
                second = y.aQuery.substring(0, anR);
            }
            return first.compareTo(second);
        }
    }

    /**
     * The main method tests all of the functions in the Term class.
     *
     * @param String array for all of the arguments.
     * @return none.
     */
    public static void main(String[] args) {

        Term[] testerTerms = {
                new Term("Hello", 3), new Term("abcdef", 5), new Term("term", 8),
                new Term("m550", 4)
        };
        for (Term term : testerTerms) {
            StdOut.println(term);
        }
        StdOut.println();

        Arrays.sort(testerTerms, Term.byReverseWeightOrder());
        for (Term term : testerTerms) {
            StdOut.println(term);
        }
        StdOut.println();

        Arrays.sort(testerTerms, Term.byPrefixOrder(2));
        for (Term term : testerTerms) {
            StdOut.println(term);
        }
        StdOut.println();

        Arrays.sort(testerTerms);
        for (Term term : testerTerms) {
            StdOut.println(term);
        }
    }

}
