package stack;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class BOJ_1725 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        long max = 0;
        Stack<int[]> stack = new Stack<>();
        for (int i = 0; i < n; i++) {
            int h = Integer.parseInt(br.readLine());

            while (!stack.isEmpty() && stack.peek()[1] > h) {
                long getH = stack.pop()[1];
                long width = i;

                if (!stack.isEmpty()) {
                    width -= stack.peek()[0] + 1;
                }

                if (getH * width > max) {
                    max = getH * width;
                }
            }
            stack.push(new int[]{i,h});
        }

        while(!stack.isEmpty()) {
            long getH = stack.pop()[1];
            long width = n;

            if(!stack.isEmpty())
                width -= stack.peek()[0] + 1;

            max= Math.max(max, getH * width);
        }
        System.out.println(max);
    }

}