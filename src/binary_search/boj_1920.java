package binary_search;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class boj_1920 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int m = Integer.parseInt(br.readLine());

        int[] arr2 = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        Arrays.sort(arr);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arr2.length; i++) {
            int val = arr2[i];

            int val2 = Arrays.binarySearch(arr, val);

            if (val2 < 0) {
                sb.append("0").append("\n");
            } else {
                sb.append("1").append("\n");
            }
        }
        System.out.println(sb.toString());
    }
}
