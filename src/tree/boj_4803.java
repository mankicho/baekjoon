package tree;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class boj_4803 {
    static int answer = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringBuilder sb = new StringBuilder();
        int idx = 1;
        while (true) {
            int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            int n = arr[0];
            int m = arr[1];

            if (n == 0 && m == 0) {
                break;
            }

            List<List<Integer>> tmpList = new ArrayList<>();

            for (int i = 0; i <= n; i++) {
                tmpList.add(new ArrayList<>());
            }

            boolean[] visited = new boolean[n + 1];
            int[] roots = new int[n + 1];
            for (int i = 1; i <= n; i++) {
                roots[i] = i;
            }
            for (int i = 0; i < m; i++) {
                int[] tmpArr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

                int a = tmpArr[0];
                int b = tmpArr[1];

                tmpList.get(a).add(b);
                tmpList.get(b).add(a);
            }

            while (true) {
                int s = findNode(visited);
                if (s == -1) {
                    break;
                }
                function(tmpList, s, visited, roots);
            }

            switch (answer) {
                case 0:
                    sb.append("Case ").append(idx).append(": No trees.").append("\n");
                    break;
                case 1:
                    sb.append("Case ").append(idx).append(": There is one tree.").append("\n");
                    break;
                default:
                    sb.append("Case ").append(idx).append(": A forest of ").append(answer).append(" trees.").append("\n");
                    break;
            }
            answer = 0;
            idx++;
        }
        System.out.println(sb.toString());
    }

    static void function(List<List<Integer>> list, int start, boolean[] visited, int[] roots) {
        LinkedList<Integer> queue = new LinkedList<>();

        if (list.get(start).isEmpty()) {
            visited[start] = true;
            answer++;
            return;
        }
        queue.add(start);
        boolean graph = false;

        while (!queue.isEmpty()) {
            int poll = queue.poll();

            if (visited[poll]) {
                continue;
            }

            visited[poll] = true;

            List<Integer> nextList = list.get(poll);
            for (int i = 0; i < nextList.size(); i++) {
                int next = nextList.get(i);

                if (visited[next]) {
                    continue;
                }

                queue.add(next);

                boolean same = union(roots, poll, next);

                if (same) { // 순환이 있다.
                    graph = true;
                }

            }
        }
        if (!graph) {
            answer++;
        }

    }

    static boolean union(int[] roots, int a, int b) {
        a = find(roots, a);
        b = find(roots, b);

        boolean same = false;

        if (a == b) {
            same = true;
        }
        if (a > b) {
            roots[a] = b;
        } else {
            roots[b] = a;
        }
        return same;
    }

    static int find(int[] roots, int a) {
        if (roots[a] == a) {
            return a;
        }

        return roots[a] = find(roots, roots[a]);
    }

    static void dfs(List<List<Integer>> list, int start, boolean[] visited) {

        List<Integer> nextList = list.get(start);
        visited[start] = true;

        for (int i = 0; i < nextList.size(); i++) {
            int next = nextList.get(i);
            if (visited[next]) {
                continue;
            }

            dfs(list, next, visited);
        }
    }

    static int findNode(boolean[] visited) {
        for (int i = 1; i < visited.length; i++) {
            if (!visited[i]) {
                return i;
            }
        }
        return -1;
    }
}