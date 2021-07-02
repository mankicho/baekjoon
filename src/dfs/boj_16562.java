package dfs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class boj_16562 {

    static int myPrice;
    static int ans = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int n = arr[0];
        int m = arr[1];
        int k = arr[2];
        arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        List<List<Integer>> list = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            list.add(new ArrayList<>());
        }
        for (int i = 0; i < m; i++) {
            int[] tmp = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            int a = tmp[0];
            int b = tmp[1];

            list.get(a).add(b);
            list.get(b).add(a);
        }

        myPrice = k;
        ans = 0;
        boolean allFriend = false;
        boolean[] visited = new boolean[n + 1];
        while (true) {
            int num = func(visited, arr);
            if (num == -1) {
                allFriend = true;
                break;
            }

            if (myPrice < arr[num - 1]) {
                break;
            }
            myPrice -= arr[num - 1];
            ans += arr[num - 1];
            dfs(list, visited, num);
        }

        if(allFriend){
            System.out.println(ans);
        }else{
            System.out.println("Oh no");
        }
    }

    static int func(boolean[] visited, int[] arr) {
        int min = 10000001;
        int idx = -1;
        for (int i = 1; i < visited.length; i++) {
            if (!visited[i]) {
                if (arr[i - 1] < min) {
                    min = arr[i - 1];
                    idx = i;
                }
            }
        }

        return idx;
    }

    static void dfs(List<List<Integer>> list, boolean[] visited, int num) {
        if (visited[num]) {
            return;
        }
        visited[num] = true;

        List<Integer> nexts = list.get(num);

        for (int i = 0; i < nexts.size(); i++) {
            int next = nexts.get(i);

            if (visited[next]) {
                continue;
            }

            dfs(list, visited, next);
        }
    }


}