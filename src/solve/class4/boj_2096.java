package solve.class4;



import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class boj_2096 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[][] values = new int[n][3];
        for (int i = 0; i < n; i++) {
            values[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }
        int[][] min = new int[2][3];
        int[][] max = new int[2][3];
        for (int i = 0; i < 3; i++) {
            min[0][i] = values[0][i];
            max[0][i] = values[0][i];
        }
        for (int i = 1; i < values.length; i++) {

            min[1][0] = minFunction(min, 0, 1) + values[i][0];
            min[1][1] = minFunction(min, 0, 2) + values[i][1];
            min[1][2] = minFunction(min, 1, 2) + values[i][2];
            max[1][0] = maxFunction(max, 0, 1) + values[i][0];
            max[1][1] = maxFunction(max, 0, 2) + values[i][1];
            max[1][2] = maxFunction(max, 1, 2) + values[i][2];

            for (int j = 0; j < 3; j++) {
                min[0][j] = min[1][j];
                max[0][j] = max[1][j];
            }
        }
        System.out.println(maxFunction(max, 0, 2) + " " + minFunction(min, 0, 2));
    }

    static int maxFunction(int[][] arr, int start, int end) {
        int max = 0;
        for (int i = start; i <= end; i++) {
            if (arr[0][i] >= max) {
                max = arr[0][i];
            }
        }
        return max;
    }

    static int minFunction(int[][] arr, int start, int end) {
        int min = Integer.MAX_VALUE;
        for (int i = start; i <= end; i++) {
            if (arr[0][i] <= min) {
                min = arr[0][i];
            }
        }
        return min;
    }
}