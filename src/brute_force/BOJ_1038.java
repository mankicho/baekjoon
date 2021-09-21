package brute_force;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ_1038 {
    static int[][] dir = new int[][]{
            {0, 1}, {1, 0}, {0, -1}, {-1, 0}
    };

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int cnt = 0;

        long start = 0;

        StringBuilder tmp = new StringBuilder();
        while (cnt <= n && start < 9876543211L) {

            tmp.append(start);

//            new Scanner(System.in).nextLine();
            int len = len(start);

            char c = '9' + 1;
            boolean find = true;
            int index = 0;
            for (int i = 0; i < tmp.length(); i++) {
                if (tmp.charAt(i) < c) {
                    c = tmp.charAt(i);
                } else {
                    find = false;
                    index = i;
                    break;
                }
            }
            if (find) {
                if (cnt == n) {
                    System.out.println(start);
                    return;
                }
                cnt++;
            } else {
                int pow = pow(tmp.length() - index);

                start = start + pow - (start % pow);
                start--;
            }
            for (int i = 0; i < len; i++) {
                tmp.deleteCharAt(tmp.length() - 1);
            }

            start++;
        }
        System.out.println(-1);
    }

    static int len(long val) {
        int cnt = 0;
        if (val == 0) {
            return 1;
        }
        while (val > 0) {
            val /= 10;
            cnt++;
        }
        return cnt;
    }

    static int pow(int idx) {
        int start = 1;

        for (int i = 0; i < idx; i++) {
            start *= 10;
        }
        return start;
    }
}