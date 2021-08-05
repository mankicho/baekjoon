package sort;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class BOJ_9576 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int testcase = Integer.parseInt(br.readLine());
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < testcase; i++) {
            int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            int n = arr[0];
            int m = arr[1];

            List<int[]> list = new ArrayList<>();
            for (int j = 0; j < m; j++) {
                int[] ints = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

                list.add(ints);
            }

            list.sort(new Comparator<int[]>() {
                @Override
                public int compare(int[] o1, int[] o2) {
                    if (o1[1] == o2[1]) {
                        return o1[0] - o2[0];
                    }
                    return o1[1] - o2[1];
                }
            });

            boolean[] visited = new boolean[n + 1];

            int ans = 0;
            for (int i1 = 0; i1 < list.size(); i1++) {
                int[] ar = list.get(i1);

                int a = ar[0];
                int b = ar[1];

                for (int j = a; j <= b; j++) {
                    if (!visited[j]) {
                        visited[j] = true;
                        ans++;
                        break;
                    }
                }
            }
            result.append(ans).append("\n");
        }
        System.out.println(result.toString());
    }
}

