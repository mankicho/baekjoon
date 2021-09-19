package brute_force;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_17297 {

    // 17297
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


        int m = Integer.parseInt(br.readLine());
        m--;
        String s = "Messi Gimossi";
        int[] arr = new int[40];
        arr[0] = 0;
        arr[1] = 5;
        arr[2] = 13;
        for (int i = 3; i <= 39; i++) {
            arr[i] = arr[i - 1] + arr[i - 2] + 1;
        }
        for (int i = 39; i >= 2; i--) {
            if (m >= arr[i]) {
                m -= (arr[i] + 1);
            }
        }

        System.out.println(m == -1 || m == 5 ? "Messi Gimossi" : s.charAt(m));

    }
}