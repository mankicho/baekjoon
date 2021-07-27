package graph;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class boj_9370 {

    public static void main(String[] args) throws Exception {
        StringBuilder result = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int testcase = Integer.parseInt(br.readLine());

        for (int i = 0; i < testcase; i++) {
            int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            int n = arr[0];
            int m = arr[1];
            int t = arr[2];

            arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int s = arr[0];
            int g = arr[1];
            int h = arr[2];

            List<Integer> candidates = new ArrayList<>();

            List<List<Node>> originals = new ArrayList<>();

            for (int j = 0; j <= n; j++) {
                originals.add(new ArrayList<>());
            }

            int path = 0;
            for (int j = 0; j < m; j++) {
                arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
                int a = arr[0];
                int b = arr[1];
                int d = arr[2];

                if ((a == g && b == h) || (a == h && b == g)) {
                    path = d;
                }
                originals.get(a).add(new Node(b, d));
                originals.get(b).add(new Node(a, d));
            }

            for (int j = 0; j < t; j++) {
                int candidate = Integer.parseInt(br.readLine());
                candidates.add(candidate);
            }

            int[] originDistance = dijkstra(originals, s);

            int accDist;
            int[] tmpDistance;
            if (originDistance[g] > originDistance[h]) {
                accDist = originDistance[h] + path;
                tmpDistance = dijkstra(originals, g);
            } else {
                accDist = originDistance[g] + path;
                tmpDistance = dijkstra(originals, h);
            }
            Collections.sort(candidates);
            for (Integer candidate : candidates) {
                if (originDistance[candidate] == accDist + tmpDistance[candidate]) {
                    result.append(candidate).append(" ");
                }
            }
            result.append("\n");
        }
        System.out.println(result.toString());
    }

    static int[] dijkstra(List<List<Node>> list, int s) {
        PriorityQueue<Node> pq = new PriorityQueue<>();

        pq.add(new Node(s, 0));

        boolean[] visited = new boolean[list.size()];
        int[] distances = new int[list.size()];

        Arrays.fill(distances, 10000000);
        distances[s] = 0;
        while (!pq.isEmpty()) {
            Node node = pq.poll();

            int nowSpot = node.num;

            if (visited[nowSpot]) {
                continue;
            }
            visited[nowSpot] = true;

            List<Node> nexts = list.get(nowSpot);

            for (int i = 0; i < nexts.size(); i++) {
                Node nextNode = nexts.get(i);

                int nextSpot = nextNode.num;
                int nextDistance = nextNode.dist;

                if (visited[nextSpot]) {
                    continue;
                }
                if (distances[nextSpot] > distances[nowSpot] + nextDistance) {
                    distances[nextSpot] = distances[nowSpot] + nextDistance;
                    pq.add(new Node(nextSpot, distances[nextSpot]));
                }
            }
        }
        return distances;
    }


    static class Node implements Comparable<Node> {
        int num;
        int dist;

        public Node(int num, int dist) {
            this.num = num;
            this.dist = dist;
        }

        @Override
        public int compareTo(Node o) {
            return this.dist - o.dist;
        }

        @Override
        public String toString() {
            return this.num + " " + this.dist;
        }
    }
}
