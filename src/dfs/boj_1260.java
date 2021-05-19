package dfs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class boj_1260 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int n = arr[0];
        int m = arr[1];
        int v = arr[2];

        List<List<Integer>> list = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            list.add(new ArrayList<>());
        }
        for (int i = 0; i < m; i++) {
            int[] tmpArr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int startNode = tmpArr[0];
            int endNode = tmpArr[1];

            list.get(startNode).add(endNode);
            list.get(endNode).add(startNode);
        }

        for (int i = 0; i < list.size(); i++) {
            Collections.sort(list.get(i));
        }
        

        StringBuilder dfsSB = new StringBuilder();
        StringBuilder bfsSB = new StringBuilder();
        boolean[] dfsVisited = new boolean[n + 1];
        boolean[] bfsVisited = new boolean[n + 1];
        dfs(dfsSB, v, list, dfsVisited);
        bfs(bfsSB, v, list, bfsVisited);
        System.out.println(dfsSB.toString());
        System.out.println(bfsSB.toString());
    }

    static void dfs(StringBuilder sb, int node, List<List<Integer>> list, boolean[] visited) {
        List<Integer> nodes = list.get(node);
        sb.append(node).append(" ");

        visited[node] = true;
        for (int i = 0; i < nodes.size(); i++) {
            int point = nodes.get(i);

            if (!visited[point]) {
                dfs(sb, point, list, visited);
            }
        }
    }

    static void bfs(StringBuilder sb, int start, List<List<Integer>> list, boolean[] visited) {

        List<Integer> nodes = list.get(start);

        Queue<List<Integer>> queue = new LinkedList<>();

        visited[start] = true;
        queue.add(nodes);

        sb.append(start).append(" ");
        while (!queue.isEmpty()) {
            List<Integer> pollList = queue.poll();

            for (int i = 0; i < pollList.size(); i++) {
                int node = pollList.get(i);

                if (!visited[node]) {
                    sb.append(node).append(" ");
                    queue.add(list.get(node));
                    visited[node] = true;
                }
            }
        }
    }
}
