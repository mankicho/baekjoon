package solve.class4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;

public class boj_1043 {

    static int[] roots;
    static boolean[] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int n = arr[0];
        int m = arr[1];
        roots = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            roots[i] = i;
        }
        int answer = 0;
        LinkedList<int[]> queue = new LinkedList<>();
        visited = new boolean[n + 1];

        String visit = br.readLine();

        String[] visitSplit = visit.split(" ");

        for (int i = 1; i < visitSplit.length; i++) {
            visited[Integer.parseInt(visitSplit[i])] = true;
        }


        for (int i = 0; i < m; i++) {
            String line = br.readLine();
            if (line.startsWith("0")) {
                continue;
            } else {
                String[] split = line.split(" ");

                String tmp = "";
                for (int j = 1; j < split.length; j++) {
                    tmp += split[j] + " ";
                }

                int[] tmpArr = Arrays.stream(tmp.split(" ")).mapToInt(Integer::parseInt).toArray();

                for (int j = 0; j < tmpArr.length - 1; j++) {
                    union(roots, tmpArr[j], tmpArr[j + 1]);
                }
                queue.add(tmpArr);
            }
        }
        answer += function(queue, visited, roots);

        System.out.println(answer);
    }

    static int find(int[] roots, int a) {
        if (a == roots[a]) {
            return a;
        }

        return roots[a] = find(roots, roots[a]);
    }

    static void union(int[] roots, int a, int b) {
        a = find(roots, a);
        b = find(roots, b);

        if (a != b) {
            if (a > b) {
                roots[a] = b;

            } else {
                roots[b] = a;
            }
        }

        if (visited[a] || visited[b]) {
            visited[a] = true;
            visited[b] = true;
        }
    }


    static int function(LinkedList<int[]> queue, boolean[] visited, int[] roots) {
        int answer = 0;
        while (!queue.isEmpty()) {
            int[] poll = queue.poll();
            boolean b = false;
            for (int i = 0; i < poll.length; i++) {
                if (visited[find(roots, poll[i])]) {
                    b = true;
                    break;
                }
            }
            if (!b) {
                answer++;
            }
        }
        return answer;
    }
}