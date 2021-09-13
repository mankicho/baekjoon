package back_tracking;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BOJ_2580 {
    static boolean[][] lengthArr;
    static boolean[][] heightArr;
    static boolean[][] boxArr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[][] map = new int[9][9];

        List<int[]> list = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            for (int j = 0; j < map[i].length; j++) {
                if (map[i][j] == 0) {
                    list.add(new int[]{i, j});
                }
            }
        }
        lengthArr = new boolean[9][10];
        heightArr = new boolean[9][10];
        boxArr = new boolean[9][10];

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                lengthArr[i][map[i][j]] = true;
                heightArr[j][map[i][j]] = true;
                int tmpI = i / 3;
                int tmpJ = j / 3;

                boxArr[tmpI * 3 + tmpJ][map[i][j]] = true;
            }
        }

        backTracking(map, list, 0, list.size(), -1);
    }

    static void backTracking(int[][] map, List<int[]> list, int n, int num, int index) {
        if (n == num) {
            boolean can = true;

            OUTER:
            for (int i = 0; i < list.size(); i++) {
                int[] arr = list.get(i);
                if (!func1(map, arr[0], arr[1]) || !func2(map, arr[0], arr[1])) {
                    can = false;
                    break OUTER;
                }
            }

            if (can) {
                for (int i = 0; i < 9; i++) {
                    for (int j = 0; j < 9; j++) {
                        System.out.print(map[i][j] + " ");
                    }
                    System.out.println();
                }
                System.exit(0);
            }
            return;
        }
        int[] arr = list.get(index + 1);

        int r = arr[0];
        int c = arr[1];
        int tmpR = r / 3;
        int tmpC = c / 3;
        for (int j = 1; j <= 9; j++) {
            if (lengthArr[r][j] || heightArr[c][j] || boxArr[tmpR * 3 + tmpC][j]) {
                continue;
            }
            map[r][c] = j;
            lengthArr[r][j] = true;
            heightArr[c][j] = true;
            boxArr[tmpR * 3 + tmpC][j] = true;
            backTracking(map, list, n + 1, num, index + 1);
            lengthArr[r][j] = false;
            heightArr[c][j] = false;
            boxArr[tmpR * 3 + tmpC][j] = false;

        }

    }

    static boolean func1(int[][] map, int row, int col) {
        int cnt = 0;
        for (int i = 0; i < 9; i++) {
            cnt += map[row][i];
        }
        if (cnt > 45) {
            return false;
        }

        cnt = 0;

        for (int i = 0; i < 9; i++) {
            cnt += map[i][col];
        }

        return cnt <= 45;
    }

    static boolean func2(int[][] map, int row, int col) {
        row = row / 3 * 3;
        col = col / 3 * 3;

        int endRow = row + 2;
        int endCol = col + 2;

        int cnt = 0;
        for (int i = row; i <= endRow; i++) {
            for (int j = col; j <= endCol; j++) {
                cnt += map[i][j];
            }
        }
        return cnt <= 45;
    }
}