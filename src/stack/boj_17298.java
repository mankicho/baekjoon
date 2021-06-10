package stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;

public class boj_17298 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String firstLine = br.readLine();
        String secondLine = br.readLine();

        int N = Integer.parseInt(firstLine);
        String[] split = secondLine.split(" ");
        int[] arr = Arrays.stream(split).mapToInt(Integer::parseInt).toArray();

        int[] result = new int[N];
        result[N - 1] = -1;
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < arr.length; i++) {
            int value = arr[i];
            int value2 = arr[i + 1];

            if (value2 > value) {
                result[i] = value2;
                continue;
            }

            // 2 1 0
            while (!stack.isEmpty()) {
                if (value > arr[stack.peek()]) {
                    int idx = stack.pop();
                    result[idx] = value;
                } else {
                    break;
                }
            }
            stack.push(i);
        }

        while (!stack.isEmpty()) {
            int popIndex = stack.pop();
            if (arr[popIndex] < arr[N - 1]) {
                result[popIndex] = arr[N - 1];
            } else {
                result[popIndex] = -1;
            }
        }

        for (int i = 0; i < result.length; i++) {
            System.out.print(result[i] + " ");
        }
    }
}
