package solve.class4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class boj_2638 {

    static boolean[][] visited;
    static int[][] open;
    static List<int[]> tmpList = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int n = arr[0];
        int m = arr[1];


        int[][] cheese = new int[n][m];

        for (int i = 0; i < n; i++) {
            cheese[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }
        int answer = 0;
        while (true) {
            List<int[]> deleteList = new ArrayList<>();
            open = new int[n][m];
            visited = new boolean[n][m];
            dfs(cheese, 0, 0);
            if (visited[n - 1][m - 1]) {
                for (int i = 0; i < tmpList.size(); i++) {
                    int[] tmpArr = tmpList.get(i);
                    open[tmpArr[0]][tmpArr[1]] = 1;
                }
            }
            boolean hasCheese = false;
            List<int[]> tmpList = new ArrayList<>();
            for (int i = 0; i < cheese.length; i++) {
                for (int j = 0; j < cheese[i].length; j++) {
                    if (cheese[i][j] == 1) {
                        hasCheese = true;
                        int num = 0;
                        if (i - 1 >= 0) {
                            if (cheese[i - 1][j] == 0 && open[i - 1][j] == 1) {
                                num++;
                            }
                        }
                        if (i + 1 < cheese.length) {
                            if (cheese[i + 1][j] == 0 && open[i + 1][j] == 1) {
                                num++;
                            }
                        }
                        if (j - 1 >= 0) {
                            if (cheese[i][j - 1] == 0 && open[i][j - 1] == 1) {
                                num++;
                            }
                        }
                        if (j + 1 < cheese[i].length) {
                            if (cheese[i][j + 1] == 0 && open[i][j + 1] == 1) {
                                num++;
                            }
                        }

                        if (num >= 2) {
                            deleteList.add(new int[]{i, j});
                        }
                    }
                }
            }

            for (int i = 0; i < deleteList.size(); i++) {
                int[] tmpArr = deleteList.get(i);

                cheese[tmpArr[0]][tmpArr[1]] = 0;
            }
            if (!hasCheese) {
                break;
            }
            answer++;
        }

        System.out.println(answer);
    }

    static void dfs(int[][] cheese, int row, int col) {
        if (visited[row][col]) {
            return;
        }
        visited[row][col] = true;
        if (cheese[row][col] == 0) {
            tmpList.add(new int[]{row, col});

            if (row - 1 >= 0) {
                dfs(cheese, row - 1, col);
            }

            if (row + 1 < cheese.length) {
                dfs(cheese, row + 1, col);
            }

            if (col - 1 >= 0) {
                dfs(cheese, row, col - 1);
            }

            if (col + 1 < cheese[row].length) {
                dfs(cheese, row, col + 1);
            }
        }
    }
}