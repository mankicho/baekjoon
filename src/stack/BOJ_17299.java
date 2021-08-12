package stack;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;

public class BOJ_17299 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] nums = new int[1000001];
        for (int i = 0; i < arr.length; i++) {
            nums[arr[i]]++;
        }

        Stack<Integer> stack = new Stack<>();

        StringBuilder result = new StringBuilder();
        for (int i = arr.length - 1; i >= 0; i--) {
            if (stack.isEmpty()) {
                stack.add(i);
                result.append(-1).append(" ");
            } else {
                while (!stack.isEmpty() && nums[arr[i]] >= nums[arr[stack.peek()]]) {
                    stack.pop();
                }
                if (stack.isEmpty()) {
                    result.append(-1).append(" ");
                } else {
                    result.append(arr[stack.peek()]).append(" ");
                }
                stack.add(i);
            }
        }
        String s = result.toString().trim();
        int[] tmp = Arrays.stream(s.split(" ")).mapToInt(Integer::parseInt).toArray();
        StringBuilder r = new StringBuilder();
        for (int i = tmp.length - 1; i >= 0; i--) {
            r.append(tmp[i]).append(" ");
        }
        System.out.println(r.toString());
    }
}

