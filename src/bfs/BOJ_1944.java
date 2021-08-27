package bfs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_1944 {

    static int[][] nodeNumber;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int n = arr[0];
        int m = arr[1];

        char[][] map = new char[n][n];
        nodeNumber = new int[n][n];

        List<int[]> list = new ArrayList<>();
        int num = 1;
        for (int i = 0; i < n; i++) {
            map[i] = br.readLine().toCharArray();
            for (int j = 0; j < map[i].length; j++) {
                if (map[i][j] == 'K' || map[i][j] == 'S') {
                    nodeNumber[i][j] = num;
                    list.add(new int[]{num++, i, j});
                }
            }
        }

        List<int[]> edges = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j] == 'K' || map[i][j] == 'S') {
                    dfs(map, new int[]{i, j}, new boolean[n][n], edges);
                }
            }
        }
        edges.sort(Comparator.comparingInt(o -> o[2]));
        int[] roots = new int[m + 2];
        for (int i = 1; i <= m+1; i++) {
            roots[i] = i;
        }

        int sum = 0;
        for (int[] edge : edges) {
            int start = edge[0];
            int end = edge[1];
            int weight = edge[2];

            if(weight == 0){
                continue;
            }
            int findStart = find(roots, start);
            int findEnd = find(roots, end);

            if (findStart != findEnd) {
                union(roots, findStart, findEnd);
                sum += weight;
            }
        }

        boolean allFind = true;
        for (int i = 2; i <roots.length ; i++) {
            if(roots[i] == i){
                allFind= false;
                break;
            }
        }
        System.out.println(allFind ? sum : -1);
    }

    static void dfs(char[][] map, int[] start, boolean[][] visited, List<int[]> edges) {
        PriorityQueue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(o -> o[2]));

        int nowNodeNumber = nodeNumber[start[0]][start[1]];

        queue.add(new int[]{start[0], start[1], 0});

        int[][] dir = new int[][]{
                {0, 1}, {1, 0}, {0, -1}, {-1, 0}
        };

        while (!queue.isEmpty()) {
            int[] poll = queue.poll();

            int row = poll[0];
            int col = poll[1];
            int path = poll[2];

            if (visited[row][col]) {
                continue;
            }

            visited[row][col] = true;
            if (map[row][col] == 'S' || map[row][col] == 'K') {
                int anotherNumber = nodeNumber[row][col];
                edges.add(new int[]{nowNodeNumber, anotherNumber, path});
            }

            for (int i = 0; i < dir.length; i++) {
                int[] d = dir[i];

                int dr = d[0] + row;
                int dc = d[1] + col;

                if (!((dr >= 0 && dr < map.length) && (dc >= 0 && dc < map.length))
                        || map[dr][dc] == '1' || visited[dr][dc]) {
                    continue;
                }

                queue.add(new int[]{dr, dc, path + 1});
            }

        }
    }

    static int find(int[] roots, int a) {
        if (roots[a] == a) {
            return a;
        }
        return roots[a] = find(roots, roots[a]);
    }

    static void union(int[] roots, int a, int b) {
        a = find(roots, a);
        b = find(roots, b);

        if (a != b) {
            if (a > b) {
                roots[a] = b;
            } else {
                roots[b] = a;
            }
        }
    }
}
