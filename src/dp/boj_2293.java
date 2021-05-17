package dp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class boj_2293 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int n = arr[0];
        int k = arr[1];

        int[] values = new int[n];
        for (int i = 0; i < n; i++) {
            values[i] = Integer.parseInt(br.readLine());
        }

        int[] dp = new int[k + 1];

        dp[0] = 1;

        for (int j = 0; j < values.length; j++) {
            int value = values[j];
            for (int i = 1; i <= k; i++) {
                if(i-value < 0){
                    continue;
                }
                dp[i] += dp[i - value] ;
            }
        }

        System.out.println(dp[k]);
    }
}
