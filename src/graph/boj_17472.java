package graph;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class boj_17472 {
    static int[][] dp;
    static int land = 2;
    static PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o[2]));
    static int[] roots;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int n = arr[0];
        int m = arr[1];

        int[][] map = new int[n][m];

        for (int i = 0; i < n; i++) {
            map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        makeLand(map);

        roots = new int[land];
        for (int i = 2; i < roots.length; i++) {
            roots[i] = i;
        }
        dp = new int[land][land];

        for (int[] ints : dp) {
            Arrays.fill(ints, 1000000);
        }
        getDistanceBetweenLands(map);

        int ans = 0;
        for (int i = 2; i < dp.length; i++) {
            for (int j = 2; j < dp.length; j++) {
                if (dp[i][j] >= 2 && dp[i][j] != 1000000) {
                    pq.add(new int[]{i, j, dp[i][j]});
                }
            }
        }
//        System.out.println("===== dp =====");
//        for (int[] ints : dp) {
//            System.out.println(Arrays.toString(ints));
//        }
//        System.out.println();
        while (!pq.isEmpty()) {
            int[] poll = pq.poll();

            int start = poll[0];
            int end = poll[1];
            int dist = poll[2];
//            System.out.println(start + " " + end + " " + dist + " " + ans);
//
            if (find(roots, start) == find(roots, end) || dist == 1) {
                continue;
            }
            union(roots, start, end);

            ans += dist;
        }

//        System.out.println("=====map=====");
//        for (int[] ints : map) {
//            System.out.println(Arrays.toString(ints));
//        }
//        System.out.println();


        for (int i = 2; i < land; i++) {
//            System.out.println(find(roots, i));
            if (find(roots, i) != 2) {
                System.out.println(-1);
                return;
            }
        }
        System.out.println(ans);
    }

    static int find(int[] roots, int a) {
        if (roots[a] == a) {
            return a;
        }
        return roots[a] = find(roots, roots[a]);
    }

    static void union(int[] roots, int a, int b) {
        a = find(roots, a);
        b = find(roots, b);

        if (a != b) {
            if (a > b) {
                roots[a] = b;
            } else {
                roots[b] = a;
            }
        }
    }

    static void getDistanceBetweenLands(int[][] map) {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (map[i][j] >= 2) {
                    function(map, i, j, map[i][j]);
                }
            }
        }
    }

    static void function(int[][] map, int row, int col, int land) {
        int step = 0;

        for (int i = row + 1; i < map.length; i++) {
            if (map[i][col] == land) {
                break;
            }
            if (map[i][col] == 0) {
                step++;
            } else if (map[i][col] >= 2) {
                int anotherLand = map[i][col];
                if (step >= 2 && dp[land][anotherLand] > step) {
                    dp[land][anotherLand] = step;
                }
                break;
            }
        }

        step = 0;
        for (int i = row - 1; i >= 0; i--) {
            if (map[i][col] == land) {
                break;
            }
            if (map[i][col] == 0) {
                step++;
            } else if (map[i][col] >= 2) {
                int anotherLand = map[i][col];
                if (step >= 2 && dp[land][anotherLand] > step) {
                    dp[land][anotherLand] = step;
                }
                break;
            }
        }
        step = 0;
        for (int i = col + 1; i < map[0].length; i++) {
            if (map[row][i] == land) {
                break;
            }
            if (map[row][i] == 0) {
                step++;
            } else if (map[row][i] >= 2) {
                int anotherLand = map[row][i];
                if (step >= 2 && dp[land][anotherLand] > step) {
                    dp[land][anotherLand] = step;
                }
                break;
            }
        }
        step = 0;
        for (int i = col - 1; i >= 0; i--) {
            if (map[row][i] == land) {
                break;
            }
            if (map[row][i] == 0) {
                step++;
            } else if (map[row][i] >= 2) {
                int anotherLand = map[row][i];
                if (step >= 2 && dp[land][anotherLand] > step) {
                    dp[land][anotherLand] = step;
                }
                break;
            }
        }
    }

    static void makeLand(int[][] map) {

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (map[i][j] == 1) {
                    bfs(map, i, j);
                }
            }
        }
    }

    static void bfs(int[][] map, int row, int col) {
        Queue<int[]> queue = new LinkedList<>();

        ((LinkedList<int[]>) queue).add(new int[]{row, col});

        int[][] direction = new int[][]{
                {0, 1}, {1, 0}, {0, -1}, {-1, 0}
        };

        boolean[][] visited = new boolean[map.length][map[0].length];
        while (!queue.isEmpty()) {
            int[] poll = queue.poll();

            int r = poll[0];
            int c = poll[1];

            if (visited[r][c]) {
                continue;
            }
            visited[r][c] = true;
            map[r][c] = land;
            for (int i = 0; i < direction.length; i++) {
                int[] dir = direction[i];

                int eR = dir[0] + r;
                int eC = dir[1] + c;

                if (!((eR >= 0 && eR < map.length) && (eC >= 0 && eC < map[0].length))
                        || visited[eR][eC] || map[eR][eC] == 0) {
                    continue;
                }
                queue.add(new int[]{eR, eC});
            }
        }
        land++;
    }
}
