package greedy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class boj_1339 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        Map<Character, Integer> totalNumMap = new HashMap<>();

        List<String> lines = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            lines.add(line);
        }

        for (String str : lines) {
            int idx = 1;
            for (int i = str.length() - 1; i >= 0; i--) {
                char c = str.charAt(i);

                totalNumMap.putIfAbsent(c, 0);

                totalNumMap.put(c, totalNumMap.get(c) + idx);
                idx = idx * 10;
            }
        }

        List<Map.Entry<Character, Integer>> list = new ArrayList<>(totalNumMap.entrySet());
        list.sort(new Comparator<Map.Entry<Character, Integer>>() {
            @Override
            public int compare(Map.Entry<Character, Integer> o1, Map.Entry<Character, Integer> o2) {
                return o2.getValue() - o1.getValue();
            }
        });

        int max = 9;
        Map<Character, Integer> map = new HashMap<>();
        for (Map.Entry<Character, Integer> entry : list) {
            if (map.get(entry.getKey()) == null) {
                map.put(entry.getKey(), max--);
            }
        }


        int ans = 0;

        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);

            int sum = 0;
            int idx = 1;
            for (int j = line.length() - 1; j >= 0; j--) {
                char c = line.charAt(j);
                sum += (map.get(c) * idx);
                idx *= 10;
            }

            ans += sum;
        }
        System.out.println(ans);
    }
}