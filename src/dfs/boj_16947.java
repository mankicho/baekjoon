package dfs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class boj_16947 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        cycleArr = new boolean[n + 1];

        List<List<Integer>> list = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            list.add(new ArrayList<>());
        }

        for (int i = 0; i < n; i++) {
            int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            int a = arr[0];
            int b = arr[1];

            list.get(a).add(b);
            list.get(b).add(a);
        }
        int[] cArr = new int[n + 1];
        dfs(list, new boolean[n + 1], cArr, 1, 0);
        StringBuilder result = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            if (cycleArr[i]) {
                result.append(0).append(" ");
            } else {
                getDistance(list, new boolean[n + 1], cycleArr, i, 0, result);
            }
        }
        System.out.println(result.toString());
    }

    static List<Integer> buffer = new ArrayList<>();
    static boolean[] cycleArr;

    static void getDistance(List<List<Integer>> list, boolean[] visited,
                            boolean[] cycleArr, int nowNode, int num, StringBuilder sb) {
        if (visited[nowNode]) {
            return;
        }

        visited[nowNode] = true;

        List<Integer> childs = list.get(nowNode);

        for (int j = 0; j < childs.size(); j++) {
            int c = childs.get(j);

            if (visited[c]) {
                continue;
            }

            if (cycleArr[c]) {
                sb.append(num+1).append(" ");
                break;
            }
            getDistance(list, visited, cycleArr, c, num + 1, sb);
        }
    }

    static void dfs(List<List<Integer>> list, boolean[] visited, int[] cArr, int nowNode, int preNode) {
        if (visited[nowNode]) {
            return;
        }
        visited[nowNode] = true;

        List<Integer> childs = list.get(nowNode);

        for (int i = 0; i < childs.size(); i++) {
            int child = childs.get(i);
            cArr[nowNode] = child;

            if (visited[child]) {
                if (child != preNode) {
                    cycleArr[nowNode] = true;
                    for (int j = child; j != nowNode; j = cArr[j]) {
                        if (cycleArr[j]) {
                            break;
                        }
                        cycleArr[j] = true;
                    }
                }
                continue;
            }
            dfs(list, visited, cArr, child, nowNode);
        }

    }
}