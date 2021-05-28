package tree;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class boj_1991 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        Map<Character, Node> nodeMap = new HashMap<>();

        int c = (int) 'A';
        for (int i = 0; i < 26; i++) {
            nodeMap.put((char) (c + i), new Node((char) (c + i)));
        }

        for (int i = 0; i < n; i++) {
            String[] arr = br.readLine().split(" ");

            char c1 = arr[0].charAt(0);
            char c2 = arr[1].charAt(0);
            char c3 = arr[2].charAt(0);

            if (c2 != '.') {
                nodeMap.get(c1).left = nodeMap.get(c2);
            }
            if (c3 != '.') {
                nodeMap.get(c1).right = nodeMap.get(c3);
            }

        }

        StringBuilder sb = new StringBuilder();
        printPre(sb, nodeMap.get('A'));
        System.out.println(sb.toString());
        sb = new StringBuilder();
        printMiddle(sb, nodeMap.get('A'));
        System.out.println(sb.toString());
        sb = new StringBuilder();
        printPost(sb, nodeMap.get('A'));
        System.out.println(sb.toString());
    }

    static void printPre(StringBuilder sb, Node root) {
        if (root == null) {
            return;
        }
        sb.append(root.alphabet);
        printPre(sb, root.left);
        printPre(sb, root.right);
    }


    static void printMiddle(StringBuilder sb, Node root) {
        if (root == null) {
            return;
        }
        printMiddle(sb, root.left);
        sb.append(root.alphabet);
        printMiddle(sb, root.right);
    }

    static void printPost(StringBuilder sb, Node root) {
        if (root == null) {
            return;
        }
        printPost(sb, root.left);
        printPost(sb, root.right);
        sb.append(root.alphabet);
    }

    private static class Node {
        private Character alphabet;
        private Node left;
        private Node right;

        public Node(Character alphabet) {
            this.alphabet = alphabet;
        }

    }
}
