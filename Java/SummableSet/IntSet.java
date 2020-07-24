//Adrianna Batista
//4-10-20
//This code creates an integer vector from user input
//File Name: IntSet.java

import java.util.*;

public class IntSet {
  public Vector<Integer> data = new Vector<Integer>(); //new vector instance
  
  public int size() { //get vector size
    return data.size();
  }

  //add a single integer to vector
  public void add(int x) {
    data.add(x);
    Collections.sort(data);
  }

  //removes a single integer from the vector
  public void remove(int x) {
    data.removeElement(Integer.valueOf(x));
  }

  //removes all elements in the vector
  public void removeAll() {
    data.clear();
  }

  //search if vector contains entered integer
  public boolean includes(int x) {
    if(data.contains(x)) {
      return true;
    }
    else {
      return false;
    }
  }

  public String listAsString() {
    String list = new String("");
    if(data.isEmpty()){
      list = " empty";
    }
    else {
      for(int i=0; i<data.size(); i++) {
        list = list + " " + data.get(i).toString();
      }
    }
    return list;
  }
}