package dfs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;

public class BOJ_2573 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int n = arr[0];
        int m = arr[1];
        int[][] map = new int[n][m];
        for (int i = 0; i < map.length; i++) {
            map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        int cnt = 0;
        while (true) {

            int result = function2(map);
            if (result == -1) {
                System.out.println(0);
                return;
            } else if (result == 1) {
                System.out.println(cnt);
                return;
            }
            function(map);
            cnt++;

        }

    }

    static boolean[][] visited;

    static int function2(int[][] map) {
        visited = new boolean[map.length][map[0].length];
        int land = 0;

        for (int i = 1; i < map.length - 1; i++) {
            for (int j = 1; j < map[0].length - 1; j++) {
                if (map[i][j] != 0 && !visited[i][j]) {
                    bfs(map, i, j);
                    land++;
                }
            }
        }
        return land >= 2 ? 1 : land == 0 ? -1 : 0;
    }

    static void bfs(int[][] map, int row, int col) {
        LinkedList<int[]> pq = new LinkedList<>();
        pq.add(new int[]{row, col});
        int[][] dir = new int[][]{
                {0, 1}, {1, 0}, {0, -1}, {-1, 0}
        };

        while (!pq.isEmpty()) {
            int[] poll = pq.poll();
            if (visited[poll[0]][poll[1]]) {
                continue;
            }
            visited[poll[0]][poll[1]] = true;

            for (int i = 0; i < dir.length; i++) {
                int[] d = dir[i];

                int r = d[0] + poll[0];
                int c = d[1] + poll[1];

                if (!((r >= 1 && r < map.length - 1) && (c >= 1 && c < map[0].length - 1)) ||
                        map[r][c] == 0 || visited[r][c]) {
                    continue;
                }
                pq.add(new int[]{r, c});
            }
        }
    }

    static void function(int[][] map) {
        int[][] dir = new int[][]{
                {0, 1}, {1, 0}, {0, -1}, {-1, 0}
        };

        int[][] tmp = new int[map.length][map[0].length];

        for (int i = 1; i < map.length - 1; i++) {
            for (int j = 1; j < map[0].length - 1; j++) {
                if (map[i][j] == 0) {
                    continue;
                }

                int num = 0;
                for (int[] d : dir) {
                    int row = i + d[0];
                    int col = j + d[1];

                    if (map[row][col] != 0) {
                        continue;
                    }
                    num++;
                }
                tmp[i][j] = Math.max(map[i][j] - num, 0);

            }
        }
        for (int i = 1; i < tmp.length; i++) {
            for (int j = 1; j < tmp[0].length; j++) {
                map[i][j] = tmp[i][j];
            }
        }


    }
}

