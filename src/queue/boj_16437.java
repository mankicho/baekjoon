package queue;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class boj_16437 {
    public static void main(String[] args) throws Exception {
        List<String> threadList = new ArrayList<>();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

//        List<List<Integer>> list = new ArrayList<>();
//        for (int i = 0; i <= n; i++) {
//            list.add(new ArrayList<>());
//        }

        long[] arr = new long[n + 1];
        int[] parents = new int[n + 1];
        int[] childs = new int[n + 1];
        for (int i = 2; i <= n; i++) {
            String line = br.readLine();
            String[] split = line.split(" ");

            if (split[0].equals("S")) {
                arr[i] = Integer.parseInt(split[1]);
            } else {
                arr[i] = Integer.parseInt(split[1]) * -1;
            }
            int node = Integer.parseInt(split[2]);
            parents[i] = node;
            childs[node]++;
        }
        long ans = 0;

        LinkedList<Integer> queue = new LinkedList<>();
        for (int i = 1; i < childs.length; i++) {
            if (childs[i] == 0) {
                queue.add(i);
            }
        }

        while (!queue.isEmpty()) {
            int node = queue.poll();

            if(node == 1){
                break;
            }
            int parent = parents[node];
            childs[parent]--;
            if (arr[node] > 0) {
                arr[parent] += arr[node];
            }
            if (childs[parent] == 0) {
                queue.add(parent);
            }
        }
        System.out.println(arr[1]);
    }
}