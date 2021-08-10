package brute_force;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BOJ_15684 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int n = arr[0];
        int m = arr[1];
        int h = arr[2];

        int[][] dp = new int[h + 2][n + 1];
        List<int[]> list = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            dp[arr[0]][arr[1]] = 1;
            dp[arr[0]][arr[1] + 1] = -1;
            list.add(arr);
        }

        if (list.size() == 0) {
            System.out.println(0);
        } else {
            for (int i = 0; i <= 3; i++) {
//                System.out.println("now threadshold = " + i);
                backTracking(i, 0, n, h, dp, 0);
                if (finish) {
                    System.out.println(i);
                    return;
                }
            }
            System.out.println(-1);

        }
    }

    static boolean finish = false;

    static void function(int h, int n, int[][] dp) {
//        int[][] tmp = new int[h + 1][n + 1];
//        for (int i = 1; i < tmp[0].length; i++) {
//            tmp[0][i] = i;
//        }
//        for (int i = 1; i <= h; i++) {
//            for (int j = 1; j <= n; j++) {
//                if (dp[i][tmp[i - 1][j]] == 0) {
//                    tmp[i][j] = tmp[i - 1][j];
//                } else {
//                    tmp[i][j] = tmp[i - 1][j] + dp[i][tmp[i - 1][j]];
//                }
//            }
//        }
//        boolean f = true;
//        for (int i = 1; i <= n; i++) {
//            if (tmp[h][i] != i) {
//                f = false;
//                break;
//            }
//        }
//        if (f) {
//            finish = true;
//        }
        for (int i = 1; i <= n; i++) {
            int x = 1;
            int y = i;
            for (int j = 1; j <= h; j++) {
                if (dp[x][y] == 1) {
                    y++;
                } else if (dp[x][y] == -1) {
                    y--;
                }
                x++;
            }
            if(y != i){
                finish = false;
                return;
            }
        }
        finish= true;
    }

    static void backTracking(int threadshold, int num, int n, int h, int[][] dp, int start) {
        if (threadshold == num) {
            function(h, n, dp);

            return;
        }
        if (finish) {
            return;
        }

        LOOP:
        for (int i = start; i <= h; i++) {
            for (int j = 1; j < n; j++) {
                if (finish) {
                    return;
                }
                if (dp[i][j] == 0 && dp[i][j + 1] == 0) {
                    dp[i][j] = 1;
                    dp[i][j + 1] = -1;
//                        System.out.println(i + " " + j + " " + (j + 1));
                    backTracking(threadshold, num + 1, n, h, dp, i);
                    dp[i][j] = 0;
                    dp[i][j + 1] = 0;
                }
            }
        }
    }
}