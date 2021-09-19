package solve.class5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

class boj_1208 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int[][] points = new int[2][n];
        for (int i = 0; i < n; i++) {
            int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            int x = arr[0];
            int y = arr[1];

            points[0][i] = x;
            points[1][i] = y;
        }

        long sum = 0L;
        long sum2 = 0L;
        for (int i = 0; i < points[0].length - 1; i++) {
            int x1 = points[0][i];
            int y1 = points[1][i];
            int x2 = points[0][i+1];
            int y2 = points[1][i+1];

            sum2 += (y1 * x2);
            sum += (x1 * y2);
        }

        System.out.println(Math.abs((sum-sum2))/2);
    }
}
