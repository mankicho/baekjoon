package tree;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class boj_11437 {


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


        int n = Integer.parseInt(br.readLine());

        int[] parents = new int[n + 1];
        int[] levels = new int[n + 1];
        boolean[] visited = new boolean[n + 1];

        List<List<Integer>> list = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            list.add(new ArrayList<>());
        }

        for (int i = 0; i < n - 1; i++) {
            int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            list.get(arr[0]).add(arr[1]);
            list.get(arr[1]).add(arr[0]);
        }

        int m = Integer.parseInt(br.readLine());

        dfs(parents, list, visited, levels, 1);
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < m; i++) {
            int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            if (levels[arr[0]] > levels[arr[1]]) {
                result.append(function(parents, levels, arr[0], arr[1])).append("\n");
            } else {
                result.append(function(parents, levels, arr[1], arr[0])).append("\n");
            }
        }
        System.out.println(result.toString());
    }

    static int function(int[] parents, int[] levels, int big, int small) {
        int smallLevel = levels[small];
        int bigLevel = levels[big];

        while (bigLevel > smallLevel) {
            big = parents[big];
            bigLevel = levels[big];
        }

        while (big != small) {
            big = parents[big];
            small = parents[small];
        }

        return small;
    }

    static void dfs(int[] parents, List<List<Integer>> list, boolean[] visited, int[] levels, int num) {
        if (visited[num]) {
            return;
        }
        visited[num] = true;

        List<Integer> childs = list.get(num);

        for (int i = 0; i < childs.size(); i++) {
            int child = childs.get(i);

            if (visited[child]) {
                continue;
            }
            parents[child] = num;
            levels[child] = levels[num] + 1;
            dfs(parents, list, visited, levels, child);
        }
    }

}