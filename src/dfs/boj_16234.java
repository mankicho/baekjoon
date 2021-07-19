package dfs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class boj_16234 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int n = arr[0];
        int l = arr[1];
        int r = arr[2];

        int[][] map = new int[n][n];

        for (int i = 0; i < n; i++) {
            map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }
        int ans = 0;
        while (true) {

            int bfsNum = 0;
            boolean[][] visited = new boolean[n][n];
            boolean[][] inList = new boolean[n][n];

            while (true) {

                int[] start = find(visited);
                if (start[0] == -1 && start[1] == -1) {
                    break;
                }
                tmpList = new ArrayList<>();
                tmp = 0;
                bfs(map, visited, inList, start[0], start[1], l, r);
                for (int[] ints : tmpList) {
                    bfsNum++;
                    int row = ints[0];
                    int col = ints[1];

                    map[row][col] = tmp / tmpList.size();
                }

            }

            if (bfsNum == 0) {
                break;
            }
            ans++;
        }
        System.out.println(ans);

    }

    static int[][] direction = new int[][]{
            {0, 1}, {1, 0}, {0, -1}, {-1, 0}
    };

    static List<int[]> tmpList;
    static int tmp;

    static int[] find(boolean[][] visited) {
        for (int i = 0; i < visited.length; i++) {
            for (int j = 0; j < visited.length; j++) {
                if (!visited[i][j]) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[]{-1, -1};
    }

    static void bfs(int[][] map, boolean[][] visited, boolean[][] inList, int r, int c, int left, int right) {

        LinkedList<int[]> queue = new LinkedList<>();

        queue.add(new int[]{r, c});

        while (!queue.isEmpty()) {
            int[] poll = queue.poll();

            int row = poll[0];
            int col = poll[1];

            if (visited[row][col]) {
                continue;
            }

            visited[row][col] = true;
            for (int i = 0; i < direction.length; i++) {
                int[] dir = direction[i];

                int endRow = dir[0] + row;
                int endCol = dir[1] + col;

                if (!((endRow >= 0 && endRow < map.length) && (endCol >= 0 && endCol < map.length))
                        || inList[endRow][endCol]) {
                    continue;
                }

                int endVal = map[endRow][endCol];
                int nowVal = map[row][col];

                int abs = Math.abs(endVal - nowVal);

                if (abs >= left && abs <= right) {
                    inList[endRow][endCol] = true;
                    tmpList.add(new int[]{endRow, endCol});
                    tmp += map[endRow][endCol];
                    if(!visited[endRow][endCol]){
                        queue.add(new int[]{endRow, endCol});

                    }
                }

            }
        }


    }
}