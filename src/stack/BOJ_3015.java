package stack;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class BOJ_3015 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        long answer = 0;
        int n = Integer.parseInt(br.readLine());

        Stack<int[]> stack = new Stack<>();
        for (int i = 0; i < n; i++) {
            int height = Integer.parseInt(br.readLine());

            if (stack.isEmpty()) {
                stack.add(new int[]{height, 1});
            } else {
                while (!stack.isEmpty() && height > stack.peek()[0]) {
                    int[] pop = stack.pop();

                    if (stack.isEmpty()) {
                        answer += 1;
                    } else {
                        if (pop[0] == stack.get(0)[0]) {
                            answer += pop[1];
                        } else {
                            answer += pop[1] + 1;
                        }
                    }
                }
                if (!stack.isEmpty() && height == stack.peek()[0]) {
                    stack.add(new int[]{height, stack.peek()[1] + 1});
                } else {
                    stack.add(new int[]{height, 1});
                }
            }
        }
//        System.out.println(answer);
//        for (int[] s : stack) {
//            System.out.println(Arrays.toString(s));
//        }
        while (!stack.isEmpty()) {
            int[] pop = stack.pop();
            if (!stack.isEmpty()) {
                if (pop[0] == stack.get(0)[0]) {
                    answer += pop[1] - 1;
                } else {
                    answer += pop[1];
                }
            }
        }
        System.out.println(answer);
    }
}