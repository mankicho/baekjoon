package graph;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class BOJ_1956 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int v = arr[0];
        int e = arr[1];
        List<List<Node>> nodes = new ArrayList<>();

        for (int i = 0; i <= v; i++) {
            nodes.add(new ArrayList<>());
        }

        for (int i = 0; i < e; i++) {
            int[] tmp = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int a = tmp[0];
            int b = tmp[1];
            int c = tmp[2];

            nodes.get(a).add(new Node(b, c));
        }

        int[][] distArr = new int[v + 1][v + 1];
        for (int i = 1; i <= v; i++) {
            distArr[i] = dijkstra(nodes, i);
        }
        int min = 100000000;
        for (int i = 1; i <= v - 1; i++) {
            for (int j = i + 1; j <= v; j++) {
                if (distArr[i][j] != 100000000 && distArr[j][i] != 100000000){
                    if(distArr[i][j] + distArr[j][i] < min){
                        min = distArr[i][j] + distArr[j][i];
                    }
                }
            }
        }
        System.out.println(min == 100000000 ? -1 : min);
    }

    static int[] dijkstra(List<List<Node>> nodes, int num) {
        PriorityQueue<Node> pq = new PriorityQueue<>();

        int[] dist = new int[nodes.size()];
        boolean[] visited = new boolean[nodes.size()];
        Arrays.fill(dist, 100000000);
        dist[num] = 0;
        pq.add(new Node(num, 0));

        while (!pq.isEmpty()) {
            Node poll = pq.poll();

            int next = poll.num;

            if (visited[next]) {
                continue;
            }
            visited[next] = true;

            List<Node> nexts = nodes.get(next);
            for (int i = 0; i < nexts.size(); i++) {
                Node n = nexts.get(i);

                if (dist[n.num] > dist[next] + n.d) {
                    dist[n.num] = dist[next] + n.d;
                    pq.add(new Node(n.num, dist[n.num]));
                }
            }
        }
        return dist;
    }

    static class Node implements Comparable<Node> {
        int num;
        int d;

        public Node(int num, int d) {
            this.num = num;
            this.d = d;
        }

        @Override
        public int compareTo(Node o) {
            return this.d - o.d;
        }
    }
}