package union_find;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class boj_20040 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int n = arr[0];
        int m = arr[1];
        int[] roots = new int[n];
        for (int i = 1; i < roots.length; i++) {
            roots[i] = i;
        }
        boolean success = false;
        for (int i = 0; i < m; i++) {
            arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            int x = arr[0];
            int y = arr[1];
            int tmpX = find(roots, x);
            int tmpY = find(roots, y);

            if (tmpX == tmpY) {
                System.out.println(i + 1);
                success = true;
                break;
            }

            if (tmpX > tmpY) {
                union(roots, y, x);
            } else {
                union(roots, x, y);
            }
        }

        if (!success) {
            System.out.println(0);
        }

    }

    static int find(int[] roots, int x) {
        if (roots[x] == x) {
            return x;
        }

        return roots[x] = find(roots, roots[x]);
    }

    static void union(int[] roots, int x, int y) {
        x = find(roots, x);
        y = find(roots, y);

        if (x != y) {
            roots[y] = x;
        }
    }
}