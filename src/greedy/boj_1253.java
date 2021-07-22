package greedy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class boj_1253 {

    static int[] dp = new int[1000001];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            map.putIfAbsent(arr[i], 0);
            map.put(arr[i], map.get(arr[i]) + 1);
        }
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] + arr[j] == arr[i] || arr[i] + arr[j] == arr[j]) {
                    if (arr[i] == 0 && arr[j] == 0) {
                        if (map.get(0) <= 2) {
                            continue;
                        }
                    } else if (arr[i] == 0) {
                        if (map.get(arr[j]) <= 1) {
                            continue;
                        }
                    } else if (arr[j] == 0) {
                        if (map.get(arr[i]) <= 1) {
                            continue;
                        }
                    } else {
                        if (arr[i] + arr[j] == arr[i]) {
                            if (map.get(arr[i]) <= 1) {
                                continue;
                            }
                        } else if (arr[i] + arr[j] == arr[j]) {
                            if (map.get(arr[j]) <= 1) {
                                continue;
                            }
                        }
                    }
                }

                set.add(arr[i] + arr[j]);
            }
        }
        int ans = 0;
        for (Integer i : set) {
            if (map.get(i) != null) {
                ans += map.get(i);
            }
        }
        System.out.println(ans);
    }
}