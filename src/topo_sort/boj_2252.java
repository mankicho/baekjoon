package topo_sort;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class boj_2252 {
    static int[] parents;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int n = arr[0];
        int m = arr[1];

        parents = new int[n + 1];
//        Map<Integer, List<Integer>> parentMap = new HashMap<>();
        Map<Integer, List<Integer>> childMap = new HashMap<>();

        for (int i = 1; i <= n; i++) {
//            parentMap.putIfAbsent(i, new ArrayList<>());
            childMap.putIfAbsent(i, new ArrayList<>());
        }


        for (int i = 0; i < m; i++) {
            arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            int a = arr[0];
            int b = arr[1];

//            parentMap.get(b).add(a);
            parents[b]++;
            childMap.get(a).add(b);
        }

        LinkedList<Integer> queue = new LinkedList<>();
        LinkedList<Integer> result = new LinkedList<>();

        for (int i = 1; i < parents.length; i++) {
            if (parents[i] == 0) {
                queue.add(i);
            }
        }
        while (!queue.isEmpty()) {
            int poll = queue.poll();

            result.add(poll);
            removeEdge(poll, childMap, queue);

        }

        StringBuilder sb = new StringBuilder();
        while (!result.isEmpty()) {
            sb.append(result.poll()).append(" ");
        }
        System.out.println(sb.toString());
    }

    static void removeEdge(int poll, Map<Integer, List<Integer>> childMap, LinkedList<Integer> queue) {
        List<Integer> tmpList = childMap.get(poll);
        for (int i = 0; i < tmpList.size(); i++) {
            int next = tmpList.get(i);

            parents[next]--;

            if (parents[next] == 0) {
                queue.add(next);
            }
        }

    }
}