package dfs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class boj_2668 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int[] arr = new int[n + 1];
        boolean[] self = new boolean[n + 1];
        Set<Integer> result = new HashSet<>();
        for (int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
            if (arr[i] == i) {
                self[i] = true;
                result.add(i);
            }
        }
        boolean[] finished = new boolean[n + 1];

        List<List<Integer>> list = new ArrayList<>();
        int idx = 0;
        for (int i = 1; i < arr.length; i++) {
            if (self[i] || finished[i]) {
                continue;
            }
            boolean[] visited = new boolean[n + 1];
            visited[i] = true;
            int next = arr[i];

            for (int j = next; ; j = arr[j]) {
                if (!visited[j]) {
                    visited[j] = true;
                } else {
                    list.add(new ArrayList<>());
                    list.get(idx).add(j);
                    finished[j] = true;
                    int tmpNext = arr[j];
                    for (int k = tmpNext; k != j; k = arr[k]) {
                        if (finished[k]) {
                            break;
                        }
                        finished[k] = true;
                        list.get(idx).add(k);
                    }
                    idx++;
                    break;
                }
            }
        }


        for (int i = 1; i <= n; i++) {
            if (self[i] && !list.get(0).contains(i)) {
                list.get(0).add(i);
            }
        }
        list.forEach(result::addAll);
        System.out.println(result.size());

        List<Integer> sortedList = new ArrayList<>(result);
        Collections.sort(sortedList);
        sortedList.forEach(System.out::println);
    }

}

