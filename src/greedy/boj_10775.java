package greedy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class boj_10775 {

    static int[] roots;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int g = Integer.parseInt(br.readLine());
        int p = Integer.parseInt(br.readLine());

        int[] arr = new int[p];

        roots = new int[100001];
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < 100001; i++) {
            map.put(i, i);
            roots[i] = i;
        }

        int ans = 0;
        for (int i = 0; i < arr.length; i++) {
            arr[i] = Integer.parseInt(br.readLine());
            int find = uFind(roots,arr[i]);
            if (find == 0) {
                break;
            }

            map.put(find, map.get(find) - 1);
            union(roots, arr[i], map.get(find));
            ans++;
        }
        System.out.println(ans);

    }

    static int uFind(int[] roots, int a) {
        if (roots[a] == a) {
            return a;
        }
        return roots[a] = uFind(roots, roots[a]);
    }

    static void union(int[] roots, int a, int b) {
        a = uFind(roots, a); // 10
        b = uFind(roots, b); // 5

        if (a != b) {
            if (a > b) {
                roots[a] = b; // roots[10] = 5;
            } else {
                roots[b] = a;
            }
        }
    }
}