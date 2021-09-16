package brute_force;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_14503 {

    static int[][] dir = new int[][]{
            {-1, 0}, {0, 1}, {1, 0}, {0, -1}
    };

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int n = arr[0];
        int m = arr[1];

        int[] robot = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int[][] map = new int[n][m];

        int cnt = 0;
        for (int i = 0; i < n; i++) {
            map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        int row = robot[0];
        int col = robot[1];
        int direction = robot[2];

        int answer = 0;

        while (true) {
            if (map[row][col] == 0) {
                map[row][col] = -1;
            }
            boolean allClear = true;
            for (int i = 1; i <= 4; i++) {
                int[] d = dir[(direction + 4 - i) % 4];

                int er = d[0] + row;
                int ec = d[1] + col;

                if (map[er][ec] == 0) {
                    row = er;
                    col = ec;
                    direction = (direction + 4 - i) % 4;
                    allClear = false;
                    break;
                }
            }
            if (allClear) {
                int[] d = dir[direction];
                row -= d[0];
                col -= d[1];

                if (map[row][col] == 1) {
                    break;
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == -1) {
                    answer++;
                }
            }
        }
        System.out.println(answer);
    }
}