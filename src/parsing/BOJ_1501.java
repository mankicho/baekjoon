package parsing;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class BOJ_1501 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());


        boolean[] one = new boolean[200];
        Map<Character, Map<Character, Map<String, Integer>>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            char first = line.charAt(0);
            char last = line.charAt(line.length() - 1);

            if (line.length() == 1) {
                one[first] = true;
                continue;
            }
            String subString = line.substring(1, line.length() - 1);

            char[] chars = subString.toCharArray();
            Arrays.sort(chars);
            String str = new String(chars);
            map.putIfAbsent(first, new HashMap<>());
            map.get(first).putIfAbsent(last, new HashMap<>());

            map.get(first).get(last).putIfAbsent(str, 0);
            Map<String, Integer> subMap = map.get(first).get(last);

            subMap.put(str, subMap.get(str) + 1);
        }

        int lines = Integer.parseInt(br.readLine());

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < lines; i++) {
            String line = br.readLine();

            String[] split = line.split(" ");

            long cnt = 1;
            for (int j = 0; j < split.length; j++) {
                String subStr = split[j];

                char first = subStr.charAt(0);
                char last = subStr.charAt(subStr.length() - 1);
                if (subStr.length() == 1) {
                    if (!one[first]) {
                        cnt = 0;
                    }
                    continue;
                }

                String str = subStr.substring(1, subStr.length() - 1);
                char[] chars = str.toCharArray();
                Arrays.sort(chars);
                String sortedStr = new String(chars);
                Map<Character, Map<String, Integer>> subMap = map.get(first);

                if (subMap == null) {
                    cnt = 0;
                    break;
                }
                Map<String, Integer> subSubMap = subMap.get(last);

                if (subSubMap == null) {
                    cnt = 0;
                    break;
                }
                cnt *= (subSubMap.get(sortedStr) == null ? 0 : subSubMap.get(sortedStr));
            }
            result.append(cnt).append("\n");
        }
        System.out.println(result.toString());
    }
}