package common_ancestor;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class boj_17435 {


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int m = Integer.parseInt(br.readLine());

        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int q = Integer.parseInt(br.readLine());

        int[][] dp = new int[m + 1][20];
        for (int i = 1; i < dp.length; i++) {
            dp[i][0] = i;
            dp[i][1] = arr[i - 1];
        }
        makeDP(dp, arr);
        for (int i = 0; i < q; i++) {
            int[] tmpArr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            int n = tmpArr[0];
            int x = tmpArr[1];
            function(dp, arr, n, x);
        }
        System.out.println(sb.toString());
    }


    static StringBuilder sb = new StringBuilder();

    static void function(int[][] dp, int[] arr, int n, int x) {

        int val = x;
        for (int i = 18; i >= 0; i--) {
            if ((n & (1 << i)) != 0) {
                val = dp[val][i + 1];
            }
        }
        sb.append(val).append("\n");
    }


    static void makeDP(int[][] dp, int[] arr) {
        for (int j = 1; j <= 18; j++) {
            for (int i = 1; i <= arr.length; i++) {
                dp[i][j + 1] = dp[dp[i][j]][j];
            }
        }

    }
}
