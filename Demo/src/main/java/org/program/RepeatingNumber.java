package org.program;

public class RepeatingNumber {

    private static int findRepeatingNumberMoreThanHalfOfArraySize(int[] arr) {
        int n = arr.length;
        int count = 1, element = arr[0];

        for (int i = 1; i < n; i++) {
            if (arr[i] == element) {
                count++;
            } else {
                count--;
                if (count == 0) {
                    element = arr[i];
                    count = 1;
                }
            }
        }

        // Check if element is the majority element
        count = 0;
        for (int num : arr) {
            if (num == element) {
                count++;
            }
        }

        if (count > n / 2) {
            return element;
        }

        return -1;
    }
    public static void main(String[] args) {
        int[] arr = new int[]{3,3,3,2,1,3,7,3};
        int res = findRepeatingNumberMoreThanHalfOfArraySize(arr);
        System.out.println("res: " + res);
    }
}
