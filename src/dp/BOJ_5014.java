package dp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

class BOJ_5014 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int f = arr[0];
        int s = arr[1];
        int g = arr[2];
        int u = arr[3];
        int d = arr[4];

        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1];
            }
        });


        pq.add(new int[]{s, 0});
        int[] dp = new int[f + 1];

        Arrays.fill(dp, 10000000);
        dp[s] = 0;
        int ans = -1;
        while (!pq.isEmpty()) {
            int[] poll = pq.poll();

            int now = poll[0];
            int step = poll[1];

            if (now + u <= f) {
                if (dp[now + u] > dp[now] + 1) {
                    dp[now + u] = dp[now] + 1;
                    pq.add(new int[]{now + u, step + 1});
                }
            }

            if (now - d >= 1) {
                if (dp[now - d] > dp[now] + 1) {
                    dp[now - d] = dp[now] + 1;
                    pq.add(new int[]{now - d, step + 1});
                }
            }
        }

        System.out.println(dp[g] == 10000000 ? "use the stairs" : dp[g]);
    }


}
