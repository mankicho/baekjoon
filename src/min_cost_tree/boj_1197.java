package min_cost_tree;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;

import static java.util.Comparator.comparingInt;

public class boj_1197 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int v = arr[0];
        int e = arr[1];

        int[][] edges = new int[e][3];
        for (int i = 0; i < e; i++) {
            arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            edges[i] = arr;
        }

        Arrays.sort(edges, comparingInt(o -> o[2]));
        int[][] result = new int[v - 1][3];

        kruskal(edges, result);

        int answer = 0;
        for (int i = 0; i < result.length; i++) {
            answer += result[i][2];
        }
        System.out.println(answer);
    }

    static void kruskal(int[][] edges, int[][] result) {
        int cnt = 0;

        int[] s = new int[result.length + 2]; // 각 정점의 연결된 정점
        for (int i = 1; i < s.length; i++) {
            s[i] = i;
        }

        int s1 = 0;
        int s2 = 0;

        int idxOfEdges = 0;
        while (cnt < result.length) {
            int[] edge = edges[idxOfEdges];

            int v1 = edge[0]; // 간선의 시작 정점
            int v2 = edge[1]; // 간선의 끝 정점

            s1 = find(s, v1); // 시작 정점과 연결된 점
            s2 = find(s, v2); // 끝 정점과 연결된 점

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