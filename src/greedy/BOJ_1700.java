package greedy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_1700 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int n = arr[0];
        int k = arr[1];

        arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

//        System.out.println(Arrays.toString(arr));
        int[] cache = new int[n];
        int ans = 0;

        int c = 0;
        boolean[] visited = new boolean[k];
        int start = 0;
        for (int i = 0; i < arr.length; i++) {
            if (c == n) {
                start = i;
                break;
            }
            if (!visited[arr[i]]) {
                cache[c++] = arr[i];
                visited[arr[i]] = true;
            }
        }
        int[][] dp = new int[101][101];
        for (int i = 0; i < arr.length; i++) {
            dp[arr[i]][i]++;
        }
//        System.out.println(start);
        for (int i = start; i < arr.length; i++) {
            int thing = arr[i];
            boolean alreadyPutIn = false;
            for (int j = 0; j < cache.length; j++) {
                if (cache[j] == thing) {
                    alreadyPutIn = true;
                    break;
                }
            }
            if (alreadyPutIn) {
                continue;
            } else {
                int cacheIdx = 0;
                int min = 0;
                for (int j = 0; j < cache.length; j++) {
                    int ca = cache[j];
                    int[] subDP = dp[ca];
                    int tmpIdx = 101;
                    for (int l = i + 1; l < arr.length; l++) {
                        if (subDP[l] > 0) {
                            tmpIdx = l;
                            break;
                        }
                    }
                    if (tmpIdx > min) {
                        min = tmpIdx;
                        cacheIdx = j;
                    }
                }
                cache[cacheIdx] = arr[i];
                ans++;
            }
        }
        System.out.println(ans);
    }
}