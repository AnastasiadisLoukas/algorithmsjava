
package skyline2333;
/**
 *
 * @author Loukas Anastasiadis AEM : 2333
 * @email aploukas@csd.auth.gr
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.lang.String;
/**
 *This programm finds the skyline of a 2-dimension Array from a .txt file.
 * 
 *To run go to destination folder of the jar file where you have also 
 *moved all your txt files , copy the path of the jar file and 
 *paste it like this in the command line : cd [path],
 *Finally execute this command : java -jar "skyline2333.jar" [filename.txt]
 *
 */

public class Skyline2333  {
    /**
     * 
     * @param args
     * @throws FileNotFoundException 
     */
    public static void main(String[] args) throws FileNotFoundException {
       //path is the name of the input file
       String path= args[0];
       Scanner file = new Scanner (new File(path));
      //storing the number of inputs in num_of_points
      int num_of_points= file.nextInt();
      int  [][] Array = new int [num_of_points][2];
      file.nextLine();
      int i;
      /*uploading the points for use from file.
       Array[i][0] are the x 
       Array[i][1] are the y
       where 0<i<num_of_points.
      */
      for (i=0;i<(num_of_points);i++){
          Array[i][0]=file.nextInt(); 
          Array[i][1]=file.nextInt();
          
      }      
      //sorts the array based on x in nlogn time (mergesort O(nlogn)
      skyline(Array,0,num_of_points-1);
      int min=0;
      int minx=0;
      i=0;
      /*sets the first minimum y of the @Array which
      it had sorted earlier based on x avoiding moving elements, only changing
      a pointer      
      */
      if (Array[i][0]==Array[i+1][0]){
          while((i+2)<(num_of_points)&& (Array[i][0]==Array[i+1][0])){
              if(Array[i+1][1]<Array[min][1]){
                  min=i+1;
              }
              i++;
          }
      }
      else{
          min=i;
      }
      //prints the first minimum.
      System.out.println(Array[min][0]+","+Array[min][1]);
      //Now continues to find the rest of the skyline points 
      //based on the first minimum which it found earlier.
      while((i+1)<num_of_points){
        //checks if current x is same with the next one
        if(Array[i][0]==Array[i+1][0]){
            minx=i;
            //it enters a loop to find the minimum of same x
            while((Array[i][0]==Array[i+1][0]) &&((i+2)<num_of_points)){
                if(Array[i+1][1]<Array[minx][1]){
                    minx=i+1;
                }
                i++;
            }
            i++;
            if(Array[minx][1]<Array[min][1]){
                System.out.println(Array[minx][0]+","+Array[minx][1]);
                min=minx;
            }
        }
        else{
            minx=i;
            if (Array[minx][1]<Array[min][1]){
                min=minx;
                System.out.println(Array[minx][0]+","+Array[minx][1]);
            }
            i++;
                        
        }
      }
    }/**
     * This basically divides the @Array to use the merge function recursively.
     * @param Array The Array that was given to be divided and then sorted.
     * @param left The first element of the Array that was given.
     * @param right  THe last element of the Array that was given.
     */
    private static void skyline(int [][]Array,int left,int right) {
        if (left < right)
        {
            int middle =(2*left+right-left)/2 ;
            skyline(Array,left,middle);
            skyline(Array,middle+1,right);            
            // Merge the sorted halves
            merge(Array,left,middle,right);      
        }
    }
    /**
     * This basically sorts the Array based on x and moves the y whenever there 
     * is a change of x as well . 
     * @param Array The Array that was given for sorting as a parameter.
     * @param left The first element of the Array that was given for sorting.
     * @param middle The middle element of the Array that was given for sorting.
     * @param right  The last element of the Array that was given for sorting.
     */
    private static void merge(int [][]Array,int left , int middle , int right){
        int i,k,j;
        int n1=middle-left+1;
        int n2=right-middle;
        /*create temp arrays*/
        int Left[][] = new int[n1][2];
        int Right[][]=new int [n2][2];
        /*Copy data to temp arrays Left[] and Right[]*/
        for (i=0;i<n1;i++){
            Left[i][0]=Array[left+i][0];
            Left[i][1]=Array[left+i][1];            
        }
        for (j=0;j<n2;j++){
            Right[j][0]=Array[middle + 1 + j][0];
            Right[j][1]=Array[middle+ 1 + j][1];
        }
        /*Merge the temp arrays back int Array*/
        i=0;
        j=0;
        k=left;
        while(i<n1 && j<n2)
        {
            //compares the x 
            if(Left[i][0] <= Right[j][0]){
                Array[k][0]=Left[i][0];
                Array[k][1]=Left[i][1];
                i++;
            }
            else{
                Array[k][0]=Right[j][0];
                Array[k][1]=Right[j][1];
                j++;
            }
            k++;
        }
        //If there are any elemets left merge them too with the rest.
        while(i<n1){
            Array[k][0]=Left[i][0];
            Array[k][1]=Left[i][1];
            i++;
            k++;
        }
        while(j<n2){
            Array[k][0]=Right[j][0];
            Array[k][1]=Right[j][1];
            j++;
            k++;
            
        }
        
        /*Copy the remaining elements*/
    }
    /*Material taken from https://www.geeksforgeeks.org/merge-sort/  and
    changed properly for 2-dimensional array */
    
    
}
    