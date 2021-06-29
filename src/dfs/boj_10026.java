package dfs;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class boj_10026 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


        int n = Integer.parseInt(br.readLine());

        char[][] chars = new char[n][n];

        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            chars[i] = line.toCharArray();
        }

        boolean[][] visited = new boolean[n][n];

        int ans1 = 0;
        while (true) {
            int[] tmp = function(visited);

            if (tmp[0] == -1 && tmp[1] == -1) {
                break;
            }

            c = chars[tmp[0]][tmp[1]];
            dfs(chars, visited, 0, tmp[0], tmp[1]);
            ans1++;
        }

        visited = new boolean[n][n];

        int ans2 = 0;
        while (true) {
            int[] tmp = function(visited);

            if (tmp[0] == -1 && tmp[1] == -1) {
                break;
            }
            c = chars[tmp[0]][tmp[1]];

            dfs(chars, visited, 1, tmp[0], tmp[1]);
            ans2++;
        }
        System.out.println(ans1 + " " + ans2);
    }

    static char c;

    static void dfs(char[][] chars, boolean[][] visited, int type, int row, int col) {
        if (visited[row][col]) {
            return;
        }
        visited[row][col] = true;
        if (type == 0) {
            if (chars[row][col] == c) {
                if (row + 1 < chars.length) {
                    dfs(chars, visited, type, row + 1, col);
                }
                if (col + 1 < chars.length) {
                    dfs(chars, visited, type, row, col + 1);
                }
                if (row - 1 >= 0) {
                    dfs(chars, visited, type, row - 1, col);
                }
                if (col - 1 >= 0) {
                    dfs(chars, visited, type, row, col - 1);
                }
            } else {
                visited[row][col] = false;
                return;
            }
        } else {
            if (chars[row][col] == c || (c == 'R' && chars[row][col] == 'G')
                    || (c == 'G' && chars[row][col] == 'R')) {
                if (row + 1 < chars.length) {
                    dfs(chars, visited, type, row + 1, col);
                }
                if (col + 1 < chars.length) {
                    dfs(chars, visited, type, row, col + 1);
                }

                if (row - 1 >= 0) {
                    dfs(chars, visited, type, row - 1, col);
                }
                if (col - 1 >= 0) {
                    dfs(chars, visited, type, row, col - 1);
                }
            } else {
                visited[row][col] = false;
                return;
            }
        }
    }

    static int[] function(boolean[][] visited) {
        int[] answer = new int[]{-1, -1};
        LOOP:
        for (int i = 0; i < visited.length; i++) {
            for (int j = 0; j < visited.length; j++) {
                if (!visited[i][j]) {
                    answer[0] = i;
                    answer[1] = j;
                    break LOOP;
                }
            }
        }
        return answer;
    }


}