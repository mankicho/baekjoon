package solve.class4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class boj_1916 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int m = Integer.parseInt(br.readLine());

        List<List<Node>> list = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            list.add(new ArrayList<>());
        }
        for (int i = 0; i < m; i++) {
            int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            int start = arr[0];
            int end = arr[1];
            int cost = arr[2];

            list.get(start).add(new Node(end, cost));
        }

        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int start = arr[0];
        int end = arr[1];

        int[] distance = function(start, list);

        System.out.println(distance[end]);

    }

    static int[] function(int start, List<List<Node>> list) {
        int[] distance = new int[list.size()];
        boolean[] visited = new boolean[list.size()];

        Arrays.fill(distance, Integer.MAX_VALUE);

        distance[start] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node now = pq.poll();

            if (visited[now.num]) {
                continue;
            }

            visited[now.num] = true;

            List<Node> nextNodeList = list.get(now.num);

            for (int i = 0; i < nextNodeList.size(); i++) {
                Node next = nextNodeList.get(i);

                if (distance[next.num] > distance[now.num] + next.weight) {
                    distance[next.num] = distance[now.num] + next.weight;

                    next.weight = distance[next.num];
                    pq.add(next);
                }
            }

        }

        return distance;
    }

    static class Node implements Comparable<Node> {
        int num;
        int weight;

        public Node(int num, int weight) {
            this.num = num;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node o) {
            return this.weight - o.weight;
        }
    }
}