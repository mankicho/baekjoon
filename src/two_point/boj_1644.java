package two_point;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class boj_1644 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int answer = 0;
        boolean[] visited = new boolean[n + 1];
        int sum = 0;
        int front = 2;
        int back = 2;
        for (int i = 2; i <= n; i++) {
            front = i;
            if (visited[i]) {
                continue;
            }

            for (int j = 2; j <= n / i; j++) {
                visited[j * i] = true;
            }
            sum += i;
            if (sum == n) {
                answer++;
            } else if (sum < n) {
                continue;
            } else {
                while (back < front) {
                    if (visited[back]) {
                        back++;
                        continue;
                    }
                    sum -= back;
                    back++;
                    if (sum == n) {
                        answer++;
                    }
                    if (sum < n) {
                        break;
                    }
                }
            }
        }
        System.out.println(answer);
    }
}