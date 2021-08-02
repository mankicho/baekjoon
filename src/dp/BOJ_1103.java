package dp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

class BOJ_1103 {

    static int[][] direction = new int[][]{
            {0, 1}, {1, 0}, {-1, 0}, {0, -1}
    };

    static boolean[][][][] visited;
    static int[][] dp;
    static boolean infinite = false;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int n = arr[0];
        int m = arr[1];

        int[][] map = new int[n][m];
        dp = new int[n][m];
        for (int i = 0; i < n; i++) {
            String line = br.readLine();

            for (int j = 0; j < line.length(); j++) {
                if (line.charAt(j) == 'H') {
                    map[i][j] = -1;
                } else {
                    map[i][j] = Character.digit(line.charAt(j), 10);
                }
            }
        }
        visited = new boolean[n][m][n][m];

        dfs(map, 0, 0, 0);
        System.out.println(infinite ? -1 : max + 1);
    }

    static int max = 0;

    static void dfs(int[][] map, int row, int col, int step) {

        if (step > max) {
            max = step;

        }
        if (!infinite) {
            for (int i = 0; i < direction.length; i++) {
                int[] dir = direction[i];

                int endRow = dir[0] * map[row][col] + row;
                int endCol = dir[1] * map[row][col] + col;

                if (!((endRow >= 0 && endRow < map.length) && (endCol >= 0 && endCol < map[0].length))
                        || map[endRow][endCol] == -1) {
                    continue;
                }

                if (visited[endRow][endCol][row][col]) {
                    infinite = true;
                    break;
                }
                if (dp[endRow][endCol] < dp[row][col] + 1) {
                    dp[endRow][endCol] = dp[row][col] + 1;
                    visited[endRow][endCol][row][col] = true;
                    dfs(map, endRow, endCol, step + 1);
                    visited[endRow][endCol][row][col] = false;
                }
            }
        }
    }

}
