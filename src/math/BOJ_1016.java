package math;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_1016 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        long[] arr = Arrays.stream(br.readLine().split(" ")).mapToLong(Long::parseLong).toArray();

        long a = arr[0];
        long b = arr[1];

        int length = (int) (b - a) + 1;
        boolean[] checked = new boolean[length];

        for (long i = 2; i <= Math.sqrt(b); i++) {
            long square = i * i;

            long start = a % square == 0 ? a / square : (a / square) + 1;

            for (long j = start; j * square <= b; j++) {
                checked[(int) ((j * square) - a)] = true;
            }
        }
        int answer = 0;
        for (int i = 0; i < checked.length; i++) {
            if (!checked[i]) {
                answer++;
            }
        }
        System.out.println(answer);

    }
}