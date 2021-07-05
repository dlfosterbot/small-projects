
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.Math;
import java.util.Scanner;

//Author: Daniel Foster

/* Inputs file of integers and finds a maximum relibability score of the optimal subarray where 0 is the least reliable, 
50 is neutral and 100 is most reliable. 
The reliability score of a single integer x is x - 50. 
In the example test file, the optimal sub array is 52, 60, 55 where the individual reliability scores are 2, 10, and 5 thus making
a total reliability score of 17. 

The program implements a divide and conquer algorithm to run in O(nlgn) time.


 */
class MaxCrossSubarray {

    final int maxLeft;
    final int maxRight;
    final int totalSum;

    public MaxCrossSubarray(int maxLeft, int maxRight, int totalSum) {
        this.maxLeft = maxLeft;
        this.maxRight = maxRight;
        this.totalSum = totalSum;
    }

    public int getMaxLeft() {
        return maxLeft;
    }

    public int getMaxRight() {
        return maxRight;
    }

    public int getTotalSum() {
        return totalSum;
    }

}

class MaxSubArrayResult {

    final int low;
    final int high;
    final int sum;

    public MaxSubArrayResult(int low, int high, int sum) {
        this.low = low;
        this.high = high;
        this.sum = sum;

    }

    public int getLow() {
        return low;
    }

    public int getHigh() {
        return high;
    }

    public int getSum() {
        return sum;
    }

}

public class MaxReliabilityScore {

    public static MaxSubArrayResult FindMaxSubArray(int[] A, int low, int high) {

        if (high == low) {

            return new MaxSubArrayResult(low, high, A[low] - 50);
        } else {
            int lowHighSum = low + high;
            int mid = (int) lowHighSum / 2;
            MaxSubArrayResult left = FindMaxSubArray(A, low, mid);
            // System.out.println("Left Low: " + left.getLow() + " Left High: " + left.getHigh() + " Left Sum: " + left.getSum());

            MaxSubArrayResult right = FindMaxSubArray(A, mid + 1, high);
            //System.out.println("Right Low: " + right.getLow() + " Right  High: " + right.getHigh() + " Right Sum: " + right.getSum());
            MaxCrossSubarray cross = MaxCrossSubarray(A, low, mid, high);
            //System.out.println("Cross Low: " + cross.getMaxLeft() + " Cross High: " + cross.getMaxRight() + " Cross Sum: " + cross.getTotalSum());
            if (left.getSum() >= right.getSum() && left.getSum() >= cross.getTotalSum()) {
                //System.out.println("returning left");
                return left;
            } else if (right.getSum() >= left.getSum() && right.getSum() >= cross.getTotalSum()) {
                //System.out.println("returning right");
                return right;
            } else {
                MaxSubArrayResult crossResult = MaxCrossSubArray(cross.getMaxLeft(), cross.getMaxRight(), cross.getTotalSum());
                //System.out.println("returning cross");
                return crossResult;
            }

        }

    }

    public static MaxCrossSubarray MaxCrossSubarray(int[] A, int low, int mid, int high) {
        int leftSum = -99999999;
        int sum = 0;
        int maxLeft = 0;
        int maxRight = 0;
        for (int i = mid; i >= low; i--) {
            sum = sum + A[i] - 50;
            //System.out.println("left sum " + sum);
            if (sum > leftSum) {
                leftSum = sum;
                maxLeft = i;
            }
        }
        int rightSum = -99999999;
        sum = 0;
        //System.out.println("partition");
        for (int j = mid + 1; j <= high; j++) {
            sum = sum + A[j] - 50;
            //System.out.println("right sum " + sum);
            if (sum > rightSum) {
                rightSum = sum;
                maxRight = j;
            }
        }
        int totalSum = leftSum + rightSum;
        //System.out.println(maxRight);
        //MaxCrossSubArrayResult result = new MaxCrossSubArrayResult(maxLeft, maxRight, totalSum);
        return new MaxCrossSubarray(maxLeft, maxRight, totalSum);

    }

    public static void main(String[] args) throws FileNotFoundException {
        File file
                = new File("test.txt");
        Scanner sc = new Scanner(file);

        int n = sc.nextInt();
        int[] vals = new int[n];
        for (int i = 1; i < n; i++) {
            if (sc.hasNextInt()) {
                vals[i] = sc.nextInt();

            }
        }
        sc.close();

        MaxSubArrayResult optimalSum = FindMaxSubArray(vals, 0, vals.length - 1);
        int lo = optimalSum.getLow();
        int hi = optimalSum.getHigh();
        int optSum = optimalSum.getSum();

        printResults(vals, lo, hi, optSum);

    }

    private static MaxSubArrayResult MaxCrossSubArray(int maxLeft, int maxRight, int totalSum) {

        return new MaxSubArrayResult(maxLeft, maxRight, totalSum);
    }

    public static void printResults(int[] arr, int lo, int hi, int sum) {

     
        System.out.print("Optimal Sub Array: [");
        for (int x = lo; x <= hi; x++) {
            if (x != hi) {
                System.out.print("" + arr[x] + ", ");
            } else {
                System.out.println("" + arr[x] + "]");
            }
        }
        
        //Prints maximum sum of sub arrays
        System.out.println("Optimal Reliability Score: " + sum);
    }

}
