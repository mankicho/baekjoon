package binary_search;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BOJ_1965 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        List<Integer> list = new ArrayList<>();
        list.add(0);
        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        for (int i = 0; i < arr.length; i++) {
            int start = 0;
            int end = list.size() - 1;

            if (arr[i] > list.get(end)) {
                list.add(arr[i]);
            } else if (arr[i] == list.get(end)) {
                continue;
            } else {

                while (start < end) {
                    int mid = (start + end) / 2;

                    if (arr[i] > list.get(mid)) {
                        start = mid + 1;
                    } else {
                        end = mid;
                    }
                }
                list.set(start, arr[i]);
            }
        }
        System.out.println(list.size() - 1);
    }
}