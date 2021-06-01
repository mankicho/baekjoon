package dp_tree;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class boj_1949 {
    static boolean[] visited;

    static int[][] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        dp = new int[n + 1][2];

        visited = new boolean[n + 1];
        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        List<List<Integer>> list = new ArrayList<>();
        List<List<Integer>> tree = new ArrayList<>();

        for (int i = 0; i <= n; i++) {
            list.add(new ArrayList<>());
            tree.add(new ArrayList<>());
        }
        for (int i = 0; i < n - 1; i++) {
            int[] tmp = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int a = tmp[0];
            int b = tmp[1];

            list.get(a).add(b);
            list.get(b).add(a);
        }

        dfs(list, tree, 1);
        dp(tree, arr, 1);

        System.out.println(Math.max(dp[1][0], dp[1][1]));
    }

    static void dp(List<List<Integer>> tree,
                   int[] weights, int root) {

        dp[root][0] = weights[root - 1]; // 우수
        dp[root][1] = 0; // 우수

        List<Integer> nextList = tree.get(root);

        for (int i = 0; i < nextList.size(); i++) {
            int next = nextList.get(i);

            dp(tree, weights, next);
            dp[root][0] += dp[next][1];
            dp[root][1] += Math.max(dp[next][0], dp[next][1]);
        }

    }


    static void dfs(List<List<Integer>> list, List<List<Integer>> tree,
                    int start) {
        LinkedList<Integer> queue = new LinkedList<>();

        queue.add(start);

        while (!queue.isEmpty()) {
            int poll = queue.poll();

            if (visited[poll]) {
                continue;
            }
            visited[poll] = true;

            List<Integer> tmpList = list.get(poll);

            for (int i = 0; i < tmpList.size(); i++) {
                int next = tmpList.get(i);

                if (visited[next]) {
                    continue;
                }

                tree.get(poll).add(next);
                queue.add(next);
            }
        }
    }
}
