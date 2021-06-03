package dp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class boj_1932 {
    static int[] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int[] arr = new int[factorial(n) + 1];
        List<int[]> list = new ArrayList<>();

        dp = new int[factorial(n) + 1];
        int idx = 1;
        for (int i = 1; i <= n; i++) {
            int[] tmpArr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            list.add(tmpArr);
            for (int j = 0; j < tmpArr.length; j++) {
                arr[idx++] = tmpArr[j];
            }
        }
        dp[1] = arr[1];
        dp(arr, list);

        int max = 0;
        for (int i = 0; i < dp.length; i++) {

            if (dp[i] > max) {
                max = dp[i];
            }
        }
        System.out.println(max);

    }

    static int factorial(int n) {
        int sum = 0;
        for (int i = 1; i <= n; i++) {
            sum += i;
        }
        return sum;
    }

    static void dp(int[] arr, List<int[]> list) {
        int idx = 2;
        int backIdx = 1;

        int[] tmpArr = list.get(0);

        for (int i = 1; i < list.size(); i++) {

            for (int j = 0; j < tmpArr.length; j++) {
                dp[idx + j] = Math.max(dp[backIdx] + arr[idx + j], dp[idx + j]);
                dp[idx + j + 1] = Math.max(dp[backIdx] + arr[idx + j + 1], dp[idx + j + 1]);
                backIdx++;
            }
            idx += list.get(i).length;

            tmpArr = list.get(i);
        }
    }

}