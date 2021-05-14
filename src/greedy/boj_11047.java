package greedy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class boj_11047 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String firstLine = br.readLine();
        int[] arr = Arrays.stream(firstLine.split(" ")).mapToInt(Integer::parseInt).toArray();

        int n = arr[0];
        int k = arr[1];

        int[] values = new int[n];
        for (int i = 0; i < n; i++) {
            values[i] = Integer.parseInt(br.readLine());
        }

        int cnt = n - 1;
        int answer = 0;
        while (cnt >= 0) {
            int value = values[cnt];

            if (k >= value) {
                int tmp = k / value;
                k = k - tmp * value;
                answer = answer + tmp;
            }

            if(k == 0){
                break;
            }
            cnt--;
        }

        System.out.println(answer);
    }
}
