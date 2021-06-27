package dp3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class boj_9252 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String first = br.readLine();
        String second = br.readLine();

        int[][] dp = new int[first.length() + 1][second.length() + 1];


        for (int i = 0; i < first.length(); i++) {
            if (first.charAt(i) == second.charAt(0)) {
                for (int j = i; j < first.length(); j++) {
                    dp[j+1][1] = 1;
                }
                break;
            }
        }

        for (int i = 0; i < second.length(); i++) {
            if (second.charAt(i) == first.charAt(0)) {
                for (int j = i; j < second.length(); j++) {
                    dp[1][j+1] = 1;
                }
                break;
            }
        }
        for (int i = 1; i < first.length(); i++) {
            char chr = first.charAt(i);
            for (int j = 1; j < second.length(); j++) {
                char chr2 = second.charAt(j);

                if (chr == chr2) {
                    dp[i + 1][j + 1] = dp[i][j] + 1;
                } else {
                    dp[i + 1][j + 1] = Math.max(dp[i + 1][j], dp[i][j + 1]);
                }
            }
        }


        System.out.println(dp[first.length()][second.length()]);
        StringBuilder sb = new StringBuilder();

        print(sb, dp, first, second, first.length(), second.length());
        if (sb.length() != 0) {
            System.out.println(sb.toString());
        }
    }

    static void print(StringBuilder sb, int[][] dp, String first, String second, int row, int col) {
        if (row < 1 || col < 1) {
            return;
        }
        if (first.charAt(row - 1) == second.charAt(col - 1)) {
            print(sb, dp, first, second, row - 1, col - 1);
            sb.append(first.charAt(row - 1));
        } else {
            if (dp[row - 1][col] > dp[row][col - 1]) {
                print(sb, dp, first, second, row - 1, col);
            } else {
                print(sb, dp, first, second, row, col - 1);
            }
        }
    }
}

