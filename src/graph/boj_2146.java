package graph;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;

public class boj_2146 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[][] map = new int[n][n];
        boolean[] landCheck = new boolean[n * n];
        visited = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map.length; j++) {
                if (!visited[i][j] && map[i][j] == 1) {
                    makeLand(map, i, j);
                }
            }
        }

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map.length; j++) {
                if (map[i][j] == 0 || landCheck[map[i][j]]) {
                    continue;
                }
                dp = new int[n][n];
                minVisited = new boolean[n][n];
                bfs(map, i, j, map[i][j]);
                landCheck[map[i][j]] = true;
            }
        }
        System.out.println(min-1);
//        for (int[] ints : map) {
//            System.out.println(Arrays.toString(ints));
//        }
    }

    static boolean[][] visited;
    static boolean[][] minVisited;
    static int[][] dp;
    static int land = 2;
    static int[][] direction = new int[][]{
            {0, 1}, {1, 0}, {0, -1}, {-1, 0}
    };
    static int min = Integer.MAX_VALUE;

    static void bfs(int[][] map, int row, int col, int land) {
//        System.out.println(row + "," + col + ":" + land);
        LinkedList<int[]> queue = new LinkedList<>();
        queue.add(new int[]{row, col});
        minVisited[row][col] = true;

        while (!queue.isEmpty()) {
            int[] poll = queue.poll();

            int tmpRow = poll[0];
            int tmpCol = poll[1];

            for (int i = 0; i < direction.length; i++) {
                int[] dir = direction[i];

                int endRow = dir[0] + tmpRow;
                int endCol = dir[1] + tmpCol;

                if (!((endRow >= 0 && endRow < map.length) && (endCol >= 0 && endCol < map.length))
                        || minVisited[endRow][endCol]) {
                    continue;
                }

                minVisited[endRow][endCol] = true;

                queue.add(new int[]{endRow, endCol});
                if (map[endRow][endCol] == land) {
                    dp[endRow][endCol] = 0;
                } else {
                    dp[endRow][endCol] = dp[tmpRow][tmpCol] + 1;
                }
            }
        }

//        for (int[] ints : dp) {
//            System.out.println(Arrays.toString(ints));
//        }
//        System.out.println();
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp.length; j++) {
                if (map[i][j] == 0 || map[i][j] == land) {
                    continue;
                }
                if (dp[i][j] < min) {
                    min = dp[i][j];
                }
            }
        }
    }

    static void makeLand(int[][] map, int row, int col) {
        LinkedList<int[]> queue = new LinkedList<>();
        queue.add(new int[]{row, col});

        visited[row][col] = true;
        while (!queue.isEmpty()) {
            int[] poll = queue.poll();

            int tmpRow = poll[0];
            int tmpCol = poll[1];
            map[tmpRow][tmpCol] = land;
            for (int i = 0; i < direction.length; i++) {
                int[] dir = direction[i];

                int endRow = tmpRow + dir[0];
                int endCol = tmpCol + dir[1];

                if (!((endRow >= 0 && endRow < map.length) && (endCol >= 0 && endCol < map.length)) ||
                        visited[endRow][endCol]) {
                    continue;
                }

                if (map[endRow][endCol] == 1) {
                    visited[endRow][endCol] = true;
                    queue.add(new int[]{endRow, endCol});
                }
            }
        }
        land++;
    }
}