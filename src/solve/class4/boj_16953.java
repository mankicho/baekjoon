package solve.class4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class boj_16953 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int a = arr[0];
        int b = arr[1];

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        pq.add(a);

        Map<Integer, Integer> map = new HashMap<>();
        map.put(a, 0);
        while (!pq.isEmpty()) {
            int i = pq.poll();


            if (i <= 0 || i > b / 2) {
                continue;
            }
            map.putIfAbsent(i * 2, 1000000);
            map.putIfAbsent(i * 10 + 1, 1000000);

            if (map.get(i * 2) > map.get(i) + 1) {
                map.put(i * 2, map.get(i) + 1);
                pq.add(i * 2);
            }

            if (map.get(i * 10 + 1) > map.get(i) + 1) {
                map.put(i * 10 + 1, map.get(i) + 1);
                pq.add(i * 10 + 1);
            }
        }

        if (map.get(b) == null) {
            System.out.println(-1);
        } else {
            System.out.println(map.get(b) + 1);
        }
    }

    static class Node implements Comparable<Node> {
        int num;
        int val;

        public Node(int num, int val) {
            this.num = num;
            this.val = val;
        }

        @Override
        public int compareTo(Node o) {
            return this.val - o.val;
        }
    }
}