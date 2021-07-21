package topo_sort;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class boj_2623 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int n = arr[0];
        int m = arr[1];

        int[] parents = new int[n + 1];
        List<List<Integer>> childs = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            childs.add(new ArrayList<>());
        }
        for (int i = 0; i < m; i++) {
            arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            for (int j = 2; j < arr.length; j++) {
                int child = arr[j];
                int parent = arr[j - 1];
                childs.get(parent).add(child);
                parents[child]++;
            }
        }

        StringBuilder result = new StringBuilder();
        int cnt = 0;
        while (true) {
            int find = find(parents);
            if (find == -1) {
                break;
            }
            cnt++;
            List<Integer> cList = childs.get(find);
            cList.forEach(i -> parents[i]--);
            result.append(find).append("\n");
        }

        System.out.println(cnt == n ? result.toString() : 0);
    }

    static int find(int[] parents) {
        for (int i = 1; i < parents.length; i++) {
            if (parents[i] == 0) {
                parents[i] = -1;
                return i;
            }
        }
        return -1;
    }
}