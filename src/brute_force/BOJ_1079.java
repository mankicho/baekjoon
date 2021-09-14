package brute_force;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_1079 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int[][] map = new int[n][n];

        for (int i = 0; i < n; i++) {
            map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }
        int eunzin = Integer.parseInt(br.readLine()) ;
        if (n == 1) {
            System.out.println(0);
            return;
        }

        backTracking(map, arr, n, 0, eunzin, new boolean[n], 0);
        System.out.println(max);
    }

    static int max = 0;

    static void backTracking(int[][] map, int[] arr, int num, int n, int eunzin, boolean[] visited, int night) {
        if ((num - n) % 2 == 0) {
            for (int i = 0; i < num; i++) {
                if (visited[i] || i == eunzin) {
                    continue;
                }
                visited[i] = true;
                for (int j = 0; j < num; j++) {
                    arr[j] += map[i][j];
                }
                backTracking(map, arr, num, n + 1, eunzin, visited, night + 1);
                for (int j = 0; j < num; j++) {
                    arr[j] -= map[i][j];
                }
                visited[i] = false;
            }
        } else {
            int maxValue = -1000;
            int maxIndex = -1;
            for (int i = 0; i < num; i++) {
                if (visited[i]) {
                    continue;
                }
                if (arr[i] > maxValue) {
                    maxValue = arr[i];
                    maxIndex = i;
                }
            }
            if (maxIndex == eunzin || maxIndex == -1) {
                max = Math.max(max, night);
                return;
            }
            visited[maxIndex] = true;
            backTracking(map, arr, num, n + 1, eunzin, visited, night);
            visited[maxIndex] = false;
        }
        max = Math.max(max, night);
    }

}