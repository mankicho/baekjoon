package segment_tree;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class boj_2042 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();


        int n = arr[0];
        int m = arr[1];
        int k = arr[2];

        long[] vals = new long[n];

        long[] tree = new long[(n + 1) * 4];

        for (int i = 0; i < n; i++) {
            vals[i] = Long.parseLong(br.readLine());
        }

        init(0, vals.length - 1, 1, tree, vals);
//        System.out.println(Arrays.toString(tree));
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < m + k; i++) {
            long[] tmp = Arrays.stream(br.readLine().split(" ")).mapToLong(Long::parseLong).toArray();

            int a = (int) tmp[0];
            int b = (int) tmp[1];
            long c = tmp[2];

            if (a == 1) {
                update(tree, 0, n - 1, 1, b - 1, c - vals[b - 1]);
                vals[b - 1] = c;
            } else {
                sb.append(sum(tree, 0, n - 1, 1, b - 1, c - 1)).append("\n");
            }
//            System.out.println(Arrays.toString(tree));
        }
        System.out.println(sb.toString());

    }

    static long init(int start, int end, int node, long[] trees, long[] vals) {
        if (start == end) {
            return trees[node] = vals[start];
        }

        int mid = (start + end) / 2;

        return trees[node] = init(start, mid, node * 2, trees, vals) + init(mid + 1, end, node * 2 + 1, trees, vals);
    }

    static void update(long[] trees, int start, int end, int node, int index, long updatedVal) {
        if (index < start || index > end) {
            return;
        }
        trees[node] = trees[node] + updatedVal;

        if (start == end) {
            return;
        }

        int mid = (start + end) / 2;

        update(trees, start, mid, node * 2, index, updatedVal);
        update(trees, mid + 1, end, node * 2 + 1, index, updatedVal);
    }

    static long sum(long[] trees, int start, int end, int node, int left, long right) {
        if (left > end || right < start) {
            return 0;
        }
        if (left <= start && end <= right) {
            return trees[node];
        }

        int mid = (start + end) / 2;

        return sum(trees, start, mid, node * 2, left, right) + sum(trees, mid + 1, end, node * 2 + 1, left, right);

    }
}

//
//}