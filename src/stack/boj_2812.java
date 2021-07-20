package stack;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;

public class boj_2812 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        String line = br.readLine();

        int n = arr[0];
        int k = arr[1];

        int size = n - k;
        int cnt = 0;

        Stack<Integer> stack = new Stack<>();
        int now = 0;
        for (int i = 0; i < line.length(); i++) {
            int num = Integer.parseInt(line.charAt(i) + "");

            if (stack.isEmpty()) {
                stack.add(num);
            } else {
                while ((stack.size() + line.length() - i) > size &&
                        !stack.isEmpty() && stack.peek() < num) {
                    stack.pop();
                }
                stack.add(num);
            }
        }
        while (stack.size() > size) {
            stack.pop();
        }
        StringBuilder result = new StringBuilder();
        for (int i : stack) {
            result.append(i);
        }
        System.out.println(result.length() == 0 ? 0 : result.toString());
    }


}