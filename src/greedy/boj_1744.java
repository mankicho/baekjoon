package greedy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class boj_1744 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[] arr = new int[n];

        int minus = 0;
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
            if (arr[i] < 0) {
                minus++;
            }
        }

        if (n == 1) {
            System.out.println(arr[0]);
            return;
        }
        Arrays.sort(arr);

        boolean odd = minus % 2 == 1;
        int ans = 0;

        int idx = 0;
        if (odd) {
            for (int i = 0; i + 1 < arr.length && arr[i + 1] < 0; i += 2) {
                ans += (arr[i] * arr[i + 1]);
                idx = i + 2;
            }

            if (idx < arr.length - 1 && arr[idx + 1] == 0) {
                idx += 2;
            } else {
                ans += arr[idx];
                idx++;
            }

            int tmpIdx = arr.length - 1;
            for (int i = arr.length - 1; i >= idx && i - 1 >= idx; i = i - 2) {
                tmpIdx = i - 2;
                if (arr[i - 1] == 0) {
                    ans += arr[i];
                    break;
                } else if (arr[i] == 1 || arr[i - 1] == 1) {
                    ans += arr[i];
                    i++;
                    tmpIdx++;
                } else {
                    ans += (arr[i] * arr[i - 1]);
                }
            }

            if (tmpIdx == idx) {
                ans += arr[idx];
            }
        } else {
            for (int i = 0; i + 1 < arr.length && arr[i] < 0; i += 2) {
                ans += (arr[i] * arr[i + 1]);
                idx = i + 2;
            }

            int tmpIdx = arr.length - 1;
            for (int i = arr.length - 1; i >= idx && i - 1 >= idx; i = i - 2) {
                tmpIdx = i - 2;
                if (arr[i - 1] == 0) {
                    ans += arr[i];
                    break;
                } else if (arr[i] == 1 || arr[i - 1] == 1) {
                    ans += arr[i];
                    i++;
                    tmpIdx++;
                } else {
                    ans += (arr[i] * arr[i - 1]);
                }
            }

            if (tmpIdx == idx) {
                ans += arr[idx];
            }
        }
        System.out.println(ans);

    }
}