package dfs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class boj_3109 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int n = arr[0];
        int m = arr[1];

        char[][] chrArr = new char[n][m];

        for (int i = 0; i < n; i++) {
            chrArr[i] = br.readLine().toCharArray();
        }


        boolean[][] visited = new boolean[n][m];

        int ans = 0;
        for (int i = 0; i < chrArr.length; i++) {
            can = false;
            dfs(chrArr, i, 0, visited);
            if (can) {
                ans++;
            }
        }
        System.out.println(ans);
    }

    static boolean can;

    static int[][] direction = new int[][]{
            {-1, 1}, {0, 1}, {1, 1}
    };

    // 배열이니까 힙 영역에 들어있잖아
    static void dfs(char[][] chrs, int row, int col, boolean[][] visited) {


        if (visited[row][col]) {
            return;
        }

        if (col == chrs[0].length - 1) {
            can = true;
        }
        if (can) {
            return;
        }

        visited[row][col] = true;

        for (int i = 0; i < direction.length; i++) {
            int[] dir = direction[i];

            int endRow = row + dir[0];

            int endCol = col + dir[1];

            // 범위를 벗어나면 안한다
            if (!((endRow >= 0 && endRow < chrs.length) && (endCol >= 0 && endCol < chrs[0].length))
                    || chrs[endRow][endCol] == 'x') {
                continue;
            }

            if (can) {
                break;
            }

            dfs(chrs, endRow, endCol, visited);
        }
    }
}