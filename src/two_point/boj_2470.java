package two_point;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class boj_2470 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        br.readLine();
        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        Arrays.sort(arr);

        int start = 0;
        int end = arr.length - 1;

        int result = Math.abs(arr[0] + arr[end]);
        int x = arr[0];
        int y = arr[arr.length - 1];

        while (start < end) {
            int sum = arr[start] + arr[end];
            int absSum = Math.abs(arr[start] + arr[end]);
            if (absSum < result) {
                result = absSum;
                x = arr[start];
                y = arr[end];
            }
            if (sum < 0) {
                start++;
            } else {
                end--;
            }
        }

        System.out.println(x + " " + y);
    }
}