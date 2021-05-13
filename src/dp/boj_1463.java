package dp;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class boj_1463 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int[] dp = new int[n + 1];

        if(n ==1){
            System.out.println(0);
            return ;
        }
        if(n<4){
            System.out.println(1);
            return ;
        }
        dp[1] = 0;
        dp[2] = 1;
        dp[3] = 1;

        for (int i = 4; i < dp.length; i++) {
            if (i % 3 == 0) {
                if (i % 2 == 0) {
                    dp[i] = Math.min(dp[i / 3], Math.min(dp[i / 2], dp[i - 1])) + 1;
                } else {
                    dp[i] = Math.min(dp[i / 3], dp[i - 1]) + 1;
                }
            } else if (i % 2 == 0) {
                dp[i] = Math.min(dp[i / 2], dp[i - 1]) + 1;
            } else {
                dp[i] = dp[i - 1] + 1;
            }
        }

        System.out.println(dp[n]);
    }
}
