package brute_force;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BOJ_1007 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int testcase = Integer.parseInt(br.readLine());
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < testcase; i++) {
            min = 10000000;
            int n = Integer.parseInt(br.readLine());

            List<int[]> list = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
                list.add(arr);
            }

            function(list, new boolean[n], 1, n / 2, 0);
            result.append(min).append("\n");
        }
        System.out.println(result.toString());
    }

    static double min;

    static void function(List<int[]> points, boolean[] visited, int num, int n, int index) {
        visited[index] = true;
        if (num == n) {
            long x = 0;
            long y = 0;
            for (int i = 0; i < visited.length; i++) {
                int[] point = points.get(i);
                if (visited[i]) {
                    x += point[0];
                    y += point[1];
                } else {
                    x -= point[0];
                    y -= point[1];
                }
            }

            double distance = Math.sqrt(x * x + y * y);
            if (distance < min) {
                min = distance;
            }
            return;
        }
        for (int i = index; i < points.size(); i++) {
            if (!visited[i]) {
                function(points, visited, num + 1, n, i);
                visited[i] = false;
            }
        }
    }
}