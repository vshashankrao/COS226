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
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.LinkedList;

public class ShortestCommonAncestor {
    /**
     * Instance Variables
     * <p>
     * aDigraph represents the Digraph being analyzed through the ShortestCommonAncestor datatype
     */
    private Digraph aDigraph;

    /**
     * The ShortestCommonAncestor constructor takes in a rooted DAG for input, and creates an empty
     * ShortestCommonAncestor object
     *
     * @param Digraph G
     * @return An empty ShortestCommonAncestor object.
     */
    public ShortestCommonAncestor(Digraph G) {
        aDigraph = G;
    }

    /**
     * The length method takes in two Integer objects for input, and returns the length of shortest
     * ancestral path between v and w
     *
     * @param Integer v, w
     * @return Integer representing the shortest path
     */
    public int length(int v, int w) {
        LinkedList<Integer> vPath = new LinkedList<>();
        LinkedList<Integer> wPath = new LinkedList<>();
        vPath.add(v);
        wPath.add(w);
        return leastCA(vPath, wPath).anEdge;

    }

    /**
     * The ancestor method takes in two Integer objects for input, and returns the shortest common
     * ancestor of vertices v and w
     *
     * @param Integer v, w
     * @return Integer representing the shortest common ancestor
     */
    public int ancestor(int v, int w) {
        LinkedList<Integer> vPath = new LinkedList<>();
        LinkedList<Integer> wPath = new LinkedList<>();
        vPath.add(v);
        wPath.add(w);
        return leastCA(vPath, wPath).aNode;
    }


    /**
     * The lengthSubset method takes in two Iterable<Integer> objects for input, and returns the
     * length of shortest ancestral path of the vertex subsets of v and w
     *
     * @param Iterable<Integer> subsetA, subsetB
     * @return Integer representing the shortest path
     */
    public int lengthSubset(Iterable<Integer> subsetA, Iterable<Integer> subsetB) {
        return leastCA(subsetA, subsetB).anEdge;
    }

    /**
     * The ancestorSubset method takes in two Iterable<Integer> objects for input, and returns the
     * length of shortest common ancestor of the vertex subsets of v and w
     *
     * @param Iterable<Integer> subsetA, subsetB
     * @return Integer representing the shortest common ancestor
     */
    public int ancestorSubset(Iterable<Integer> subsetA, Iterable<Integer> subsetB) {
        return leastCA(subsetA, subsetB).aNode;
    }

    /**
     * The NodeEdge class is a private class that returns an object containing a specified node and
     * edge as one single object
     */
    private class NodeEdge {
        /**
         * Instance Variables
         * <p>
         * aNode represents the node in the NodeEdge datatype anEdge represents the edge in the
         * NodeEdge datatype
         */
        private int aNode;
        private int anEdge;

        /**
         * The NodeEdge constructor takes in two integers for input, and creates an empty NodeEdge
         * object
         *
         * @param Integer node, edge
         * @return An empty NodeEdge object.
         */
        public NodeEdge(int edge, int node) {
            anEdge = edge;
            aNode = node;

        }
    }

    /**
     * The private leastCA method takes in two Iterable<Integer> objects for input, and returns a
     * NodeEdge representing a the least common ancestor between iterables v and w
     *
     * @param Iterable<Integer> v, w
     * @return NodeEdge
     */
    private NodeEdge leastCA(Iterable<Integer> v, Iterable<Integer> w) {
        for (int eachV : v) {
            for (int eachW : w) {
                if (eachV == eachW) {
                    return new NodeEdge(0, eachV);
                }
            }
        }
        int[] vertex = new int[aDigraph.V()];
        int[] totalL = new int[aDigraph.V()];
        Queue<Integer> intQueue = new Queue<Integer>();
        for (int i : v) {
            intQueue.enqueue(i);
            vertex[i] = -1;
        }
        for (int i : w) {
            intQueue.enqueue(i);
            vertex[i] = 1;
        }
        while (!intQueue.isEmpty()) {
            int current = intQueue.dequeue();
            for (int aVert : aDigraph.adj(current)) {
                if (vertex[aVert] == 0) {
                    intQueue.enqueue(aVert);
                    vertex[aVert] = vertex[current];
                    totalL[aVert] = totalL[current] + 1;
                }
                else if (vertex[aVert] != vertex[current]) {
                    return new NodeEdge(totalL[aVert] + totalL[current] + 1, aVert);
                }
            }
        }
        return new NodeEdge(-1, -1);
    }

    /**
     * The main method tests all of the functions in the ShortestCommonAncestor class.
     *
     * @param String array for all of the arguments.
     * @return none.
     */
    public static void main(String[] args) {
        In in = new In(args[0]);
        Digraph G = new Digraph(in);
        ShortestCommonAncestor sca = new ShortestCommonAncestor(G);
        while (!StdIn.isEmpty()) {
            int v = StdIn.readInt();
            int w = StdIn.readInt();
            int length = sca.length(v, w);
            int ancestor = sca.ancestor(v, w);
            StdOut.printf("length = %d, ancestor = %d\n", length, ancestor);
        }
    }

}
