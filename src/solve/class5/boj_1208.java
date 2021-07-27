package solve.class5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

class boj_1208 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int n = arr[0];
        int s = arr[1];

        arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int mid = n / 2;

        int[] dp1 = new int[(1 << mid)];
        List<Integer> list = new ArrayList<>();
        Integer[] dp2 = new Integer[(1 << (n - mid))];
        Arrays.fill(dp2, 0);
        for (int i = 1; i < (1 << mid); i++) {
            for (int j = 0; j < mid && (1 << j) <= i; j++) {
                if ((i & (1 << j)) != 0) {
                    dp1[i] += arr[j];
                }
            }
        }
        for (int i = 1; i < (1 << (n - mid)); i++) {
            for (int j = 0; j < n - mid && (1 << j) <= i; j++) {
                if ((i & (1 << j)) != 0) {
                    dp2[i] += arr[j + mid];
                }
            }
        }
        Arrays.sort(dp1);
        Arrays.sort(dp2, Comparator.reverseOrder());
        int frontIdx = 0;
        int backIdx = 0;

        long ans = 0;
        while (frontIdx < dp1.length && backIdx < dp2.length) {
            int sum = dp1[frontIdx] + dp2[backIdx];

            if (sum == s) {
                long frontCount = 1;
                long backCount = 1;

                int frontValue = dp1[frontIdx];
                int backValue = dp2[backIdx];
                frontIdx++;
                backIdx++;
                while (frontIdx < dp1.length) {
                    if (dp1[frontIdx] == frontValue) {
                        frontCount++;
                        frontIdx++;
                    } else {
                        break;
                    }
                }
                while (backIdx < dp2.length) {
                    if (dp2[backIdx] == backValue) {
                        backIdx++;
                        backCount++;
                    } else {
                        break;
                    }
                }
                ans += (frontCount * backCount);
            } else if (sum > s) {
                backIdx++;
            } else {
                frontIdx++;
            }
        }

        if (s != 0) {
            while (backIdx < dp2.length) {
                if (dp2[backIdx++] == s) {
                    ans++;
                } else {
                    break;
                }
            }

            while (frontIdx < dp1.length) {
                if (dp1[frontIdx++] == s) {
                    ans++;
                } else {
                    break;
                }
            }
        }
        System.out.println(s == 0 ? ans - 1 : ans);
    }
}
