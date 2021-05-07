package sort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class boj_11650 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String firstline = br.readLine();

        int N = Integer.parseInt(firstline);

        int[][] arr = new int[N][2];

        for (int i = 0; i < N; i++) {
            String[] split = br.readLine().split(" ");
            arr[i] = Arrays.stream(split).mapToInt(Integer::parseInt).toArray();
        }

        Arrays.sort(arr, (o1, o2) -> {
            if (o1[0] == o2[0]) {
                return o1[1] - o2[1];
            } else {
                return o1[0] - o2[0];
            }
        });

        Arrays.stream(arr).forEach(array -> System.out.println(Arrays.toString(array)));
    }
}
