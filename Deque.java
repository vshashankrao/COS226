/* *****************************************************************************
 *  Name:    Shashank Rao
 *  NetID:   aturing
 *  Precept: P00
 *
 *  Description:  Prints 'Hello, World' to the terminal window.
 *                By tradition, this is everyone's first program.
 *                Prof. Brian Kernighan initiated this tradition in 1974.
 *
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    // Instance variable front to represent the first node of the Deque
    private Node front;
    // Instance variable rear to represent the last node of the Deque
    private Node rear;
    // Instance variable dequeSize to represent the size of the Deque
    private int dequeSize;

    /**
     * private class Node to represent an individual Node in the Deque
     */
    private class Node {
        // Instance variable representing the next node
        Node next;
        // Instance variable representing the back node
        Node back;
        // Instance variable representing the item of node type
        Item item;

        /**
         * The Node constructor takes an item as input, and creates an empty Node object of type
         * item
         *
         * @param Item
         * @return An empty Node object.
         */
        Node(Item item) {
            this.next = null;
            this.back = null;
            this.item = item;
        }
    }


    /**
     * The Deque constructor takes no input, and creates an empty Deque object
     *
     * @param None
     * @return A empty Deque object.
     */
    public Deque() {
        this.front = null;
        this.rear = null;
        this.dequeSize = 0;
    }

    /**
     * The isEmpty method no input, and checks if the Deque is empty
     *
     * @param None
     * @return Boolean.
     */
    public boolean isEmpty() {
        if (dequeSize == 0) {
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * The size method takes no input, and returns the number of items on the deque
     *
     * @param None
     * @return Integer.
     */
    public int size() {
        return dequeSize;
    }

    /**
     * The addFirst method takes an Item as input, and returns nothing. It adds the inputted item to
     * the front of the deque
     *
     * @param Item
     * @return None.
     */
    public void addFirst(Item item) {
        if (item == null) {
            throw new NullPointerException();
        }
        if (isEmpty()) {
            this.front = new Node(item);
            this.rear = front;
        }
        else {

            Node node = new Node(item);
            node.next = this.front;
            front.back = node;
            front = node;
        }
        dequeSize += 1;
    }

    /**
     * The addFirst method takes an Item as input, and returns nothing. It adds the inputted item to
     * the rear of the deque
     *
     * @param Item
     * @return None.
     */
    public void addLast(Item item) {
        if (item == null) {
            throw new NullPointerException();
        }
        if (isEmpty()) {
            rear = new Node(item);
            front = rear;
        }
        else {

            Node node = new Node(item);
            rear.next = node;
            node.back = rear;
            node.next = front;
            front = node;
        }
        dequeSize += 1;
    }

    /**
     * The removeFirst method takes no input, and returns an item object. It removes the item from
     * the front of the deque and returns it
     *
     * @param None
     * @return Item.
     */
    public Item removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException("The Deque is empty");
        }
        Node node = front;
        if (size() == 1) {
            front = null;
            rear = null;
        }
        else {
            front.next.back = null;
            front = front.next;
        }
        dequeSize -= 1;
        node.next = null;
        return node.item;
    }

    /**
     * The removeLast method takes no input, and returns an item object. It removes the item from
     * the rear of the deque and returns it
     *
     * @param None
     * @return Item.
     */
    public Item removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException("The Deque is empty");
        }
        Node node = rear;
        if (size() == 1) {
            front = null;
            rear = null;
        }
        else {
            rear.back.next = null;
            rear = rear.back;
        }
        dequeSize -= 1;
        node.next = null;
        return node.item;
    }

    /**
     * The Iterator method takes no input, and returns an Iterator<Item> object. It returns an
     * iterator over items in order from front to back
     *
     * @param None
     * @return Iterator<Item>.
     */
    public Iterator<Item> iterator() {
        return new QueueIterator();
    }

    /**
     * The QueueIterator class is a private class used to implement the Deque iterator method
     */
    private class QueueIterator implements Iterator<Item> {
        // Instance variable node which represents the focused node in the iteration
        private Node node;

        /**
         * The QueueIterator constructor takes no input, and creates an empty QueueIterator object
         *
         * @param None
         * @return A empty QueueIterator object.
         */
        public QueueIterator() {
            node = front;
        }

        /**
         * The hasNext method takes no input, and checks if the QueueIterator has a consecuitve
         * node
         *
         * @param None
         * @return Boolean.
         */
        public boolean hasNext() {
            if (node != null) {
                return true;
            }
            else {
                return false;
            }
        }

        /**
         * The next method no input and returns an Item. It returns the next consecutive node after
         * checking the hasNext() condition
         *
         * @param None
         * @return Item.
         */
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException("There are no remaining elements");
            }
            else {
                Item temp = node.item;
                node = node.next;
                return temp;
            }
        }

        public void remove() {
            throw new UnsupportedOperationException("This operation can't be performed");
        }
    }

    /**
     * The main method tests all of the functions in the Deque class.
     *
     * @param String array for all of the arguments.
     * @return none.
     */
    public static void main(String[] args) {
        Deque<String> tester = new Deque<String>();
        StdOut.println("Empty? This should print true " + tester.isEmpty());
        StdOut.println("The size of the deque should print 0 " + tester.size());

        tester.addFirst("FirstF");
        StdOut.println(tester.removeFirst());
        tester.addFirst("FirstL");
        StdOut.println(tester.removeLast());
        tester.addLast("LastF");
        StdOut.println(tester.removeFirst());
        tester.addLast("LastL");
        StdOut.println(tester.removeLast());

        Deque<Integer> tester2 = new Deque<Integer>();
        for (int i = 0; i < 10; i++) {
            tester2.addLast(i);
        }


        for (int i : tester2) {
            if (i <= 0) {
                StdOut.println(0);
                break;
            }
            else {
                StdOut.println(i);
            }
        }

    }

}
