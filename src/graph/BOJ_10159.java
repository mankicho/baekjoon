package graph;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class BOJ_10159 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int m = Integer.parseInt(br.readLine());

        List<List<Integer>> childs = new ArrayList<>();
        List<List<Integer>> parents = new ArrayList<>();

        for (int i = 0; i <= n; i++) {
            childs.add(new ArrayList<>());
            parents.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            String line = br.readLine();
            String[] lineSplit = line.split(" ");

            int a = Integer.parseInt(lineSplit[0]);
            int b = Integer.parseInt(lineSplit[1]);

            childs.get(a).add(b);
            parents.get(b).add(a);
        }
        dpDown = new int[n + 1];
        dpUp = new int[n + 1];

//        dfsDow
//        n(childs,1,new boolean[n+1]);

        StringBuilder result = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            boolean[] visitedDown = new boolean[n + 1];
            boolean[] visitedUp = new boolean[n + 1];
            cnt = 0;
            dfs(childs, i, visitedDown);
            dfs(parents, i, visitedUp);

            for (int j = 1; j <= n; j++) {
                if (visitedDown[j] || visitedUp[j]) {
                    cnt++;
                }
            }
            result.append(n-cnt).append("\n");
        }
        System.out.println(result.toString());

    }

    static int cnt;

    static int[] dpDown;
    static int[] dpUp;

    static void dfs(List<List<Integer>> list, int now, boolean[] visited) {
        if (visited[now]) {
            return;
        }
        visited[now] = true;
        List<Integer> nexts = list.get(now);
        for (int i = 0; i < nexts.size(); i++) {
            int n = nexts.get(i);
            if (visited[n]) {
                continue;
            }
            dfs(list, n, visited);
        }
    }

}