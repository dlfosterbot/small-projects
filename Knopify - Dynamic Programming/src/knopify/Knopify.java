package knopify;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.lang.*;

/**
 *
 * @author Daniel Foster
 */
public class Knopify {


    /***
     * Input: Integer n1 that specifies length of binder 1, Integer n2 that specifies length of binder 2, followed by colors appended 
     * to each array
     * Output: The longest common subsequence of the two binders.
     * 
     * The LCS shares the order but is not necessarily a consecutive subsequence.
     * 
     * Uses dynamic programming and memoization in O(m+n) time.
     * 
     * 
     * 
     */
    
    //recursively prints solution
    public static void printLCS(int[][][] arr, String[] X, int i, int j) {
        if (i == 0 || j == 0) {
            return;
        }
        if (arr[1][i][j] == 1) {
            printLCS(arr, X, i - 1, j - 1);
            System.out.println(X[i - 1]);

        } else if (arr[1][i][j] == 2) {
            printLCS(arr, X, i - 1, j);
        } else {
            printLCS(arr, X, i, j - 1);
        }

    }
//creates tables
    public static int[][][] lcsLength(String[] str1, String[] str2) {
        int m = str1.length;
        int n = str2.length;

        //declare 3D array for storing the "b" and "c" table
        int[][][] arr = new int[2][m + 1][n + 1];

        //initialize row 1 and column 1 to zero of the first 2d array
        for (int i = 1, x = 0; i <= m; i++, x++) {
            for (int j = 1, y = 0; j <= n; j++, y++) {
                if (str1[x].contentEquals(str2[y])) {

                    arr[0][i][j] = arr[0][i - 1][j - 1] + 1;

                    //b array "diagonal arrow" recognized as 1
                    arr[1][i][j] = 1;

                } else if (arr[0][i - 1][j] >= arr[0][i][j - 1]) {

                    arr[0][i][j] = arr[0][i - 1][j];
                    //b array "up arrow" recognized as 2
                    arr[1][i][j] = 2;
                } else {

                    arr[0][i][j] = arr[0][i][j - 1];
                    //c array "left arrow" recognized as 3
                    arr[1][i][j] = 3;
                }
            }
        }
        return arr;
    }

    public static void main(String[] args) throws FileNotFoundException {

        //Scanner sin = new Scanner(System.in);
        
        File file = new File("test.txt");
        Scanner sc = new Scanner(file);

        int n = 2;
        int[] vals = new int[n];
        for (int i = 0; i < n; i++) {
            if (sc.hasNextInt()) {
                vals[i] = sc.nextInt();
                //System.out.println(vals[i]);
            }
        }
        
        //scans ints
       
       /*int[] vals = new int[2];
        for (int i = 0; i < 2; i++) {
            vals[i] = sin.nextInt();
        }*/
        //scans strings
        int size = vals[0] + vals[1];
        String[] string = new String[size];
        for (int i = 0; i < size; i++) {
            string[i] = sc.next();
           // System.out.println(string[i]);
        }
        sc.close();
        String[] binder1 = new String[vals[0]];
        String[] binder2 = new String[vals[1]];
        

        //initialize binders - array out of bounds possible error
        for (int i = 0; i < binder1.length; i++) {
            binder1[i] = string[i];
            
        }
        for (int i = binder1.length, j = 0; j < binder2.length; i++, j++) {
            binder2[j] = string[i];
        }
        int[][][] bcArr = lcsLength(binder1, binder2);
       printLCS(bcArr, binder1, binder1.length, binder2.length);

    }

}
