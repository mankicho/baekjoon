package two_point;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class boj_3273 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int x = Integer.parseInt(br.readLine());

        Arrays.sort(arr);

        int start = 0;
        int end = arr.length - 1;

        int cnt = 0;
        while (start < end) {
            int sum = arr[start] + arr[end];
            if (sum > x) {
                end--;
            } else if (sum == x) {
                end--;
                cnt++;
            } else {
                start++;
            }
        }

        System.out.println(cnt);
    }
}