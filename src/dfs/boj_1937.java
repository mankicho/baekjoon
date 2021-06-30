package dfs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;

public class boj_1937 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


        int n = Integer.parseInt(br.readLine());
        int[][] forest = new int[n][n];


        PriorityQueue<Node> pq = new PriorityQueue<>();
        for (int i = 0; i < forest.length; i++) {
            forest[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            for (int j = 0; j < forest[i].length; j++) {
                pq.add(new Node(forest[i][j], i, j));
            }
        }

        dp = new int[n][n];
        for (int[] ints : dp) {
            Arrays.fill(ints, 1);
        }
        boolean[][] visited = new boolean[n][n];

        while (!pq.isEmpty()) {

            Node node = pq.poll();
            dfs(node.row, node.col, visited, forest);
            int[] loc = function(visited);
            if (loc[0] == -1 && loc[1] == -1) {
                break;
            }

        }
        System.out.println(max);
    }

    static int[] function(boolean[][] visited) {
        for (int i = 0; i < visited.length; i++) {
            for (int j = 0; j < visited.length; j++) {
                if (!visited[i][j]) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[]{-1, -1};
    }

    static int max = 0;

    static int[][] direction = new int[][]{
            {-1, 0}, {0, 1}, {1, 0}, {0, -1}
    };

    static int[][] dp;

    static void dfs(int row, int col, boolean[][] visited, int[][] forest) {
        if (visited[row][col]) {
            return;
        }
        visited[row][col] = true;

        for (int i = 0; i < direction.length; i++) {
            int[] dir = direction[i];

            int tmpRow = row + dir[0];
            int tmpCol = col + dir[1];

            if (!((tmpRow >= 0 && tmpRow < forest.length) && (tmpCol >= 0 && tmpCol < forest.length))) {
                continue;
            }

            if (forest[tmpRow][tmpCol] > forest[row][col]) {
                if (visited[tmpRow][tmpCol]) {
                    if (dp[tmpRow][tmpCol] + 1 > dp[row][col]) {
                        dp[row][col] = dp[tmpRow][tmpCol] + 1;
                    }
                } else {
                    dfs(tmpRow, tmpCol, visited, forest);
                    if (dp[row][col] < dp[tmpRow][tmpCol] + 1) {
                        dp[row][col] = dp[tmpRow][tmpCol] + 1;
                    }
                }
            }
        }

        if (dp[row][col] > max) {
            max = dp[row][col];
        }
    }

    static class Node implements Comparable<Node> {
        int val;
        int row;
        int col;

        public Node(int val, int row, int col) {
            this.val = val;
            this.row = row;
            this.col = col;
        }

        @Override
        public int compareTo(Node o) {
            return val - o.val;
        }
    }
}