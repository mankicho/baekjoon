package solve.class4;



import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class boj_11779 {
    static int answer = 0;
    static List<Integer> path = new ArrayList<>();

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

        int[] tmp = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int start = tmp[0];
        int end = tmp[1];

        nodeList.stream().forEach(list -> list.sort((o1, o2) -> o1.distance - o2.distance));
        boolean[] visited = new boolean[n + 1];
        int[] distance = new int[n + 1];
        int[] preNode = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            preNode[i] = i;
        }
        Arrays.fill(distance, 200000000);
        distance[start] = 0;
        dijkstra(nodeList, start, visited, distance, preNode, end);
        StringBuilder result = new StringBuilder();
        result.append(distance[end]).append("\n").append(answer).append("\n");
        for (int i = path.size() - 1; i >= 0; i--) {
            result.append(path.get(i)).append(" ");
        }
        System.out.println(result.toString());

    }

    static void dijkstra(List<List<Node>> nodeList, int start, boolean[] visited,
                         int[] distance, int[] preNode, int end) {
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

                if (visited[next.num]) {
                    continue;
                }

                if (distance[next.num] > distance[now.num] + next.distance) {
                    preNode[next.num] = now.num;
                    distance[next.num] = Math.min(distance[next.num], distance[now.num] + next.distance);
                    pq.add(new Node(next.num, distance[next.num]));
                }
            }
        }
        int dest = end;
        while (dest != start) {
            path.add(dest);
            dest = preNode[dest];
            answer++;
        }

        path.add(start);
        answer++;
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