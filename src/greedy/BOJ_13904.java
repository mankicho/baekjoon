package greedy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class BOJ_13904 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        List<int[]> list = new ArrayList<>();
        boolean[] visited = new boolean[1001];

        for (int i = 0; i < n; i++) {
            int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            list.add(arr);
        }

        list.sort((o1, o2) -> {
            if (o2[1] == o1[1]) {
                return o2[0] - o1[1];
            }
            return o2[1] - o1[1];
        });

        int ans = 0;
//        list.forEach(s -> System.out.println(Arrays.toString(s)));
        for (int i = 0; i < list.size(); i++) {
            int[] assignment = list.get(i);

            int d = assignment[0];
            int w = assignment[1];

            int idx = find(d, visited);
            if (idx == -1) {
                continue;
            }

            visited[idx] = true;
            ans += w;
        }
        System.out.println(ans);
    }

    static int find(int d, boolean[] visited) {
        for (int i = d; i >= 1; i--) {
            if (!visited[i]) {
                return i;
            }
        }
        return -1;
    }


}
