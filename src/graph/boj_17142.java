package graph;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class boj_17142 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int n = arr[0];
        int m = arr[1];

        int[][] virus = new int[n][n];

        boolean b = false;
        List<int[]> v = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            virus[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            for (int j = 0; j < virus[i].length; j++) {
                if (virus[i][j] == 2) {
                    v.add(new int[]{i, j});
                }
                if (!b && virus[i][j] == 0) {
                    b = true;
                }
            }
        }
        int[][] dp = new int[n][n];

        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[i].length; j++) {
                if (dp[i][j] != 1) {
                    dp[i][j] = 100000;
                }
            }
        }
        dfs(virus, v, m, -1);

        if(!b){
            System.out.println(0);
        }else {
            System.out.println(min == 1000000 ? -1 : min);
        }
    }

    static int cnt = 0;

    static int min = 1000000;

    static List<int[]> vList = new ArrayList<>();

    static void dfs(int[][] v, List<int[]> list, int m, int start) {
        if (cnt == m) {
            function(v, vList);
            return;
        }
        for (int i = start + 1; i < list.size(); i++) {
            cnt++;
            vList.add(list.get(i));
            dfs(v, list, m, i);
            vList.remove(list.get(i));
            cnt--;
        }
    }

    static int[][] direction = new int[][]{
            {0, 1}, {1, 0}, {0, -1}, {-1, 0}
    };

    static void function(int[][] v, List<int[]> vList) {
        LinkedList<int[]> queue = new LinkedList<>();

        queue.addAll(vList);

        int[][] dp = new int[v.length][v.length];

        for (int[] ints : dp) {
            Arrays.fill(ints, 100000);
        }
        vList.forEach(arr -> dp[arr[0]][arr[1]] = 0);
        while (!queue.isEmpty()) {
            int[] poll = queue.poll();

            int row = poll[0];
            int col = poll[1];

            for (int i = 0; i < direction.length; i++) {
                int[] dir = direction[i];

                int endRow = dir[0] + row;
                int endCol = dir[1] + col;

                if (!((endRow >= 0 && endRow < dp.length) && (endCol >= 0 && endCol < dp.length))
                        || v[endRow][endCol] == 1) {
                    continue;
                }

                if (dp[endRow][endCol] > dp[row][col] + 1) {
                    dp[endRow][endCol] = dp[row][col] + 1;
                    queue.add(new int[]{endRow, endCol});
                }
            }
        }
        int max = -1;
        boolean can = true;
        LOOP:
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp.length; j++) {
                if (dp[i][j] != 100000 && v[i][j] == 0 && dp[i][j] > max) {
                    max = dp[i][j];
                }
                if (v[i][j] == 0 && dp[i][j] == 100000) {
                    can = false;
                    break LOOP;
                }
            }
        }
        if (can && max < min) {
            min = max;
        }
    }
}