package solve.class5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class boj_1202 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int n = arr[0];
        int k = arr[1];

        List<List<Integer>> list = new ArrayList<>();

        for (int i = 0; i <= 1000000; i++) {
            list.add(new ArrayList<>());
        }

        PriorityQueue<Integer> bag = new PriorityQueue<>();
        for (int i = 0; i < n; i++) {
            int[] tmp = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int a = tmp[0];
            int b = tmp[1];

            list.get(a).add(b);
        }

        for (int i = 0; i < k; i++) {
            bag.add(Integer.parseInt(br.readLine()));
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());

        long ans = 0;
        for (int i = 0; i <= 1000000; i++) {
            if (bag.isEmpty()) {
                break;
            }
            int nowBag = bag.peek();
            List<Integer> tmpList = list.get(i);

            if (!tmpList.isEmpty()) {
                pq.addAll(tmpList);
            }

            if (nowBag == i) {
                if (!pq.isEmpty()) {
                    ans += pq.poll();
                    bag.poll();
                }

                while (!bag.isEmpty() && nowBag == bag.peek()) {
                    if (!pq.isEmpty()) {
                        ans += pq.poll();
                    }
                    bag.poll();
                }
            }
        }
        while (!bag.isEmpty()) {
            bag.poll();
            if (!pq.isEmpty()) {
                ans += pq.poll();
            }
        }
        System.out.println(ans);
    }

}