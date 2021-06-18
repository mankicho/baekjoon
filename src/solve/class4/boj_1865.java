package solve.class4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class boj_1865 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int testcase = Integer.parseInt(br.readLine());

        for (int i = 0; i < testcase; i++) {
            int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            int n = arr[0];
            int m = arr[1];
            int w = arr[2];


            List<List<Node>> doro = new ArrayList<>();
            List<List<Node>> hole = new ArrayList<>();

            for (int j = 0; j <= n; j++) {
                doro.add(new ArrayList<>());
                hole.add(new ArrayList<>());
            }

            for (int j = 0; j < m; j++) {
                int[] tmp = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

                int s = tmp[0];
                int e = tmp[1];
                int t = tmp[2];

                doro.get(s).add(new Node(e, t));
                doro.get(e).add(new Node(s, t));
            }

            doro.forEach(d -> d.sort((o1, o2) -> o1.cost - o2.cost));

            for (int j = 0; j < w; j++) {
                int[] tmp = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

                int s = tmp[0];
                int e = tmp[1];
                int t = tmp[2];
                hole.get(s).add(new Node(e, t * -1));
            }

            boolean can = false;
            LOOP:
            for (int j = 0; j < hole.size(); j++) {
                List<Node> holeNode = hole.get(j);

                if (!holeNode.isEmpty()) {
                    boolean[] visited = new boolean[n + 1];
                    int[] distance = new int[n + 1];
                    Arrays.fill(distance, 100000000);

                    distance[j] = 0;

                    dfs(hole, doro, visited, distance, j, j);
                    if (distance[j] < 0) {
                        can = true;
                        break LOOP;
                    }

//                    System.out.println(Arrays.toString(distance));
                    for (int k = 1; k < distance.length; k++) {
                        if (!doro.get(k).isEmpty()) {
                            for (int l = 0; l < doro.get(k).size(); l++) {
                                if (doro.get(k).get(l).to == j
                                        && (distance[k] + doro.get(k).get(l).cost < 0)) {
                                    can = true;
                                    break LOOP;
                                }
                            }

                        }
                    }
                }
            }
            if (can) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        }

    }

    static void dfs(List<List<Node>> hole, List<List<Node>> doro, boolean[] visited,
                    int[] distance, int start, int first) {

        PriorityQueue<Node> queue = new PriorityQueue<>();
        queue.add(new Node(start, 0));

        while (!queue.isEmpty()) {
            Node now = queue.poll();

            List<Node> nextList = doro.get(now.to);
            List<Node> nextHList = hole.get(now.to);


            if (visited[now.to]) {
                continue;
            }

            visited[now.to] = true;

            for (int i = 0; i < nextHList.size(); i++) {
                Node next = nextHList.get(i);

                if (!(distance[now.to] + next.cost < 0)) {
                    continue;
                }

                distance[next.to] = Math.min(distance[next.to], distance[now.to] + next.cost);
                queue.add(new Node(next.to, distance[next.to]));
            }

            for (int i = 0; i < nextList.size(); i++) {
                Node next = nextList.get(i);

                if (visited[next.to]) {
                    continue;
                }
                distance[next.to] = Math.min(distance[next.to], distance[now.to] + next.cost);
                queue.add(new Node(next.to, distance[next.to]));
            }
        }
    }


    static class Node implements Comparable<Node> {
        int to;
        int cost;

        public Node(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return this.cost - o.cost;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "to=" + to +
                    ", cost=" + cost +
                    '}';
        }
    }
}