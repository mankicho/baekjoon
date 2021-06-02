package dp3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class boj_1311  {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int[][] arr = new int[n][n];
        for (int i = 0; i < n; i++) {
            arr[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }
        int[] mask = new int[(int) Math.pow(2, n)];

        Arrays.fill(mask, Integer.MAX_VALUE);

        mask[0] = 0;
        dp(arr, mask, n);
        System.out.println(mask[mask.length - 1]);
    }

    static void dp(int[][] arr, int[] maskArr, int n) throws Exception {
        for (int i = 0; i < (int) Math.pow(2, n); i++) {
            int x = numOf(i, n);
            for (int j = 0; j < n; j++) {
                if ((i & (1 << j)) == 0) {
                    maskArr[i | (1 << j)] = Math.min(maskArr[i | (1 << j)]
                            , maskArr[i] + arr[x][j]);
                }

            }
        }
    }

    static int numOf(int mask, int n) {
        int num = 0;
        for (int i = 0; i < n; i++) {
            if ((mask & (1 << i)) != 0) {
                num++;
            }
        }
        return num;
    }

}