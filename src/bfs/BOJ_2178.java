package bfs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class BOJ_2178 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int n = arr[0];
        int m = arr[1];

        int[][] map = new int[n][m];

        for (int i = 0; i < n; i++) {
            map[i] = Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
        }

        int[][] dp = new int[n][m];
        for (int[] ints : dp) {
            Arrays.fill(ints, 987654321);
        }

        dp[0][0] = 1;

        PriorityQueue<int[]> queue = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[2] - o2[2];
            }
        });

        int[][] dir = new int[][]{
                {1, 0}, {0, 1}, {-1, 0}, {0, -1}
        };

        queue.add(new int[]{0, 0, 0});

        while (!queue.isEmpty()) {
            int[] poll = queue.poll();

            int row = poll[0];
            int col = poll[1];

            for (int i = 0; i < dir.length; i++) {
                int[] d = dir[i];
                int endRow = d[0] + row;
                int endCOl = d[1] + col;

                if (!((endRow >= 0 && endRow < n) && (endCOl >= 0 && endCOl < m))
                        || map[endRow][endCOl] == 0) {
                    continue;
                }

                if (dp[endRow][endCOl] > dp[row][col] + 1) {
                    dp[endRow][endCOl] = dp[row][col] + 1;
                    queue.add(new int[]{endRow, endCOl, dp[endRow][endCOl]});
                }
            }
        }
        System.out.println(dp[n-1][m-1]);
    }
}