package priority_queue;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

class boj_1600 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int k = Integer.parseInt(br.readLine());
        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int w = arr[0];
        int h = arr[1];

        int[][] map = new int[h][w];

        for (int i = 0; i < h; i++) {
            map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        }
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[2] == o2[2]) {
                    if (o1[1] == o2[1]) {
                        return o1[0] - o2[0];
                    }
                    return o1[1] - o2[1];
                }
                return o1[2] - o2[2];
            }
        });

        pq.add(new int[]{0, 0, 0, 0});

        int[][] dir = new int[][]{
                {0, 1}, {1, 0}, {0, -1}, {-1, 0}
        };

        int[][] horseDir = new int[][]{
                {-1, -2}, {-1, -2}, {-2, 1}, {-1, 2},
                {1, -2}, {2, -1}, {2, 1}, {1, 2}
        };

        int[][][] dp = new int[k + 1][h][w];
        for (int i = 0; i <= k; i++) {
            for (int[] ints : dp[i]) {
                Arrays.fill(ints, 10000000);
            }
        }

        dp[0][0][0] = 0;
        int ans = -1;
        while (!pq.isEmpty()) {
            int[] now = pq.poll();

            int row = now[0];
            int col = now[1];
            int step = now[2];
            int horseMoving = now[3];

//            System.out.println(row + " " + col + " " + step + " " + horseMoving);
            if (row == map.length - 1 && col == map[0].length - 1) {
                ans = step;
                break;
            }

            for (int i = 0; i < dir.length; i++) {
                int[] d = dir[i];

                int endRow = row + d[0];
                int endCol = col + d[1];

                if (!((endRow >= 0 && endRow < map.length) && (endCol >= 0 && endCol < map[0].length))
                        || map[endRow][endCol] == 1) {
                    continue;
                }

                if (dp[horseMoving][endRow][endCol] > dp[horseMoving][row][col] + 1) {
                    dp[horseMoving][endRow][endCol] = dp[horseMoving][row][col] + 1;
                    pq.add(new int[]{endRow, endCol, dp[horseMoving][endRow][endCol], horseMoving});
                }
            }

            if (horseMoving < k) {

                for (int i = 0; i < horseDir.length; i++) {
                    int[] hd = horseDir[i];

                    int endRow = row + hd[0];
                    int endCol = col + hd[1];

                    if (!((endRow >= 0 && endRow < map.length) && (endCol >= 0 && endCol < map[0].length))
                            || map[endRow][endCol] == 1) {
                        continue;
                    }

                    if (dp[horseMoving + 1][endRow][endCol] > dp[horseMoving][row][col] + 1) {
                        dp[horseMoving + 1][endRow][endCol] = dp[horseMoving][row][col] + 1;
                        pq.add(new int[]{endRow, endCol, dp[horseMoving + 1][endRow][endCol], horseMoving + 1});
                    }
                }
            }

        }

        System.out.println(ans);
    }
}