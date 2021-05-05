package sort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class boj_1427 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();

        char[] chars = s.toCharArray();

        int[] arr = new int[10];
        for (int i = 0; i < chars.length; i++) {
            arr[Character.digit(chars[i], 10)]++;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = arr.length - 1; i >= 0; i--) {
            if (arr[i] != 0) {
                while (arr[i] > 0) {
                    sb.append(i);
                    arr[i]--;
                }
            }
        }
        System.out.println(sb.toString());

    }

}
