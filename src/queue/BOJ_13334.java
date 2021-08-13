package queue;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class BOJ_13334 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int[][] datas = new int[n][2];
        for (int i = 0; i < n; i++) {
            int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();


            datas[i][0] = Math.min(arr[0], arr[1]);
            datas[i][1] = Math.max(arr[0], arr[1]);
        }

        Arrays.sort(datas, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[1] == o2[1]) {
                    return o1[0] - o2[0];
                }
                return o1[1] - o2[1];
            }
        });

        int d = Integer.parseInt(br.readLine());
//        for (int[] data : datas) {
//            System.out.println(Arrays.toString(data));
//        }
        PriorityQueue<Integer> queue = new PriorityQueue<>();

        int max = 0;
        for (int i = 0; i < datas.length; i++) {
            if (queue.isEmpty()) {
                if (datas[i][0] >= datas[i][1] - d) {
                    queue.add(datas[i][0]);
                }
            } else {

                while (!queue.isEmpty() && queue.peek() < datas[i][1] - d) {
                    queue.poll();
                }
                if (datas[i][1] - datas[i][0] <= d) {
                    queue.add(datas[i][0]);
                }
                max = Math.max(queue.size(), max);
            }
        }
        if (queue.size() > max) {
            max = queue.size();
        }
        System.out.println(max);
    }

}