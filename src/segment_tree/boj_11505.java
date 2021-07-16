package segment_tree;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class boj_11505 {

    static long[] trees;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int n = arr[0];
        int m = arr[1];
        int k = arr[2];

        int[] values = new int[n];
        for (int i = 0; i < n; i++) {
            int v = Integer.parseInt(br.readLine());
            values[i] = v;
        }

        trees = new long[n * 4];

        init(values, 0, values.length - 1, 1);

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < m + k; i++) {
            arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            int a = arr[0];
            int b = arr[1];
            int c = arr[2];

            if (a == 1) {
                values[b - 1] = c;
                update(0, values.length - 1, b - 1, 1, c);
            } else {
                result.append(get(0, values.length - 1, b - 1, c - 1, 1)).append("\n");
            }
        }
        System.out.println(result.toString());
    }

    static void init(int[] values, int start, int end, int node) {
        if (start == end) {
            trees[node] = values[start];
            return;
        }

        int mid = (start + end) / 2;

        init(values, start, mid, node * 2);
        init(values, mid + 1, end, node * 2 + 1);
        trees[node] = trees[node * 2] * trees[node * 2 + 1] % 1000000007;
    }

    static long update(int start, int end, int index, int node, int updatedValue) {
        if (index > end || index < start) {
            return trees[node];
        }

        if (start == end) {
            return trees[node] = updatedValue;
        }

        int mid = (start + end) / 2;
        return trees[node] = update(start, mid, index, node * 2, updatedValue)
                * update(mid + 1, end, index, node * 2 + 1, updatedValue) % 1000000007;
    }

    static long get(int start, int end, int left, int right, int node) {
        if (left > end || right < start) {
            return 1;
        }
        if (left <= start && end <= right) {
            return trees[node] % 1000000007;
        }

        int mid = (start + end) / 2;

        return get(start, mid, left, right, node * 2) * get(mid + 1, end, left, right, node * 2 + 1)
                % 1000000007;
    }
}

//
//}