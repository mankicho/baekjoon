package tree;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class boj_1967 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());


        List<List<Node>> nodeList = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            nodeList.add(new ArrayList<>());
        }
        for (int i = 0; i < n - 1; i++) {
            int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            int parent = arr[0];
            int child = arr[1];
            int distance = arr[2];
            nodeList.get(parent).add(new Node(child, distance));
            nodeList.get(child).add(new Node(parent, distance));
        }

        int[] distance = dijkstra(1, nodeList);
        int idx = 0;
        int max = -1;

        for (int i = 1; i < distance.length; i++) {
            if (distance[i] > max) {
                max = distance[i];
                idx = i;
            }
        }

        distance = dijkstra(idx, nodeList);

        idx = 0;
        max = -1;

        for (int i = 1; i < distance.length; i++) {
            if (distance[i] > max) {
                max = distance[i];
                idx = i;
            }
        }

        System.out.println(distance[idx]);
    }

    static int[] dijkstra(int start, List<List<Node>> list) {
        int[] distance = new int[list.size()];
        boolean[] visited = new boolean[list.size()];

        Arrays.fill(distance, Integer.MAX_VALUE);
        PriorityQueue<Node> pq = new PriorityQueue<>();

        pq.add(new Node(start, 0));

        distance[start] = 0;
        while (!pq.isEmpty()) {
            Node now = pq.poll();

            if (visited[now.num]) {
                continue;
            }

            visited[now.num] = true;

            List<Node> nodes = list.get(now.num);

            for (int i = 0; i < nodes.size(); i++) {
                Node next = nodes.get(i);

                if (distance[next.num] > distance[now.num] + next.weight) {
                    distance[next.num] = distance[now.num] + next.weight;
                    pq.add(new Node(next.num,distance[next.num]));
                }
            }
        }
        return distance;
    }

    private static class Node implements Comparable<Node> {
        int num;
        int weight;

        public Node(int num, int weight) {
            this.num = num;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node o) {
            return weight - o.weight;
        }
    }
}
