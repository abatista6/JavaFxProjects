package sample;

import java.util.*;

/** JavaFX IntSortingSet Program
 * @author Adrianna Batista
 * @version 1.0
 * file name Controller.java
 */

public class Controller { //Class of integer vector set
    public static Vector<Integer> data = new Vector<>(); //new vector instance

    public static int size() { //get vector size
        return data.size();
    }

    //add a single integer to vector
    public static void add(int x) {
        data.add(x);
        //Collections.sort(data);
    }

    //add a single integer to vector
    public static void sortAscend() {
        Collections.sort(data);
    }

    //add a single integer to vector
    public static void sortDescend() {
        Collections.sort(data, Collections.reverseOrder());
    }

    //removes a single integer from the vector
    public static void remove(int x) {
        data.removeElement(x);
    }

    //removes all elements in the vector
    public static void removeAll() {
        data.clear();
    }

    //return vector as string list
    public static String listAsString() {
        String list = ("");
        if(data.isEmpty()){
            list = " empty";
        }
        else {
            for (Integer datum : data) {
                list = list + " " + datum.toString();
            }
        }
        return list;
    }
}
