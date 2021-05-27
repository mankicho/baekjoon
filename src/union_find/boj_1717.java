package union_find;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class boj_1717 {
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int n = arr[0];
        int m = arr[1];
        int[] roots = new int[n + 1];
        for (int i = 0; i < roots.length; i++) {
            roots[i] = i;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < m; i++) {
            arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            int type = arr[0];
            int a = arr[1];
            int b = arr[2];

            if (type == 0) {
                union(roots, a, b);
            } else {
                a = find(roots, a);
                b = find(roots, b);
                if (a == b) {
                    sb.append("YES").append("\n");
                } else {
                    sb.append("NO").append("\n");
                }
            }
        }
        System.out.println(sb.toString());

    }

    static void union(int[] roots, int a, int b) {
        int tmpA = find(roots, a);
        int tmpB = find(roots, b);

        if (tmpA != tmpB) {
            roots[tmpB] = tmpA;
        }
    }

    static int find(int[] roots, int num) {
        if (roots[num] == num) {
            return num;
        }

        roots[num] = find(roots, roots[num]);
        return roots[num];
    }
}
