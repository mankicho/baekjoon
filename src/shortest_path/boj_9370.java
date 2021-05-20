package shortest_path;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class boj_9370 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        for (int j = 0; j < T; j++) {
            int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            int n = arr[0];
            int m = arr[1];
            int t = arr[2];

            int[] arr2 = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            int s = arr2[0];
            int g = arr2[1];
            int h = arr2[2];

            List<List<Node>> nodeList = new ArrayList<>();

            for (int i = 0; i <= n; i++) {
                nodeList.add(new ArrayList<>());
            }
            int tmpDistance = 0;
            for (int i = 0; i < m; i++) {
                int[] arr3 = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
                int a = arr3[0];
                int b = arr3[1];
                int d = arr3[2];

                if (a == g && b == h || a == h && b == g) {
                    nodeList.get(a).add(new Node(b, 2 * d - 1));
                    nodeList.get(b).add(new Node(a, 2 * d - 1));
                } else {
                    nodeList.get(a).add(new Node(b, 2 * d));
                    nodeList.get(b).add(new Node(a, 2 * d));
                }
            }

            StringBuilder sb = new StringBuilder();
            List<Integer> destinies = new ArrayList<>();
            for (int i = 0; i < t; i++) {
                destinies.add(Integer.parseInt(br.readLine()));
            }
            int[] tmpArr = function(s, n, nodeList);

            List<Integer> tmpList = new ArrayList<>();
            for (int k = 0; k < destinies.size(); k++) {
                int destiny = destinies.get(k);

                if (tmpArr[destiny] != Integer.MAX_VALUE && tmpArr[destiny] % 2 != 0) {
                    tmpList.add(destiny);
                }
            }
            Collections.sort(tmpList);
            tmpList.forEach(i -> sb.append(i).append(" "));
            System.out.println(sb.toString());
        }


    }

    static int[] function(int start, int n, List<List<Node>> list) {
        boolean[] visited = new boolean[n + 1];
        int[] distance = new int[n + 1];

        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[start] = 0;

        PriorityQueue<Node> queue = new PriorityQueue<>();
        queue.add(new Node(start, 0));
        while (!queue.isEmpty()) {
            Node node = queue.poll();

            if (visited[node.index]) {
                continue;
            }

            visited[node.index] = true;

            List<Node> nodes = list.get(node.index);
            for (int i = 0; i < nodes.size(); i++) {
                Node other = nodes.get(i);

                if (distance[other.index] > distance[node.index] + other.distance) {
                    distance[other.index] = distance[node.index] + other.distance;
                    queue.add(other);
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
