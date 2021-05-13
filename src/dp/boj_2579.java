package dp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class boj_2579 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        List<Integer> stairs = new ArrayList<>();
        stairs.add(0);
        for (int i = 0; i < n; i++) {
            stairs.add(Integer.parseInt(br.readLine()));
        }

        if (n <= 3) {
            int val = 0;
            for (int i : stairs) {
                val += i;
            }
            System.out.println(val);
            return;
        }
        int[] dp = new int[n + 1];

        dp[1] = stairs.get(1);
        dp[2] = stairs.get(2)+stairs.get(1);
        dp[3] = Math.max(stairs.get(1) + stairs.get(3), stairs.get(2) + stairs.get(3));
        for (int i = 4; i < dp.length; i++) {
            dp[i] = Math.max(dp[i - 3] + stairs.get(i-1) + stairs.get(i), dp[i - 2] + stairs.get(i));
        }


        System.out.println(dp[n]);

    }
}
