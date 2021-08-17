package bfs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class BOJ_9328 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int testcase = Integer.parseInt(br.readLine());
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < testcase; i++) {
            int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int n = arr[0];
            int m = arr[1];

            int floorKeys = 0;
            char[][] map = new char[n][m];
            boolean[] visited = new boolean[500];
            for (int j = 0; j < n; j++) {
                map[j] = br.readLine().toCharArray();
                for (int k = 0; k < map[j].length; k++) {
                    if (map[j][k] >= 'a' && map[j][k] <= 'z') {
                        if (!visited[map[j][k]]) {
                            floorKeys++;
                            visited[map[j][k]] = true;
                        }
                    }
                }
            }

            String keys = br.readLine();
            boolean[] keyArr = new boolean[500];
            for (int j = 0; j < keys.length(); j++) {
                keyArr[keys.charAt(j)] = true;
                keyArr[keys.charAt(j) - 32] = true;
            }


            List<int[]> enters = new ArrayList<>();

            for (int j = 0; j < map[0].length; j++) {
                if (map[0][j] != '*') {
                    enters.add(new int[]{0, j});
                }

                if (map[n - 1][j] != '*') {
                    enters.add(new int[]{n - 1, j});
                }
            }

            for (int j = 1; j < map.length - 1; j++) {
                if (map[j][0] != '*') {
                    enters.add(new int[]{j, 0});
                }
                if (map[j][m - 1] != '*') {
                    enters.add(new int[]{j, m - 1});
                }
            }
            for (int j = 0; j < floorKeys; j++) {
                findKeysUsingBFS(map, enters, keyArr);
            }
            result.append(findDocumentsUsingBFS(map, enters, keyArr)).append("\n");
        }
        System.out.println(result.toString());
    }

    static int findDocumentsUsingBFS(char[][] map, List<int[]> enters, boolean[] keyArr) {
        LinkedList<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[map.length][map[0].length];

        int ans = 0;
        enters.forEach(enter -> {
            queue.add(new int[]{enter[0], enter[1]});
        });

        int[][] dir = new int[][]{
                {1, 0}, {0, 1}, {-1, 0}, {0, -1}
        };

        while (!queue.isEmpty()) {
            int[] poll = queue.poll();
            int r = poll[0];
            int c = poll[1];

            if (visited[r][c]) {
                continue;
            }

            if (map[r][c] == '$') {
                ans++;
            }

            visited[r][c] = true;

            for (int i = 0; i < dir.length; i++) {
                int[] d = dir[i];

                int row = d[0] + r;
                int col = d[1] + c;

                if (!((row >= 0 && row < map.length) && (col >= 0 && col < map[0].length))
                        || visited[row][col] || map[row][col] == '*') {
                    continue;
                }
                if (map[r][c] >= 'A' && map[r][c] <= 'Z') {
                    if (!keyArr[map[r][c]]) {
                        continue;
                    }
                }

                queue.add(new int[]{row, col});
            }
        }
        return ans;
    }

    static void findKeysUsingBFS(char[][] map, List<int[]> enters, boolean[] keyArr) {
        LinkedList<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[map.length][map[0].length];

        enters.forEach(enter -> {
            queue.add(new int[]{enter[0], enter[1]});
        });

        int[][] dir = new int[][]{
                {1, 0}, {0, 1}, {-1, 0}, {0, -1}
        };

        while (!queue.isEmpty()) {
            int[] poll = queue.poll();
            int r = poll[0];
            int c = poll[1];

            if (visited[r][c]) {
                continue;
            }

            if (map[r][c] >= 'A' && map[r][c] <= 'Z') {
                if (!keyArr[map[r][c]]) {
                    continue;
                }
            }

            if (map[r][c] >= 'a' && map[r][c] <= 'z') {
                keyArr[map[r][c]] = true;
                keyArr[map[r][c] - 32] = true;
            }
            visited[r][c] = true;

            for (int i = 0; i < dir.length; i++) {
                int[] d = dir[i];

                int row = d[0] + r;
                int col = d[1] + c;

                if (!((row >= 0 && row < map.length) && (col >= 0 && col < map[0].length))
                        || visited[row][col] || map[row][col] == '*') {
                    continue;
                }

                queue.add(new int[]{row, col});
            }
        }
    }
}