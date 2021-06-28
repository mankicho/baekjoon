package dfs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class boj_1707 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int testcase = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < testcase; i++) {
            int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int v = arr[0];
            int e = arr[1];

            List<List<Integer>> list = new ArrayList<>(v);
            for (int j = 0; j <= v; j++) {
                list.add(new ArrayList<>());
            }

            int idx = 0;
            int idx2 = 0;
            for (int j = 0; j < e; j++) {
                int[] tmp = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

                list.get(tmp[0]).add(tmp[1]);
                list.get(tmp[1]).add(tmp[0]);
            }

            if (v == 1 || e == 0) {
                sb.append("YES").append("\n");
                continue;
            }
            binary = true;
            flags = new int[v + 1];

            while (binary) {
                int tmpIdx = find(flags);
                if (tmpIdx == -1) {
                    break;
                }

                flags[tmpIdx] = 1;
                boolean[] visited = new boolean[v + 1];
                bfs(list, visited, tmpIdx);
                if (list.get(tmpIdx).size() > 0) {
                    visited = new boolean[v + 1];
                    bfs(list, visited, list.get(tmpIdx).get(0));
                }

            }
            if (binary) {
                sb.append("YES").append("\n");
            } else {
                sb.append("NO").append("\n");
            }


        }

        System.out.println(sb.toString());


    }


    static int[] flags;

    static boolean binary = true;

    static int find(int[] flags) {
        for (int i = 1; i < flags.length; i++) {
            if (flags[i] == 0) {
                return i;
            }
        }
        return -1;
    }

    static void bfs(List<List<Integer>> list, boolean[] visited, int num) {

        LinkedList<Integer> queue = new LinkedList<>();
        queue.add(num);

        LOOP:
        while (!queue.isEmpty()) {
            int poll = queue.poll();

            if (visited[poll]) {
                continue;
            }

            visited[poll] = true;

            List<Integer> childs = list.get(poll);

            for (int i = 0; i < childs.size(); i++) {
                int child = childs.get(i);

                if (visited[child]) {
                    continue;
                }

                if (flags[child] == 0) {
                    flags[child] = flags[poll] * -1;
                } else {
                    if (flags[child] != flags[poll] * -1) {
                        binary = false;
                        break LOOP;
                    }
                }
                queue.add(child);
            }
        }
    }
}