package greedy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_1083 {

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int m = Integer.parseInt(br.readLine());

        int cnt = 0;
        int start = 0;
        boolean[] visited = new boolean[arr.length];
        while (start < arr.length && cnt < m) {
            int max = arr[start];
            int index = start;
            int[] temp = arr.clone();
            for (int i = start + 1; i < arr.length; i++) {
                if (visited[i]) {
                    continue;
                }
                if (arr[i] > max) {
                    max = arr[i];
                    index = i;
                }
            }

            if (cnt + (index - start) <= m) {
                temp[start] = max;
                for (int i = start + 1; i <= index; i++) {
                    temp[i] = arr[i - 1];
                }
                cnt += (index - start);
                start++;
                arr = Arrays.copyOfRange(temp, 0, temp.length);
                visited = new boolean[arr.length];
            } else {
                boolean allVisited = true;
                for (int i = 0; i < visited.length; i++) {
                    if (!visited[i]) {
                        allVisited = false;
                        break;
                    }
                }
                if (allVisited) {
                    start++;
                } else {
                    visited[index] = true;
                }
            }
        }
        StringBuilder result = new StringBuilder();
        for (int i = 0; i <arr.length ; i++) {
            result.append(arr[i]).append(" ");

        }
        System.out.println(result.toString());
    }


}