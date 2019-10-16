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

import edu.princeton.cs.algs4.StdOut;

import java.util.Collections;
import java.util.Comparator;

public class BinarySearchDeluxe {
    /**
     * The firstIndexOf method takes in a Key[] array, Key object, and Comparator<Key> object for
     * input. It Returns the index of the first key in a[] that equals the search key, or -1 if no
     * such key exists
     *
     * @param Key array, Key object, Comparator<Key> object.
     * @return Integer.
     */
    public static <Key> int firstIndexOf(Key[] a, Key key, Comparator<Key> comparator) {
        if (a == null || key == null || comparator == null) {
            throw new IllegalArgumentException("Parameters can't be null");
        }
        int output = -1;
        int lo = 0;
        int hi = a.length - 1;
        if (comparator.compare(key, a[0]) == 0) {
            return 0;
        }
        while (lo <= hi) {
            int mid = ((hi - lo) / 2) + lo;
            if (comparator.compare(key, a[mid]) < 0) {
                hi = mid - 1;
            }
            else if (comparator.compare(key, a[mid]) > 0) {
                lo = mid + 1;
            }
            else if (comparator.compare(a[mid - 1], a[mid]) == 0) {
                hi = mid - 1;
            }
            else {
                return mid;
            }

        }
        return output;
    }

    /**
     * The lastIndexOf method takes in a Key[] array, Key object, and Comparator<Key> object for
     * input. It Returns the index of the last key in a[] that equals the search key, or -1 if no
     * such key exists
     *
     * @param Key array, Key object, Comparator<Key> object.
     * @return Integer.
     */
    public static <Key> int lastIndexOf(Key[] a, Key key, Comparator<Key> comparator) {
        if (a == null || key == null || comparator == null) {
            throw new IllegalArgumentException("Parameters can't be null");
        }
        int output = -1;
        int lo = 0;
        int hi = a.length - 1;
        if (comparator.compare(a[hi], key) == 0) {
            return hi;
        }
        while (lo <= hi) {
            int mid = ((hi - lo) / 2) + lo;
            if (comparator.compare(key, a[mid]) < 0) {
                hi = mid - 1;
            }
            else if (comparator.compare(key, a[mid]) > 0) {
                lo = mid + 1;
            }
            else if (comparator.compare(a[mid + 1], a[mid]) == 0) {
                lo = mid + 1;
            }
            else {
                return mid;
            }

        }
        return output;
    }


    /**
     * The main method tests all of the functions in the BinarySearchDeluxe class.
     *
     * @param String array for all of the arguments.
     * @return none.
     */
    public static void main(String[] args) {
        Integer[] testerArr = {
                10, 10, 10, 9, 9, 9, 8, 8, 8, 7, 7, 7, 6, 6, 6, 5, 5, 5, 4, 4, 4, 3, 3, 3, 2, 2, 2,
                1, 1, 1
        };
        
        StdOut.print(
                BinarySearchDeluxe.firstIndexOf(testerArr, 10, Collections.reverseOrder()) + "\t");
        StdOut.println(BinarySearchDeluxe.lastIndexOf(testerArr, 10, Collections.reverseOrder()));
        StdOut.print(
                BinarySearchDeluxe.firstIndexOf(testerArr, 9, Collections.reverseOrder()) + "\t");
        StdOut.println(BinarySearchDeluxe.lastIndexOf(testerArr, 9, Collections.reverseOrder()));
        StdOut.print(
                BinarySearchDeluxe.firstIndexOf(testerArr, 6, Collections.reverseOrder()) + "\t");
        StdOut.println(BinarySearchDeluxe.lastIndexOf(testerArr, 6, Collections.reverseOrder()));
        StdOut.print(
                BinarySearchDeluxe.firstIndexOf(testerArr, 1, Collections.reverseOrder()) + "\t");
        StdOut.println(BinarySearchDeluxe.lastIndexOf(testerArr, 1, Collections.reverseOrder()));
        StdOut.print(
                BinarySearchDeluxe.firstIndexOf(testerArr, 12, Collections.reverseOrder()) + "\t");
        StdOut.println(BinarySearchDeluxe.lastIndexOf(testerArr, 12, Collections.reverseOrder()));
    }
}



