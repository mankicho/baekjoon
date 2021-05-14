package greedy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class boj_1931 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());


        List<int[]> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            list.add(arr);
        }

        Collections.sort(list, (o1, o2) -> {
            if (o1[0] == o2[0]) {
                return o2[1] - o1[1];
            }
            return o2[0] - o1[0];
        });

        int idx = 0;
        int answer = 1;

        int[] arr = list.get(0);

        int start = arr[0];
        int end = arr[1];
        for (int i = 1; i < list.size(); i++) {

            int[] arr2 = list.get(i);

            int start2 = arr2[0];
            int end2 = arr2[1];
            if (end2 <= start) {
                answer++;
                start = start2;
                end = end2;
            } else if (start2 >= start && end2 <= end) {
                end = end2;
                start = start2;
            }
        }
        System.out.println(answer);

    }
}
