package brute_force;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_16235 {

    static int[][] dir = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    static int[][] dir2 = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}, {1, 1}, {1, -1}, {-1, 1}, {-1, -1}};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int n = arr[0];
        int m = arr[1];
        int k = arr[2];

        int[][] plusMap = new int[n][n];

        int[][] map = new int[n][n];

        for (int[] ints : map) {
            Arrays.fill(ints, 5);
        }

        for (int i = 0; i < n; i++) {
            plusMap[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o[2]));
        PriorityQueue<int[]> deadPQ = new PriorityQueue<>(Comparator.comparingInt(o -> o[2]));

        for (int i = 0; i < m; i++) {
            arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int x = arr[0] - 1;
            int y = arr[1] - 1;
            int z = arr[2];

            pq.add(new int[]{x, y, z});
        }

        int cnt = 0;
        while (cnt < k) {

            for (int i = 0; i < 4; i++)
                switch (i) {
                    case 0:
                        List<int[]> list = new ArrayList<>();
                        while (!pq.isEmpty()) {
                            int[] poll = pq.poll();

                            int r = poll[0];
                            int c = poll[1];
                            int z = poll[2];

                            if (map[r][c] < z) {
                                if (z > 1) {
                                    deadPQ.add(poll);
                                }
                            } else {
                                poll[2] += 1;
                                map[r][c] -= z;
                                list.add(poll);
                            }
                        }
                        pq.addAll(list);
                        break;
                    case 1:
                        while (!deadPQ.isEmpty()) {
                            int[] poll = deadPQ.poll();

                            int r = poll[0];
                            int c = poll[1];
                            int z = poll[2];

                            map[r][c] += z / 2;
                        }
                        break;
                    case 2:
                        List<int[]> tmpList = new ArrayList<>();
                        Iterator<int[]> iter = pq.iterator();
                        while (iter.hasNext()) {
                            int[] poll = iter.next();

                            int r = poll[0];
                            int c = poll[1];
                            int z = poll[2];

                            if (z % 5 == 0) {
                                for (int j = 0; j < dir2.length; j++) {
                                    int[] d = dir2[j];

                                    int row = d[0] + r;
                                    int col = d[1] + c;

                                    if (row >= 0 && row < n && col >= 0 && col < n) {
                                        tmpList.add(new int[]{row, col, 1});
                                    }
                                }
                            }
                        }
                        pq.addAll(tmpList);
                        break;
                    default:
                        for (int j = 0; j < n; j++) {
                            for (int l = 0; l < n; l++) {
                                map[j][l] += plusMap[j][l];
                            }
                        }
                        break;
                }
            cnt++;
        }

        System.out.println(pq.size());
    }

}