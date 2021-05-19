package dfs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class boj_2606 {
    static int answer = 1;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int link = Integer.parseInt(br.readLine());

        List<List<Integer>> list = new ArrayList<>();

        for (int i = 0; i <= n; i++) {
            list.add(new ArrayList<>());
        }
        for (int i = 0; i < link; i++) {
            int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            int firstNode = arr[0];
            int secondNode = arr[1];

            list.get(firstNode).add(secondNode);
            list.get(secondNode).add(firstNode);
        }

        boolean[] computers = new boolean[n+1];

        int firstNode = 1;

        function(firstNode, list, computers);
        System.out.println(answer-1);
    }

    static void function(int start, List<List<Integer>> list, boolean[] computers) {
        List<Integer> com = list.get(start);
        computers[start] = true;

        for (int i = 0; i < com.size(); i++) {
            int node = com.get(i);

            if (!computers[node]) {
                answer++;
                function(node, list, computers);
            }
        }


    }
}
