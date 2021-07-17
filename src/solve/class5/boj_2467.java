package solve.class5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class boj_2467 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        Node[] node = new Node[n];

        int min = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            node[i] = new Node(arr[i]);
        }
        Arrays.sort(node);

        int a = 0;
        int b = 0;
        for (int i = 1; i < node.length; i++) {
            Node pre = node[i - 1];
            Node now = node[i];

            if (Math.abs(pre.num + now.num) < min) {
                min = Math.abs(pre.num + now.num);
                a = pre.num;
                b = now.num;
            }
        }
        System.out.println(a > b ? b + " " + a : a + " " + b);
    }

    static class Node implements Comparable<Node> {
        int num;

        public Node(int num) {
            this.num = num;
        }

        @Override
        public int compareTo(Node o) {
            return Math.abs(o.num) - Math.abs(num);
        }

        @Override
        public String toString() {
            return "Node{" +
                    "num=" + num +
                    '}';
        }
    }
}