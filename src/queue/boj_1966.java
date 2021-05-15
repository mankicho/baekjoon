package queue;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;

import static java.util.Comparator.*;

public class boj_1966 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++) {
            int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int[] priority = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();


            LinkedList<Node> queue = new LinkedList<>();
            LinkedList<Node> queue2 = new LinkedList<>();
            int want = arr[1];
            for (int j = 0; j < priority.length; j++) {
                Node newNode = new Node(j, priority[j]);
                queue.add(newNode);
                queue2.addLast(newNode);
            }

            queue2.sort(comparingInt(o -> o.val));

            int answer = 0;
            while (!queue.isEmpty()) {
                Node node = queue.pop();


                br.readLine();
                if (node.val != queue2.peekLast().val) {
                    queue.addLast(node);
                } else {
                    answer++;
                    if (node.id == want) {
                        break;
                    }
                    queue2.pollLast();
                }
            }

            System.out.println(answer);
        }
    }

    private static class Node {
        int id;
        int val;

        public Node(int id, int val) {
            this.id = id;
            this.val = val;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "id=" + id +
                    ", val=" + val +
                    '}';
        }
    }
}
