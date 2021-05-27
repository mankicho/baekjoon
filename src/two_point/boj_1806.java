package two_point;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class boj_1806 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int n = arr[0];
        int s = arr[1];
        arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int start = 0;
        int end = 1;
        int cnt = arr.length;
        int sum = arr[0] + arr[1];
        if (arr[0] >= s) {
            System.out.println(1);
            return;
        }

        boolean isAll = false;
        while (true) {
            if (sum >= s) {
                if (cnt > end - start + 1) {
                    cnt = end - start + 1;
                }
                sum -= arr[start];
                start++;
            } else {
                ++end;
                sum += arr[end];
            }
//            5 1 3 5 10 7 4 9 2 8

            if (end == arr.length - 1) {
                if (start == 0 && sum < s) {
                    isAll = true;
                    break;
                }
                while (sum >= s) {
                    sum -= arr[start++];
                }


                if (cnt > end - start + 2) {
                    cnt = end - start + 2;
                }
                break;
            }
        }

        if (cnt == arr.length) {
            if (isAll && sum < s) { // 없는경우
                System.out.println(0);
            } else {
                System.out.println(cnt);
            }
        } else {
            System.out.println(cnt);
        }

    }
}