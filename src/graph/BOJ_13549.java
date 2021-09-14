package graph;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class BOJ_13549 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int n = arr[0];
        int k = arr[1];

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o[1]));
        pq.add(new int[]{n, 0});

        int[] dp = new int[100001];

        Arrays.fill(dp, 1000000);
        dp[n] = 0;
        while (!pq.isEmpty()) {
            int[] poll = pq.poll();

            int p = poll[0];

            if (p == k) {
                System.out.println(poll[1]);
                break;
            }
            if (p >= 0 && dp[p - 1] > dp[p] + 1) {
                dp[p - 1] = dp[p] + 1;
                pq.add(new int[]{p - 1, dp[p] + 1});
            }

            if (p < 100000 && dp[p + 1] > dp[p] + 1) {
                dp[p + 1] = dp[p] + 1;
                pq.add(new int[]{p + 1, dp[p] + 1});
            }

            if (p * 2 <= 100000 && dp[p * 2] > dp[p]) {
                dp[p * 2] = dp[p];
                pq.add(new int[]{p * 2, dp[p]});
            }
        }
    }


}