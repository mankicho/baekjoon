package swipping;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class boj_2836 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int n = arr[0];
        int m = arr[1];

        List<int[]> list = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            if (arr[0] > arr[1]) {
                list.add(arr);
            }
        }

        list.sort((o1, o2) -> {
            if (o1[0] == o2[0]) {
                return o1[1] - o2[1];
            }
            return o2[0] - o1[0];
        });

        long answer = m;

        int start = list.get(0)[0];
        int end = list.get(0)[1];

        for (int i = 1; i < list.size(); i++) {
            if (list.get(i)[0] == start) {
                continue;
            }

            int nextStart = list.get(i)[0];
            int nextEnd = list.get(i)[1];

            if (nextStart < end) {
                answer += 2 * (start - end);
                start = nextStart;
                end = nextEnd;
            } else if (nextEnd < end) {
                end = nextEnd;
            }
        }

        answer += 2 * (start - end);
        System.out.println(answer);
    }


}
