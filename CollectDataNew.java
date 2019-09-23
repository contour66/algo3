     
import java.util.Arrays;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;


/** Class that calculates runtime prints them to file. 
   *Authors: Greg Marx and Zamir Thind
   *Programming Assignment 1
   *CPSC-3273-AO1 
   */

public class CollectDataNew {
   
  /** Main driver for class. 
   *@param args not used.
   *@throws IOException for print.
   */
   public static void main(String[] args) 
      throws IOException {
      
      // Declare, allocate array of 100000 ints, a[0]...a[999999]
      int[] huge = new int[100000];       
      
      //Creates new file and printwriter.
      FileWriter fw = new FileWriter("output.txt");
      PrintWriter pw = new PrintWriter(fw);
      pw.println("N " + "," + " InsertSort Time " + "," 
         + " MergeSort Time " + "," 
         + " QuickSort Time "); 
   
      //Assigns random values to each element.
      for (int i = 0; i < huge.length; i++) {
         huge[i] = (int) (Math.random() * 100000);  // Random number 0-999999
      }
      
      // Run and plot sortArray in increments of 10
      for (int n = 10; n <= huge.length; n += 1000) { 
         
         // Declare, allocate array of n ints
         int[] insertArray = new int[n];
         int[] mergeArray = new int[n];           
         int[] quickArray = new int[n];           
         

         // Copy n values from array huge into new array
         insertArray = Arrays.copyOf(huge, n);
         mergeArray = Arrays.copyOf(huge, n);
         quickArray = Arrays.copyOf(huge, n);       
        
        //+++++++++++++++++++++++++++++++++++++++++//  
        //****** CALCULATE INSERT SORT ************//
        //+++++++++++++++++++++++++++++++++++++++++//     
         
         // Get time prior to sorting
         long startTimeInsertSort = System.currentTimeMillis();          
         
         // Call sort method to sort new array
         insertSort(insertArray); 
      
         // Get time after sorting
         long endTimeInsertSort = System.currentTimeMillis(); 
         
          // Subtract endTime - startTime for elapsed time
         long elapsedTimeInsertSort = (endTimeInsertSort - startTimeInsertSort); 
           
         //+++++++++++++++++++++++++++++++++++++++++//  
         //****** CALCULATE MERGE SORT *************//
         //+++++++++++++++++++++++++++++++++++++++++//            
                 
         long startTimeMergeSort = System.currentTimeMillis();          
         
         // Call sort method to sort new array
         mergeSort(mergeArray, 0, mergeArray.length - 1); 
      
         // Get time after sorting
         long endTimeMergeSort = System.currentTimeMillis(); 
                     
         // Subtract endTime - startTime for elapsed time
         long elapsedTimeMergeSort = (endTimeMergeSort - startTimeMergeSort);  
         
          //+++++++++++++++++++++++++++++++++++++++++//  
         //****** CALCULATE QUICK SORT *************//
         //+++++++++++++++++++++++++++++++++++++++++//            
                 
         long startTimeQuickSort = System.currentTimeMillis();          
         
         // Call sort method to sort new array
         quickSort(quickArray, 0, quickArray.length - 1); 
      
         // Get time after sorting
         long endTimeQuickSort = System.currentTimeMillis(); 
                     
         // Subtract endTime - startTime for elapsed time
         long elapsedTimeQuickSort = (endTimeQuickSort - startTimeQuickSort);  
         

         
         
         
         // Prints length of new arrays        
         System.out.println("For n ints: " + insertArray.length);
         
         // Prints time elapsed for InsertSort
         System.out.println("InsertSort elapsed time: " + elapsedTimeInsertSort);
         
         // Prints time elapsed for MergeSort
         System.out.println("MergeSort elapsed time: " + elapsedTimeMergeSort);
      
         // Prints time elapsed for QuickSort
         System.out.println("QuickSort elapsed time: " + elapsedTimeQuickSort);
         // System.out.println("InsertSort ET: " + elapsedTime);            
      
                  
         // Write n and the T(n)/n*n to output.txt
         pw.println(n + ", " + elapsedTimeInsertSort + ", " + elapsedTimeMergeSort
         + ", " + elapsedTimeQuickSort); 
              
      }
      
      pw.close();
   }
   
   
   public static void merge(int A[], int p, int q, int r) {
   
   // Find sizes of two subarrays to merge
      int n1 = q - p + 1;
      int n2 = r - q;
      
   // Create temp arrays
      int L[] = new int[n1 + 1];
      int R[] = new int[n2 + 1];
      
      for (int i = 0; i < n1; i++)
         L[i] = A[p + i];
      for (int j = 0; j < n2; j++)
         R[j] = A[q + 1 + j];
         
      L[n1] = Integer.MAX_VALUE;
      R[n2] = Integer.MAX_VALUE;
   
   // Initialize indexes of first and second subarrays
      int i = 0;
      int j = 0;
   
   // Initial index of merged subarray
      for (int k = p; k <= r; k++) {
         if (L[i] <= R[j]) {
            A[k] = L[i];
            i = i + 1;
         }
         else {
            A[k] = R[j];
            j = j + 1;
         }
      }
   }

   public static void mergeSort(int A[], int p, int r) {
      if (p < r) {
         int q = ((p + r) / 2);
      
         mergeSort(A, p, q);
         mergeSort(A, q + 1, r);
      
         merge(A, p, q, r);
      }
   }
   
   public static void insertSort(int A[]){
      int n = A.length;
      for(int i = 1; i < n; ++i) {
         int key = A[i];
         int j = i - 1;
       
       /* Move elements of arr[0..i-1], that are 
          greater than key, to one position ahead 
          of their current position */
         while(j >= 0 && A[j] > key) {
            A[j + 1] = A[j];
            j = j -1;
         }
         A[j + 1] = key;
      }
   }
   
   public static int partition(int A[], int low, int high) {
   
      int pivot = A[high];
      int i = low - 1;
      for ( int j = low; j < high; j++) {
         if( A[j] < pivot) {
            i++;
            int temp = A[i];
            A[i] = A[j];
            A[j] = temp;
         }
         
      }
      int temp = A[i+1];
      A[i+1] = A[high];
      A[high] = temp;
      
      return i + 1;
   
   }
   
   public static void quickSort(int A[], int low, int high){
      if (low < high) {
      
         int partitionIndex = partition(A, low, high);
         
         quickSort(A, low, partitionIndex - 1);
         quickSort(A, partitionIndex + 1, high);
         
      }
   
   
   }
   
   
}
