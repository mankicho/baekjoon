import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int n = arr[0];
        int l = arr[1];
        int r = arr[2];

        long[][] dp = new long[n][n + 2];

        for (int i = 1; i < n; i++) {
            dp[0][i] = 1;
            dp[1][i] = n - i;
        }

        for (int i = 2; i < n; i++) {
            for (int j = 1; j < n; j++) {
                if (i + j <= n) {
                    for (int k = j + 1; k < n; k++) {
                        dp[i][j] += dp[i - 1][k];
                    }
                }
            }
        }

        for (long[] ints : dp) {
            System.out.println(Arrays.toString(ints));
        }

        System.out.println(((dp[l - 1][1] % 1000000007L) *
                (dp[r - 1][l + 1] == 0 ? 1 : (dp[r - 1][l + 1] % 1000000007))) % 1000000007);
    }


}
