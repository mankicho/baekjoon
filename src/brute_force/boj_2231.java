package brute_force;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class boj_2231 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();

        int N = Integer.parseInt(line);

        int k = 10;

        int num = 1;
        while (N / k != 0) {
            num++;
            k *= 10;
        }

        // 389
        int answer = N - num * 9;
        int tmpAnswer = answer + 1;
        int tmpAnswer2 = 0;
        int tmp = 10;
        int tmp2 = 1;
        int cnt = 0;
        while (answer < N) {
            tmpAnswer2 = answer;
            while (cnt < num) {
                answer = answer + (tmpAnswer2 % tmp) / tmp2;
                tmp = tmp * 10;
                tmp2 = tmp2 * 10;
                cnt++;
            }
            cnt = 0;
            tmp = 10;
            tmp2 = 1;
            tmpAnswer++;
            if (answer == N) {
                break;
            }
            answer = tmpAnswer;
        }
        System.out.println(tmpAnswer2);
    }
}
