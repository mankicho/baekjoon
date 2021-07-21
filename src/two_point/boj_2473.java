package two_point;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class boj_2473 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        br.readLine();
        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        long min = Long.MAX_VALUE;
        int first = -1;
        int second = -1;
        int third = -1;

        Arrays.sort(arr);
        for (int i = 0; i < arr.length; i++) {
            int j = i + 1;
            int k = arr.length - 1;
            while (j < k) {
                long sum = arr[i];
                sum += (arr[j] + arr[k]);

                long abs = Math.abs(sum);

                if (abs < min) {
                    min = abs;
                    first = arr[i];
                    second = arr[j];
                    third = arr[k];
                }

                if (sum <= 0) {
                    j++;
                } else {
                    k--;
                }
            }
        }
        System.out.println(first + " " + second + " " + third);
    }


}