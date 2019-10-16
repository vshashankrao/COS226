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
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    // Instance array randomQueue which represents the randomized queue
    private Item[] randomQueue;
    // Instance variable queueSize which represents the size of the randomized queue
    private int queueSize;

    /**
     * The RandomizedQueue constructor takes no input, and creates an empty RandomizedQueue object
     *
     * @param None
     * @return A empty RandomizedQueue object.
     */
    public RandomizedQueue() {
        randomQueue = (Item[]) new Object[1];
        queueSize = 0;
    }

    /**
     * The isEmpty method no input, and checks if the RandomizedQueue is empty
     *
     * @param None
     * @return Boolean.
     */
    public boolean isEmpty() {
        if (queueSize == 0) {
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * The size method takes no input, and returns the number of items in the RandomizedQueue
     *
     * @param None
     * @return Integer.
     */
    public int size() {
        return queueSize;
    }

    /**
     * The enqueue method takes an Item as input, and returns nothing. It adds the inputted item to
     * the top of the RandomizedQueue
     *
     * @param Item
     * @return None.
     */
    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("Can't enqueue a null value");
        }
        if (queueSize == randomQueue.length) {
            resize(randomQueue.length * 2);
        }

        randomQueue[queueSize] = item;
        queueSize += 1;
    }

    /**
     * The dequeue method takes no input, and returns an Item. It removes the item from the top of
     * the RandomizedQueue and returns it
     *
     * @param None
     * @return Item.
     */
    public Item dequeue() {
        if (isEmpty()) {
            throw new NullPointerException("The queue is empty");
        }
        int i = StdRandom.uniform(queueSize);
        Item output = randomQueue[i];
        if (i != (queueSize - 1)) {
            randomQueue[i] = randomQueue[queueSize - 1];
        }
        queueSize -= 1;
        randomQueue[queueSize] = null;
        if (queueSize == (randomQueue.length / 4)) {
            resize(randomQueue.length / 2);
        }
        return output;
    }

    /**
     * The sample method takes no input, and returns an Item. It returns a random item from the
     * queue but doesn't remove it
     *
     * @param None
     * @return Item.
     */
    public Item sample() {
        return randomQueue[StdRandom.uniform(queueSize - 1)];
    }

    /**
     * The Iterator method takes no input, and returns an Iterator<Item> object. It returns an
     * iterator over items in random order
     *
     * @param None
     * @return Iterator<Item>.
     */
    public Iterator<Item> iterator() {
        return new RandomQueueIterator();
    }

    /**
     * The private method resize takes n as input, and returns Nothing. It resizes the array to the
     * specified size
     *
     * @param Integer
     * @return None.
     */
    private void resize(int n) {
        Item[] tempArr = (Item[]) new Object[n];
        for (int i = 0; i < queueSize; i++) {
            tempArr[i] = randomQueue[i];
        }
        randomQueue = tempArr;
    }

    /**
     * The RandomQueueIterator class is a private class used to implement the RandomQueue iterator
     * method
     */
    private class RandomQueueIterator implements Iterator<Item> {
        // Instance variable index which represents the index of the array
        private int index = 0;

        /**
         * The RandomQueueIterator constructor takes no input, and creates an empty
         * RandomQueueIterator object
         *
         * @param None
         * @return A empty RandomQueueIterator object
         */
        public Item next() {
            if (index == 0) {
                StdRandom.shuffle(randomQueue);
            }
            if (!hasNext()) {
                throw new NoSuchElementException("There are no remaining elements");
            }
            Item item = randomQueue[index];
            index += 1;
            return item;
        }


        /**
         * The hasNext method no input, and checks if the RandomQueueIterator has a consecutive
         * element by comparing the index with the queueSize
         *
         * @param None
         * @return Boolean.
         */
        public boolean hasNext() {
            if (index < queueSize) {
                return true;
            }
            else {
                return false;
            }
        }

        public void remove() {
            throw new UnsupportedOperationException("This operation can't be performed");
        }


    }

    /**
     * The main method tests all of the functions in the RandomizedQueue class.
     *
     * @param String array for all of the arguments.
     * @return none.
     */
    public static void main(String[] args) {
        RandomizedQueue<String> tester = new RandomizedQueue<String>();

        StdOut.println("Empty? should return true " + tester.isEmpty());
        StdOut.println("Size: " + tester.size());

        tester.enqueue("First");
        tester.enqueue("Second");
        tester.enqueue("Third");
        tester.enqueue("Fourth");


        StdOut.println("Empty? should return false " + tester.isEmpty());
        StdOut.println("Size: " + tester.size());
        StdOut.println();
        StdOut.println();

        StdOut.println("This is a sample " + tester.sample());

        for (String s : tester)
            StdOut.println(s);

        while (!tester.isEmpty()) {
            StdOut.println(tester.dequeue());
        }


    }

}




