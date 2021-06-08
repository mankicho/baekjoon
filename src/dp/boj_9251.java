package dp;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class boj_9251 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String a = br.readLine();
        String b = br.readLine();

        String tmpA;
        String tmpB;
        if (a.length() > b.length()) {
            tmpA = a;
            tmpB = b;
        } else {
            tmpA = b;
            tmpB = a;
        }

        System.out.println(function(tmpB, tmpA));
    }

    static int function(String shorts, String longs) {
        int[][] dp = new int[longs.length() + 1][longs.length() + 1];
        for (int i = 1; i < shorts.length() + 1; i++) {
            char c1 = shorts.charAt(i - 1);
            for (int j = 1; j < longs.length() + 1; j++) {
                char c2 = longs.charAt(j - 1);
                if (c1 == c2) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[shorts.length()][longs.length()];
    }
}