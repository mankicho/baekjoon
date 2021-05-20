package shortest_path;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class boj_1504 {
    static int[] distance1;

    static boolean isOver = false;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int n = arr[0];
        int e = arr[1];

        distance1 = new int[n + 1];

        Arrays.fill(distance1, Integer.MAX_VALUE);
        List<List<Node>> nodeList = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            nodeList.add(new ArrayList<>());
        }
        for (int i = 0; i < e; i++) {
            int[] tmpArr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int a = tmpArr[0];
            int b = tmpArr[1];
            int c = tmpArr[2];

            nodeList.get(a).add(new Node(b, c));
            nodeList.get(b).add(new Node(a, c));
        }
        arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int mustNode1 = arr[0];
        int mustNode2 = arr[1];

        int first = function(1, mustNode1, nodeList) + function(mustNode1, mustNode2, nodeList) + function(mustNode2, n, nodeList);
        int second = function(1, mustNode2, nodeList) + function(mustNode2, mustNode1, nodeList) + function(mustNode1, n, nodeList);

        if(isOver){
            System.out.println(-1);
        }else
        System.out.println(first > second ? second : first);


    }

    static int function(int start, int end, List<List<Node>> list) {
        boolean[] visited = new boolean[distance1.length];

        int[] distance = new int[distance1.length];
        Arrays.fill(distance, Integer.MAX_VALUE);
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(start, 0));
        distance[start] = 0;
        while (!pq.isEmpty()) {
            Node node = pq.poll();

            if (visited[node.index]) {
                continue;
            }
            visited[node.index] = true;

            for (int i = 0; i < list.get(node.index).size(); i++) {
                Node n = list.get(node.index).get(i);
                if (distance[n.index] > distance[node.index] + n.distance) {
                    distance[n.index] = distance[node.index] + n.distance;
                    pq.add(n);
                }
            }
        }
        if (distance[end] == Integer.MAX_VALUE) {
            isOver = true;
        }
        return distance[end];
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

        @Override
        public String toString() {
            return "Node{" +
                    "index=" + index +
                    ", distance=" + distance +
                    '}';
        }
    }
}
