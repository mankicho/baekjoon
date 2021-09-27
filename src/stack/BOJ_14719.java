package stack;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class BOJ_14719 {
    static Map<Integer, Map<Integer, Integer>> dirMap = new HashMap<>();

    static int[][] dir = new int[][]{
            {0, 1}, {1, 0}, {0, -1}, {-1, 0}
    };

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int h = arr[0];
        int w = arr[1];

        arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int start = arr[0];
        int ans = 0;
        int sum = 0;
        Stack<Integer> stack = new Stack<>();
        stack.add(0);
        for (int i = 1; i < arr.length; i++) {
            int peek = stack.peek();

            if (arr[peek] > arr[i]) {
                sum += (arr[peek] - arr[i]);
            } else {
                ans += sum;
                stack.pop();
                stack.add(i);
                sum = 0;
            }
        }

        if (!stack.isEmpty()) {
            Stack<Integer> tmpStack = new Stack<>();
            tmpStack.add(arr.length - 1);
            int sum2 = 0;
            for (int i = arr.length - 2; i >= stack.peek(); i--) {
                int peek = tmpStack.peek();
                if (arr[peek] > arr[i]) {
                    sum2 += (arr[peek] - arr[i]);
                } else {
                    ans += sum2;
                    tmpStack.pop();
                    tmpStack.add(i);
                    sum2 = 0;
                }
            }
        }
        System.out.println(ans);
    }
}