package solve.class4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;

public class boj_12851 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int n = arr[0];
        int k = arr[1];
        int ans = 0;
        PriorityQueue<Node> queue = new PriorityQueue<>();

        queue.add(new Node(n, 0));

        int[] dp = new int[1000000];
        Arrays.fill(dp, 1000000);
        dp[n] = 0;
        if (n >= k) {
            System.out.println(k - n);
            System.out.println(1);
            return;
        }
        while (!queue.isEmpty()) {
            Node now = queue.poll();


            if (now.num > k*2 || now.path > dp[k]) {
                continue;
            }
            if (now.num == k) {
                if (now.path < dp[k]) {
                    ans = 1;
                } else {
                    ans++;
                }
            }
            if (now.num - 1 > 0 && dp[now.num - 1] > dp[now.num]) {
                dp[now.num - 1] = dp[now.num] + 1;
                queue.add(new Node(now.num - 1, dp[now.num - 1]));
            }

            if (dp[now.num + 1] > dp[now.num]) {
                dp[now.num + 1] = dp[now.num] + 1;
                queue.add(new Node(now.num + 1, dp[now.num + 1]));
            }

            if (dp[now.num * 2] > dp[now.num]) {
                dp[now.num * 2] = dp[now.num] + 1;
                queue.add(new Node(now.num * 2, dp[now.num * 2]));
            }
        }
        System.out.println(dp[k]);
        if (n == 1) {
            System.out.println(ans / 2);
        } else {
            System.out.println(ans);
        }
    }

    static class Node implements Comparable<Node> {
        int num;
        int path;

        public Node(int num, int path) {
            this.num = num;
            this.path = path;
        }

        @Override
        public int compareTo(Node o) {
            return this.path - o.path;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "num=" + num +
                    ", path=" + path +
                    '}';
        }
    }
}