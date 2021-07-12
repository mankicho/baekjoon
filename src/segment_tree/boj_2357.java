package segment_tree;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class boj_2357 {

    static int[][] trees;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int n = arr[0];
        int m = arr[1];

        trees = new int[n * 4 + 1][2];
        int[] values = new int[n];
        for (int i = 0; i < n; i++) {
            values[i] = Integer.parseInt(br.readLine());
        }

        init(trees, 0, n - 1, 1, values);
//        System.out.println(Arrays.toString(values));
//        for (int[] tree : trees) {
//            System.out.println(Arrays.toString(tree));
//        }
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < m; i++) {
            max = 0;
            min = Integer.MAX_VALUE;
            arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            get(trees, 0, n-1, 1, arr[0] - 1, arr[1]-1);
            result.append(min).append(" ").append(max).append("\n");
        }
        System.out.println(result.toString());
    }

    static int max;
    static int min;

    static void init(int[][] trees, int start, int end, int node, int[] values) {
//        if (end - start == 1) {
//            trees[node] = new int[]{Math.max(values[start], values[end]), Math.min(values[start], values[end])};
//            return;
//        }

        if (start == end) {
            trees[node] = new int[]{values[start], values[start]};
            return;
        }

        int mid = (start + end) / 2;
        init(trees, start, mid, node * 2, values);
        init(trees, mid + 1, end, node * 2 + 1, values);

        int leftMax = trees[node * 2][0];
        int leftMin = trees[node * 2][1];

        int rightMax = trees[node * 2 + 1][0];
        int rightMin = trees[node * 2 + 1][1];
        trees[node] = new int[]{Math.max(leftMax, rightMax), Math.min(leftMin, rightMin)};
    }


    static void get(int[][] trees, int start, int end, int node, int left, int right) {
        if (left > end || right < start) {
            return;
        }

        if (left <= start && end <= right) {
            int[] arr = trees[node];

            if (arr[0] > max) {
                max = arr[0];
            }

            if (arr[1] < min) {
                min = arr[1];
            }
            return;
        }

        int mid = (start + end) / 2;

        get(trees, start, mid, node * 2, left, right);
        get(trees, mid + 1, end, node * 2 + 1, left, right);

    }

}

//
//}