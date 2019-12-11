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

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class KdTreeST<Value> {


    private class Node {

        /**
         * Instance Variables
         *
         * p represents the point
         * value represents the symbol table maps the point to this value
         * rect represents the axis-aligned rectangle corresponding to this node
         * lb represents the left/bottom subtree
         * rt represents the right/top subtree
         * aSize represents the size
         * aType represents the type
         */
        private Point2D p;
        private Value value;
        private RectHV rect;
        private Node lb;
        private Node rt;
        private int aSize;
        private boolean aType;

        /**
         * The Node constructor takes in a Point2D, Value, boolean and RectHV for input, and creates an empty
         * Node object
         *
         * @param Point2D, Value, boolean, RectHV
         * @return A empty Node object.
         */
        public Node(Point2D p, Value value, boolean type, RectHV rect) {
            this.p = p;
            this.value = value;
            aType = type;
            this.rect = rect;
            aSize = 1;
        }

        /*
         * The compare method takes in two Double objects for input, and returns an integer
         * representing the comparison of each Double object to the other
         *
         * @param Double p1, p2
         * @return Integer indicating comparison
         */
        public int compare(Double p1, Double p2) {
            return p1.compareTo(p2);
        }

        /**
         * The compareTo method takes in a Point2D object for input, and returns an integer
         * representing the comparison of the point of the selected object compared to the
         * parameter
         *
         * @param Point2D
         * @return Integer indicating comparison
         */
        public int compareTo(Point2D point) {
            if (!aType) {
                int cmp = compare(this.p.x(), point.x());
                if (cmp != 0) {
                    return cmp;
                }
                return compare(this.p.y(), point.y());
            }
            int cmp = compare(this.p.y(), point.y());
            if (cmp != 0) {
                return cmp;
            }
            return compare(this.p.x(), point.x());

        }

        /**
         * The compareTo method takes in a RectHV object for input, and returns an integer
         * representing the comparison of the point of the selected object compared to the
         * parameter
         *
         * @param RectHV
         * @return Integer indicating comparison
         */
        public int compareTo(RectHV rect) {
            if (!aType) {
                if (this.p.x() > rect.xmax()) {
                    return 1;
                }
                if (this.p.x() < rect.xmin()) {
                    return -1;
                }
            }
            else {
                if (this.p.y() > rect.ymax()) {
                    return 1;
                }
                if (this.p.y() < rect.ymin()) {
                    return -1;
                }
            }
            return 0;

        }

        /**
         * The private findRect method takes in a Point2D object for input, and returns a RectHV
         * object which will find a rectangle if it exists at a spcified point
         *
         * @param Point2D
         * @return RectHV
         */
        private RectHV findRect(Point2D point) {
            double xMin = this.rect.xmin();
            double xMax = this.rect.xmax();
            double yMin = this.rect.ymin();
            double yMax = this.rect.ymax();
            if (aType) {
                if (this.compareTo(point) > 0) {
                    yMax = point.y();
                }
                else {
                    yMin = point.y();
                }
            }
            else {

                if (this.compareTo(point) > 0) {
                    xMax = point.y();
                }
                else {
                    xMin = point.y();
                }

            }
            return new RectHV(xMin, yMin, xMax, yMax);
        }

    }

    private Node root;

    /**
     * The KdTreeST constructor takes in no input, and creates an empty symbol table of points
     *
     * @param None
     * @return A empty KdTreeST object.
     */
    public KdTreeST() {
        root = null;
    }

    /**
     * The private size method takes a node for input, and returns an int representing the size of
     * the node and 0 if null
     *
     * @param Node
     * @return Integer representing the size
     */
    private int size(Node n) {
        if (n == null) {
            return 0;
        }
        else {
            return n.aSize;
        }
    }

    /**
     * The put method takes in two Point2D objects, Node, and boolean for input and returns a Node
     * and associates the value val with point p
     *
     * @param Point2D, Value, boolean, Node node, Node root
     * @return Node
     */
    private Node put(Node node, Point2D p, Value val, Node ancestor, boolean type) {
        if (node == null) {
            RectHV aRect;
            if (ancestor == null) {
                aRect = new RectHV(0, 0, 1, 1);
            }
            else {
                aRect = ancestor.findRect(p);
            }
            return new Node(p, val, type, aRect);
        }
        int cmp = node.compareTo(p);
        if (cmp > 0) {
            node.lb = put(node.lb, p, val, node, !type);
        }
        else if (cmp < 0) {
            node.rt = put(node.rt, p, val, node, !type);
        }
        else {
            node.p = p;
            node.value = val;
        }


        node.aSize = 1 + size(node.lb) + size(node.rt);
        return node;
    }

    /**
     * The get method takes in a Point2D and Node for input and returns a Value object representing
     * the value associated with Node and Point2D p
     *
     * @param Point2D, Node
     * @return Value
     */
    private Value get(Node node, Point2D p) {
        if (p == null || node == null) {
            throw new java.lang.IllegalArgumentException("Point p or Node node is empty");
        }
        int cmp = node.compareTo(p);
        if (cmp < 0) {
            return get(node.lb, p);
        }
        else if (cmp > 0) {
            return get(node.rt, p);
        }
        else {
            return node.value;
        }
    }

    /**
     * The method range takes in a RectHV object for input, and returns an iterable of type Point2D
     * which returns all of the points in the inside the rectangle
     *
     * @param RectHV
     * @return Iterable<Point2D>
     */
    private Iterable<Point2D> range(Node node, RectHV rect, Queue<Point2D> rangeQueue) {
        if (node == null) {
            return rangeQueue;
        }
        int cmp = node.compareTo(rect);
        if (cmp == 1) {
            range(node.lb, rect, rangeQueue);
        }
        else if (cmp == -1) {
            range(node.rt, rect, rangeQueue);
        }
        else {
            if (rect.contains(node.p)) {
                rangeQueue.enqueue(node.p);
            }
            range(node.lb, rect, rangeQueue);
            range(node.rt, rect, rangeQueue);
        }
        return rangeQueue;
    }

    /**
     * The method nearest takes in two Point2D objects, Node, and Double for input, and returns a
     * Point2D represetning a nearest neighbor of point point and null if the symbol table is empty
     *
     * @param Point2D point, Point2D nearestP, Node, Double
     * @return Point2D
     */
    private Point2D nearest(Point2D point, Node currN, Point2D nearestP, double minDist) {
        if (currN == null) {
            return nearestP;
        }
        double rectDist = currN.rect.distanceSquaredTo(point);
        if (rectDist < minDist) {
            double pointDist = currN.p.distanceSquaredTo(point);
            if (pointDist < minDist) {
                minDist = pointDist;
                nearestP = currN.p;
            }
            if (currN.compareTo(point) == 1) {
                nearestP = nearest(point, currN.lb, nearestP, minDist);
                nearestP = nearest(point, currN.rt, nearestP, nearestP.distanceSquaredTo(point));
            }
            else if (currN.compareTo(point) == -1) {
                nearestP = nearest(point, currN.lb, nearestP, minDist);
                nearestP = nearest(point, currN.rt, nearestP, nearestP.distanceSquaredTo(point));
            }
        }


        return nearestP;
    }


    /**
     * The isEmpty method takes no input, and returns a boolean depening on whether or not the
     * object is empty
     *
     * @param None
     * @return Boolean representing whether or not it is empty.
     */
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * The size method takes no input, and returns an int representing the number of points in the
     * tree
     *
     * @param None
     * @return Integer representing the size of the tree
     */
    public int size() {
        return size(root);
    }

    /**
     * The put method takes in a Point2D and Value object for input and returns nothing it
     * associates the value val with point p
     *
     * @param Point2D, Value
     * @return None
     */
    public void put(Point2D p, Value val) {
        if (p == null) {
            throw new java.lang.IllegalArgumentException("Point p is empty");
        }
        root = put(root, p, val, null, false);

    }

    /**
     * The get method takes in a Point2D object for input and returns a Value object representing
     * the value associated with point p
     *
     * @param Point2D
     * @return Value
     */
    public Value get(Point2D p) {
        if (p == null) {
            throw new java.lang.IllegalArgumentException("Point p is empty");
        }
        return get(root, p);
    }

    /**
     * The contains method takes in a Point object for input, and returns a boolean depending on
     * whether or not the symbol table contains point p
     *
     * @param Point2D
     * @return Boolean representing whether or not P is in aPoint.
     */
    public boolean contains(Point2D p) {
        if (p == null) {
            throw new java.lang.IllegalArgumentException("Point p is empty");
        }
        return get(p) != null;
    }

    /**
     * The method points takes in no input, and returns an iterable of type Point2D which returns
     * all of the points in the symbol table
     *
     * @param None
     * @return Iterable<Point2D>
     */
    public Iterable<Point2D> points() {
        Queue<Point2D> output = new Queue<Point2D>();
        Queue<Node> iterable = new Queue<Node>();
        iterable.enqueue(root);
        while (!iterable.isEmpty()) {
            Node top = iterable.dequeue();
            if (top == null) {
                continue;
            }
            output.enqueue(top.p);
            iterable.enqueue(top.lb);
            iterable.enqueue(top.rt);
        }
        return output;
    }

    /**
     * The method range takes in a RectHV object for input, and returns an iterable of type Point2D
     * which returns all of the points in the inside the rectangle
     *
     * @param RectHV
     * @return Iterable<Point2D>
     */
    public Iterable<Point2D> range(RectHV rect) {
        Queue<Point2D> outputQueue = new Queue<Point2D>();
        return range(root, rect, outputQueue);
    }

    /**
     * The method nearest takes in a Point2D for input, and returns a Point2D represetning a nearest
     * neighbor of point p and null if the symbol table is empty
     *
     * @param Point2D p
     * @return Point2D
     */
    public Point2D nearest(Point2D p) {
        if (isEmpty()) {
            return null;
        }
        double rootDist = root.p.distanceSquaredTo(p);
        return nearest(p, root, root.p, Double.POSITIVE_INFINITY);
    }

    /**
     * The main method tests all of the functions in the KdTreeST class.
     *
     * @param String array for all of the arguments.
     * @return none.
     */
    public static void main(String[] args) {
        Point2D aPoint = new Point2D(0.5, 0.5);
        KdTreeST<Integer> kdtree = new KdTreeST<Integer>();
        double[] vals = StdIn.readAllDoubles();
        for (int i = 0, j = 1; i < vals.length; j++) {
            double x = vals[i++];
            double y = vals[i++];
            kdtree.put(new Point2D(x, y), j);
        }
        StdOut.println(
                "contains test: This should return true and returns " + kdtree.contains(aPoint));

        StdOut.println("isEmpty test: This should return false and returns " + kdtree.isEmpty());
        int size = kdtree.size();
        StdOut.println(
                "Size test: This should return the size of the selected input file which in this case is 1 "
                        + size);
        Iterable<Point2D> pointList = kdtree.points();
        StdOut.println("Points test: This prints the points contained in KdTreeST which are : ");
        for (Point2D point : pointList) {
            StdOut.println(point.toString());
        }
        RectHV rect = new RectHV(0.0, 0.0, 0.5, 0.5);
        Iterable<Point2D> rangeList = kdtree.range(rect);
        StdOut.println(
                "Range test: This prints the points contained in the rectangle range (0, 0, 0.5, 0.5) which are : ");
        for (Point2D point : rangeList) {
            StdOut.println(point.toString());
        }
        Point2D closest = kdtree.nearest(new Point2D(0.5, 0.75));
        StdOut.println("Nearest test: This prints the point closest point to (0.5, 0.5) which is : "
                               + closest.toString());
        StdOut.println(
                "get test: This should return 1 and returns " + kdtree.get(aPoint));

    }
}


