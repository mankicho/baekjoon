package queue;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class BOJ_3078 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int n = arr[0];
        int k = arr[1];

        List<List<Integer>> list = new ArrayList<>();
        for (int i = 0; i <= 20; i++) {
            list.add(new ArrayList<>());
        }
        for (int i = 0; i < n; i++) {
            list.get(br.readLine().length()).add(i);
        }

        long[] dp = new long[300001];

        for (int i = 1; i < dp.length; i++) {
            dp[i] = dp[i - 1] + i - 1;
        }
        long ans = 0L;

        for (int i = 2; i <= 20; i++) {
            List<Integer> friends = list.get(i);
            LinkedList<Integer> queue = new LinkedList<>();

            for (int j = 0; j < friends.size(); j++) {
                if (queue.isEmpty()) {
                    queue.add(friends.get(j));
                } else {
                    if (friends.get(j) - queue.peek() <= k) {
                        queue.add(friends.get(j));
                    } else {
                        ans += dp[queue.size()];
                        while (!queue.isEmpty() && friends.get(j) - queue.peek() > k) {
                            queue.poll();
                        }
                        ans -= dp[queue.size()];
                        queue.add(friends.get(j));
                    }
                }
            }
            ans += dp[queue.size()];
        }
        System.out.println(ans);
    }


}