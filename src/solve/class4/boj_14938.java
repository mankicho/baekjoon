package solve.class4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class boj_14938 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();


        int n = arr[0];
        int m = arr[1];
        int r = arr[2];

        int[] distance = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        List<List<Node>> nodes = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            nodes.add(new ArrayList<>());
        }

        for (int i = 0; i < r; i++) {
            arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            int a = arr[0];
            int b = arr[1];
            int l = arr[2];

            nodes.get(a).add(new Node(b, l));
            nodes.get(b).add(new Node(a, l));
        }


        for(int i=1;i<=n;i++){
            dijkstra(nodes,i,distance,m);
        }
        System.out.println(max);
    }

    static int max = 0;

    static void dijkstra(List<List<Node>> nodes, int start, int[] distance, int m) {
        boolean[] visited = new boolean[nodes.size()];
        int[] tmpDistance = new int[nodes.size()];
        Arrays.fill(tmpDistance, 1000000);
        tmpDistance[start] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node now = pq.poll();

            if (visited[now.num]) {
                continue;
            }

            visited[now.num] = true;

            List<Node> nexts = nodes.get(now.num);

            for (int i = 0; i < nexts.size(); i++) {
                Node next = nexts.get(i);
                if (visited[next.num]) {
                    continue;
                }
                if (tmpDistance[next.num] > tmpDistance[now.num] + next.distance) {
                    tmpDistance[next.num] = tmpDistance[now.num] + next.distance;
                    pq.add(new Node(next.num, tmpDistance[next.num]));
                }
            }
        }

        int sum = 0;
        for (int i = 1; i < tmpDistance.length; i++) {
            if (tmpDistance[i] <= m) {
                sum += distance[i-1];
            }
        }

        if(sum > max){
            max = sum;
        }
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
            return distance - o.distance;
        }
    }
}