package org.program;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BinarySearchIterationParity {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int startRange = sc.nextInt();
        int endRange = sc.nextInt();
        sc.close();

        List<String> results = findNumbersWithSameIterations(startRange, endRange);
        if (results.isEmpty()) {
            System.out.println("No matching pairs found.");
        } else {
            for (String result : results) {
                System.out.println(result);
            }
        }
    }

    public static List<String> findNumbersWithSameIterations(int start, int end) {
        List<String> matches = new ArrayList<>();
        for (int num = start; num <= end; num++) {
            int iterations = 0;
            int reverseIterations = 0;
            int reverse = reverseNumber(num);
            if (num != reverse) {
                iterations = binarySearchIterations(num, start, end);
                reverseIterations = binarySearchIterations(reverse, start, end);
            }

            if (iterations != -1 && iterations != 0 && iterations == reverseIterations) {
                matches.add("Number: " + num + ", Reverse: " + reverse + ", Iterations: " + iterations);
            }
        }
        return matches;
    }

    public static int binarySearchIterations(int target, int start, int end) {
        int iterations = 0;
        int left = start;
        int right = end;

        while (left <= right) {
            iterations++;
            int mid = left + (right - left) / 2;

            if (mid == target) {
                return iterations;
            } else if (mid < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }

    public static int reverseNumber(int num) {
        int reversed = 0;
        while (num != 0) {
            int digit = num % 10;
            reversed = reversed * 10 + digit;
            num /= 10;
        }
        return reversed;
    }
}
