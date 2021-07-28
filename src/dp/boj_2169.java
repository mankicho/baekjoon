import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

class boj_2169 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int n = arr[0];
        int m = arr[1];

        int[][] map = new int[n][m];

        for (int i = 0; i < n; i++) {
            map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        int[][] dp = new int[n][m];
        int[][] temp = new int[n][m];
        int[][] temp2 = new int[n][m];

        dp[0][0] = map[0][0];
        for (int i = 1; i < m; i++) {
            dp[0][i] = map[0][i] + dp[0][i - 1];
        }
        for (int i = 1; i < n; i++) {

            temp[i][0] = dp[i - 1][0] + map[i][0];
            temp2[i][m - 1] = dp[i - 1][m - 1] + map[i][m - 1];
            for (int j = 1; j < m; j++) {
                temp[i][j] = Math.max(dp[i - 1][j], temp[i][j - 1]) + map[i][j];
            }

            for (int j = m - 2; j >= 0; j--) {
                temp2[i][j] = Math.max(dp[i - 1][j], temp2[i][j + 1]) + map[i][j];
            }

            for (int j = 0; j < m; j++) {
                dp[i][j] = Math.max(temp[i][j], temp2[i][j]);
            }
        }

        System.out.println(dp[n - 1][m - 1]);
    }
}
