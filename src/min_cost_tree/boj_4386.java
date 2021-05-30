package min_cost_tree;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class boj_4386 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        double[][] starts = new double[n][2];
        for (int i = 0; i < n; i++) {
            double[] arr = Arrays.stream(br.readLine().split(" ")).mapToDouble(Double::parseDouble).toArray();

            starts[i] = arr;
        }

//        double[][] edges = new double[n * n][3];
        List<double[]> list = new ArrayList<>();

        for (int i = 0; i < starts.length; i++) {
            double[] start = starts[i];

            for (int j = i + 1; j < starts.length; j++) {
                double[] end = starts[j];
                double x1 = start[0];
                double y1 = start[1];

                double x2 = end[0];
                double y2 = end[1];

                double[] edge = new double[]{i, j, getDistance(x1, y1, x2, y2)};

                list.add(edge);
            }
        }

        list.sort(new Comparator<double[]>() {
            @Override
            public int compare(double[] o1, double[] o2) {
                return Double.compare(o1[2], o2[2]);
            }
        });

        double[][] result = new double[n][3];

        kruskal(list, result);

        double answer = 0;
        for (int i = 0; i < result.length; i++) {
            double[] distance = result[i];
            answer += distance[2];
        }
        System.out.println(answer);
    }

    static double getDistance(double x1, double y1, double x2, double y2) {
        double x = x2 - x1;
        double y = y2 - y1;

        return (Math.round(Math.sqrt(x * x + y * y)* 100) ) / 100.0;
    }

    static void kruskal(List<double[]> edges, double[][] result) {
        int cnt = 0;

        int[] s = new int[result.length + 1]; // 각 정점의 연결된 정점
        for (int i = 1; i < s.length; i++) {
            s[i] = i;
        }

        int s1 = 0;
        int s2 = 0;

        int idxOfEdges = 0;
        while (cnt < result.length && idxOfEdges < edges.size()) {
            double[] edge = edges.get(idxOfEdges);

            double v1 = edge[0]; // 간선의 시작 정점
            double v2 = edge[1]; // 간선의 끝 정점

            s1 = find(s, (int) v1); // 시작 정점과 연결된 점
            s2 = find(s, (int) v2); // 끝 정점과 연결된 점

            if (s1 != s2) { // 그래프가 사이클을 이루지 않으면
                union(s, s1, s2);
                result[cnt] = edge;
                cnt++;
            }
            idxOfEdges++;
        }
    }

    static void union(int[] s, int a, int b) {
        a = find(s, a);
        b = find(s, b);

        if (a != b) {
            s[b] = a;
        }
    }

    static int find(int[] s, int a) {
        if (s[a] == a) {
            return a;
        }

        return s[a] = find(s, s[a]);
    }
}