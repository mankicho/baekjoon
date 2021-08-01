package greedy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

class boj_2437 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        br.readLine();

        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        Arrays.sort(arr);

        int start = 0;
        if (arr[0] > 1) {
            System.out.println(1);
            return;
        } else {
            start = arr[0];
        }

        for (int i = 1; i < arr.length; i++) {
            if (arr[i] <= start + 1) {
                start += arr[i];
            } else {
                break;
            }
        }
        System.out.println(start + 1);

    }
}
