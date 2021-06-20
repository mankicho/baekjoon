package solve.class4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class boj_15686 {
    static List<int[]> virus;
    static int all = 0;
    static int wallNum = 0;
    static int spread;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int n = arr[0];
        int m = arr[1];

        int[][] research = new int[n][m];

        virus = new ArrayList<>();
        wall = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            int[] tmpArr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            for (int j = 0; j < tmpArr.length; j++) {
                if (tmpArr[j] == 2) {
                    virus.add(new int[]{i, j});
                } else if (tmpArr[j] == 0) {
                    all++;
                } else {
                    wallNum++;
                }
            }
            research[i] = tmpArr;
        }

        spread = virus.size();
        if (all == 3) {
            function(research, 0, -1, 0, 0);
            System.out.println(max);
            return;
        }
        function(research, 0, -1, 0, 1);
        System.out.println(max);
    }

    static int max = 0;
    static boolean[][] visited;
    static boolean[][] wall;

    static void function(int[][] research, int row, int col, int num, int type) {

        if (type == 0) {
            if (num == 2) {
                visited = new boolean[research.length][research[0].length];

                for (int i = 0; i < virus.size(); i++) {
                    int[] v = virus.get(i);
                    spread(v[0], v[1], research);
                }
                int safeArea = research.length * research[0].length - spread - (wallNum + 2);
                if (safeArea > max) {
                    max = safeArea;
                }
                spread = virus.size();
                return;
            }
        } else {
            if (num == 3) {
                visited = new boolean[research.length][research[0].length];

                for (int i = 0; i < virus.size(); i++) {
                    int[] v = virus.get(i);
                    spread(v[0], v[1], research);
                }
                int safeArea = research.length * research[0].length - spread - (wallNum + 3);
                if (safeArea > max) {
                    max = safeArea;
                }
                spread = virus.size();
                return;
            }
        }

        for (int i = 0; i < research.length; i++) {
            for (int j = 0; j < research[i].length; j++) {
                if (i < row || (i == row && j <= col)) {
                    continue;
                }

                if (research[i][j] == 0) {
                    research[i][j] = 1;
                    function(research, i, j, num + 1, type);
                    research[i][j] = 0;
                }
            }
        }
    }


    static void spread(int row, int col, int[][] research) {
        if (row + 1 < research.length && research[row + 1][col] == 0 && !visited[row + 1][col]) {
            spread++;
            visited[row + 1][col] = true;
            spread(row + 1, col, research);
        }

        if (row - 1 >= 0 && research[row - 1][col] == 0 && !visited[row - 1][col]) {
            spread++;
            visited[row - 1][col] = true;
            spread(row - 1, col, research);
        }

        if (col - 1 >= 0 && research[row][col - 1] == 0 && !visited[row][col - 1]) {
            spread++;
            visited[row][col - 1] = true;
            spread(row, col - 1, research);
        }

        if (col + 1 < research[0].length && research[row][col + 1] == 0 && !visited[row][col + 1]) {
            spread++;
            visited[row][col + 1] = true;
            spread(row, col + 1, research);
        }

    }
}