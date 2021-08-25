package dp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_1102 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int[][] datas = new int[n + 1][n + 1];
        for (int i = 0; i < n; i++) {
            datas[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        String line = br.readLine();

        int y = 0;
        int light = 0;
        for (int i = 0; i < line.length(); i++) {
            if (line.charAt(i) == 'Y') {
                y++;
                light += (1 << i);
            }
        }

        int p = Integer.parseInt(br.readLine());

        if (y >= p) {
            System.out.println(0);
            return;
        }
        if (y == 0) {
            System.out.println(-1);
            return;
        }
        int[][] dp = new int[n + 1][(1 << n)];

        for (int[] ints : dp) {
            Arrays.fill(ints, 987654321);
        }

        dp[y][light] = 0;

        for (int i = y + 1; i <= p; i++) {
            for (int j = 1; j < (1 << n); j++) {
                if (dp[i - 1][j] == 987654321) {
                    continue;
                }
                for (int k = 0; k < n; k++) {
                    if ((j & (1 << k)) != 0) {
                        continue;
                    }
                    int min = 987654321;
                    for (int l = 0; l < n; l++) {
                        if ((j & (1 << l)) != 0) {
                            min = Math.min(min, datas[l][k]);
                        }
                    }
                    dp[i][(1 << k) | j] = Math.min(dp[i][(1 << k) | j], dp[i - 1][j] + min);
                }
            }
        }

//        for (int[] ints : dp) {
//            System.out.println(Arrays.toString(ints));
//        }
        int answer = 987654321;
        for (int i : dp[p]) {
            answer = Math.min(i, answer);
        }
        System.out.println(answer);
    }


}