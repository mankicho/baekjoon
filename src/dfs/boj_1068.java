package dfs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class boj_1068 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int delete = Integer.parseInt(br.readLine());
        List<List<Integer>> list = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            list.add(new ArrayList<>());
        }
        int root = 0;

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == -1) {
                root = i;
                continue;
            }
            list.get(arr[i]).add(i);
        }

        boolean[] visited = new boolean[n + 1];
        visited[delete] = true;

        dfs(list, root, visited,delete);

        System.out.println(ans);
    }

    static int ans = 0;

    static void dfs(List<List<Integer>> list, int num, boolean[] visited, int delete) {

        if (visited[num]) {
            return;
        }

        visited[num] = true;

        List<Integer> nexts = list.get(num);

        if (nexts.size() == 0 || (nexts.size() == 1 && nexts.get(0) == delete)) {
            ans++;
            return;
        }

        for (int i = 0; i < nexts.size(); i++) {
            int next = nexts.get(i);

            if (visited[next]) {
                continue;
            }

            dfs(list, next, visited,delete);
        }

    }
}