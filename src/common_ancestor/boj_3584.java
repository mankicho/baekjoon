package common_ancestor;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class boj_3584 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testcase = Integer.parseInt(br.readLine());

        for (int i = 0; i < testcase; i++) {
            int n = Integer.parseInt(br.readLine());

            int[] parents = new int[n + 1];
            List<List<Integer>> childsList = new ArrayList<>();
            for (int tmp = 0; tmp <= n; tmp++) {
                childsList.add(new ArrayList<>());
                
            }
            int[] levels = new int[n + 1];


            for (int j = 0; j < n - 1; j++) {
                int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

                int parent = arr[0];
                int child = arr[1];

                parents[child] = parent;
                childsList.get(parent).add(child);
            }

            int root = 0;
            for (int k = 1; k < parents.length; k++) {
                if (parents[k] == 0) {
                    root = k;
                    break;
                }
            }

            int[] finds = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            int first = finds[0];
            int second = finds[1];

            dfs(levels, childsList, root, 1);
            function(first, second, parents, levels);

        }
        System.out.println(sb.toString());

    }

    static StringBuilder sb = new StringBuilder();

    static void function(int first, int second, int[] parents, int[] levels) {
        int firstParent = parents[first];
        int secondParent = parents[second];

        if (firstParent == 0) {
            sb.append(first).append("\n");
            return;
        }

        if (secondParent == 0) {
            sb.append(second).append("\n");
            return;
        }
        if (first == second) {
            sb.append(first).append("\n");
            return;
        }

        if (levels[first] > levels[second]) {
            int tmp = first;
            int tmpLevel = levels[tmp];

            while (tmpLevel >= levels[second]) {
                if(tmp == second){
                    break;
                }
                tmp = parents[tmp];
                tmpLevel = levels[tmp];
            }
            function(tmp, second, parents, levels);
        } else {
            int tmp = second;
            int tmpLevel = levels[second];

            while (tmpLevel >= levels[first]) {
                if (tmp == first) {
                    break;
                }
                tmp = parents[tmp];
                tmpLevel = levels[tmp];
            }
            function(tmp, first, parents, levels);
        }
    }

    static void dfs(int[] levels, List<List<Integer>> childs, int root, int level) {
        levels[root] = level;
        for (int i = 0; i < childs.get(root).size(); i++) {
            dfs(levels, childs, childs.get(root).get(i), level + 1);
        }
    }
}