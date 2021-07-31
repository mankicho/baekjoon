package dp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

class boj_14226 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int[][] dp = new int[n + 1][n + 1];
        for (int[] ints : dp) {
            Arrays.fill(ints, 100000);
        }
        dp[0][1] = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o[2]));

        pq.add(new int[]{1, 0, 0});

        while (!pq.isEmpty()) {
            int[] poll = pq.poll();

            int screen = poll[0];
            int clipBoard = poll[1];

            if (screen == n) {
                break;
            }
            if (screen <= n) {
                if (dp[screen][screen] > dp[clipBoard][screen] + 1) {
                    dp[screen][screen] = dp[clipBoard][screen] + 1;
                    pq.add(new int[]{screen, screen, dp[screen][screen]});
                }
            }

            if (screen + clipBoard <= n) {
                if (dp[clipBoard][screen + clipBoard] > dp[clipBoard][screen] + 1) {
                    dp[clipBoard][screen + clipBoard] = dp[clipBoard][screen] + 1;
                    pq.add(new int[]{screen + clipBoard, clipBoard, dp[clipBoard][screen + clipBoard]});
                }
            }

            if (screen - 1 >= 0) {
                if (dp[clipBoard][screen - 1] > dp[clipBoard][screen] + 1) {
                    dp[clipBoard][screen - 1] = dp[clipBoard][screen] + 1;
                    pq.add(new int[]{screen - 1, clipBoard, dp[clipBoard][screen - 1]});
                }
            }
        }
        int min = 100000;
        for (int i = 0; i < dp.length; i++) {
            if(dp[i][n]<min){
                min = dp[i][n];
            }
        }
        System.out.println(min);
    }


}
