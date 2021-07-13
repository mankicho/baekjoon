package graph;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class boj_1922 {
    public static void boj_1922(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[2] - o2[2];
            }
        });
        for (int i = 0; i < m; i++) {
            int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            pq.add(arr);
        }

        int[] roots = new int[n + 1];

        for (int i = 1; i < roots.length; i++) {
            roots[i] = i;
        }

        int ans = 0;
        while (!pq.isEmpty()) {
            int[] arr = pq.poll();

            int a = arr[0];
            int b = arr[1];

            if (find(roots, a) == find(roots, b)) {
                continue;
            }

            union(roots, a, b);
            ans += arr[2];
        }
        System.out.println(ans);
    }

    static int find(int[] roots, int a) {
        if (roots[a] == a) {
            return a;
        }

        return roots[a] = find(roots, roots[a]);
    }

    static void union(int[] roots, int a, int b) {
        a = find(roots, a);
        b = find(roots, b);

        if (a != b) {
            if (a > b) {
                roots[a] = b;
            } else {
                roots[b] = a;
            }
        }
    }
}