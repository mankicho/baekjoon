package binary_search;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class boj_1477 {

    static int[] dp = new int[1000001];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int n = arr[0];
        int m = arr[1];
        int l = arr[2];

        arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        Arrays.sort(arr);

        List<Integer> list = new ArrayList<>();
        list.add(0);

        for (int i : arr) {
            list.add(i);
        }
        list.add(l);
        int start = 0;
        int end = l;

        while (start < end) {
            int mid = (start + end) / 2;

            int sum = 0;
            for (int i = 1; i < list.size(); i++) {
                int range = list.get(i) - list.get(i - 1) - 1;

                sum += range / mid;
            }

            if (sum > m) {
                start = mid + 1;
            } else {
                end = mid;
            }
        }
        System.out.println(start);
    }
}