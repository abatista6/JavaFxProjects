//Adrianna Batista
//2-25-20
//Java Programming
//This code generates 10 random numbers to populate an array which is then sorted in ascending order

import java.util.*; //import all utility libraries
public class Main {
  
  public static void main(String[] args) {
        int randarr[] = new int [10]; //allocate new array
        Random ran= new Random(); //create Random class instance
        System.out.println("Unsorted random array:"); //output array details
	      for(int i=0; i<randarr.length; i++){ //loop through array
	        int b = ran.nextInt(500); //generate random integers 0 to 500
	        randarr[i]=b; //insert random number into array at position i
	      }
        System.out.print(Arrays.toString(randarr)); //convert array to string for output

        System.out.println("Sorted random array:"); //output array details

        int temp; //create temp variable
	      for(int i=1; i< randarr.length; i++){ //loop through array
	        temp=randarr[i]; //assign number at position i to temp
	        int j=i; //assign position i to variable j
	        while (j>0 && randarr[j-1] > temp){
		        randarr[j] = randarr[j-1]; //assign the number in the previous position to the current positon j
		        j--; //decrement array position
	        }
	        randarr[j]=temp; //assign temp to array position j
        }
        System.out.print(Arrays.toString(randarr)); //convert array to string for output
  }
}