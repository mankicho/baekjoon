package stack;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;

public class boj_17298 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < arr.length; i++) {
            stack.add(arr[i]);
        }

        Stack<Integer> tmpStack = new Stack<>();

        int max = -1;
        StringBuilder result = new StringBuilder();
        while (!stack.isEmpty()) {
            int pop = stack.pop();

            if (tmpStack.isEmpty()) {
                result.append(-1).append(" ");
                tmpStack.add(pop);
            } else {
                boolean b = false;
                while (!tmpStack.isEmpty()) {
                    int tmpPeek = tmpStack.peek();
                    if (tmpPeek > pop) {
                        b = true;
                        result.append(tmpPeek).append(" ");
                        break;
                    }
                    tmpStack.pop();
                }
                if (!b) {
                    result.append("-1").append(" ");
                }
                tmpStack.add(pop);
            }
        }
        String[] split = result.toString().split(" ");

        result = new StringBuilder();

        for(int i=split.length-1;i>=0;i--){
            result.append(split[i]).append(" ");
        }
        System.out.println(result.toString());
    }
}