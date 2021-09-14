package graph;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class BOJ_2589 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int n = arr[0];
        int m = arr[1];

        char[][] map = new char[n][m];

        for (int i = 0; i < n; i++) {
            map[i] = br.readLine().toCharArray();
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == 'L') {
                    bfs(map, i, j);
                }
            }
        }
        System.out.println(max);
    }

    static int max = 0;

    static void bfs(char[][] map, int row, int col) {
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o[2]));

        pq.add(new int[]{row, col, 0});


        int[][] dir = new int[][]{
                {1, 0}, {0, 1}, {-1, 0}, {0, -1}
        };
        boolean[][] visited = new boolean[map.length][map[0].length];
        int cnt = 0;
        while (!pq.isEmpty()) {
            int[] poll = pq.poll();

            int r = poll[0];
            int c = poll[1];
            cnt = Math.max(cnt, poll[2]);
            if (visited[r][c]) {
                continue;
            }
            visited[r][c] = true;

            for (int i = 0; i < dir.length; i++) {
                int[] d = dir[i];

                int endRow = d[0] + r;
                int endCol = d[1] + c;

                if (!((endRow >= 0 && endRow < map.length) && (endCol >= 0 && endCol < map[0].length))
                        || visited[endRow][endCol] || map[endRow][endCol] == 'W') {
                    continue;
                }

                pq.add(new int[]{endRow, endCol, poll[2] + 1});
            }
        }
        max = Math.max(max, cnt);
    }


}