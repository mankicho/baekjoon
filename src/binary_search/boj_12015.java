package binary_search;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class boj_12015 {

    static int[] dp = new int[1000001];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        List<Integer> sortedList = new ArrayList<>();

        sortedList.add(0);

        for (int i = 0; i < arr.length; i++) {
            int v = arr[i];

            if (v > sortedList.get(sortedList.size() - 1)) {
                sortedList.add(v);
            } else {
                int start = 0;
                int end = sortedList.size() - 1;

                // 0 10 20 30 40 1 2 3
                while (start < end) {
                    int mid = (start + end) / 2;

                    if (sortedList.get(mid) >= v) {
                        end = mid;
                    } else {
                        start = mid + 1;
                    }
                }
                sortedList.set(start, v);
            }
        }
        System.out.println(sortedList.size()-1);

    }

    // 10 20 1 2 3 4
    static void insert(List<Integer> list, int start, int end, int value) {
        int mid = (start + end) / 2;


    }
}