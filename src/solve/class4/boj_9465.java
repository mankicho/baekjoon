package solve.class4;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class boj_9465 {

    static boolean[][] visited;
    static int[][] open;
    static List<int[]> tmpList = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for (int j = 0; j < t; j++) {
            int n = Integer.parseInt(br.readLine());

            int[][] arr = new int[2][n];
            for (int i = 0; i < 2; i++) {
                arr[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            }

            int[][] dp = new int[2][n];

            dp[0][0] = arr[0][0];
            dp[1][0] = arr[1][0];
            if(n==1){
                sb.append(Math.max(dp[0][0],dp[1][0])).append("\n");
                continue;
            }
            dp[0][1] = dp[1][0] + arr[0][1];
            dp[1][1] = dp[0][0] + arr[1][1];

            for (int i = 2; i < arr[0].length; i++) {
                dp[0][i] = Math.max(dp[1][i - 1], dp[1][i - 2]) + arr[0][i];
                dp[1][i] = Math.max(dp[0][i - 1], dp[0][i - 2]) + arr[1][i];
            }

            sb.append(Math.max(dp[0][n - 1], dp[1][n - 1])).append("\n");
        }
        System.out.println(sb.toString());
    }
}