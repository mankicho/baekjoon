package brute_force;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_1027 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


        int n = Integer.parseInt(br.readLine());
        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int max = 0;
        for (int i = 0; i < arr.length; i++) {
            int startCnt = 0;
            for (int j = i + 1; j < arr.length; j++) {
                boolean can = true;
                for (int k = i + 1; k < j; k++) {
                    double first = ((double) arr[k] - arr[i]) / (k - i);
                    double second = ((double) arr[j] - arr[i]) / (j - i);
                    if (first >= second) {
                        can = false;
                        break;
                    }
                }
                if (can) {
                    startCnt++;
                }
            }

            for (int j = i - 1; j >= 0; j--) {
                boolean can = true;
                for (int k = i - 1; k > j; k--) {
                    double first = ((double) (arr[k] - arr[i])) / (i - k);
                    double second = ((double) (arr[j] - arr[i])) / (i - j);

                    if (first >= second) {
                        can = false;
                    }
                }
                if (can) {
                    startCnt++;
                }
            }
            max = Math.max(startCnt, max);
        }
        System.out.println(max);
    }
}
