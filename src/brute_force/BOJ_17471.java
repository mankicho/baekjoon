package brute_force;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class BOJ_17471 {

    static int[][] dir = new int[][]{
            {0, 1}, {1, 0}, {0, -1}, {-1, 0}
    };
    private static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        List<List<Integer>> list = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            list.add(new ArrayList<>());
        }
        for (int i = 1; i <= n; i++) {
            int[] adjecent = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            for (int j = 1; j < adjecent.length; j++) {
                list.get(i).add(adjecent[j]);
            }
        }

        backTracking(arr, new int[n + 1], list, n, 0, 0);
        System.out.println(min == Integer.MAX_VALUE ? -1 : min);
    }

    static void backTracking(int[] arr, int[] tmpArr, List<List<Integer>> list, int n, int index, int size) {
        if (size == n) {
            return;
        }

        if (index > 0) {
            LinkedList<Integer> queue = new LinkedList<>();

            queue.add(index);

            boolean[] visited = new boolean[n + 1];
            int cnt = 0;
            while (!queue.isEmpty()) {
                int poll = queue.poll();

                if (visited[poll]) {
                    continue;
                }
                visited[poll] = true;
                List<Integer> nexts = list.get(poll);

                for (int i = 0; i < nexts.size(); i++) {
                    int next = nexts.get(i);

                    if (tmpArr[next] == 1 && !visited[next]) {
                        queue.add(next);
                    }
                }
                cnt++;
            }

            if (cnt == size) {
                int tmp = 0;
                for (int i = 1; i < visited.length; i++) {
                    if (!visited[i]) {
                        tmp = i;
                        break;
                    }
                }
                queue.add(tmp);

                int tmpCnt = 0;
                while (!queue.isEmpty()) {
                    int poll = queue.poll();

                    if (visited[poll]) {
                        continue;
                    }
                    visited[poll] = true;

                    List<Integer> nexts = list.get(poll);

                    for (int j = 0; j < nexts.size(); j++) {
                        int next = nexts.get(j);

                        if (!visited[next] && tmpArr[next] == 0) {
                            queue.add(next);
                        }
                    }
                    tmpCnt++;
                }

                if (tmpCnt == n - size) {
                    int left = 0;
                    int right = 0;
                    for (int i = 1; i < tmpArr.length; i++) {
                        if (tmpArr[i] == 0) {
                            right += arr[i - 1];
                        } else {
                            left += arr[i - 1];
                        }
                    }
                    min = Math.min(min, Math.abs(left - right));
                }
            }
        }
        for (int i = index + 1; i <= n; i++) {
            if (tmpArr[i] == 0) {
                tmpArr[i]++;
                backTracking(arr, tmpArr, list, n, i, size + 1);
                tmpArr[i]--;
            }
        }
    }
}
