package binary_search;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ_1300 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int k = Integer.parseInt(br.readLine());


        int start = 1;
        int end = k;
        while (start < end) {
            int mid = (start + end) / 2;

            int sum = 0;
            for (int i = 1; i <= n; i++) {
                sum += (Math.min(mid / i, n));
            }

            if (sum >= k) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }
        System.out.println(start);
    }
}