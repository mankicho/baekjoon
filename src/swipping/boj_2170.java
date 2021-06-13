package swipping;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class boj_2170 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


        int n = Integer.parseInt(br.readLine());

        List<int[]> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            list.add(arr);
        }

        list.sort((o1, o2) -> {
            if (o1[0] == o2[0]) {
                return o2[1] - o1[1];
            }
            return o1[0] - o2[0];
        });


        int[] startArr = list.get(0);
        int start = startArr[0];
        int end = startArr[1];
        int answer = end - start;
        for (int i = 1; i < list.size(); i++) {
            int[] tmp = list.get(i);

            if (tmp[0] == start) {
                continue;
            }

            start = tmp[0];

            if (end > start && tmp[1] > end) {
                answer += (tmp[1] - end);
                end = tmp[1];
            }

            if (end < start) {
                end = tmp[1];
                answer += (end - start);
            }
        }
        System.out.println(answer);
    }
}