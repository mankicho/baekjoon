package solve.class4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class boj_17070 {

    static int[][] house;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        house = new int[n][n];

        for (int i = 0; i < house.length; i++) {
            house[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        move(0, 1, 1);
        System.out.println(ans);
    }

    static int ans = 0;

    static void move(int x, int y, int dir) {
        if (x == house.length - 1 && y == house.length - 1 ) {
            ans++;
            return;
        }

        switch (dir) {
            case 1:
                if (x < house.length && y + 1 < house.length
                        && house[x][y + 1] == 0) {
                    move(x, y + 1, 1);
                }

                if (x + 1 < house.length && y + 1 < house.length
                        && house[x + 1][y + 1] == 0
                        && house[x + 1][y] == 0
                        && house[x][y + 1] == 0) {
                    move(x + 1, y + 1, 2);
                }
                break;
            case 2:
                if (x < house.length && y + 1 < house.length
                        && house[x][y + 1] == 0) {
                    move(x, y + 1, 1);
                }

                if (x + 1 < house.length && y + 1 < house.length
                        && house[x + 1][y + 1] == 0
                        && house[x + 1][y] == 0
                        && house[x][y + 1] == 0) {
                    move(x + 1, y + 1, 2);
                }
                if (x + 1 < house.length && y < house.length
                        && house[x + 1][y] == 0) {
                    move(x + 1, y, 3);
                }
                break;
            default:
                if (x + 1 < house.length && y + 1 < house.length
                        && house[x + 1][y + 1] == 0
                        && house[x + 1][y] == 0
                        && house[x][y + 1] == 0) {
                    move(x + 1, y + 1, 2);
                }
                if (x + 1 < house.length && y < house.length
                        && house[x + 1][y] == 0) {
                    move(x + 1, y, 3);
                }
        }
    }


}
