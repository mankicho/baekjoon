package brute_force;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BOJ_17406 {
    static int[][] dir = new int[][]{
            {0, 1}, {1, 0}, {0, -1}, {-1, 0}
    };

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int n = arr[0];
        int m = arr[1];
        int k = arr[2];

        int[][] map = new int[n][m];

        for (int i = 0; i < n; i++) {
            map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        List<int[]> list = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            list.add(Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray());
        }
        backTracking(list, new int[list.size()], new boolean[list.size()], map, 0, list.size());
        System.out.println(ans);

    }

    static int ans = Integer.MAX_VALUE;

    static void backTracking(List<int[]> list, int[] selectedArr, boolean[] visited
            , int[][] map, int n, int fin) {
        if (n == fin) {
            int[][] clonedMap = new int[map.length][map[0].length];

            for (int i = 0; i < map.length; i++) {
                for (int j = 0; j < map[i].length; j++) {
                    clonedMap[i][j] = map[i][j];
                }
            }

            for (int i = 0; i < selectedArr.length; i++) {
                int idx = selectedArr[i];

                int[] kArr = list.get(idx);
                int r = kArr[0];
                int c = kArr[1];
                int s = kArr[2];

                rotate(r - 1, c - 1, s, clonedMap);
            }

            int min = Integer.MAX_VALUE;
            for (int i = 0; i < clonedMap.length; i++) {
                int sum = 0;
                for (int j = 0; j < clonedMap[i].length; j++) {
                    sum += clonedMap[i][j];
                }
                min = Math.min(min, sum);
            }
            ans = Math.min(min, ans);
            return;
        }

        for (int i = 0; i < list.size(); i++) {
            if (visited[i]) {
                continue;
            }
            visited[i] = true;
            selectedArr[n] = i;
            backTracking(list, selectedArr, visited, map, n + 1, fin);
            visited[i] = false;
        }
    }

    static void rotate(int r, int c, int s, int[][] clonedMap) {
        while (s >= 1) {
            int tmp = clonedMap[r - s][c + s];
            int tmp2 = clonedMap[r + s][c + s];
            int tmp3 = clonedMap[r + s][c - s];

            for (int j = c + s; j >= c - s + 1; j--) {
                clonedMap[r - s][j] = clonedMap[r - s][j - 1];
            }

            for (int i = r + s; i >= r - s + 2; i--) {
                clonedMap[i][c + s] = clonedMap[i - 1][c + s];
            }
            clonedMap[r - s + 1][c + s] = tmp;

            for (int j = c - s; j <= c + s - 2; j++) {
                clonedMap[r + s][j] = clonedMap[r + s][j + 1];
            }
            clonedMap[r + s][c + s - 1] = tmp2;

            for (int i = r - s; i <= r + s - 2; i++) {
                clonedMap[i][c - s] = clonedMap[i + 1][c - s];
            }
            clonedMap[r + s - 1][c - s] = tmp3;
            s--;
        }
    }
}