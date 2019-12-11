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

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

import java.util.HashMap;
import java.util.LinkedList;

public class WordNet {

    /**
     * Instance Variables
     * <p>
     * synsetList represents the list of Synsets in the form of a Hash Table
     * <p>
     * nouns represents the list of nouns in the form of a Hash Table
     * <p>
     * aDigraph represents the main Digraph
     * <p>
     * shortestCA represents the shortest common ancestor
     */
    private HashMap<String, LinkedList<Integer>> synsetList
            = new HashMap<String, LinkedList<Integer>>();
    private HashMap<Integer, String[]> nouns
            = new HashMap<Integer, String[]>();
    private Digraph aDigraph;
    private ShortestCommonAncestor shortestCA;

    /**
     * The WordNet constructor takes in two Strings for input, and creates an empty WordNet object
     * by reading in the input files for both the synsets and hypernyms
     *
     * @param String synsets, hypernyms
     * @return A empty WordNet object.
     */
    public WordNet(String synsets, String hypernyms) {
        if (synsets == null || hypernyms == null) {
            throw new IllegalArgumentException("synsets and hypernyms can't be empty");
        }
        int lineCount = 0;
        In inputFile = new In(synsets);
        while (inputFile.hasNextLine()) {
            String aLine = inputFile.readLine();
            String[] splitArr = aLine.split(",");
            int synID = Integer.parseInt(splitArr[0]);
            String[] nounSyn = splitArr[1].split(" ");
            nouns.put(synID, nounSyn);
            for (String word : nounSyn) {
                LinkedList<Integer> nounList = synsetList.get(word);
                if (nounList == null) {
                    nounList = new LinkedList<Integer>();
                }
                nounList.add(synID);
                synsetList.put(word, nounList);

            }
            lineCount += 1;

        }
        inputFile.close();
        aDigraph = new Digraph(lineCount);
        In hyperInput = new In(hypernyms);
        while (hyperInput.hasNextLine()) {
            String aLine = hyperInput.readLine();
            String[] splitArr = aLine.split(",");
            int synID = Integer.parseInt(splitArr[0]);
            for (int i = 1; i < splitArr.length; i++) {
                aDigraph.addEdge(synID, Integer.parseInt(splitArr[i]));
            }
        }
        hyperInput.close();
        shortestCA = new ShortestCommonAncestor(aDigraph);


    }

    /**
     * The method points takes in no input, and returns an iterable of type String which returns all
     * of the WordNet nouns
     *
     * @param None
     * @return Iterable<Point2D>
     */
    public Iterable<String> nouns() {
        return synsetList.keySet();
    }

    /**
     * The isNoun method takes in a String object for input, and returns a boolean depending on
     * whether or not word is a Noun
     *
     * @param String word
     * @return Boolean representing whether or not word is a noun
     */
    public boolean isNoun(String word) {
        return synsetList.containsKey(word);
    }

    /**
     * The sca method takes in two String objects for input, and returns a String representing a
     * synset (second field of synsets.txt) that is a shortest common ancestor of noun1 and noun2
     * (defined below)
     *
     * @param String noun1, noun2
     * @return String representing synset which is sca
     */
    public String sca(String noun1, String noun2) {
        if (!isNoun(noun1) || !isNoun(noun2)) {
            throw new IllegalArgumentException("Either noun1 or noun2 is not a wordnet noun");
        }
        LinkedList<Integer> x = synsetList.get(noun1);
        LinkedList<Integer> y = synsetList.get(noun2);
        int synID = shortestCA.ancestorSubset(x, y);
        String[] data = nouns.get(synID);
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < data.length; i++) {
            output.append(data[i]);
            if (i < data.length - 1) {
                output.append(" ");
            }
        }
        return output.toString();
    }

    /**
     * The distance method takes in two String objects for input, and returns the distance between
     * noun1 and noun2 (defined below)
     *
     * @param None
     * @return Integer representing the size of the tree
     */
    public int distance(String noun1, String noun2) {
        if (!isNoun(noun1) || !isNoun(noun2)) {
            throw new IllegalArgumentException("Either noun1 or noun2 is not a wordnet noun");
        }
        LinkedList<Integer> x = synsetList.get(noun1);
        LinkedList<Integer> y = synsetList.get(noun2);
        return shortestCA.lengthSubset(x, y);

    }

    /**
     * The main method tests all of the functions in the WordNet class.
     *
     * @param String array for all of the arguments.
     * @return none.
     */
    public static void main(String[] args) {
        WordNet tester = new WordNet("synsets.txt", "hypernyms.txt");
        StdOut.println(
                "isNoun test: This should return true and it returns " + tester.isNoun("mover"));
        StdOut.println("distance test: This should return 3 and it returns " + tester
                .distance("African", "renegade"));
        StdOut.println(
                "sca test: This should return person individual someone somebody mortal soul and it returns "
                        + tester
                        .sca("African", "renegade"));

        // Commented because output is large
        //StdOut.println("nouns test: This should return all of the nouns and it returns " + tester.nouns());

    }

}
