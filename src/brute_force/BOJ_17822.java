package brute_force;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class BOJ_17822 {
    static int[][] dir = new int[][]{
            {-1, 0}, {1, 0}, {0, 1}, {0,-1}
    };

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int n = arr[0];
        int m = arr[1];
        int t = arr[2];

        int[][] values = new int[n + 1][m];

        for (int i = 0; i < n; i++) {
            values[i + 1] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }
        visited = new boolean[values.length][values[0].length];
        for (int i = 0; i < t; i++) {
//            System.out.println("before");
//            for (int[] v : values) {
//                System.out.println(Arrays.toString(v));
//
//            }
            arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int x = arr[0];
            int d = arr[1];
            int k = arr[2];

            if (d == 0) {
                for (int j = x; j <= n; j = j + x) {
                    for (int tmp = 0; tmp < k; tmp++) {
                        int temp = values[j][values[j].length - 1];
                        for (int l = values[j].length - 1; l >= 1; l--) {
                            values[j][l] = values[j][l - 1];
                        }
                        values[j][0] = temp;
                    }
                }
            } else {
                for (int j = x; j <= n; j = j + x) {
                    for (int tmp = 0; tmp < k; tmp++) {
                        int temp = values[j][0];
                        for (int l = 1; l < values[j].length; l++) {
                            values[j][l - 1] = values[j][l];
                        }
                        values[j][values[j].length - 1] = temp;
                    }
                }
            }
//            System.out.println("rotate");
//            for (int[] v : values) {
//                System.out.println(Arrays.toString(v));
//            }
            visited = new boolean[values.length][values[0].length];
            same = false;

            for (int j = 1; j <= n; j++) {
                for (int l = 0; l < m; l++) {
                    if (values[j][l] != -10 && !visited[j][l]) {
                        bfs(values, j, l);
                    }
                }
            }

            if (!same) {
                double sum = 0;
                int num = 0;
                for (int j = 1; j <= n; j++) {
                    for (int l = 0; l < m; l++) {
                        if (values[j][l] == -10) {
                            continue;
                        }
                        sum += values[j][l];
                        num++;
                    }
                }
//                System.out.println("여기");
                if(num == 0){
                    break;
                }
                double avg = sum / num;
//                System.out.println("avg = " +avg);
                for (int j = 1; j <= n; j++) {
                    for (int l = 0; l < m; l++) {
                        if (values[j][l] == -10) {
                            continue;
                        }
                        values[j][l] = values[j][l] > avg ?
                                values[j][l] - 1 :
                                values[j][l] == avg ? values[j][l] : values[j][l] + 1;
                    }
                }
            }
//            System.out.println("after");
//            for (int[] v : values) {
//                System.out.println(Arrays.toString(v));
//            }
//            System.out.println();
        }

        int answer = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < m; j++) {
                if (values[i][j] != -10) {
                    answer += values[i][j];
                }
            }

        }
        System.out.println(answer);
    }

    static boolean same;
    static boolean[][] visited;

    static void bfs(int[][] values, int row, int col) {

        LinkedList<int[]> queue = new LinkedList<>();
        queue.add(new int[]{row, col});
        int val = values[row][col];

        List<int[]> tmpList = new ArrayList<>();
        while (!queue.isEmpty()) {
            int[] poll = queue.poll();
            int r = poll[0];
            int c = poll[1];

            if (visited[r][c]) {
                continue;
            }
            if (values[r][c] != val) {
                continue;
            }
            visited[r][c] = true;
            tmpList.add(new int[]{r, c});

            for (int i = 0; i < dir.length; i++) {
                int[] d = dir[i];
                int er = d[0] + r;
                int ec = d[1] + c;

                if (er == 0 || er == visited.length) {
                    continue;
                }

                if (ec == -1) {
                    ec = visited[0].length - 1;
                } else if (ec == visited[0].length) {
                    ec = 0;
                }
                queue.add(new int[]{er, ec});
            }
        }
        if (tmpList.size() >= 2) {
            same = true;
            tmpList.forEach(arr -> values[arr[0]][arr[1]] = -10);
        }
    }
}