package org.program;

import java.util.Arrays;

public class LongestIncreasingSequence {
    public static int LongestIncSeq(int[] arr) {
        int n = arr.length;
        if (n == 1) return arr[0];

        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        for (int i = 0; i < n; i++) {
            for (int j = i+1; j < n; j++) {
                if (arr[i] < arr[j] && (dp[j] < dp[i]+1)) {
                    dp[j] = dp[i] + 1;
                }
            }
        }
        System.out.println(dp[n-1]);
        return dp[n-1];
    }

    public static void main (String[] args) {
        // keep this function call here
        System.out.print("Test 1: " + (LongestIncSeq(new int[] {9, 9, 4, 2}) == 1 ) + "\r\n");
        System.out.print("Test 2: " + (LongestIncSeq(new int[] {10, 22, 9, 33, 21, 50, 41, 60, 22, 68, 90}) ==  7 ) + "\r\n");
        System.out.print("Test 3: " + (LongestIncSeq(new int[] {1, 4, 9, 10, 5, 6}) == 4 ) + "\r\n");
    }
}
