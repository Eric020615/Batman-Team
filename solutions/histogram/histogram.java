package histogram;

import java.io.*;
import java.util.*;

public class histogram {
    
    public static class MergeSort {

        /** The no-argument constructor with an empty body */
        public MergeSort() {
        }

        /** The method for sorting the data */
        public void mergeSort(int[] list) {
            if (list.length > 1) {
                // Merge sort the first half
                int[] firstHalf = new int[list.length / 2];
                System.arraycopy(list, 0, firstHalf, 0, list.length / 2);
                mergeSort(firstHalf);

                // Merge sort the second half
                int secondHalfLength = list.length - list.length / 2;
                int[] secondHalf = new int[secondHalfLength];
                System.arraycopy(list, list.length / 2, secondHalf, 0, secondHalfLength);
                mergeSort(secondHalf);

                // Merge firstHalf with secondHalf into list
                merge(firstHalf, secondHalf, list);
            }
        }

        /** The method to merge two sorted lists */
        public void merge(int[] list1, int[] list2, int[] temp) {
            int current1 = 0; // Current index in list1
            int current2 = 0; // Current index in list2
            int current3 = 0; // Current index in temp

            while (current1 < list1.length && current2 < list2.length) {
                if (list1[current1] < list2[current2])
                    temp[current3++] = list1[current1++];
                else
                    temp[current3++] = list2[current2++];
            }

            while (current1 < list1.length)
                temp[current3++] = list1[current1++];

            while (current2 < list2.length)
                temp[current3++] = list2[current2++];
        }

    }

    /** The main method to start with 2 lines of input, end with printing 2 lines of output
     *  @param args
     *  @throws IOException */
    public static void main(String[] args) throws IOException {
        // Create an object ms to invoke the MergeSort class
        MergeSort ms = new MergeSort();
//        long startime = new Date().getTime();
        // Create a variable dir of File type to store the directory of histogram test cases folder
        File dir = new File("C:\\Users\\USER\\IdeaProjects\\Batman-Team\\solutions\\histogram\\cases");
        // List and store all the .txt files in an array files of File type
        File[] files = dir.listFiles();
        // Start for to loop through every file in the array
        for (File file : files) {
            if (file.isFile()) {
                Scanner sc = null;
                try {
                    // Make an input stream of the particular .txt file
                    sc = new Scanner(new FileInputStream(file));
                    // Start while to check whether the file has nextLine or not
                    while (sc.hasNextLine()) {
                        // Store the first line of String type in a variable str1
                        String str1 = sc.nextLine();
                        // Split the first line contents and store them in an array num1 of String type
                        String[] num1 = str1.split(" ");
                        // N represents the number of data points
                        // M represents the number of bins
                        // Store the first element in a variable N of int type
                        // Store the second element in a variable M of int type
                        int N = Integer.parseInt(num1[0]);
                        int M = Integer.parseInt(num1[1]);
                        // Store the second line of String type in a variable str2
                        String str2 = sc.nextLine();
                        // Split the second line contents and store them in an array num2 of String type
                        String[] num2 = str2.split(" ");
                        // Create an array data of int type to store the data points
                        int[] data = new int[N];
                        // Start for to store every data point in the array data
                        for (int i = 0; i < N; i++) {
                            data[i] = Integer.parseInt(num2[i]);
                        } // End for
                        /* Invoke the mergeSort method in the MergeSort class to
                           sort and arrange the data points in ascending order */
                        ms.mergeSort(data);
                        // The formula to calculate the width of an interval
                        // Store the value of calculation in a variable width of int type
                        int width = (data[N - 1] - data[0]) / M;
                        // Create an array interval of int type to store the cutoffs for the histogram
                        int[] interval = new int[M + 1];
                        interval[0] = data[0];
                        // Start for to calculate and store the cutoffs of every interval
                        for (int i = 1; i < interval.length; i++) {
                            interval[i] = interval[i - 1] + width;
                        } // End for
                        // Start for to print out the cutoffs of every interval for the histogram
                        for (int i = 0; i < interval.length; i++) {
                            System.out.print(interval[i] + " ");
                        } // End for
                        System.out.println();
                        // Create an array count of int type to store the counts for each interval
                        int[] count = new int[M];
                        // Start for to calculate the counts for each interval
                        for (int i = 0; i < M; i++) {
                            // Start for to loop through every data point in a particular interval
                            for (int j = 0; j < data.length; j++) {
                                if (i == M - 1) {
                                    if (data[j] >= interval[i] && data[j] <= interval[i + 1]) {
                                        count[i]++;
                                    }
                                } else {
                                    if (data[j] >= interval[i] && data[j] < interval[i + 1]) {
                                        count[i]++;
                                    }
                                }
                            } // End for
                        } // End for
                        // Start for to print out the counts for each interval
                        for (int i = 0; i < count.length; i++) {
                            System.out.print(count[i] + " ");
                        } // End for
                        System.out.println();
                    } // End while
                } catch (IOException e) {
                    // Display the message about the I/O exception
                    System.out.println("File got some problems.");
                } finally {
                    if (sc != null)
                        sc.close();
                }
            } // End if
//            long Endtime = new Date().getTime();
//            long difference = Endtime - startime;
//            System.out.println("The execution time is " + difference + "ms");
        } // End for
    }

}