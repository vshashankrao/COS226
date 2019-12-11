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
import edu.princeton.cs.algs4.StdOut;

public class Outcast {
    /**
     * Instance Variables
     * <p>
     * aWordNet represents the WordNet object being analyzed
     */
    private WordNet aWordNet;

    /**
     * The WordNet constructor takes in a WordNet for input, and creates an empty Outcast object
     *
     * @param WordNet wordnet
     * @return A empty Outcast object.
     */
    public Outcast(WordNet wordnet) {
        aWordNet = wordnet;
    }

    /**
     * The outcast method takes a String array of WordNet nouns, return an outcast
     *
     * @param String array for all of the arguments.
     * @return String representing outcast.
     */
    public String outcast(String[] nouns) {
        int mDist = 0;
        int output = -1;
        for (int i = 0; i < nouns.length; i++) {
            int d = 0;
            for (String noun2 : nouns) {
                d += aWordNet.distance(nouns[i], noun2);
            }
            if (d > mDist) {
                mDist = d;
                output = i;
            }
        }
        return nouns[output];
    }

    /**
     * The main method tests all of the functions in the Outcast class.
     *
     * @param String array for all of the arguments.
     * @return none.
     */
    public static void main(String[] args) {
        WordNet wordnet = new WordNet(args[0], args[1]);
        Outcast outcast = new Outcast(wordnet);
        for (int t = 2; t < args.length; t++) {
            In in = new In(args[t]);
            String[] nouns = in.readAllStrings();
            StdOut.println(args[t] + ": " + outcast.outcast(nouns));
        }
    }

}
