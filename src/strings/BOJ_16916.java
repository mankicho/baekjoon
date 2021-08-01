package strings;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ_16916 {

    static int[] pi;

    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String first = br.readLine();
        String second = br.readLine();

        pi = new int[second.length()];

        int j = 0;
        // ABABACA
        for (int i = 1; i < second.length(); i++) {

            while (j > 0 && second.charAt(i) != second.charAt(j)) {
                j = pi[j - 1];
            }

            if (second.charAt(i) == second.charAt(j)) {
                pi[i] = ++j;
            }
        }

        j = 0;
        boolean contain = false;
        for (int i = 0; i < first.length(); i++) {

            while (j > 0 && first.charAt(i) != second.charAt(j)) {
                j = pi[j - 1];
            }

            if (first.charAt(i) == second.charAt(j)) {
                ++j;
                if (j == second.length()) {
                    contain = true;
                    break;
                }
            }
        }

        if (contain) {
            System.out.println(1);
        } else {
            System.out.println(0);
        }
    }
}

