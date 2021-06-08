package dp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class boj_2565 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        List<int[]> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            list.add(Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray());
        }

        Collections.sort(list, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });

        int[] tmp = new int[list.size()];

        for (int i = 0; i < tmp.length; i++) {
            tmp[i] = list.get(i)[1];
        }

        int[] dp = new int[tmp.length];
        Arrays.fill(dp, 1);

        for (int i = 0; i < dp.length; i++) {
            int val = tmp[i];
            for (int j = 0; j < i; j++) {
                if (val > tmp[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }

        int max = 0;
        for(int i=0;i<dp.length;i++){
            if(dp[i] > max){
                max = dp[i];
            }
        }
        System.out.println(dp.length-max);
    }


}