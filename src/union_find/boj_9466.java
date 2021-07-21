package union_find;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class boj_9466 {

    static int[] roots;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int tc = Integer.parseInt(br.readLine());
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < tc; i++) {
            int n = Integer.parseInt(br.readLine());
            roots = new int[n + 1];
            for (int j = 1; j < roots.length; j++) {
                roots[j] = j;
            }
            int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            ans = 0;
            boolean[] visited = new boolean[n + 1];
            for (int j = 1; j < roots.length; j++) {
                dfs(arr, j, visited);
            }
            result.append(n - ans).append("\n");
        }
        System.out.println(result.toString());
    }

    static int ans;

    static void dfs(int[] arr, int now, boolean[] visited) {
        if (visited[now]) {
            return;
        }
        visited[now] = true;

        int next = arr[now - 1];

        if (find(now, roots) != find(next, roots)) {
            union(roots, now, next);
            dfs(arr, next, visited);
        } else {
            int cnt = 1;
            for (int i = next; i != now; i = arr[i-1]) {
                cnt++;
            }
            ans += cnt;
        }
    }

    static int find(int a, int[] roots) {
        if (roots[a] == a) {
            return a;
        }
        return roots[a] = find(roots[a], roots);
    }

    static void union(int[] roots, int a, int b) {
        a = find(a, roots);
        b = find(b, roots);
        if (a != b) {
            if (a > b) {
                roots[a] = b;
            } else {
                roots[b] = a;
            }
        }
    }
}

