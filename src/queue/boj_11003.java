package queue;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;

public class boj_11003 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int n = arr[0];
        int l = arr[1];
        arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        LinkedList<Integer> queue = new LinkedList<>();

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {

            while (!queue.isEmpty() && queue.peekFirst() <= i - l) {
                queue.pollFirst();
            }

            while (!queue.isEmpty() && arr[queue.peekLast()] > arr[i]) {
                queue.pollLast();
            }
            queue.offerLast(i);
            result.append(arr[queue.peekFirst()]).append(" ");
        }
        System.out.println(result.toString());

    }


}

