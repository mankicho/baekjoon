package parsing;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class boj_16402 {
    static int[] roots;
    static Map<String, Integer> map;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        map = new HashMap<>();
        Map<Integer, String> map2 = new HashMap<>();

        int n = arr[0];
        int m = arr[1];

        roots = new int[n + 1];
        for (int i = 1; i < roots.length; i++) {
            roots[i] = i;
        }

        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            String[] split = line.split(" ");

            map.put(split[2], i + 1);
            map2.put(i + 1, split[2]);
        }

        for (int i = 0; i < m; i++) {
            String line = br.readLine();
            String[] split = line.split(",");

            String first = split[0].split(" ")[2];
            String second = split[1].split(" ")[2];

            int win = Integer.parseInt(split[2]);

            if (win == 1) {
                union(roots, first, second);
            } else {
                union(roots, second, first);
            }
        }

        int sum = 0;
        StringBuilder result = new StringBuilder();
        List<String> tmpList = new ArrayList<>();
        for (int i = 1; i < roots.length; i++) {
            if (roots[i] == i) {
                sum++;
                tmpList.add(map2.get(i));
            }

        }
        tmpList.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });

        for (String s : tmpList) {
            result.append("Kingdom of ")
                    .append(s).append("\n");
        }
        System.out.println(sum);
        System.out.println(result.toString());
    }

    static int find(int[] roots, int a) {
        if (roots[a] == a) {
            return a;
        }

        return roots[a] = find(roots, roots[a]);
    }

    static void union(int[] roots, String winner, String loser) {
        int win = find(roots, map.get(winner));
        int lose = find(roots, map.get(loser));

        if (win == lose) { // 속국이 종주국을 공격
            roots[lose] = map.get(winner);
            roots[map.get(winner)] = map.get(winner);
        } else { // 왕국이 다른 왕국을 공격
            roots[lose] = win;
        }

    }
}