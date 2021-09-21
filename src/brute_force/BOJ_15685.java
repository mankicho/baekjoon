package brute_force;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BOJ_15685 {
    static List<int[]> list;
    static List<int[]> list2;
    static int[][] dir = new int[][]{
            {0, 1}, {-1, 0}, {0, -1}, {1, 0}
    };

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        boolean[][] map = new boolean[101][101];
        list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            int col = arr[0];
            int row = arr[1];
            int direction = arr[2];
            int g = arr[3];
            list2 = new ArrayList<>();
            list2.add(new int[]{row, col});
            int[] d = dir[direction];
            list2.add(new int[]{row + d[0], col + d[1]});

            function(row, col, direction, g);
        }

//        list.forEach(arr -> System.out.println(Arrays.toString(arr)));
        for (int i = 0; i < list.size(); i++) {
            int[] arr = list.get(i);

            int r = arr[0];
            int c = arr[1];

            map[r][c] = true;
        }
        int answer = 0;
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                if (map[i][j] && map[i][j + 1] && map[i + 1][j] && map[i + 1][j + 1]) {
                    answer++;
                }
            }
        }
        System.out.println(answer);
    }

    static void function(int row, int col, int direction, int g) {


        int[] d = dir[direction];
        int criRow = row + d[0];
        int criCol = col + d[1];
        for (int i = 1; i <= g; i++) {
            List<int[]> tempList = new ArrayList<>();
            for (int j = 0; j < list2.size(); j++) {
                int[] arr = list2.get(j);

                int tempRow = criRow - arr[0];
                int tempCol = criCol - arr[1];

                tempList.add(new int[]{criRow + (-1 * tempCol), criCol + tempRow});
            }

            list2.addAll(tempList);

            int tempRow = criRow - row;
            int tempCol = criCol - col;

            criRow -= tempCol;
            criCol += tempRow;
        }
        list.addAll(list2);

    }
}