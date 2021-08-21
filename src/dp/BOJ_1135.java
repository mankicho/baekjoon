package dp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_1135 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] dp = new int[n];
        List<List<Integer>> childs = new ArrayList<>();

        for (int i = 0; i <= n; i++) {
            childs.add(new ArrayList<>());
        }

        for (int i = 0; i < arr.length; i++) {
            int parent = arr[i];
            if (parent == -1) {
                continue;
            }
            childs.get(parent).add(i);
        }

        System.out.println(dfs(childs, 0));
    }

    static int dfs(List<List<Integer>> childs, int num) {
        List<Integer> nexts = childs.get(num);
        if (nexts.size() == 0) {
            return 0;
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
        for (int i = 0; i < nexts.size(); i++) {
            int next = nexts.get(i);
            int cnt = dfs(childs, next);
            pq.add(cnt);
        }
        int max = 0;
        int cnt = 1;
        while (!pq.isEmpty()) {
            int poll = pq.poll();
            max = Math.max(max, poll + cnt);
            cnt++;
        }
        return max;
    }
}