package segment_tree;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class boj_11659 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int n = arr[0];
        int m = arr[1];

        int[] val = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] dp = new int[n + 1];

        for (int i = 0; i < val.length; i++) {
            dp[i + 1] = dp[i] + val[i];
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < m; i++) {
            int[] tmp = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int a = tmp[0];
            int b = tmp[1];

            if (a > b) {
                sb.append(dp[a] - dp[b - 1]).append("\n");

            } else {
                sb.append(dp[b] - dp[a - 1]).append("\n");
            }
        }
        System.out.println(sb.toString());
    }
}