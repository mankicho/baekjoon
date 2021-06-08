package dp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class boj_11054 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();


        int[] dp1 = new int[arr.length];
        int[] dp2 = new int[arr.length];
        Arrays.fill(dp1, 1);
        for (int i = 1; i < arr.length; i++) {
            int val = arr[i];
            for (int j = 0; j < i; j++) {
                if (val > arr[j]) {
                    dp1[i] = Math.max(dp1[i], dp1[j] + 1);
                }
            }
        }

        Arrays.fill(dp2, 1);
        for (int i = arr.length - 1; i >= 0; i--) {
            int val = arr[i];
            for (int j = arr.length - 1; j > i; j--) {
                if (val > arr[j]) {
                    dp2[i] = Math.max(dp2[i], dp2[j] + 1);
                }
            }
        }
        int max = 0;
        for (int i = 0; i < dp1.length; i++) {
            if (dp1[i] + dp2[i] > max) {
                max = dp1[i] + dp2[i];
            }
        }
        System.out.println(max - 1);
    }
}