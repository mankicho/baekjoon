package greedy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class BOJ_2212 {
    static Map<Integer, Map<Integer, Integer>> dirMap = new HashMap<>();

    static int[][] dir = new int[][]{
            {0, 1}, {1, 0}, {0, -1}, {-1, 0}
    };

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int k = Integer.parseInt(br.readLine());

        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        Arrays.sort(arr);
        int[] diff = new int[arr.length - 1];

        for (int i = 0; i < arr.length - 1; i++) {
            diff[i] = arr[i + 1] - arr[i];
        }

        Arrays.sort(diff);

        int num = n - k;

        int answer = 0;
        for (int i = 0; i < diff.length && i < num; i++) {
            answer += diff[i];
        }
        System.out.println(answer);
    }


}