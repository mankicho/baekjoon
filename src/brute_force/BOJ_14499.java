package brute_force;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_14499 {

    static int[][][] arrs;
    static int[] dice;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


        int[][] dir = new int[][]{
                {0, 0}, {0, 1}, {0, -1}, {-1, 0}, {1, 0}
        };

        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int n = arr[0];
        int m = arr[1];
        int r = arr[2];
        int c = arr[3];
        int k = arr[4];

        int[][] map = new int[n][m];

        dice = new int[7];

        for (int i = 0; i < n; i++) {
            map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        int[] kArr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < kArr.length; i++) {

            int order = kArr[i];

            int er = r + dir[order][0];
            int ec = c + dir[order][1];

            if ((er >= 0 && er < n) && (ec >= 0 && ec < m)) {
                changeValue(order);

                if (map[er][ec] == 0) {
                    map[er][ec] = dice[6];
                } else {
                    dice[6] = map[er][ec];
                    map[er][ec] = 0;
                }
                r = er;
                c = ec;
                result.append(dice[1]).append("\n");
            }

        }
        System.out.println(result.toString());
    }

    static void changeValue(int dir) {
        int[] temp = dice.clone();
        switch (dir) {
            case 1:
                dice[1] = temp[4];
                dice[3] = temp[1];
                dice[4] = temp[6];
                dice[6] = temp[3];
                break;
            case 2:
                dice[1] = temp[3];
                dice[3] = temp[6];
                dice[4] = temp[1];
                dice[6] = temp[4];
                break;
            case 3:
                dice[1] = temp[5];
                dice[2] = temp[1];
                dice[5] = temp[6];
                dice[6] = temp[2];
                break;
            case 4:
                dice[1] = temp[2];
                dice[2] = temp[6];
                dice[5] = temp[1];
                dice[6] = temp[5];
                break;
        }
    }
}