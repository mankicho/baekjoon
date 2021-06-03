package topo_sort;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int testCase = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < testCase; i++) {
            int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            int[] times = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            int n = arr[0];
            int k = arr[1];

            int[] result = new int[n + 1];

            int[] parents = new int[n + 1];

            Map<Integer, List<Integer>> childMap = new HashMap<>();
            for (int tmp = 0; tmp <= n; tmp++) {
                childMap.putIfAbsent(tmp, new ArrayList<>());
            }

            for (int j = 0; j < k; j++) {
                int[] tmpArr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

                int first = tmpArr[0];
                int second = tmpArr[1];

                parents[second]++;
                childMap.get(first).add(second);
            }

            int target = Integer.parseInt(br.readLine());

            LinkedList<Integer> queue = new LinkedList<>();

            List<Integer> roots = findRoots(parents);

            for (int u = 0; u < roots.size(); u++) {
                result[roots.get(u)] = times[roots.get(u) - 1];
                queue.add(roots.get(u));
            }

            while (!queue.isEmpty()) {
                int poll = queue.poll();

                List<Integer> childList = childMap.get(poll);

                for (int l = 0; l < childList.size(); l++) {
                    int child = childList.get(l);

                    result[child] = Math.max(result[poll] + times[child - 1], result[child]);
                    parents[child]--;

                    if (parents[child] == 0) {
                        queue.add(child);
                    }
                }
            }
            sb.append(result[target]).append("\n");
        }
        System.out.println(sb.toString());

    }

    static List<Integer> findRoots(int[] parents) {
        List<Integer> tmpList = new ArrayList<>();

        for (int i = 1; i < parents.length; i++) {
            if (parents[i] == 0) {
                tmpList.add(i);
            }
        }
        return tmpList;
    }
}