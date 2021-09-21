package brute_force;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_1107 {
    static int[][] dir = new int[][]{
            {0, 1}, {-1, 0}, {0, -1}, {1, 0}
    };

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        int[] arr = null;

        if (m > 0) {
            arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        }
        boolean[] visited = new boolean[10];
        for (int i = 0; arr != null && i < arr.length; i++) {
            visited[arr[i]] = true;
        }
        int now = 100;

        int val = function(visited, n);

        int temp = Math.abs(n - val);

        int temp2 = len(val);

        System.out.println(Math.min(temp + temp2, Math.abs(now - n)));
    }

    static int min = 500001;

    static int function(boolean[] visited, int num) {
        int val = -1;
        for (int i = 0; i <= 999999; i++) {
            if (hasBrokenNum(visited, i)) {
                continue;
            }
            if (Math.abs(i - num) < min) {
                min = Math.abs(i - num);
                val = i;
            }
        }
        return val == -1 ? 98765432 : val;
    }

    static int len(int num) {
        int cnt = 0;
        if (num == 0) {
            return 1;
        }
        while (num > 0) {
            num /= 10;
            cnt++;
        }
        return cnt;
    }

    static boolean hasBrokenNum(boolean[] visited, int num) {
        //9876
        if (num == 0) {
            return visited[0];
        }
        while (num > 0) {
            int mod = num % 10;

            if (visited[mod]) {
                return true;
            }

            num /= 10;
        }
        return false;
    }
}