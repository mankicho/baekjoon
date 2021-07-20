package priority_queue;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class boj_1939 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int n = arr[0];
        int m = arr[1];

        List<Map<Integer, Integer>> trees = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            trees.add(new HashMap<>());
        }
        for (int i = 0; i < m; i++) {
            arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            int a = arr[0];
            int b = arr[1];
            int c = arr[2];

            trees.get(a).putIfAbsent(b, 0);
            trees.get(b).putIfAbsent(a, 0);
            if (c > trees.get(a).get(b)) {
                trees.get(a).put(b, c);
            }
            if (c > trees.get(b).get(a)) {
                trees.get(b).put(a, c);
            }
        }


        int[] node = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int start = node[0];
        int end = node[1];

        boolean[] visited = new boolean[n + 1];
        dijkstra(trees, start, visited, end);
    }

    static void dijkstra(List<Map<Integer, Integer>> trees, int now, boolean[] visited, int end) {
        int[] distance = new int[visited.length];
        distance[now] = Integer.MAX_VALUE;
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o2[1] - o1[1];
            }
        });

        pq.add(new int[]{now, 0});

        while (!pq.isEmpty()) {
            int[] poll = pq.poll();
            int num = poll[0];
//            System.out.println(num+" : " + distance[num]);
            if (visited[num]) {
                continue;
            }
            visited[num] = true;
            Map<Integer, Integer> map = trees.get(num);
            for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
                int next = entry.getKey();
                int nextDist = entry.getValue();
//                System.out.println(now+" : " + next +" " +nextDist);

                int tmp = Math.min(distance[num], nextDist);
                if (distance[next] < tmp) {
                    distance[next] = tmp;
                    pq.add(new int[]{next, distance[next]});
                }
            }
        }
        System.out.println(distance[end]);
    }


}