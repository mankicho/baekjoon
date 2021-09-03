package dp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_1029 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int[][] datas = new int[n][n];

        for (int i = 0; i < n; i++) {
            datas[i] = Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
        }

        int[][][] dp = new int[n + 1][n + 1][(1 << n)];

        for (int[][] ints : dp) {
            for (int[] anInt : ints) {
                Arrays.fill(anInt, -1);
            }
        }
        for (int i = 2; i <= n; i++) {
            dp[1][i][(1 << (i - 1)) | 1] = datas[0][i - 1];
        }

        for (int m = 0; m < n; m++) {
            for (int j = 2; j <= n; j++) {
                for (int k = 1; k < (1 << n); k++) {
                    for (int i = 1; i <= n; i++) {
                        if (dp[i][j][k] != -1) { // 그림이 있으면
                            for (int l = 1; l < n; l++) {
//                            if (j == (l + 1)) {
//                                continue;
//                            }
                                if ((k & (1 << l)) != 0) { // 이미 갖고있음
                                    continue;
                                }

                                if (datas[j - 1][l] < dp[i][j][k]) {  // 판매가격이 구매했던가격보다 작으면
                                    continue;
                                }

                                dp[j][l + 1][(1 << l) | k] = datas[j - 1][l];
                            }
                        }
                    }
                }
            }
        }

//        for (int[] ints : dp) {
//            System.out.println(Arrays.toString(ints));
//        }
        int max = 0;
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[i].length; j++) {
                for (int k = 0; k < dp[i][j].length; k++) {

                    if (dp[i][j][k] != -1) {
                        int cnt = 0;
                        for (int l = 0; l < n; l++) {
                            if ((k & (1 << l)) != 0) {
                                cnt++;
                            }
                        }
                        max = Math.max(max, cnt);
                    }
                }
            }
        }
        System.out.println(max);
    }

}