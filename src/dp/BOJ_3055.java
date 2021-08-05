package dp;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_3055 {
    static int[][] direction = new int[][]{
            {0, 1}, {1, 0}, {0, -1}, {-1, 0}
    };

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int r = arr[0];
        int c = arr[1];

        char[][] map = new char[r][c];

        int[] beeber = {};
        int[] start = {};
        List<int[]> waters = new ArrayList<>();
        for (int i = 0; i < map.length; i++) {
            map[i] = br.readLine().toCharArray();
            for (int j = 0; j < map[i].length; j++) {
                if (map[i][j] == 'D') {
                    beeber = new int[]{i, j};
                }
                if (map[i][j] == 'S') {
                    start = new int[]{i, j};
                }
                if (map[i][j] == '*') {
                    waters.add(new int[]{i, j});
                }
            }
        }

        int[][] watersDP = waterBFS(waters, map);

        going(map, start, beeber, watersDP);

    }

    static void going(char[][] map, int[] start, int[] end, int[][] watersDP) {
        int endRow = end[0];
        int endCol = end[1];

        int startRow = start[0];
        int startCol = start[1];
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o[2]));
        pq.add(new int[]{startRow, startCol, 0});
        boolean[][] visited = new boolean[map.length][map[0].length];

        boolean arrived = false;
        int ans = 0;
        while (!pq.isEmpty()) {
            int[] now = pq.poll();

            int r = now[0];
            int c = now[1];
            int nowStep = now[2];

            if (visited[r][c]) {
                continue;
            }
            visited[r][c] = true;
            if (r == endRow && c == endCol) {
                arrived = true;
                ans = nowStep;
                break;
            }
            for (int i = 0; i < direction.length; i++) {
                int[] dir = direction[i];

                int endR = dir[0] + r;
                int endC = dir[1] + c;

                if (!((endR >= 0 && endR < map.length) && (endC >= 0 && endC < map[0].length))
                        || map[endR][endC] == 'X' || visited[endR][endC]) {
                    continue;
                }

                if (nowStep + 1 < watersDP[endR][endC]) {
                    pq.add(new int[]{endR, endC, nowStep + 1});
                }
            }
        }

        System.out.println(arrived ? ans : "KAKTUS");
    }

    static int[][] waterBFS(List<int[]> waters, char[][] map) {
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o[2]));
        waters.forEach(water -> pq.add(new int[]{water[0], water[1], 0}));

        int[][] dp = new int[map.length][map[0].length];
        for (int[] ints : dp) {
            Arrays.fill(ints, 1000000);
        }

        for (int[] water : waters) {
            dp[water[0]][water[1]] = 0;
        }


        while (!pq.isEmpty()) {
            int[] now = pq.poll();

            int r = now[0];
            int c = now[1];

            for (int i = 0; i < direction.length; i++) {
                int[] dir = direction[i];

                int endR = dir[0] + r;
                int endC = dir[1] + c;

                if (!((endR >= 0 && endR < map.length) && (endC >= 0 && endC < map[0].length))
                        || map[endR][endC] == 'X' || map[endR][endC] == 'D') {
                    continue;
                }

                if (dp[endR][endC] > dp[r][c] + 1) {
                    dp[endR][endC] = dp[r][c] + 1;
                    pq.add(new int[]{endR, endC, dp[endR][endC]});
                }
            }
        }
        return dp;
    }


}

