package graph;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class BOJ_2636 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int n = arr[0];
        int m = arr[1];

        int[][] map = new int[n][m];

        for (int i = 0; i < n; i++) {
            map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        int min = n * m;
        int num = 0;
        while (true) {
            bfsFindNotHole(map);

            List<int[]> list = new ArrayList<>();

            int cnt = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (map[i][j] == 1) {
                        boolean melt = false;
                        for (int k = 0; k < dir.length; k++) {
                            int[] d = dir[k];
                            int row = i + d[0];
                            int col = j + d[1];

                            if (!((row >= 0 && row < map.length) && (col >= 0 && col < map[0].length))
                                    || map[row][col] != 2) {
                                continue;
                            }
                            melt = true;
                        }

                        if (melt) {
                            list.add(new int[]{i, j});
                        }
                        cnt++;
                    }
                }
            }
            list.forEach(a -> map[a[0]][a[1]] = 2);

            if (cnt == 0) {
                break;
            }
            min = Math.min(min, cnt);
            num++;
        }
        System.out.println(num);
        System.out.println(min);
    }

    static int[][] dir = new int[][]{
            {1, 0}, {0, 1}, {-1, 0}, {0, -1}
    };

    static void bfsFindNotHole(int[][] map) {
        LinkedList<int[]> queue = new LinkedList<>();

        queue.add(new int[]{0, 0});


        boolean[][] visited = new boolean[map.length][map[0].length];
        while (!queue.isEmpty()) {
            int[] poll = queue.poll();

            int r = poll[0];
            int c = poll[1];

            if (visited[r][c]) {
                continue;
            }
            visited[r][c] = true;

            map[r][c] = 2;
            for (int i = 0; i < dir.length; i++) {
                int[] d = dir[i];

                int row = d[0] + r;
                int col = d[1] + c;

                if (!((row >= 0 && row < map.length) && (col >= 0 && col < map[0].length))
                        || map[row][col] == 1) {
                    continue;
                }
                if (visited[row][col]) {
                    continue;
                }
                queue.add(new int[]{row, col});
            }
        }
    }

}