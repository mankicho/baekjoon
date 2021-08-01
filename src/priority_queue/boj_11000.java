package priority_queue;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;

class boj_11000 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[][] schedules = new int[n][2];
        for (int i = 0; i < n; i++) {
            schedules[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        Arrays.sort(schedules, (o1, o2) -> {
            if (o1[0] == o2[0]) {
                return o1[1] - o2[1];
            }
            return o1[0] - o2[0];
        });

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 0; i < schedules.length; i++) {
            int[] sche = schedules[i];

            int s = sche[0];
            int t = sche[1];

            if (pq.isEmpty()) {
                pq.add(t);
            } else {
                if (s >= pq.peek()) {
                    pq.poll();
                }
                pq.add(t);
            }
        }
        System.out.println(pq.size());
    }
}
