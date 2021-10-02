package tree;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BOJ_2250 {
    static int[][] dir = new int[][]{
            {0, 1}, {1, 0}, {0, -1}, {-1, 0}
    };

    static int[][] tmpBuffer;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        tmpBuffer = new int[n + 1][2];

        for (int i = 1; i < tmpBuffer.length; i++) {
            tmpBuffer[i] = new int[]{Integer.MAX_VALUE, Integer.MIN_VALUE};
        }
        List<List<Integer>> list = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            list.add(new ArrayList<>());
        }
        int[] parent = new int[n + 1];
        for (int i = 0; i < n; i++) {
            int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            int a = arr[0];
            int b = arr[1];
            int c = arr[2];

            if (b != -1) {
                parent[b] = a;
            }
            if (c != -1) {
                parent[c] = a;
            }

            list.get(a).add(b);
            list.get(a).add(c);
        }

        int start = 0;
        for (int i = 1; i < parent.length; i++) {
            if (parent[i] == 0) {
                start = i;
                break;
            }
        }
        dfs(list, start, 1);

        int max = 0;
        int index = 1;
        for (int i = 1; i <= maxHeight; i++) {
            if (tmpBuffer[i][1] - tmpBuffer[i][0] > max) {
                max = tmpBuffer[i][1] - tmpBuffer[i][0];
                index = i;
            }
        }

        System.out.println(index + " " + (max + 1));
    }

    static int number = 1;
    static int maxHeight = 0;

    static void dfs(List<List<Integer>> list, int now, int height) {
        maxHeight = Math.max(maxHeight, height);
        List<Integer> nexts = list.get(now);

        for (int i = 0; i < nexts.size(); i++) {
            if (i == 1) {
                tmpBuffer[height][0] = Math.min(tmpBuffer[height][0], number);
                tmpBuffer[height][1] = Math.max(tmpBuffer[height][1], number);
                number++;
            }
            int next = nexts.get(i);

            if (next == -1) {
                continue;
            }

            dfs(list, next, height + 1);
        }
    }
}