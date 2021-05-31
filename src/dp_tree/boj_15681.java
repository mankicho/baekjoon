package dp_tree;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class boj_15681 {
    static Map<Integer, Node> nodeMap = new HashMap<>();
    static int[] mArr;
    static boolean[] visited;
    static boolean[] deleted;

    static List<Node> tmpNodeList = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int n = arr[0];
        int r = arr[1];
        int q = arr[2];

        for (int i = 1; i <= n; i++) {
            nodeMap.putIfAbsent(i, new Node(i));
        }
        List<List<Integer>> nodeList = new ArrayList<>();

        for (int i = 0; i <= n; i++) {
            nodeList.add(new ArrayList<>());
        }
        visited = new boolean[nodeList.size()];
        deleted = new boolean[nodeList.size()];
        mArr = new int[n + 1];
        Arrays.fill(mArr, 1);
        for (int i = 0; i < n - 1; i++) {
            arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            int u = arr[0];
            int v = arr[1];

            nodeList.get(u).add(v);
            nodeList.get(v).add(u);
        }

        dfs(nodeList, r);

        for (Map.Entry<Integer, Node> entry : nodeMap.entrySet()) {
            Node node = entry.getValue();

            if (node.child.isEmpty()) {
                tmpNodeList.add(node);
            }
        }
        while (!nodeMap.isEmpty()) {
            function();
        }


        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < q; i++) {
            int tmp = Integer.parseInt(br.readLine());

            sb.append(mArr[tmp]).append("\n");
        }

        System.out.println(sb.toString());

    }

    static void function() {

        List<Node> tmpList = new ArrayList<>();
        tmpNodeList.forEach(node -> {
            if (node.parent != null) {
                node.parent.child.remove(node);
                mArr[node.parent.num] += mArr[node.num];

                if (node.parent.child.isEmpty()) {
                    tmpList.add(node.parent);
                }
            }
            nodeMap.remove(node.num);
        });

        tmpNodeList = tmpList;
    }

    static void dfs(List<List<Integer>> nodeList, int root) {

        if (visited[root]) {
            return;
        }
        visited[root] = true;

        List<Integer> list = nodeList.get(root);

        for (int i : list) {
            if (visited[i]) {
                continue;
            }
            nodeMap.get(root).child.add(nodeMap.get(i));
            nodeMap.get(i).parent = nodeMap.get(root);

            dfs(nodeList, i);
        }
    }


    private static class Node {
        int num;
        Node parent;
        List<Node> child;

        public Node(int num) {
            this.num = num;
            child = new ArrayList<>();
        }

        @Override
        public String toString() {
            return "Node{" +
                    "num=" + num +
                    ", parent=" + (parent == null ? "" : parent.num) +
                    ", child=" + child +
                    '}';
        }
    }
}