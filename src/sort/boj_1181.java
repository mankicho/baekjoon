package sort;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class boj_1181 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();

        int N = Integer.parseInt(line);

        PriorityQueue<Node> priorityQueue = new PriorityQueue<>();

        for (int i = 0; i < N; i++) {
            priorityQueue.add(new Node(br.readLine()));
        }

        StringBuilder sb = new StringBuilder();

        while (!priorityQueue.isEmpty()) {
            String str = priorityQueue.poll().word;

            if(sb.toString().contains(str)){
                continue;
            }

            sb.append(str).append("\n");
        }
        System.out.println(sb.toString());

    }

    private static class Node implements Comparable<Node> {
        private String word;

        public Node(String word) {
            this.word = word;
        }

        @Override
        public int compareTo(Node o) {
            if (this.word.length() == o.word.length()) {
                return this.word.compareTo(o.word);
            }

            return this.word.length() - o.word.length();

        }
    }
}
