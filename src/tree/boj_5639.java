package tree;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class boj_5639 {
    static Node root;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String line;

        while ((line = br.readLine()) != null) {

            int num = Integer.parseInt(line);

            Node node = new Node(num);
            add(node);
        }
        StringBuilder sb = new StringBuilder();

        print(sb, root);
        System.out.println(sb.toString());
    }

    static void print(StringBuilder sb, Node node) {
        if (node == null) {
            return;
        }
        print(sb, node.left);
        print(sb, node.right);
        sb.append(node.num).append("\n");
    }

    static void add(Node node) {
        if (root == null) {
            root = node;
        } else {
            Node parent = null;
            Node child = root;
            boolean isLeft = false;
            while (child != null) {
                int num = child.num;

                parent = child;
                if (node.num > num) {
                    isLeft = false;
                    child = child.right;
                } else {
                    isLeft = true;
                    child = child.left;
                }
            }
            if (isLeft) {
                parent.left = node;
            } else {
                parent.right = node;
            }
        }
    }

    static class Node {
        int num;
        Node left;
        Node right;

        public Node(int num) {
            this.num = num;
        }
    }
}