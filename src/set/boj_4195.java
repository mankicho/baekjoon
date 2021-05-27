package set;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class boj_4195 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int testcase = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < testcase; i++) {
            int f = Integer.parseInt(br.readLine());
            int[] roots = new int[2 * f + 1];
            for (int k = 1; k < roots.length; k++) {
                roots[k] = k;

            }
            int[] nums = new int[2 * f + 1];
            Arrays.fill(nums, 1);
            int num = 1;
            Map<String, Integer> idMap = new HashMap<>();
            for (int j = 0; j < f; j++) {

                String[] friends = br.readLine().split(" ");

                String first = friends[0];
                String second = friends[1];

                idMap.putIfAbsent(first, num++);
                idMap.putIfAbsent(second, num++);

                union(roots, nums, idMap.get(first), idMap.get(second));
                int a = find(roots, idMap.get(first));
                int b = find(roots, idMap.get(second));

                if (a == b) {
                    sb.append(nums[a]).append("\n");
                } else {
                    sb.append("0").append("\n");
                }

            }

        }
        System.out.println(sb.toString());
    }


    static int find(int[] roots, int a) {
        if (roots[a] == a) {
            return a;
        }

        roots[a] = find(roots, roots[a]);
        return roots[a];
    }

    /*
    1,3 1은 (3)
    3,7 1은 (3,7)
    1,6 1은 (3,7,6)
    2,4 2는 (4)
    3,4 1은 (3,7,6,4)
    2,3 1은 (2,3,7,6,4)
    2,6
    2,5 1은 (2,3,7,6,4,5)
    1,4
     */
    static void union(int[] roots, int[] nums, int a, int b) {
        a = find(roots, a);
        b = find(roots, b);

        if (a != b) {
            if (a > b) {
                roots[a] = b;
                nums[b] += nums[a];
            } else {
                roots[b] = a;
                nums[a] += nums[b];
            }
        }

    }
}