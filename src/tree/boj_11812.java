package tree;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

class boj_11812 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        long[] arr = Arrays.stream(br.readLine().split(" ")).mapToLong(Long::parseLong).toArray();

        long k = arr[1];
        long q = arr[2];


        StringBuilder result = new StringBuilder();
        for (int i = 0; i < q; i++) {
            arr = Arrays.stream(br.readLine().split(" ")).mapToLong(Long::parseLong).toArray();

            long a = arr[0];
            long b = arr[1];

            int ans = 0;
            if(k==1){
                result.append(Math.abs(b-a)).append("\n");
                continue;
            }
            while (a != b) {
                if (a > b) {
                    ans++;
                    a += (k - 2);
                    a /= k;
                    if (a == 0) {
                        a++;
                    }
                } else {
                    ans++;
                    b += (k - 2);
                    b /= k;
                    if (b == 0) {
                        b++;
                    }
                }
            }
            result.append(ans).append("\n");
        }
        System.out.println(result.toString());
    }
}
