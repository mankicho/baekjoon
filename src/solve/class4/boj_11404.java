package solve.class4;



import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class boj_11404 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        List<List<Node>> nodeList = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            nodeList.add(new ArrayList<>());
        }
        for (int i = 0; i < m; i++) {
            int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            int a = arr[0];
            int b = arr[1];
            int c = arr[2];

            nodeList.get(a).add(new Node(b, c));
        }

        nodeList.stream().forEach(list -> list.sort((o1, o2) -> o1.distance - o2.distance));
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            boolean[] visited = new boolean[n + 1];
            int[] distance = new int[n + 1];

            Arrays.fill(distance, 200000000);
            distance[i] = 0;
            dijkstra(nodeList, i, visited, distance, sb);
        }
        System.out.println(sb.toString());
    }

    static void dijkstra(List<List<Node>> nodeList, int start, boolean[] visited,
                         int[] distance, StringBuilder sb) {
        Node n = new Node(start, 0);

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(n);
        while (!pq.isEmpty()) {
            Node now = pq.poll();

            if (visited[now.num]) {
                continue;
            }

            visited[now.num] = true;
            List<Node> nextNodes = nodeList.get(now.num);
            for (int i = 0; i < nextNodes.size(); i++) {
                Node next = nextNodes.get(i);
                if(visited[next.num]){
                    continue;
                }
                distance[next.num] = Math.min(distance[next.num], distance[now.num] + next.distance);
                pq.add(new Node(next.num, distance[next.num]));
            }
        }
        for (int i = 1; i < distance.length; i++) {
            if(distance[i] == 200000000){
                sb.append("0").append(" ");
            }else {
                sb.append(distance[i]).append(" ");
            }
        }
        sb.append("\n");
    }

    static class Node implements Comparable<Node> {
        int num;
        int distance;

        public Node(int num, int distance) {
            this.num = num;
            this.distance = distance;
        }

        @Override
        public int compareTo(Node o) {
            return this.distance - o.distance;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "num=" + num +
                    ", distance=" + distance +
                    '}';
        }
    }
}