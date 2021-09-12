package back_tracking;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BOJ_1062 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int n = arr[0];
        int k = arr[1];

        if (k < 5) {
            System.out.println(0);
            return;
        }
        List<String> list = new ArrayList<>();

        int[] chrArr = new int[150];

        chrArr['a'] = 1;
        chrArr['n'] = 1;
        chrArr['t'] = 1;
        chrArr['i'] = 1;
        chrArr['c'] = 1;
        for (int i = 0; i < n; i++) {
            String s = br.readLine();
            char[] c = s.toCharArray();
            Arrays.sort(c);
            list.add(new String(c));
        }

        backTracking(list, chrArr, 0, k - 5, 'a');
        System.out.println(max);
    }

    static int max = 0;

    static void backTracking(List<String> list, int[] als, int n, int num, char index) {
        if (n == num) {
            int cnt = 0;
            for (int i = 0; i < list.size(); i++) {
                String word = list.get(i);

                char first = ' ';
                boolean read = true;
                for (int j = 0; j < word.length(); j++) {
                    if (word.charAt(j) == first) {
                        continue;
                    }
                    first = word.charAt(j);

                    if (als[first] == 0) {
                        read = false;
                        break;
                    }
                }
                if (read) {
                    cnt++;
                }
            }
            if (cnt > max) {
                max = cnt;
            }
            return;
        }
        for (char c = (char) (index + 1); c <= 'z'; c++) {
            if (c == 'a' || c == 'n' || c == 'i' || c == 't' || c == 'c') {
                continue;
            }
            als[c]++;
            backTracking(list, als, n + 1, num,c);
            als[c]--;
        }
    }
}