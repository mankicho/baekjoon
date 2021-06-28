package priority_queue;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class boj_1715 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 0; i < n; i++) {
            pq.add(Integer.parseInt(br.readLine()));
        }

        int ans = 0;
        while (pq.size() > 1) {
            int poll = pq.poll();
            if (pq.isEmpty()) {
                break;
            }
            int poll2 = pq.poll();

            pq.add(poll + poll2);
            ans += (poll + poll2);
        }

        System.out.println(ans);
    }
}