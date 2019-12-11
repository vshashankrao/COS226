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

import edu.princeton.cs.algs4.BST;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdOut;

public class PointST<Value> {
    /**
     * Instance Variables
     * <p>
     * aPoint representing the PointST BST
     */
    private BST<Point2D, Value> aPoint;


    /**
     * The PointST constructor takes in no input, and creates an empty symbol table of points
     *
     * @param None
     * @return A empty PointST object.
     */
    public PointST() {
        aPoint = new BST<Point2D, Value>();
    }

    /**
     * The isEmpty method takes no input, and returns a boolean depening on whether or not the
     * object is empty
     *
     * @param None
     * @return Boolean representing whether or not it is empty.
     */
    public boolean isEmpty() {
        return (aPoint.size() == 0);
    }

    /**
     * The size method takes no input, and returns an int representing the size of the object
     *
     * @param None
     * @return Integer representing the size
     */
    public int size() {
        return aPoint.size();
    }

    /**
     * The put method takes in a Point2D and Value object for input and returns nothing it
     * associates the value val with point p
     *
     * @param Point2D, Value
     * @return None
     */
    public void put(Point2D p, Value val) {
        aPoint.put(p, val);
    }

    /**
     * The get method takes in a Point2D object for input and returns a Value object representing
     * the value associated with point p
     *
     * @param Point2D
     * @return Value
     */
    public Value get(Point2D p) {
        return aPoint.get(p);
    }


    /**
     * The contains method takes in a Point object for input, and returns a boolean depending on
     * whether or not the symbol table contains point p
     *
     * @param Point2D
     * @return Boolean representing whether or not P is in aPoint.
     */
    public boolean contains(Point2D p) {
        return aPoint.contains(p);
    }

    /**
     * The method points takes in no input, and returns an iterable of type Point2D which returns
     * all of the points in the symbol table
     *
     * @param None
     * @return Iterable<Point2D>
     */
    public Iterable<Point2D> points() {
        return aPoint.keys();
    }

    /**
     * The method range takes in a RectHV object for input, and returns an iterable of type Point2D
     * which returns all of the points in the inside the rectangle
     *
     * @param RectHV
     * @return Iterable<Point2D>
     */
    public Iterable<Point2D> range(RectHV rect) {
        Queue<Point2D> output = new Queue<Point2D>();
        for (Point2D point : points()) {
            if (rect.contains(point)) {
                output.enqueue(point);
            }
        }
        return output;
    }

    /**
     * The method nearest takes in a Point2D for input, and returns a Point2D represetning a nearest
     * neighbor of point p and null if the symbol table is empty
     *
     * @param Point2D p
     * @return Point2D
     */
    public Point2D nearest(Point2D p) {
        if (aPoint.isEmpty()) {
            return null;
        }
        Point2D output = aPoint.min();
        double minDist = p.distanceSquaredTo(output);
        for (Point2D point : points()) {
            if ((p.distanceSquaredTo(point) < minDist) && (!p.equals(point))) {
                minDist = p.distanceSquaredTo(point);
                output = point;
            }

        }
        return output;
    }

    /**
     * The main method tests all of the functions in the PointST class.
     *
     * @param String array for all of the arguments.
     * @return none.
     */
    public static void main(String[] args) {

        Point2D first = new Point2D(0, 0);
        Point2D second = new Point2D(0, 1);
        Point2D third = new Point2D(1, 0);
        Point2D fourth = new Point2D(1, 1);
        Point2D fifth = new Point2D(5, 5);

        PointST<Integer> tester = new PointST<Integer>();
        StdOut.println(
                "First isEmpty test: This should print true and it prints " + tester.isEmpty());

        tester.put(first, 0);
        tester.put(second, -1);
        tester.put(third, 1);
        tester.put(fourth, 2);

        StdOut.println(
                "Second isEmpty test: This should print false and it prints " + tester.isEmpty());

        StdOut.println("size test: This should print 4 and it prints " + tester.size());
        StdOut.println("put and get test: This should print 1 and it prints " + tester.get(third));

        StdOut.println(
                "points test: This should print (0, 0), (1, 0), (0, 1), and (1, 1) and it prints "
                        + tester.points());

        StdOut.println(
                "contains test: This should print true and it prints " + tester.contains(third));
        StdOut.println(
                "contains test: This should print false and it prints " + tester.contains(fifth));
        RectHV testRect = new RectHV(0, 0, 8, 8);

        StdOut.println(
                "range test: This should print all of the points in tester and it prints " + tester
                        .range(testRect));
        StdOut.println(
                "nearest test: This should print the nearest point to 'fourth' which is (1, 0) and it prints "
                        + tester
                        .nearest(fourth));


    }

}
