package min_cost_tree;

import java.util.Arrays;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class boj_9372 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testCase = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < testCase; i++) {
            int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            int n = arr[0];
            int m = arr[1];
            for(int j=0;j<m;j++){
                br.readLine();
            }

            System.out.println(n-1);
        }
    }
}