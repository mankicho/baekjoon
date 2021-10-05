package dp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_1256 {
    static int[][] dir = new int[][]{
            {0, 1}, {1, 0}, {0, -1}, {-1, 0}
    };

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int n = arr[0];
        int m = arr[1];
        int k = arr[2];

        long[][] dp = new long[n + 1][m + 1];

        initDP(dp, n, m);

        if(dp[n][m] < k){
            System.out.println(-1);
            return;
        }
        StringBuilder sb = new StringBuilder();
        function(sb, dp, n, m, k);
        System.out.println(sb.toString());
    }

    static long initDP(long[][] dp, int a, int z) {
        if (a == 0 || z == 0) {
            return 1;
        }
        if (dp[a][z] != 0) {
            return dp[a][z];
        }
        return dp[a][z] = Math.min(initDP(dp, a - 1, z) + initDP(dp, a, z - 1), 1000000001);
    }

    static void function(StringBuilder sb, long[][] dp, int a, int z, long k) {
        if (a == 0) {
            for (int i = 0; i < z; i++) {
                sb.append('z');
            }
            return;
        }

        if (z == 0) {
            for (int i = 0; i < a; i++) {
                sb.append('a');
            }
            return;
        }

        long tmp = dp[a - 1][z];
        if (tmp == 0) {
            tmp = 1;
        }
        if (k > tmp) {
            sb.append('z');
            function(sb, dp, a, z - 1, k - tmp);
        } else {
            sb.append('a');
            function(sb, dp, a - 1, z, k);
        }
    }
}