package dfs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BOJ_14725 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


        int n = Integer.parseInt(br.readLine());
        Node root = new Node("");
        for (int i = 0; i < n; i++) {
            String line = br.readLine();

            String[] split = line.split(" ");

            Node tmp = root;
            OUTER:
            for (int j = 1; j < split.length; j++) {
                Node node = new Node(split[j]);
                List<Node> nodes = tmp.childs;
                for (int k = 0; k < nodes.size(); k++) {
                    if (nodes.get(k).compare(node)) {
                        tmp = nodes.get(k);
                        continue OUTER;
                    }
                }
                nodes.add(node);
                tmp = node;
            }

        }

        print(root, -1);
        System.out.println(result.toString());
    }

    static void print(Node root, int num) {
        for (int i = 0; i < num * 2; i++) {
            result.append('-');
        }
        if (!root.val.equals("")) {
            result.append(root.val).append("\n");
        }
        Collections.sort(root.childs);

        for (int i = 0; i < root.childs.size(); i++) {
            print(root.childs.get(i), num + 1);
        }
    }

    static StringBuilder result = new StringBuilder();

    static class Node implements Comparable<Node> {
        String val;
        List<Node> childs = new ArrayList<>();

        public Node(String val) {
            this.val = val;
        }

        boolean compare(Node o) {
            return this.val.equals(o.val);
        }

        @Override
        public int compareTo(Node o) {
            return val.compareTo(o.val);
        }
    }
}