package dp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class boj_1912 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int[] dp = new int[n + 1];
        Arrays.fill(dp, -1000 * 100000 - 1);
        dp[1] = arr[0];
        List<Integer> tmpList = new ArrayList<>();
        if (arr[0] < 0) {
            tmpList.add(1);
        }
        for (int i = 2; i < dp.length; i++) {
            if (arr[i - 1] < 0) {
                tmpList.add(i);
            } else {
                if (!tmpList.isEmpty()) {
                    int idx = tmpList.get(0);
                    int sum = 0;
                    for (int j = idx; j < i; j++) {
                        sum += arr[j - 1];
                    }
                    dp[i] = Math.max(dp[idx - 1] + sum + arr[i - 1], arr[i - 1]);
                    tmpList.clear();
                } else {
                    dp[i] = dp[i - 1] + arr[i - 1];
                }
            }
        }
        if (!tmpList.isEmpty()) {
            int idx = tmpList.get(0);
            if (idx == 1) {
                Arrays.sort(arr);
                System.out.println(arr[arr.length - 1]);
                return;
            }
        }
        int max = Integer.MIN_VALUE;

        for (int i = 1; i < dp.length; i++) {
            if (dp[i] > max) {
                max = dp[i];
            }
        }
        System.out.println(max);
    }
}