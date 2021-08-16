package bfs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_5427 {
    static StringBuilder result = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int testcase = Integer.parseInt(br.readLine());
        for (int i = 0; i < testcase; i++) {
            int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            int n = arr[1];
            int m = arr[0];

            char[][] map = new char[n][m];
            int[] start = {};
            List<int[]> fires = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                map[j] = br.readLine().toCharArray();
                for (int k = 0; k < map[j].length; k++) {
                    if (map[j][k] == '*') {
                        fires.add(new int[]{j, k});
                    } else if (map[j][k] == '@') {
                        start = new int[]{j, k};
                    }
                }

            }
            int[][] dp = bfsFires(fires, map);
            bfs(fires, map, start, dp);
        }
        System.out.println(result.toString());
    }

    static int[][] bfsFires(List<int[]> fires, char[][] maps) {
        int[][] dir = new int[][]{
                {1, 0}, {0, 1}, {-1, 0}, {0, -1}
        };
        int[][] dp = new int[maps.length][maps[0].length];
        for (int[] ints : dp) {
            Arrays.fill(ints,987654321);
        }
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o[2]));

        fires.forEach(fire -> {
            pq.add(new int[]{fire[0], fire[1], 0});
            dp[fire[0]][fire[1]] = 0;
        });

        while (!pq.isEmpty()) {
            int[] poll = pq.poll();

            int r = poll[0];
            int c = poll[1];

            for (int i = 0; i < dir.length; i++) {
                int[] d = dir[i];
                int row = d[0] + r;
                int col = d[1] + c;
                if (!((row >= 0 && row < maps.length) && (col >= 0 && col < maps[0].length))) {
                    continue;
                }

                if (maps[row][col] == '#') {
                    continue;
                }

                if (dp[row][col] > poll[2] + 1) {
                    dp[row][col] = poll[2] + 1;
                    pq.add(new int[]{row, col, dp[row][col]});
                }
            }
        }
        return dp;
    }

    static void bfs(List<int[]> fires, char[][] maps, int[] start, int[][] dp) {
        int[][] dir = new int[][]{
                {1, 0}, {0, 1}, {-1, 0}, {0, -1}
        };

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o[2]));

        pq.add(new int[]{start[0], start[1], 0});

        boolean[][] visited = new boolean[maps.length][maps[0].length];
        while (!pq.isEmpty()) {
            int[] poll = pq.poll();

            int r = poll[0];
            int c = poll[1];
            if (!((r >= 0 && r < maps.length) && (c >= 0 && c < maps[0].length))) {
                result.append(poll[2]).append("\n");
                return;
            }
            if (visited[r][c]) {
                continue;
            }
            visited[r][c] = true;

            for (int i = 0; i < dir.length; i++) {
                int[] d = dir[i];
                int row = d[0] + r;
                int col = d[1] + c;

                if (!((row >= 0 && row < maps.length) && (col >= 0 && col < maps[0].length))) {
                    result.append(poll[2] + 1).append("\n");
                    return;
                }
                if (maps[row][col] == '#') {
                    continue;
                }

                if (dp[row][col] > poll[2] + 1) {
                    pq.add(new int[]{row, col, poll[2] + 1});
                }
            }
        }
        result.append("IMPOSSIBLE").append("\n");
    }
}