package brute_force;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;

public class boj_7568 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String line = br.readLine();

        int N = Integer.parseInt(line);

        int[][] values = new int[N][2];
        for (int i = 0; i < N; i++) {
            String secondLine = br.readLine();
            String[] split = secondLine.split(" ");

            values[i][0] = Integer.parseInt(split[0]);
            values[i][1] = Integer.parseInt(split[1]);
        }

        int[] result = new int[N];

        for(int i=0;i<N;i++){
            int[] arr = values[i];
            int k = 0;
            for(int j=0;j<N;j++){
                if(i==j){
                    continue;
                }

                int[] arr2 = values[j];

                if(arr2[0] > arr[0] && arr2[1] > arr[1]){
                    k++;
                }
            }
            result[i] = k+1;
        }

        for(int i=0;i<result.length;i++){
            System.out.print(result[i]+" ");
        }
    }
}

// 4 4 1 4 2 2 5