package priority_queue;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class boj_1655 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();

        PriorityQueue<Integer> bigQueue = new PriorityQueue<>((o1, o2) -> o2 - o1);
        PriorityQueue<Integer> smallQueue = new PriorityQueue<>((o1, o2) -> o1 - o2);

        for (int i = 0; i < n; i++) {
            int val = Integer.parseInt(br.readLine());

            if (i % 2 == 0) {
                bigQueue.add(val);
            } else {
                smallQueue.add(val);
            }

            if (!smallQueue.isEmpty() && !bigQueue.isEmpty()) {
                if (bigQueue.peek() > smallQueue.peek()) {
                    smallQueue.add(bigQueue.poll());
                    bigQueue.add(smallQueue.poll());
                }
            }

            sb.append(bigQueue.peek()).append("\n");
        }

        System.out.println(sb.toString());
    }
}
