package tree;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_4256 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int testcase = Integer.parseInt(br.readLine());

        for (int i = 0; i < testcase; i++) {
            StringBuilder tmp = new StringBuilder();
            int n = Integer.parseInt(br.readLine());

            String preOrder = br.readLine();
            String inOrder = br.readLine();

            int[] pre = Arrays.stream(preOrder.split(" ")).mapToInt(Integer::parseInt).toArray();
            int[] in = Arrays.stream(inOrder.split(" ")).mapToInt(Integer::parseInt).toArray();

            function(pre, in, 0, in.length - 1, 0, tmp);
            result.append(tmp.reverse().toString().trim()).append("\n");
        }
        System.out.println(result.toString().trim());

    }

    static StringBuilder result = new StringBuilder();

    static void function(int[] pre, int[] in, int start, int end, int criIdx, StringBuilder tmp) {
        if (start > end || start >= in.length || end < 0) {
            return;
        }
        int val = pre[criIdx];
        if(start == end){
            tmp.append(val).append(" ");
            return;
        }
        tmp.append(val).append(" ");
        int idx = 0;
        for (int i = start; i <= end; i++) {
            if (in[i] == val) {
                idx = i;
                break;
            }
        }

        int offset = idx - start;
        function(pre, in, idx + 1, end, criIdx + offset + 1, tmp); // right
        function(pre, in, start, idx - 1, criIdx + 1, tmp); // left
    }
}