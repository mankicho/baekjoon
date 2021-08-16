package bfs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_5214 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int n = arr[0];
        int k = arr[1];
        int m = arr[2];

        List<List<Integer>> lines = new ArrayList<>();
        List<List<Integer>> subways = new ArrayList<>();

        for (int i = 0; i <= m; i++) {
            lines.add(new ArrayList<>());
        }
        for (int i = 0; i <= n; i++) {
            subways.add(new ArrayList<>());
        }
        for (int i = 0; i < m; i++) {
            arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            for (int j = 0; j < arr.length; j++) {
                int line = i + 1;
                int subway = arr[j];

                lines.get(line).add(subway);
                subways.get(subway).add(line);
            }
        }

        bfs(lines, subways, m, n);
    }

    static void bfs(List<List<Integer>> lines, List<List<Integer>> subways, int l, int end) {
        PriorityQueue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(o -> o[2]));

        List<Integer> startLines = subways.get(1);
        for (Integer startLine : startLines) {
            queue.add(new int[]{1, startLine, 1});
        }
        boolean[] visited = new boolean[l + 1];

        int cnt = 0;
        while (!queue.isEmpty()) {
            int[] poll = queue.poll();

            int now = poll[0];
            int nowLine = poll[1];
            int changed = poll[2];

            if(now == end){
                System.out.println(changed);
                return;
            }
            List<Integer> subwaysOfLine = lines.get(nowLine);
//            System.out.println(subwaysOfLine);
            LOOP:
            for (int i = 0; i < subwaysOfLine.size(); i++) {
                int subway = subwaysOfLine.get(i);

                if (subway == end) {
                    System.out.println(changed + 1);
                    return;
                }
                List<Integer> linesOfSubway = subways.get(subway);

                for (int j = 0; j < linesOfSubway.size(); j++) {
                    if (cnt == l) {
                        break;
                    }
                    int line = linesOfSubway.get(j);
                    if (visited[line]) {
                        continue;
                    }
                    visited[line] = true;
                    queue.add(new int[]{subway, line, changed + 1});
                    cnt++;
                }
            }
        }
        System.out.println(-1);

    }
}