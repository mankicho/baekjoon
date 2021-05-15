package queue;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;

public class boj_2164 {
    public static void main(String[] args) throws Exception {
        LinkedList<Integer> queue = new LinkedList<>();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        for (int i = 1; i <= n; i++) {
            queue.add(i);
        }

        while (!queue.isEmpty()) {
            int pop = queue.pop();

            if (queue.isEmpty()) {
                queue.add(pop);
                break;
            }

            queue.add(queue.pop());
        }

        System.out.println(queue.pop());
    }
}
