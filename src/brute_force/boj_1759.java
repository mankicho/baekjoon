package brute_force;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class boj_1759 {


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        n = arr[0];

        String line = br.readLine();

        String[] split = line.split(" ");

        Arrays.sort(split);

        StringBuilder result = new StringBuilder();

        function(result, split, 0, -1);
        System.out.println(result.toString());
    }

    static int n;

    static int mo = 0;
    static int za = 0;
    static StringBuilder tmp = new StringBuilder();

    static void function(StringBuilder sb, String[] strs, int num, int start) {
        if (num == n) {
            if (mo >= 1 && za >= 2) {
                sb.append(tmp.toString()).append("\n");
            }
            return;
        }

        for (int i = start + 1; i < strs.length; i++) {
            tmp.append(strs[i]);
            boolean moBool = false;
            if (strs[i].equals("a") || strs[i].equals("e") || strs[i].equals("i")
                    || strs[i].equals("o") || strs[i].equals("u")) {
                moBool = true;
                mo++;
            } else {
                za++;
            }
            function(sb, strs, num + 1, i);
            tmp.deleteCharAt(tmp.length() - 1);
            if (moBool) {
                mo--;
            } else {
                za--;
            }
        }
    }
}