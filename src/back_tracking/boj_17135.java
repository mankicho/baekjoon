package back_tracking;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class boj_17135 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int n = arr[0];
        int m = arr[1];
        int d = arr[2];

        int[][] map = new int[n][m];

        for (int i = 0; i < map.length; i++) {
            map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        backTracking(map, new boolean[m], 0, d, -1);
        System.out.println(max);
    }

    static List<int[]> list = new ArrayList<>();
    static int max = 0;

    static void backTracking(int[][] map, boolean[] visited, int num, int d, int start) {
        if (num == 3) {
            int[][] tmp = new int[map.length][map[0].length];

            for (int i = 0; i < map.length; i++) {
                for (int j = 0; j < map[0].length; j++) {
                    tmp[i][j] = map[i][j];
                }
            }
            function(tmp, d);
            return;
        }

        for (int i = start + 1; i < visited.length; i++) {
            if (visited[i]) {
                continue;
            }

            visited[i] = true;
            int[] gungsu = new int[]{map.length, i};
            list.add(gungsu);
            backTracking(map, visited, num + 1, d, i);
            list.remove(gungsu);
            visited[i] = false;
        }
    }

    static void move(int[][] map) {
        for (int i = map.length - 2; i >= 0; i--) {
            for (int j = 0; j < map[0].length; j++) {
                map[i + 1][j] = map[i][j];
            }
        }
        for (int i = 0; i < map[0].length; i++) {
            map[0][i] = 0;
        }
    }

    static void function(int[][] map, int d) {
        int ans = 0;
        int cnt = 0;

//        list.forEach(arr -> System.out.println(Arrays.toString(arr)));
//        System.out.println("=== first ===");
//        for (int[] ints : map) {
//            System.out.println(Arrays.toString(ints));
//        }
//        System.out.println();
        while (cnt < map.length) {
            List<int[]> tmpList = new ArrayList<>();
            for (int i = 0; i < list.size(); i++) {
                int[] gungsuLoc = list.get(i);
                int row = gungsuLoc[0];
                int col = gungsuLoc[1];
                int enemyRow = -1;
                int enemyCol = -1;
                int min = 1000000;
                for (int j = (map.length - d < 0 ? 0 : map.length - d); j < map.length; j++) {
                    for (int k = 0; k < map[j].length; k++) {
                        if (map[j][k] == 1) {
                            if ((Math.abs(row - j) + Math.abs(col - k)) <= d && (Math.abs(row - j) + Math.abs(col - k)) <= min) {
                                if (Math.abs(row - j) + Math.abs(col - k) == min) {
                                    if (k < enemyCol) {
                                        enemyRow = j;
                                        enemyCol = k;
                                    }
                                } else {
                                    min = Math.abs(row - j) + Math.abs(col - k);
                                    enemyRow = j;
                                    enemyCol = k;
                                }
                            }
                        }
                    }
                }
                if (enemyRow != -1 && enemyCol != -1) {
                    boolean already = false;
                    for (int k = 0; k < tmpList.size(); k++) {
                        int[] enemyLocation = tmpList.get(k);
                        if (enemyLocation[0] == enemyRow && enemyLocation[1] == enemyCol) {
                            already = true;
                            break;
                        }
                    }
                    if (!already) {
                        tmpList.add(new int[]{enemyRow, enemyCol});
                    }
                }
            }

            tmpList.forEach(tl -> {
                int enemyR = tl[0];
                int enemyC = tl[1];

                map[enemyR][enemyC] = 0;
            });
            ans += tmpList.size();
            move(map);
//            for (int[] ints : map) {
//                System.out.println(Arrays.toString(ints));
//            }
//            System.out.print(ans+" :");
//            for(int i=0;i<tmpList.size();i++){
//                System.out.print(Arrays.toString(tmpList.get(i))+" ");
//            }
//            System.out.println();
//
//            System.out.println();
            cnt++;
        }
        if (ans > max) {
            max = ans;
        }
    }

}