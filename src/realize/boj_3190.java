package realize;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class boj_3190 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int k = Integer.parseInt(br.readLine());

        int[][] board = new int[n][n];

        for (int i = 0; i < k; i++) {
            int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            int row = arr[0];
            int col = arr[1];

            board[row - 1][col - 1] = 1;
        }

        int l = Integer.parseInt(br.readLine());

        Map<Integer, String> map = new HashMap<>();
        for (int i = 0; i < l; i++) {
            String line = br.readLine();
            String[] split = line.split(" ");
            map.put(Integer.parseInt(split[0]), split[1]);
        }

        LinkedList<int[]> snake = new LinkedList<>();
        snake.add(new int[]{0, 0});
        int ans = 0;

        int[][] direction = new int[][]{
                {0, 1}, {1, 0}, {0, -1}, {-1, 0}
        };

        int dir = 0;

        LOOP:
        while (true) {
            int[] poll = snake.peekLast();

//            System.out.print(poll[0] + "," + poll[1] + "==> ");

            int row = poll[0] + direction[dir][0];
            int col = poll[1] + direction[dir][1];
            ans++;

            if (!((row >= 0 && row < n) && (col >= 0 && col < n))) {
                break;
            }

            for (int[] arr : snake) {
                if (row == arr[0] && col == arr[1]) {
                    break LOOP;
                }
            }
            snake.add(new int[]{row, col});
            if (board[row][col] == 0) {
                snake.pollFirst();
            }
            board[row][col] = 0;
            if (map.get(ans) != null) {
                String exe = map.get(ans);
                if ("D".equals(exe)) {
                    if (dir == 3) {
                        dir = 0;
                    } else {
                        dir++;
                    }
                } else {
                    if (dir == 0) {
                        dir = 3;
                    } else {
                        dir--;
                    }
                }
            }
//            snake.forEach(ar -> System.out.print(Arrays.toString(ar) + " "));
//            System.out.println();
        }
        System.out.println(ans);

    }
}