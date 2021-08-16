package bfs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_2021 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int n = arr[0];
        int l = arr[1];

        List<List<Integer>> lines = new ArrayList<>();
        List<List<Integer>> subways = new ArrayList<>();

        for (int i = 0; i <= l; i++) {
            lines.add(new ArrayList<>());
        }
        for (int i = 0; i <= n; i++) {
            subways.add(new ArrayList<>());
        }
        for (int i = 0; i < l; i++) {
            arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            for (int j = 0; j < arr.length - 1; j++) {
                int line = i + 1;
                int subway = arr[j];

                lines.get(line).add(subway);
                subways.get(subway).add(line);
            }
        }
        arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int start = arr[0];
        int end = arr[1];

        bfs(lines,subways,start,l,end);
    }

    static void bfs(List<List<Integer>> lines, List<List<Integer>> subways, int start, int l, int end) {
        PriorityQueue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(o -> o[1]));

        List<Integer> startLines = subways.get(start);
        for (Integer startLine : startLines) {
            queue.add(new int[]{startLine,0});
        }
        boolean[] visited = new boolean[l+1];

        int cnt = 0;
        while (!queue.isEmpty()) {
            int[] poll = queue.poll();

            int nowLine = poll[0];
            int changed = poll[1];

            List<Integer> subwaysOfLine = lines.get(nowLine);
//            System.out.println(subwaysOfLine);
            LOOP:
            for (int i = 0; i < subwaysOfLine.size(); i++) {
                int subway = subwaysOfLine.get(i);

                if (subway == end) {
                    System.out.println(changed);
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
                    queue.add(new int[]{line, changed + 1});
                    cnt++;
                }
            }
        }
        System.out.println(-1);
    }
}