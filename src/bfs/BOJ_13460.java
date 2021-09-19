package bfs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;

public class BOJ_13460 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int n = arr[0];
        int m = arr[1];

        char[][] map = new char[n][m];

        int[] red = null;
        int[] blue = null;
        for (int i = 0; i < n; i++) {
            map[i] = br.readLine().toCharArray();
            for (int j = 0; j < map[i].length; j++) {
                if (map[i][j] == 'B') {
                    blue = new int[]{i, j};
                } else if (map[i][j] == 'R') {
                    red = new int[]{i, j};
                }
            }
        }

        backTracking(map, red, blue, 0, -5);
        System.out.println(min == 987654321 ? -1 : min > 9 ? -1 : min + 1);
    }

    static int[][] dir = new int[][]{
            {1, 0}, {0, 1}, {-1, 0}, {0, -1}
    };

    static int min = 987654321;

    static void backTracking(char[][] map, int[] red, int[] blue, int cnt, int direction) {
        if (cnt > min || cnt > 9) {
            return;
        }
        int redRow = red[0];
        int redCol = red[1];
        int blueRow = blue[0];
        int blueCol = blue[1];
        int tmpRedRow = redRow;
        int tmpRedCol = redCol;
        int tmpBlueRow = blueRow;
        int tmpBlueCol = blueCol;
        //        System.out.println("red : [" + tmpRedRow + "," + tmpRedCol + "] blue : [" + tmpBlueRow + "," + tmpBlueCol + "]");
        for (int i = 0; i < dir.length; i++) {
            //            System.out.println(redRow + " " + redCol + " " + blueRow + " " + blueCol);
            if (Math.abs(i - direction) == 2) {
                continue;
            }
            int[] d = dir[i];

            boolean finish = false;
            boolean blueFinish = false;
            boolean blueOnPath = false;
            boolean redMoving = false;
            boolean blueMoving = false;
            while ((redRow + d[0] >= 0 && redRow + d[0] < map.length) && (redCol >= 0 && redCol < map[0].length)) {
                int row = redRow + d[0];
                int col = redCol + d[1];

                if (map[row][col] == '#') {
                    break;
                } else if (map[row][col] == 'O') {
                    finish = true;
                } else if (!finish && row == blueRow && col == blueCol) {
                    blueOnPath = true;
                    break;
                }

                redMoving = true;
                redRow += d[0];
                redCol += d[1];
            }

            while ((blueRow + d[0] >= 0 && blueRow + d[0] < map.length) && (blueCol + d[1] >= 0 && blueCol + d[1] < map[0].length)) {
                int row = blueRow + d[0];
                int col = blueCol + d[1];

                if (map[row][col] == '#') {
                    break;
                } else if (map[row][col] == 'O') {
                    finish = false;
                    blueFinish = true;
                    break;
                } else if (row == redRow && col == redCol) {
                    break;
                }
                blueRow += d[0];
                blueCol += d[1];
                blueMoving = true;
            }

            if (finish) {
                //                System.out.println(tmpRedRow + " " + tmpRedCol + " " + tmpBlueRow + " " + tmpBlueCol + " " + i);
                //                System.out.println(redRow + " " + redCol + " " + blueRow + " " + blueCol);
                min = Math.min(cnt, min);
                return;
            }
            if (blueFinish) {
                redRow = tmpRedRow;
                redCol = tmpRedCol;
                blueRow = tmpBlueRow;
                blueCol = tmpBlueCol;
                continue;
            }
            if (blueOnPath) {
                redRow = blueRow - d[0];
                redCol = blueCol - d[1];
            }
            if (!redMoving && !blueMoving) {
                continue;
            }


            backTracking(map, new int[]{redRow, redCol}, new int[]{blueRow, blueCol}, cnt + 1, i);
            redRow = tmpRedRow;
            redCol = tmpRedCol;
            blueRow = tmpBlueRow;
            blueCol = tmpBlueCol;
        }
    }
}