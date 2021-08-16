package binary_search;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_22945 {
    static StringBuilder result = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        if (n <= 3) {
            System.out.println((arr.length - 2) * Math.min(arr[arr.length - 1], arr[0]));
            return;
        }

        int start = 0;
        int end = arr.length - 1;

        int max = 0;
        while (start < end) {
            max = Math.max(max, (end - start - 1) * Math.min(arr[start], arr[end]));
            if (arr[end] > arr[start]) {
                start++;
            } else {
                end--;
            }
        }
        System.out.println(max);

    }
}