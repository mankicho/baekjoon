package greedy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class BOJ_1781 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        List<int[]> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            list.add(Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray());
        }

        list.sort((o1, o2) -> {
            if (o1[0] == o2[0]) {
                return o2[1] - o1[1];
            }
            return o1[0] - o2[0];
        });

        PriorityQueue<Integer> queue = new PriorityQueue<>();

        for (int i = 0; i < list.size(); i++) {
            int[] assign = list.get(i);

//            System.out.println(Arrays.toString(assign));
            int deadline = assign[0];
            int num = assign[1];

            if (queue.size() < deadline) {
                queue.add(num);
            }else{
                if(!queue.isEmpty() && num > queue.peek()){
                    queue.poll();
                    queue.add(num);
                }
            }
        }
        int sum =0 ;
        while(!queue.isEmpty()){
            sum+=queue.poll();
        }
        System.out.println(sum);
    }
}