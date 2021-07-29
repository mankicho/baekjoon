package brute_force;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class boj_2629 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        boolean[] visited = new boolean[40001];

        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < arr.length; i++) {
            List<Integer> tmpList = new ArrayList<>();
            for (Integer val : set) {
                if (val + arr[i] <= 40000) {
                    tmpList.add(val + arr[i]);
                }

                if (val > arr[i]) {
                    if (val - arr[i] > 0) {
                        tmpList.add(val - arr[i]);
                    }
                } else {
                    if (arr[i] - val > 0) {
                        tmpList.add(arr[i] - val);
                    }
                }
            }
            tmpList.add(arr[i]);
            set.addAll(tmpList);
        }

        for (int i : set) {
            visited[i] = true;
        }
        br.readLine();

        arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            result.append(visited[arr[i]] ? "Y" : "N").append(" ");
        }
        System.out.println(result.toString());
    }
}

