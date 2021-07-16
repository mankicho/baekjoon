package greedy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class boj_2109 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        List<PriorityQueue<Integer>> pqList = new ArrayList<>();
        for (int i = 0; i <= 10000; i++) {
            pqList.add(new PriorityQueue<>(Comparator.reverseOrder()));
        }

        int[] dp = new int[10001];
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            map.putIfAbsent(arr[1], new ArrayList<>());

            map.get(arr[1]).add(arr[0]);
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
        int ans = 0;
        int num = 0;
        for (int i = 10000; i >= 1; i--) {
            List<Integer> list = map.get(i);

            if(list != null && !list.isEmpty()) {
                pq.addAll(list);
            }

            if (!pq.isEmpty()) {
                ans += pq.poll();
            }
        }

        System.out.println(ans);
    }

    static class Node {
        int val;
        int day;

        public Node(int val, int day) {
            this.val = val;
            this.day = day;
        }
    }

}