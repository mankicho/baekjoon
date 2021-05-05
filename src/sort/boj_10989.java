package sort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class boj_10989 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String firstLine = br.readLine();
        int N = Integer.parseInt(firstLine);

        int[] arr = new int[10000001];

        for (int i = 0; i < N; i++) {
            arr[Integer.parseInt(br.readLine())]++;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != 0) {
                while (arr[i] > 0) {
                    sb.append(i).append("\n");
                    arr[i]--;
                }
            }
        }
        System.out.println(sb.toString());
    }
}
