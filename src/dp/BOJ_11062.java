package dp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

class BOJ_11062 {
    static int[][][] dp;
    static int[] arr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringBuilder result = new StringBuilder();
        int testcase = Integer.parseInt(br.readLine());
        for (int i = 0; i < testcase; i++) {
            br.readLine();
            arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            dp = new int[2][arr.length + 1][arr.length + 1];

            for (int j = 0; j < 2; j++) {
                for (int k = 1; k <= arr.length; k++) {
                    for (int l = 0; l <= arr.length; l++) {
                        dp[j][k][l] = -1;
                    }
                }
            }

            result.append(recursion(0, 1, arr.length)).append("\n");
        }
        System.out.println(result.toString());
    }

    static int recursion(int turn, int start, int end) {
        if (dp[turn][start][end] != -1) {
            return dp[turn][start][end];
        }

        if (start >= end) {
            if (turn == 0) {
                return arr[start - 1];
            } else {
                return 0;
            }
        }

        if (turn == 0) {
            dp[turn][start][end] = Math.max(arr[start - 1] + recursion(turn + 1, start + 1, end),
                    arr[end - 1] + recursion(turn + 1, start, end - 1));
        } else {
            dp[turn][start][end] = Math.min(recursion(turn - 1, start + 1, end), recursion(turn - 1, start, end - 1));
        }
        return dp[turn][start][end];
    }


}
