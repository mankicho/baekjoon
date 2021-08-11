package sort;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BOJ_8980 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int n = arr[0];
        int c = arr[1];

        int m = Integer.parseInt(br.readLine());
        List<int[]> list = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            list.add(arr);
        }
        list.sort((o1, o2) -> {
            if (o1[1] == o2[1]) {
                return o1[0] - o2[0];
            }
            return o1[1] - o2[1];
        });

//        list.forEach(ar -> System.out.println(Arrays.toString(ar)));


        int[][] dp = new int[m + 1][n + 1];

        int[] accDP = new int[n + 1];

        int idx = 0;
        int ans = 0;
        for (int[] thing : list) {
            int start = thing[0];
            int end = thing[1];
            int num = thing[2];

            for (int i = start; i < end; i++) {
                if (i > start) {
                    num = dp[idx][i - 1];
                }
                if (accDP[i] + num > c) {
                    dp[idx][i] = c - accDP[i];
                    accDP[i] = c;
                } else {

                    dp[idx][i] = num;
                    accDP[i] += num;
                }
            }
            ans += dp[idx][end-1];
            idx++;
        }
        System.out.println(ans);
    }
}