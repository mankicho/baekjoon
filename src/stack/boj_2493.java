package stack;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;

public class boj_2493 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        Stack<Integer> stack = new Stack<>();
        int[] result = new int[n];
        stack.add(arr.length - 1);
        for (int i = arr.length - 2; i >= 0; i--) {
            int val = arr[i];

            if (val < arr[stack.peek()]) {
                stack.push(i);
            } else {
                while (!stack.isEmpty()) {
                    if (val < arr[stack.peek()]) {
                        break;
                    }
                    int popIdx = stack.pop();
                    result[popIdx] = i + 1;
                }
                stack.push(i);
            }
        }

        if (!stack.isEmpty()) {
            result[stack.pop()] = 0;
        }
        StringBuilder sb = new StringBuilder();
        for (int i : result) {
            sb.append(i).append(" ");
        }
        System.out.println(sb.toString());
    }
}

//
//}