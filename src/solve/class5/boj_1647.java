package solve.class5;



import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.Comparator.comparingInt;

public class boj_1647 {

    static int[] roots;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int n = arr[0];
        int m = arr[1];

        roots = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            roots[i] = i;
        }
        List<int[]> list = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            list.add(Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray());
        }

        list.sort(comparingInt(o -> o[2]));

        int idx = 0;
        int sum = 0;
        int num = 0;
        while (idx < list.size() && num < n - 2) {
            int[] tmp = list.get(idx);
            idx++;

            if (roots[find(roots, tmp[0])] == roots[find(roots, tmp[1])]) {
                continue;
            }
            union(roots, tmp[0], tmp[1]);
            sum += tmp[2];
            num++;
        }
        System.out.println(sum);
    }

    static void union(int[] roots, int a, int b) {
        a = find(roots, a);
        b = find(roots, b);

        if (a != b) {
            if (a > b) {
                roots[a] = b;
            } else {
                roots[b] = a;
            }
        }
    }

    static int find(int[] roots, int a) {
        if (roots[a] == a) {
            return a;
        }

        return roots[a] = find(roots, roots[a]);
    }

}