package dp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class boj_1516 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int[] times = new int[n + 1];
        boolean[] visited = new boolean[n + 1];
        List<List<Integer>> childs = new ArrayList<>();
        List<List<Integer>> parents = new ArrayList<>();
        int[] pArr = new int[n + 1];

        for (int i = 0; i <= n; i++) {
            childs.add(new ArrayList<>());
            parents.add(new ArrayList<>());
        }

        for (int i = 1; i <= n; i++) {
            int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            times[i] = arr[0];
            for (int j = 1; j < arr.length - 1; j++) {
                parents.get(i).add(arr[j]);
                childs.get(arr[j]).add(i);
                pArr[i]++;
            }
        }

        int[] ans = new int[n + 1];
        while (true) {
            int root = findRoot(pArr, visited);
            if (root == -1) {
                break;
            }
            visited[root] = true;

            int max = -1;
            List<Integer> pList = parents.get(root);

            for (int j = 0; j < pList.size(); j++) {
                if (ans[pList.get(j)] > max) {
                    max = ans[pList.get(j)];
                }
            }
            if (max == -1) {
                ans[root] = times[root];
            } else {
                ans[root] = max + times[root];
            }

            for (int i = 0; i < childs.get(root).size(); i++) {
                int child = childs.get(root).get(i);

                pArr[child]--;
            }
        }
        StringBuilder sb = new StringBuilder();
        for(int i=1;i<ans.length;i++){
            sb.append(ans[i]).append("\n");
        }
        System.out.println(sb.toString());
    }

    static int findRoot(int[] pArr, boolean[] v) {
        for (int i = 1; i < pArr.length; i++) {
            if (!v[i] && pArr[i] == 0) {
                return i;
            }
        }
        return -1;
    }
}