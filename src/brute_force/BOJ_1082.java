package brute_force;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_1082 {

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int money = Integer.parseInt(br.readLine());

        for (int i = arr.length - 1; i >= 1; i--) {
            max = "0";
            backTracking(arr, i, money, new StringBuilder());
            if (max.length() > tmpMax.length()) {
                tmpMax = max;
            } else if (max.length() == tmpMax.length()) {
                if (max.compareTo(tmpMax) > 0) {
                    tmpMax = max;
                }
            }
        }
        System.out.println(tmpMax);
    }

    static String max;
    static String tmpMax = "0";
    static int modMax = 0;

    static void backTracking(int[] arr, int index, int money, StringBuilder sb) {
        int val = arr[index];

        if (val > money) {
            return;
        }
        int mod = money / val;
        if (mod == 0) {
            return;
        }
        for (int i = 0; i < mod; i++) {
            sb.append(index);
        }
        if (sb.length() > max.length()) {
            max = sb.toString();
        } else if (sb.length() == max.length()) {
            if (sb.toString().compareTo(max) > 0) {
                max = sb.toString();
            }
        }

        money -= val * mod;
        for (int i = index - 1; i >= 0; i--) {
            if (arr[i] >= val) {
                continue;
            }
            for (int j = 0; j < mod; j++) {
                StringBuilder tmp = new StringBuilder();
                for (int k = 0; k < j; k++) {
                    tmp.append(sb.charAt(sb.length() - 1));
                    sb.deleteCharAt(sb.length() - 1);
                }
                backTracking(arr, i, money + (j * val), sb);
                sb.append(tmp.toString());
            }
        }

        for (int i = 0; i < mod; i++) {
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}