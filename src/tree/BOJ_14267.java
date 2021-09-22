package tree;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BOJ_14267 {
    static int[][] dir = new int[][]{
            {0, 1}, {-1, 0}, {0, -1}, {1, 0}
    };

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int n = arr[0];
        int m = arr[1];

        arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        List<List<Integer>> childs = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            childs.add(new ArrayList<>());
        }

        for (int i = 1; i < arr.length; i++) {
            childs.get(arr[i]).add(i + 1);
        }

        int[] tmpArr = new int[n + 1];
        for (int i = 0; i < m; i++) {
            arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            int a = arr[0];
            int b = arr[1];

            tmpArr[a] += b;
        }
        result = new int[n+1];
        dfs(childs,1,tmpArr);
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <=n ; i++) {
            sb.append(result[i]).append(" ");
        }
        System.out.println(sb.toString());
    }

    static int[] result;

    static void dfs(List<List<Integer>> list, int now, int[] tmpArr) {
        List<Integer> childs = list.get(now);

        result[now] += tmpArr[now];
        for (int i = 0; i < childs.size(); i++) {
            int c = childs.get(i);
            tmpArr[c] += tmpArr[now];
            dfs(list, c, tmpArr);
        }
    }
}