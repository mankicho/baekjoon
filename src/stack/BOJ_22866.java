package stack;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;

public class BOJ_22866 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        Stack<int[]> leftStack = new Stack<>();
        Stack<int[]> rightStack = new Stack<>();

        int[] idxs = new int[arr.length];
        Arrays.fill(idxs, n * n);
        int[] nums = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            if (leftStack.isEmpty()) {
                leftStack.add(new int[]{arr[i], i});
            } else {
                while (!leftStack.isEmpty() && leftStack.peek()[0] <= arr[i]) {
                    int[] pop = leftStack.pop();
                    nums[pop[1]] += leftStack.size();
                    if (arr[i] > pop[0] && Math.abs(pop[1] - idxs[pop[1]]) > Math.abs(pop[1] - i)) {
                        idxs[pop[1]] = i;
                    }

                    if (!leftStack.isEmpty()) {
                        if (Math.abs(leftStack.peek()[1] - pop[1]) <= Math.abs(pop[1] - idxs[pop[1]])) {
                            idxs[pop[1]] = leftStack.peek()[1];
                        }
                    }
                }

                leftStack.add(new int[]{arr[i], i});
            }
//            System.out.println(Arrays.toString(idxs));
        }
//        System.out.println(Arrays.toString(idxs));
        for (int i = arr.length - 1; i >= 0; i--) {
            if (rightStack.isEmpty()) {
                rightStack.add(new int[]{arr[i], i});
            } else {
                while (!rightStack.isEmpty() && rightStack.peek()[0] <= arr[i]) {
                    int[] pop = rightStack.pop();
                    nums[pop[1]] += rightStack.size();
                    if (arr[i] > pop[0] && Math.abs(pop[1] - idxs[pop[1]]) > Math.abs(pop[1] - i)) {
                        idxs[pop[1]] = i;
                    }

                    if (!rightStack.isEmpty()) {
                        if (Math.abs(rightStack.peek()[1] - pop[1]) < Math.abs(pop[1] - idxs[pop[1]])) {
                            idxs[pop[1]] = rightStack.peek()[1];
                        }
                    }
                }

                rightStack.add(new int[]{arr[i], i});
            }
        }

        while (!leftStack.isEmpty()) {
            int[] pop = leftStack.pop();
            nums[pop[1]] += leftStack.size();
            if (!leftStack.isEmpty()) {
                if (Math.abs(leftStack.peek()[1] - pop[1]) <= Math.abs(pop[1] - idxs[pop[1]])) {
                    idxs[pop[1]] = leftStack.peek()[1];
                }
            }
        }

        while (!rightStack.isEmpty()) {
            int[] pop = rightStack.pop();
            nums[pop[1]] += rightStack.size();
            if (!rightStack.isEmpty()) {
                if (Math.abs(rightStack.peek()[1] - pop[1]) < Math.abs(pop[1] - idxs[pop[1]])) {
                    idxs[pop[1]] = rightStack.peek()[1];
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                sb.append(0).append("\n");
            } else {
                sb.append(nums[i]).append(" ").append(idxs[i] + 1).append("\n");
            }
        }
        System.out.println(sb.toString());

    }

}