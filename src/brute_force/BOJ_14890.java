package brute_force;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_14890 {
    static StringBuilder result = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int n = arr[0];
        int l = arr[1];
        int[][] map = new int[n][n];

        for (int i = 0; i <  n; i++) {
            map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        boolean[][][] visited = new boolean[2][n][n];
        int ans = 0;
        for (int i = 0; i < n; i++) {
            boolean canWalk = true;
            for (int j = 1; j < n; j++) {
                if (Math.abs(map[i][j] - map[i][j - 1]) >= 2) {
                    canWalk = false;
                    break;
                }
                if (map[i][j] == map[i][j - 1]) {
                    continue;
                }
                if (Math.abs(map[i][j] - map[i][j - 1]) == 1) {
                    int num = 0;
                    if (map[i][j] > map[i][j - 1]) {
                        int tmpJ = j - 1;
                        int tmpVal = map[i][tmpJ];
                        while (tmpJ >= 0 && !visited[0][i][tmpJ] && map[i][tmpJ] == tmpVal && num < l) {
                            tmpJ--;
                            num++;
                        }
                        if (num == l) {
                            for (int k = j - 1; k > j - 1 - l; k--) {
                                visited[0][i][k] = true;
                            }
                            continue;
                        }
                    } else {
                        int tmpJ = j;
                        int tmpVal = map[i][tmpJ];
                        while (tmpJ < n && !visited[0][i][tmpJ] && map[i][tmpJ] == tmpVal && num < l) {
                            tmpJ++;
                            num++;
                        }
                        if (num == l) {
                            for (int k = j; k < j + l; k++) {
                                visited[0][i][k] = true;
                            }
                            j += l - 1;
                            continue;
                        }
                    }
                    canWalk = false;
                }

            }
            if (canWalk) {
                ans++;
            }
        }

        for (int j = 0; j < n; j++) {
            boolean canWalk = true;
            for (int i = 1; i < n; i++) {
                if (Math.abs(map[i][j] - map[i - 1][j]) >= 2) {
                    canWalk = false;
                    break;
                }
                if (map[i][j] == map[i - 1][j]) {
                    continue;
                }
                if (Math.abs(map[i][j] - map[i - 1][j]) == 1) {
                    int num = 0;
                    if (map[i][j] > map[i - 1][j]) {
                        int tmpI = i - 1;
                        int tmpVal = map[tmpI][j];
                        while (tmpI >= 0 && !visited[1][tmpI][j] && map[tmpI][j] == tmpVal && num < l) {
                            tmpI--;
                            num++;
                        }
                        if (num == l) {
                            for (int k = i - 1; k > i - 1 - l; k--) {
                                visited[1][k][j] = true;
                            }
                            continue;
                        }
                    } else {
                        int tmpI = i;
                        int tmpVal = map[tmpI][j];
                        while (tmpI < n && !visited[1][tmpI][j] && map[tmpI][j] == tmpVal && num < l) {
                            tmpI++;
                            num++;
                        }
                        if (num == l) {
                            for (int k = i; k < i + l; k++) {
                                visited[1][k][j] = true;
                            }
                            i += l - 1;
                            continue;
                        }
                    }
                    canWalk = false;
                }

            }
            if (canWalk) {
                ans++;
            }
        }
        System.out.println(ans);
    }
}