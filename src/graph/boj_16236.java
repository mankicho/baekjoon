package graph;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class boj_16236 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] map = new int[n][n];

        int sharkRow = 0;
        int sharkCol = 0;
        for (int i = 0; i < n; i++) {
            map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            for (int j = 0; j < map[i].length; j++) {
                if (map[i][j] == 9) {
                    sharkRow = i;
                    sharkCol = j;
                }
            }
        }

        int ans = 0;
        while (true) {
            int[] sharkLoc = bfs(map, sharkRow, sharkCol, new boolean[n][n]);
            if ((sharkLoc[0] == -1 && sharkLoc[1] == -1)) {
                break;
            }
            eat++;

            sharkRow = sharkLoc[0];
            sharkCol = sharkLoc[1];
            ans += sharkLoc[2];
            if (level == eat) {
                level++;
                eat = 0;
            }
            map[sharkRow][sharkCol] = 0;
        }
        System.out.println(ans);

    }

    static int eat = 0;
    static int level = 2;

    static int[] bfs(int[][] map, int sharkRow, int sharkCol, boolean[][] visited) {
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o[3]));

        pq.add(new int[]{sharkRow, sharkCol, level, 0});

        int[][] direction = new int[][]{
                {0, 1}, {1, 0}, {0, -1}, {-1, 0}
        };

        int min = 100000;
        int sharkR = -1;
        int sharkC = -1;
        boolean[][] inQueue = new boolean[map.length][map.length];
        inQueue[sharkRow][sharkCol] = true;
        while (!pq.isEmpty()) {
            int[] poll = pq.poll();
            int row = poll[0];
            int col = poll[1];
            int sharkLevel = poll[2];
            int step = poll[3];


            if (map[row][col] != 0 && map[row][col] != 9 && map[row][col] < sharkLevel) {
                if (step <= min) {
                    if (step == min) {
                        if (row < sharkR) {
                            sharkR = row;
                            sharkC = col;
                        } else if (row == sharkR && col < sharkC) {
                            sharkC = col;
                        }
                    } else {
                        min = step;
                        sharkR = row;
                        sharkC = col;
                    }
                }
            }

            if (visited[row][col]) {
                continue;
            }
            visited[row][col] = true;

            for (int i = 0; i < direction.length; i++) {
                int[] dir = direction[i];

                int endRow = dir[0] + row;
                int endCol = dir[1] + col;
                if (!((endRow >= 0 && endRow < map.length) && (endCol >= 0 && endCol < map.length))
                        || visited[endRow][endCol]) {
                    continue;
                }

                if (map[endRow][endCol] != 9 && map[endRow][endCol] > sharkLevel) {
                    continue;
                }

                if (!inQueue[endRow][endCol]) {
                    inQueue[endRow][endCol] = true;
                    pq.add(new int[]{endRow, endCol, sharkLevel, step + 1});
                }

            }

        }
        return new int[]{sharkR, sharkC, min};
    }
}