package shortest_path;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class boj_1753 {

    static int[] distance;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int v = arr[0];
        int e = arr[1];

        int start = Integer.parseInt(br.readLine());

        distance = new int[v + 1];

        List<List<Node>> nodeList = new ArrayList<>();

        for (int i = 0; i <= v; i++) {
            nodeList.add(new ArrayList<>());
        }

        for (int i = 1; i <= v; i++) {
            distance[i] = Integer.MAX_VALUE;
        }

        for (int i = 0; i < e; i++) {
            int[] tmpArr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            int a = tmpArr[0];
            int b = tmpArr[1];
            int c = tmpArr[2];

            nodeList.get(a).add(new Node(b, c));
        }
        StringBuilder sb = new StringBuilder();

        function(nodeList, distance, start, v);
        for (int i = 1; i < distance.length; i++) {
            if (distance[i] == Integer.MAX_VALUE) {
                sb.append("INF").append("\n");
                continue;
            }
            sb.append(distance[i]).append("\n");
        }
    }

    static void function(List<List<Node>> list, int[] distance, int start, int num) {
        boolean[] visited = new boolean[num + 1];
        // 두 정점 사이에 여러개의 간선이 있을 수 있지만 가장 작은 값을 사용하기위해
        PriorityQueue<Node> pq = new PriorityQueue<>();

        distance[start] = 0;
        pq.add(new Node(start, 0));

        while (!pq.isEmpty()) {
            int now = pq.poll().index;

            if (visited[now]) {
                continue;
            }
            visited[now] = true;

            for (Node node : list.get(now)) {

                if (distance[node.index] > distance[now] + node.distance) {
                    distance[node.index] = distance[now] + node.distance;
                    pq.add(new Node(node.index, distance[node.index]));
                }
            }
        }
    }

    static class Node implements Comparable<Node> {

        public int index;
        public int distance;

        public Node(int index, int distance) {
            this.index = index;
            this.distance = distance;
        }

        @Override
        public int compareTo(Node o) {
            return o.distance > this.distance ? -1 : 1;
        }
    }
}
