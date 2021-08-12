package tree;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BOJ_2132 {


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        List<List<Integer>> list = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            list.add(new ArrayList<>());
        }

        for (int i = 0; i < n - 1; i++) {
            int[] tmp = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int a = tmp[0];
            int b = tmp[1];

            list.get(a).add(b);
            list.get(b).add(a);
        }
        dfs(list, new boolean[n + 1], arr, 1, arr[0]);

        int startMax = max;
        int start = maxIdx;
        max = 0;
        maxIdx = 1;
//        System.out.println(startMax + " " + start);
        dfs(list, new boolean[n + 1], arr, start, arr[start - 1]);
        System.out.println(max + " " + Math.min(maxIdx, start));
    }

    static int maxIdx = 1;
    static int max = 0;

    static void dfs(List<List<Integer>> list, boolean[] visited, int[] arr, int num, int startValue) {
        if (visited[num]) {
            return;
        }

        if (startValue >= max) {
            if (startValue == max) {
                if (num < maxIdx) {
                    maxIdx = num;
                }
            } else {
                max = startValue;
                maxIdx = num;
            }
        }
        visited[num] = true;
        List<Integer> nexts = list.get(num);

        for (int i = 0; i < nexts.size(); i++) {
            int next = nexts.get(i);

            if (visited[next]) {
                continue;
            }

            dfs(list, visited, arr, next, startValue + arr[next - 1]);
        }
    }

}