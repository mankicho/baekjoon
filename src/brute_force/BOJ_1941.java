package brute_force;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class BOJ_1941 {

    static int[][] dir = new int[][]{
            {0, 1}, {1, 0}, {0, -1}, {-1, 0}
    };

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        char[][] chars = new char[5][5];

        for (int i = 0; i < 5; i++) {
            chars[i] = br.readLine().toCharArray();
        }

        backTracking(chars, 0, -1, new boolean[5][5], new ArrayList<>(), 0);
        System.out.println(answer);

    }

    static int answer = 0;


    static void backTracking(char[][] chars, int row, int col, boolean[][] visited, List<int[]> list, int y) {
        if (y > 3) {
            return;
        }
        if (list.size() == 7) {

            LinkedList<int[]> queue = new LinkedList<>();
            int[] tmp = list.get(0);
            queue.add(tmp);

            boolean[][] tmpVisited = new boolean[5][5];
            int cnt = 0;
            while (!queue.isEmpty()) {
                int[] poll = queue.poll();

                int r = poll[0];
                int c = poll[1];
                if (tmpVisited[r][c]) {
                    continue;
                }
                tmpVisited[r][c] = true;
                cnt++;
                for (int i = 0; i < 4; i++) {
                    int[] d = dir[i];

                    int er = d[0] + r;
                    int ec = d[1] + c;

                    if (er >= 0 && er < 5 && ec >= 0 && ec < 5
                            && !tmpVisited[er][ec] && visited[er][ec]) {
                        queue.add(new int[]{er, ec});
                    }
                }
            }

            if (cnt == 7) {
                answer++;
            }
            return;
        }
        for (int i = row; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (i == row && j <= col) {
                    continue;
                }
                if (!visited[i][j]) {
                    int[] tmp = new int[]{i, j};
                    visited[i][j] = true;
                    list.add(tmp);
                    backTracking(chars, i, j, visited, list, chars[i][j] == 'Y' ? y + 1 : y);
                    visited[i][j] = false;
                    list.remove(tmp);
                }
            }
        }
    }
}