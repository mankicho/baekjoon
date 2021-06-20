package solve.class5;



import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class boj_1766 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int n = arr[0];
        int m = arr[1];


        PriorityQueue<Node> pq = new PriorityQueue<>();

        List<List<Integer>> childs = new ArrayList<>();

        for (int i = 0; i <= n; i++) {
            childs.add(new ArrayList<>());
        }
        int[] parents = new int[n + 1];

        for (int i = 0; i < m; i++) {
            arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            int a = arr[0];
            int b = arr[1];

            childs.get(a).add(b);
            parents[b]++;
        }

        for (int i = 1; i < parents.length; i++) {
            if (parents[i] == 0) {
                pq.add(new Node(i, 0));
            }
        }
        StringBuilder sb = new StringBuilder();
        boolean[] visited = new boolean[n + 1];

        while (!pq.isEmpty()) {
            Node now = pq.poll();
            if (visited[now.num]) {
                continue;
            }
            visited[now.num] = true;
            sb.append(now.num).append(" ");
            List<Integer> childList = childs.get(now.num);

            for (int i = 0; i < childList.size(); i++) {
                int child = childList.get(i);
                parents[child]--;
                pq.add(new Node(child, parents[child]));
            }
        }
        System.out.println(sb.toString());
    }

    static class Node implements Comparable<Node> {
        int num;
        int parents;

        public Node(int num, int parents) {
            this.num = num;
            this.parents = parents;
        }

        @Override
        public int compareTo(Node o) {
            if (parents == o.parents) {
                return this.num - o.num;
            }
            return this.parents - o.parents;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "num=" + num +
                    ", parents=" + parents +
                    '}';
        }
    }
}