package dp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class boj_7579 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int n = arr[0];
        int m = arr[1];

        int[] vals = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int[] c = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        dp = new int[10001];
        for (int i = 0; i < vals.length; i++) {
            int cost = c[i];
            for (int j = 10000; j >= cost; j--) {
                if (dp[j - cost] != -1) {
                    if (dp[j - cost] + vals[i] > dp[j]) {
                        dp[j] = dp[j - cost] + vals[i];
                    }
                }
            }
            if (dp[cost] < vals[i]) {
                dp[cost] = vals[i];
            }
        }
        for(int i=1;i<10001;i++){
            if(dp[i] >=m){
                System.out.println(i);
                break;
            }
        }
    }

    static int sum;
    static int valSum;
    static int[] dp;

    static int min = 1000000001;

    static void function(int[] vals, int[] c, int num, int m) {

    }
}