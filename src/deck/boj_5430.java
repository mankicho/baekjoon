package deck;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;

public class boj_5430 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testCase = Integer.parseInt(br.readLine());

        StringBuilder result = new StringBuilder();
        LOOP:
        for (int i = 0; i < testCase; i++) {
            String str = br.readLine();
            int n = Integer.parseInt(br.readLine());

            String line = br.readLine()
                    .replace("[", "")
                    .replace("]", "");

            if (n == 0) {
                if (str.contains("D")) {
                    result.append("error");
                } else {
                    result.append("[]");
                }
                result.append("\n");
                continue;
            }
            int[] arr = Arrays.stream(line.split(",")).mapToInt(Integer::parseInt).toArray();
            LinkedList<Integer> linkedList = new LinkedList<>();
            boolean first = true;
            for (int k : arr) {
                linkedList.add(k);
            }

            for (int j = 0; j < str.length(); j++) {
                if (str.charAt(j) == 'R') {
                    first = !first;
                } else {
                    if (linkedList.isEmpty()) {
                        result.append("error").append("\n");
                        continue LOOP;
                    } else {
                        if (first) {
                            linkedList.pollFirst();
                        } else {
                            linkedList.pollLast();
                        }
                    }
                }
            }
            if(linkedList.isEmpty()){
                result.append("[]").append("\n");
            }

            while (!linkedList.isEmpty()) {
                result.append("[");
                if (first) {
                    while (!linkedList.isEmpty()) {
                        result.append(linkedList.pollFirst()).append(",");
                    }
                } else {
                    while (!linkedList.isEmpty()) {
                        result.append(linkedList.pollLast()).append(",");
                    }
                }
                result.deleteCharAt(result.length() - 1);
                result.append("]").append("\n");
            }

        }
        System.out.println(result.toString());
    }
}