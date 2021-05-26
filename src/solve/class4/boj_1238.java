package solve.class4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class boj_1238 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int n = arr[0];
        int m = arr[1];
        int x = arr[2];

        List<List<Node>> nodes = new ArrayList<>();

        for (int i = 0; i <= n; i++) {
            nodes.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int me = arr[0];
            int other = arr[1];
            int distance = arr[2];

            nodes.get(me).add(new Node(other, distance));
        }


        if (n == 1) {
            System.out.println(0);
            return;
        }
        int[] xToDistance = function(x, nodes);


        int max = 0;
        for (int i = 1; i <= n; i++) {
            if (i == x) {
                continue;
            }

            int[] tmpArr = function(i, nodes);

            if (tmpArr[x] + xToDistance[i] > max) {
                max = tmpArr[x] + xToDistance[i];
            }
        }

        System.out.println(max);
    }

    static int[] function(int start, List<List<Node>> nodes) {
        int[] distance = new int[nodes.size()];
        boolean[] visited = new boolean[nodes.size()];

        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[start] = 0;

        LinkedList<Node> queue = new LinkedList<>();

        queue.add(new Node(start, 0));
        while (!queue.isEmpty()) {
            Node n = queue.poll();

            if (visited[n.index]) {
                continue;
            }

            visited[n.index] = true;

            List<Node> nodeList = nodes.get(n.index);
            for (int i = 0; i < nodeList.size(); i++) {
                Node next = nodeList.get(i);

                if (distance[next.index] > distance[n.index] + next.distance) {
                    distance[next.index] = distance[n.index] + next.distance;
                    queue.add(next);
                }
            }
        }

        return distance;
    }

    private static class Node implements Comparable<Node> {
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

        @Override
        public String toString() {
            return "Node{" +
                    "index=" + index +
                    ", distance=" + distance +
                    '}';
        }
    }
}