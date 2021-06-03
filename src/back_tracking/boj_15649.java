package back_tracking;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class boj_15649 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int n = arr[0];
        int m = arr[1];

        boolean[] visited = new boolean[n + 1];
        backTracking(n, m, "", visited);
        System.out.println(result.toString());
    }

    static StringBuilder result = new StringBuilder();

    static int num = 0;

    static void backTracking(int n, int m, String sb, boolean[] visited) {
        if (num == m) {
            result.append(sb).append("\n");
            return;
        }
        for (int i = 1; i <= n; i++) {
            if (visited[i]) {
                continue;
            }
            visited[i] = true;
            sb += i + " ";
            num++;
            backTracking(n, m, sb, visited);
            sb = sb.substring(0, sb.length() - 2);
            visited[i] = false;
            num--;
        }
    }
}