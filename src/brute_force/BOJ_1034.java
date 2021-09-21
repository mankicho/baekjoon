package brute_force;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_1034 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int n = arr[0];
        int m = arr[1];

        String[] strs = new String[n];

        int[] buffer = new int[n];
        for (int i = 0; i < n; i++) {
            strs[i] = br.readLine();
            for (int j = 0; j < strs[i].length(); j++) {
                if (strs[i].charAt(j) == '0') {
                    buffer[i]++;
                }
            }
        }

        int max = 0;
        int k = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            String first = strs[i];
            if (buffer[i] > k || (buffer[i] % 2 != k % 2)) {
                continue;
            }
            int cnt = 0;
            for (int j = 0; j < n; j++) {
                String second = strs[j];
                if (first.equals(second)) {
                    cnt++;
                }
            }
            max = Math.max(max, cnt);
        }
        System.out.println(max);
    }
}