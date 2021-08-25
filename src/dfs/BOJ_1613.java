package dfs;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BOJ_1613 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int n = arr[0];
        int k = arr[1];

        List<List<Integer>> list = new ArrayList<>();

        for (int i = 0; i <= n; i++) {
            list.add(new ArrayList<>());
        }
        for (int i = 0; i < k; i++) {
            int[] tmp = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            int parent = tmp[0];
            int child = tmp[1];

            list.get(parent).add(child);
        }
        int[][] dp = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            dfs(list, i, i, new boolean[n + 1], dp);
        }
        int s = Integer.parseInt(br.readLine());
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < s; i++) {
            int[] tmp = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            result.append(dp[tmp[0]][tmp[1]]).append("\n");
        }
        System.out.println(result.toString());
    }

    static void dfs(List<List<Integer>> list, int start, int num, boolean[] visited, int[][] dp) {
        if (visited[num]) {
            return;
        }
        visited[num] = true;
        List<Integer> childs = list.get(num);

        for (int i = 0; i < childs.size(); i++) {
            int next = childs.get(i);
            if (visited[next]) {
                continue;
            }
            dp[start][next] = -1;
            dp[next][start] = 1;
            dfs(list, start, next, visited, dp);
        }
    }
}