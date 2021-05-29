package tree;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class boj_2263 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int[] inOrder = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] postOrder = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();


        index = new int[inOrder.length + 1];

        for (int i = 0; i < n; i++) {
            index[inOrder[i]] = i;
        }

        makeTree(inOrder, postOrder, 0, inOrder.length - 1, 0, postOrder.length - 1);
        System.out.println(sb.toString());
    }


    static int[] index;
    static StringBuilder sb = new StringBuilder();


    static void makeTree(int[] inOrder, int[] postOrder, int inOrderStart,
                         int inOrderEnd, int postOrderStart, int postOrderEnd) {

        if (inOrderStart > inOrderEnd || postOrderStart > postOrderEnd) {
            return;
        }
        int root = postOrder[postOrderEnd];

        int rootIdx = index[root];
        sb.append(root).append(" ");

        makeTree(inOrder, postOrder, inOrderStart, rootIdx - 1, postOrderStart,
                postOrderStart + ((rootIdx - 1 - inOrderStart)));
        makeTree(inOrder, postOrder, rootIdx + 1, inOrderEnd, postOrderStart + (rootIdx - inOrderStart),
                postOrderEnd - 1);
    }




}