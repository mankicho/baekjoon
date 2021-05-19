package dfs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class boj_2667 {
    static int num = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[][] arr = new int[n][n];

        for (int i = 0; i < n; i++) {
            arr[i] = Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
        }

        boolean[][] visited = new boolean[n][n];
        List<Integer> list = new ArrayList<>();
        int answer = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                if (visited[i][j]) {
                    continue;
                }

                if (arr[i][j] == 1) {
                    scan(i, j, arr, visited);
                    answer++;
                    list.add(num);
                    num = 0;
                }
            }
        }
        System.out.println(answer);
        Collections.sort(list);

        for(int i : list){
            System.out.println(i);
        }
    }

    static void scan(int row, int col, int[][] arr, boolean[][] visited) {
        if (visited[row][col]) {
            return;
        }

        visited[row][col] = true;
        num++;

        if (row + 1 < arr.length && arr[row + 1][col] == 1) {
            if (!visited[row + 1][col]) {
                scan(row + 1, col, arr, visited);
            }
        }

        if (row - 1 >= 0 && arr[row - 1][col] == 1) {
            if (!visited[row - 1][col]) {
                scan(row - 1, col, arr, visited);
            }
        }

        if (col + 1 < arr.length && arr[row][col + 1] == 1) {
            if (!visited[row][col + 1]) {
                scan(row, col + 1, arr, visited);
            }
        }

        if (col - 1 >= 0 && arr[row][col - 1] == 1) {
            if (!visited[row][col - 1]) {
                scan(row, col - 1, arr, visited);
            }
        }
    }
}
