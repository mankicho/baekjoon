package back_tracking;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_17136 {


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[][] map = new int[10][10];

        for (int i = 0; i < 10; i++) {
            map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        int[] papers = new int[5];

        Arrays.fill(papers, 5);
        backTracking(map, 0, 0, papers, 0);
        System.out.println(min == 987654321 ? -1 : min);
    }

    static int min = 987654321;

    static void backTracking(int[][] map, int row, int col, int[] papers, int num) {
        OUTER:
        for (int i = row; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (num >= min) {
                    return;
                }
                if (map[i][j] == 1) {
                    for (int k = 4; k >= 1; k--) {
                        if (papers[k] == 0) {
                            continue;
                        }
                        if (i + k < 10 && j + k < 10) {
                            if (isComposedOfOne(map, i, j, k)) {
//                                changeValue(map, i, j, k, 0);
                                int[][] tmp = arrayDeepCopy(map, i, j, k);
                                papers[k]--;
                                backTracking(tmp, i, j, Arrays.copyOfRange(papers, 0, papers.length), num + 1);
                                papers[k]++;
//                                changeValue(map, i, j, k, 1);
                            }
                        }
                    }
                    if (papers[0] == 0) {
                        return;
                    }
                    papers[0]--;
                    num++;
                    map[i][j] = 0;
                }
            }
        }
        if (num < min) {
            min = num;
        }
    }

    static int[][] arrayDeepCopy(int[][] map, int row, int col, int k) {
        int[][] tmp = new int[10][10];

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                tmp[i][j] = map[i][j];
            }
        }
        for (int i = row; i <= row + k; i++) {
            for (int j = col; j <= col + k; j++) {
                tmp[i][j] = 0;
            }
        }
        return tmp;
    }

    static boolean isComposedOfOne(int[][] map, int row, int col, int k) {
        for (int i = row; i <= row + k; i++) {
            for (int j = col; j <= col + k; j++) {
                if (map[i][j] == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    static void changeValue(int[][] map, int row, int col, int k, int val) {
        for (int i = row; i <= row + k; i++) {
            for (int j = col; j <= col + k; j++) {
                map[i][j] = val;
            }
        }
    }
}
