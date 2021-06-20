package solve.class4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class boj_17144 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int r = arr[0];
        int c = arr[1];
        int t = arr[2];

        int[][] vals = new int[r + 2][c + 2];

        int topRow = 0;
        for (int i = 1; i < vals.length - 1; i++) {
            int[] tmp = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            for (int j = 0; j < tmp.length; j++) {
                if (tmp[j] == -1) {
                    if (topRow == 0) {
                        topRow = i;
                    }
                }
                vals[i][j + 1] = tmp[j];
            }
        }
        for (int i = 0; i < t; i++) {
            spread(vals, topRow);
            clean(vals, topRow);
        }

        int sum = 0;
        for (int i = 1; i < vals.length; i++) {
            for (int j = 1; j < vals[0].length; j++) {
                sum += vals[i][j];
            }
        }

        System.out.println(sum );
    }

    static void spread(int[][] vals, int topRow) {
        int[][] tmp = new int[vals.length][vals[0].length];
        for (int i = 1; i < vals.length - 1; i++) {
            for (int j = 1; j < vals[0].length - 1; j++) {
                if (vals[i][j] == 0 || ((i == topRow || i == topRow + 1) && j == 1)) {
                    continue;
                }

                tmp[i - 1][j] += vals[i][j] / 5;
                tmp[i + 1][j] += vals[i][j] / 5;
                tmp[i][j - 1] += vals[i][j] / 5;
                tmp[i][j + 1] += vals[i][j] / 5;

                if (i == 1 || i == vals.length - 2) {
                    if (j == 1 || j == vals[0].length - 2) {
                        vals[i][j] -= ((vals[i][j] / 5) * 2);
                    } else {
                        vals[i][j] -= ((vals[i][j] / 5) * 3);
                    }
                } else {
                    if (j == 1 && (i == topRow - 1 || i == topRow + 2)) {
                        vals[i][j] -= ((vals[i][j] / 5) * 2);
                    } else if (j == 1 || j == vals[0].length - 2 || (j == 2 && (i == topRow || i == topRow + 1))) {
                        vals[i][j] -= ((vals[i][j] / 5) * 3);
                    } else {
                        vals[i][j] -= ((vals[i][j] / 5) * 4);
                    }
                }
            }
        }
        tmp[topRow][1] = 0;
        tmp[topRow + 1][1] = 0;

        for (int i = 1; i < vals.length - 1; i++) {
            for (int j = 1; j < vals[0].length - 1; j++) {
                vals[i][j] += tmp[i][j];
            }
        }
    }

    static void clean(int[][] vals, int topRow) {
        int[][] tmp = new int[vals.length][vals[0].length];
        for (int i = 1; i < vals[0].length - 1; i++) {
            tmp[topRow][i + 1] = vals[topRow][i];
            tmp[topRow + 1][i + 1] = vals[topRow + 1][i];
        }

        for (int i = vals[0].length - 2; i > 0; i--) {
            tmp[1][i - 1] = vals[1][i];

            tmp[vals.length - 2][i - 1] = vals[vals.length - 2][i];
        }

        for (int i = topRow; i > 0; i--) {
            tmp[i - 1][vals[0].length - 2] = vals[i][vals[0].length - 2];
        }

        for (int i = topRow + 1; i < vals.length - 1; i++) {
            tmp[i + 1][vals[0].length - 2] = vals[i][vals[0].length - 2];
        }

        for (int i = 1; i < topRow; i++) {
            tmp[i + 1][1] = vals[i][1];
        }

        for (int i = vals.length - 2; i > topRow + 1; i--) {
            tmp[i - 1][1] = vals[i][1];
        }

        for (int i = 1; i < vals.length - 1; i++) {
            for (int j = 1; j < vals[0].length - 1; j++) {
                if (i == 1 || i == topRow || i == topRow + 1 || i == vals.length - 2) {
                    vals[i][j] = tmp[i][j];
                }

                if (j == 1 || j == vals[0].length - 2) {
                    vals[i][j] = tmp[i][j];
                }
            }
        }

        vals[topRow][1] = 0;
        vals[topRow + 1][1] = 0;
        vals[topRow][2] = 0;
        vals[topRow + 1][2] = 0;
    }
}