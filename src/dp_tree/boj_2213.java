package dp_tree;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class boj_2213 {
    static int[] selected;
    static boolean[] visited;
    static PriorityQueue<Integer> pq = new PriorityQueue<>();
    static List<List<Integer>> tree = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        selected = new int[n + 1];
        visited = new boolean[n + 1];

        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        List<List<Integer>> list = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            list.add(new ArrayList<>());
            tree.add(new ArrayList<>());
        }

        for (int i = 0; i < n - 1; i++) {
            int[] tmpArr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            int a = tmpArr[0];
            int b = tmpArr[1];

            list.get(a).add(b);
            list.get(b).add(a);
        }
        dfs(list);
        int ans1 = function(tree, arr, 1, 0);
        int ans2 = function(tree, arr, 1, 1);

        if (ans1 > ans2) {
            selected[1] = 1;
            System.out.println(ans1);
            trace(tree, 1, 1);
        } else {
            selected[0] = 0;
            System.out.println(ans2);
            trace(tree, 1, 0);
        }

        StringBuilder sb = new StringBuilder();
        while (!pq.isEmpty()) {
            sb.append(pq.poll()).append(" ");
        }
        System.out.println(sb.toString());
    }

    static void trace(List<List<Integer>> tree, int start, int include) {
        List<Integer> list = tree.get(start);

        if (include == 1) {
            pq.add(start);
            for (int i = 0; i < list.size(); i++) {
                int next = list.get(i);
                trace(tree, next, 0);
            }
        } else {
            for (int i = 0; i < list.size(); i++) {
                int next = list.get(i);
                trace(tree, next, selected[next]);
            }
        }
    }

    static void dfs(List<List<Integer>> list) {
        LinkedList<Integer> queue = new LinkedList<>();
        queue.add(1);

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

    static int function(List<List<Integer>> list, int[] weights, int start, int type) {
        int ans = 0;
        if (type == 0) { // 나를 포함하면
            List<Integer> nextList = list.get(start);

            for (int i = 0; i < nextList.size(); i++) {
                int next = nextList.get(i);
                ans += function(list, weights, next, 1);
            }
            return ans + weights[start - 1];
        } else { // 나를 포함하지 않으면
            List<Integer> nextList = list.get(start);

            for (int i = 0; i < nextList.size(); i++) {
                int next = nextList.get(i);
                int ans1 = function(list, weights, next, 0);
                int ans2 = function(list, weights, next, 1);

                if (ans1 > ans2) {
                    selected[next] = 1;
                } else {
                    selected[next] = 0;
                }
                ans += Math.max(ans1, ans2);
            }

            return ans;
        }
    }
}