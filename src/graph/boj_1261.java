package graph;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class boj_1261 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int n = arr[0];
        int m = arr[1];

        int[][] spot = new int[m][n];

        for (int i = 0; i < m; i++) {
            spot[i] = Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
        }

        PriorityQueue<int[]> queue = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[2] - o2[2];
            }
        });
        queue.add(new int[]{0, 0, 0});

        int[][] direction = new int[][]{
                {0, 1}, {1, 0}, {0, -1}, {-1, 0}
        };

        boolean[][] visited = new boolean[m][n];

        visited[0][0] = true;
        while (!queue.isEmpty()) {
            int[] poll = queue.poll();

            int row = poll[0];
            int col = poll[1];
            int crush = poll[2];

            if (row == m - 1 && col == n - 1) {
                System.out.println(crush);
                break;
            }
            for (int i = 0; i < direction.length; i++) {
                int[] dir = direction[i];

                int endRow = dir[0] + row;
                int endCol = dir[1] + col;

                if (!((endRow >= 0 && endRow < m) && (endCol >= 0 && endCol < n))
                        || visited[endRow][endCol]) {
                    continue;
                }

                if (spot[endRow][endCol] == 0) {
                    visited[endRow][endCol] = true;
                    queue.add(new int[]{endRow, endCol, crush});
                } else {
                    visited[endRow][endCol] = true;
                    queue.add(new int[]{endRow, endCol, crush + 1});
                }

            }
        }

    }
}