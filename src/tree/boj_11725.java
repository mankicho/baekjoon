package tree;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class boj_11725 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        List<List<Integer>> list = new ArrayList<>();

        Map<Integer, Integer> parentMap = new HashMap<>();

        for (int i = 0; i <= n; i++) {
            list.add(new ArrayList<>());
        }
        for (int i = 0; i < n - 1; i++) {
            int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            int first = arr[0];
            int second = arr[1];

            list.get(first).add(second);
            list.get(second).add(first);

        }

        bfs(list,parentMap);

        StringBuilder sb = new StringBuilder();
        for(int i=2;i<=n;i++){
            sb.append(parentMap.get(i)).append("\n");
        }
        System.out.println(sb.toString());
    }

    static void bfs(List<List<Integer>> list, Map<Integer, Integer> parentMap) {
        boolean[] visited = new boolean[list.size()];

        LinkedList<Integer> queue = new LinkedList<>();

        queue.add(1);

        while (!queue.isEmpty()) {
            int now = queue.poll();

            if (visited[now]) {
                continue;
            }

            visited[now] = true;

            List<Integer> nexts = list.get(now);

            for (int i = 0; i < nexts.size(); i++) {
                int next = nexts.get(i);

                if(visited[next]){
                    continue;
                }
                parentMap.put(next, now);
                queue.add(next);
            }
        }
    }


}