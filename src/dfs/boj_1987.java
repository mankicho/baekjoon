package dfs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class boj_1987 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int n = arr[0];
        int m = arr[1];

        char[][] chars = new char[n][m];
        visited = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            chars[i] = br.readLine().toCharArray();
        }

        dfs(chars, 0, 0);
        System.out.println(max);
    }

    static int bitMask = 0;

    static int[][] direction = new int[][]{
            {-1, 0}, {0, 1}, {1, 0}, {0, -1}
    };
    static boolean[][] visited;

    static int path = 0;
    static int max = 0;

    static void dfs(char[][] chars, int row, int col) {
        if ((bitMask & (1 << (chars[row][col] - 'A'))) != 0 || visited[row][col]) {
            return;
        }

        path++;
        if (path > max) {
            max = path;
        }
        visited[row][col] = true;
        bitMask = bitMask | (1 << (chars[row][col] - 'A'));

        for (int i = 0; i < direction.length; i++) {
            int[] dir = direction[i];

            int tmpRow = row + dir[0];
            int tmpCol = col + dir[1];
            if (!((tmpRow >= 0 && tmpRow < chars.length) &&
                    (tmpCol >= 0 && tmpCol < chars[0].length))) {
                continue;
            }

            dfs(chars, tmpRow, tmpCol);
        }

        visited[row][col] = false;
        bitMask = bitMask - (1 << (chars[row][col] - 'A'));
        path--;
    }

}