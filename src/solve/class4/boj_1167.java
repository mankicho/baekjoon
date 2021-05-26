package solve.class4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class boj_1167 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int v = Integer.parseInt(br.readLine());

        List<List<Node>> nodes = new ArrayList<>();
        for (int i = 0; i <= v; i++) {
            nodes.add(new ArrayList<>());
        }

        for (int i = 0; i < v; i++) {
            int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            int me = arr[0];

            for (int j = 1; j < arr.length - 1; j = j + 2) {
                int other = arr[j];
                int distance = arr[j + 1];

                nodes.get(me).add(new Node(other, distance));
            }
        }

        int[] arr = function(1, nodes);

        int max = 0;
        int idx = 0;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
                idx = i;
            }
        }

        arr = function(idx, nodes);

        max = 0;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        System.out.println(max);
    }

    static int[] function(int start, List<List<Node>> list) {
        int[] distance = new int[list.size()];
        boolean[] visited = new boolean[list.size()];

        PriorityQueue<Node> queue = new PriorityQueue<>();

        queue.add(new Node(start, 0));

        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[start] = 0;
        while (!queue.isEmpty()) {
            Node node = queue.poll();

            if (visited[node.index]) {
                continue;
            }

            visited[node.index] = true;

            List<Node> nodes = list.get(node.index);

            for (int i = 0; i < nodes.size(); i++) {
                Node n = nodes.get(i);
                if (distance[n.index] > distance[node.index] + n.distance) {
                    distance[n.index] = distance[node.index] + n.distance;
                    queue.add(new Node(n.index, distance[n.index]));
                }
            }
        }

        return distance;
    }

    static class Node implements Comparable<Node> {
        int index;
        int distance;

        public Node(int index, int distance) {
            this.index = index;
            this.distance = distance;
        }

        @Override
        public int compareTo(Node o) {
            return this.distance - o.distance;
        }
    }
}