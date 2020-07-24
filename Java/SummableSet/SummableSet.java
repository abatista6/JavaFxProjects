//Adrianna Batista
//4-10-20
//Java Programming
//SummableSet inherits from IntSet
//This code creates an integer vector from user input
//File Name: SummableSet.java

import java.util.*;

public class SummableSet extends IntSet {

  //data members
  private int sum = new Integer(0);//initate new sum integer instance

  public void add(int num) { //call inherited function and increment sum
    if(!super.includes(num)){
      super.add(num);
      sum = sum + num;
    }
    else {
      System.out.println("Duplicate integer entered. Try again.");
    }
  }

  public void remove(int num) { //call inherited function and decrement sum
    if(super.includes(num)) {
      sum = sum-num;
      super.remove(num);
      System.out.println("The integer "+num+" was removed.\n");
    }
    else {
      System.out.println("The integer "+num+" was not found for removal.\n");
    }
  }

  //get sum total
  public int getTotalSum() {
    return sum;
  }

  //main function output
  public static void main(String[] args) {

    SummableSet x = new SummableSet(); //new SummableSet instance
    boolean loop = true;
    int input; //user input
    Scanner scan = new Scanner(System.in);
    while(loop){
      System.out.println("\n1. Insert Integer\n2. Remove Integer\n3. Search for Integer\n4. Display Vector Elements & Sum\n5. Clear Vector\n6. Exit Program");
      int choose = scan.nextInt();
      switch(choose) {
        case 1:
          System.out.println("Enter an integer to insert:");
          try {
            input = scan.nextInt();
            x.add(input);
            System.out.println("Vector Contents =" + x.listAsString());
          }
          catch (InputMismatchException ex)
          {
            System.out.println("ERROR: Invalid entry. Try again.");
          }
          break;
        case 2:
          System.out.println("Enter an integer to remove:");
          try {
            input = scan.nextInt();
            if (x.size()==0) {
              System.out.println("ERROR: Vector is empty.");
            }
            else {
              x.remove(input);
              System.out.println("Vector Contents =" + x.listAsString());
            }
          }
          catch (InputMismatchException ex)
          {
            System.out.println("ERROR: Invalid entry. Try again.");
          }
          break;
        case 3:
          System.out.println("Enter an integer to search for:");
          try {
            input = scan.nextInt();
            if (x.size()==0) {
              System.out.println("ERROR: Vector is empty.");
            }
            else if (x.includes(input)) {
              System.out.println("Integer was found.");
            }
            else {
            System.out.println("Integer was NOT found.");
            }
          }
          catch (InputMismatchException ex) {
            System.out.println("ERROR: Invalid entry. Try again.");
          }
          break;
        case 4:
          System.out.println("Vector Contents =" + x.listAsString());
          System.out.println("Vector Sum = " + Integer.toString(x.getTotalSum()));
          break;
        case 5:
          System.out.println("Enter 0 to clear vector or 1 to cancel");
          try {
            input = scan.nextInt();
            if (x.size()==0) {
              System.out.print("Empty");
            }
            else if(scan.nextInt() == 0){
              x.removeAll();         
            }
            System.out.println("Vector Contents =" + x.listAsString());
            System.out.println("Vector Sum = " + Integer.toString(x.getTotalSum()));
          }
          catch (InputMismatchException ex) {
            System.out.println("ERROR: Invalid entry. Try again.");
          }
          break;
        case 6:
          loop = false;
          System.out.println("...Exiting Program...");
          break;
        default:
          System.out.println("ERROR\n");
          break;
      }
    }
  }
}