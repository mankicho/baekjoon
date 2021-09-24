package brute_force;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_17140 {
    static int[][] dir = new int[][]{
            {-1, 0}, {1, 0}, {0, 1}, {-1, 0}
    };

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int r = arr[0];
        int c = arr[1];
        int k = arr[2];


        int[][] sortedArr = new int[101][101];

        for (int[] ints : sortedArr) {
            Arrays.fill(ints, -1);
        }
        for (int i = 0; i < 3; i++) {
            int[] values = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            for (int j = 0; j < values.length; j++) {
                sortedArr[i][j] = values[j];
            }
        }

        int cnt = 0;
        while (cnt < 101) {
            if (sortedArr[r - 1][c - 1] == k) {
                System.out.println(cnt);
                break;
            }
            int rowNum = 0;
            int colNum = 0;
            for (int i = 0; i < 101; i++) {
                if (sortedArr[0][i] == -1) {
                    break;
                }
                colNum++;
            }
            for (int i = 0; i < 101; i++) {
                if (sortedArr[i][0] == -1) {
                    break;
                }
                rowNum++;
            }

            if (rowNum >= colNum) {
                int max = colNum;
                int[] lengthArr = new int[101];
                for (int i = 0; i < 101; i++) {
                    if (sortedArr[i][0] == -1) {
                        break;
                    }
                    int[] visited = new int[101];
                    for (int j = 0; j < sortedArr[i].length; j++) {
                        if (sortedArr[i][j] == -1) {
                            break;
                        }
                        visited[sortedArr[i][j]]++;
                    }

                    Map<Integer, List<Integer>> map = new HashMap<>();
                    for (int j = 0; j < visited.length; j++) {
                        if (visited[j] > 0) {
                            map.putIfAbsent(visited[j], new ArrayList<>());
                            map.get(visited[j]).add(j);
                        }
                    }

                    int start = 0;
                    for (int j = 1; j <= 101; j++) {
                        if (map.get(j) == null || map.get(j).isEmpty()) {
                            continue;
                        }
                        List<Integer> list = map.get(j);
                        list.sort(Comparator.naturalOrder());

                        for (int l = 0; l < list.size() && start < 101; l++) {
                            if (list.get(l) == 0) {
                                continue;
                            }
                            sortedArr[i][start] = list.get(l);
                            sortedArr[i][start + 1] = j;
                            start += 2;
                        }
                    }
                    max = Math.max(max, start);
                    lengthArr[i] = start;
                }
                for (int i = 0; i < 101; i++) {
                    if (sortedArr[i][0] == -1) {
                        break;
                    }
                    for (int j = 0; j < max; j++) {
                        if (j >= lengthArr[i] || sortedArr[i][j] == -1) {
                            sortedArr[i][j] = 0;
                        }
                    }
                }
            } else {
                int max = rowNum;
                int[] lengthArr = new int[101];
                for (int i = 0; i < 101; i++) {
                    if (sortedArr[0][i] == -1) {
                        break;
                    }
                    int[] visited = new int[101];
                    for (int j = 0; j < sortedArr[i].length; j++) {
                        if (sortedArr[j][i] == -1) {
                            break;
                        }
                        visited[sortedArr[j][i]]++;
                    }

                    Map<Integer, List<Integer>> map = new HashMap<>();
                    for (int j = 0; j < visited.length; j++) {
                        if (visited[j] > 0) {
                            map.putIfAbsent(visited[j], new ArrayList<>());
                            map.get(visited[j]).add(j);
                        }
                    }
                    int start = 0;
                    for (int j = 1; j <= 101; j++) {
                        if (map.get(j) == null) {
                            continue;
                        }
                        List<Integer> list = map.get(j);
                        list.sort(Comparator.naturalOrder());

                        for (int l = 0; l < list.size() && start < 101; l++) {
                            if (list.get(l) == 0) {
                                continue;
                            }
                            sortedArr[start][i] = list.get(l);
                            sortedArr[start + 1][i] = j;
                            start += 2;
                        }
                    }
                    max = Math.max(max, start);
                    lengthArr[i] = start;
                }

                for (int i = 0; i < 101; i++) {
                    if (sortedArr[0][i] == -1) {
                        break;
                    }
                    for (int j = 0; j < max; j++) {
                        if (j >= lengthArr[i] || sortedArr[j][i] == -1) {
                            sortedArr[j][i] = 0;
                        }
                    }
                }
            }
            for (int i = 0; i < 101; i++) {
                if (sortedArr[i][0] == -1) {
                    break;
                }
                for (int j = 0; j < 101; j++) {
                    if (sortedArr[i][j] == -1) {
                        break;
                    }
                }

            }
            cnt++;
        }
        if(cnt == 101){
            System.out.println(-1);
        }
    }
}