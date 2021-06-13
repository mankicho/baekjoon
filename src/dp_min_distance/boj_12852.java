package dp_min_distance;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class boj_12852 {
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int[] pre = new int[n + 1];
        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[n] = 0;
        StringBuilder sb = new StringBuilder();
        for (int i = n; i >= 1; i--) {
            if (i % 3 == 0) {
                if (dp[i / 3] > dp[i] + 1) {
                    dp[i / 3] = dp[i] + 1;
                    pre[i / 3] = i;
                }
            }
            if (i % 2 == 0) {
                if (dp[i / 2] > dp[i] + 1) {
                    dp[i / 2] = dp[i] + 1;
                    pre[i / 2] = i;
                }
            }
            if (dp[i - 1] > dp[i] + 1) {
                dp[i - 1] = dp[i] + 1;
                pre[i - 1] = i;
            }
        }


        int idx = 1;
        while (idx != n) {
            sb.append(idx).append(" ");
            idx = pre[idx];
        }
        sb.append(n);
        System.out.println(dp[1]);
        String[] split = sb.toString().split(" ");

        StringBuilder tmp = new StringBuilder();
        for(int i= split.length-1;i>=0;i--){
            tmp.append(Integer.parseInt(split[i])).append(" ");
        }
        System.out.println(tmp.toString());
    }

}