package bfs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_14867 {

    static int[][] dir = new int[][]{
            {0, 1}, {1, 0}, {0, -1}, {-1, 0}
    };
    private static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int a = arr[0];
        int b = arr[1];
        int c = arr[2];
        int d = arr[3];

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o[2]));

        pq.add(new int[]{0, 0, 0});

        Map<Integer, Map<Integer, Boolean>> map = new HashMap<>();
        for (int i = 0; i <= a; i++) {
            map.put(i, new HashMap<>());
        }

        int cnt = 0;
        int answer = -1;
        while (!pq.isEmpty() && cnt < 10 * a) {
            int[] poll = pq.poll();

            int tmpA = poll[0];
            int tmpB = poll[1];
            int tmpC = poll[2];

            if (tmpA == c && tmpB == d) {
                answer = tmpC;
                break;
            }

            if (map.get(tmpA).get(tmpB) != null && map.get(tmpA).get(tmpB)) {
                continue;
            } else {
                map.get(tmpA).put(tmpB, true);
            }

            if (tmpA != 0) {
                pq.add(new int[]{0, tmpB, tmpC + 1});
            }
            if (tmpB != 0) {
                pq.add(new int[]{tmpA, 0, tmpC + 1});
            }

            if (tmpA < a) {
                pq.add(new int[]{a, tmpB, tmpC + 1});
            }

            if (tmpB < b) {
                pq.add(new int[]{tmpA, b, tmpC + 1});
            }

            int tmp = tmpA + tmpB;

            if (tmp > b) {
                pq.add(new int[]{tmp - b, b, tmpC + 1});
            } else {
                pq.add(new int[]{0, tmp, tmpC + 1});
            }

            if (tmp > a) {
                pq.add(new int[]{a, tmp - a, tmpC + 1});
            } else {
                pq.add(new int[]{tmp, 0, tmpC + 1});
            }
        }
        System.out.println(answer);
    }
}

