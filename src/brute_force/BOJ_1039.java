package brute_force;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BOJ_1039 {
    static int[][] dir = new int[][]{
            {0, 1}, {1, 0}, {0, -1}, {-1, 0}
    };

    static int max = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int n = arr[0];
        int k = arr[1];

        if (n < 10) {
            System.out.println(-1);
            return;
        }

        if (n < 100 && n % 10 == 0) {
            System.out.println(-1);
            return;
        }
        String start = String.valueOf(n);

        int[] values = new int[start.length()];

        for (int i = 0; i < start.length(); i++) {
            values[i] = Character.digit(start.charAt(i), 10);
        }
//

        backTracking(values, 0, k, -1);
        System.out.println(max);
    }

    static void backTracking(int[] values, int n, int num, int index) {
        if (n == num) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < values.length; i++) {
                sb.append(values[i]);
            }

            if (Integer.parseInt(sb.toString()) > max) {
                max = Integer.parseInt(sb.toString());
            }
            return;
        }

        for (int i = index + 1; i < values.length; i++) {
            List<Integer> tmpList = new ArrayList<>();

            int sVal = values[i];
            int tmp = values[i];
            for (int j = i + 1; j < values.length; j++) {
                if (values[j] > tmp) {
                    tmp = values[j];
                }
            }

            for (int j = i + 1; j < values.length; j++) {
                if (values[j] == tmp) {
                    tmpList.add(j);
                }
            }

            for (int j = 0; j < tmpList.size(); j++) {
                int idx = tmpList.get(j);

                int changedVal = values[idx];

                values[idx] = sVal;
                values[i] = changedVal;
                backTracking(values, n + 1, num, i);
                values[i] = sVal;
                values[idx] = changedVal;
            }
        }

        int cnt = n;

        int temp1 = values[values.length - 1];
        int temp2 = values[values.length - 2];
        while (cnt < num) {
            int temp3 = values[values.length - 1];

            values[values.length - 1] = values[values.length - 2];
            values[values.length - 2] = temp3;
            cnt++;
        }

        StringBuilder s = new StringBuilder();
        for (int i = 0; i < values.length; i++) {
            s.append(values[i]);
        }

        if (Integer.parseInt(s.toString()) > max) {
            max = Integer.parseInt(s.toString());
        }
        values[values.length - 1] = temp1;
        values[values.length - 2] = temp2;
    }
}