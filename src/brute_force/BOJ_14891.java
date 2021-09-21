package brute_force;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_14891 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[][] arrs = new int[4][];

        for (int i = 0; i < 4; i++) {
            arrs[i] = Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
        }

        int k = Integer.parseInt(br.readLine());

        for (int i = 0; i < k; i++) {
            int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            int num = arr[0];
            int direction = arr[1];

            int[] rotate = new int[4];

            rotate[num - 1] = direction;
            int tmp = num;
            while (tmp - 1 < 3) {
                if (arrs[tmp - 1][2] == arrs[tmp][6]) {
                    break;
                }
                rotate[tmp] = rotate[tmp - 1] * -1;
                tmp++;
            }
            tmp = num;

            while (tmp - 1 >= 1) {
                if (arrs[tmp - 1][6] == arrs[tmp - 2][2]) {
                    break;
                }
                rotate[tmp - 2] = rotate[tmp - 1] * -1;
                tmp--;
            }

            rotateArr(rotate, arrs);
        }

        int answer = 0;
        for (int i = 0; i < 4; i++) {
            if (arrs[i][0] == 1) {
                answer += (int) Math.pow(2, i);
            }
        }
        System.out.println(answer);

    }

    // 01111101

    static void rotateArr(int[] rotate, int[][] arrs) {
        for (int i = 0; i < 4; i++) {
            if (rotate[i] == 0) {
                continue;
            }

            switch (rotate[i]) {
                case 1:
                    int tmp = arrs[i][arrs[i].length - 1];
                    for (int j = arrs[i].length - 2; j >= 0; j--) {
                        arrs[i][j + 1] = arrs[i][j];
                    }
                    arrs[i][0] = tmp;
                    break;
                case -1:
                    int tmp2 = arrs[i][0];
                    for (int j = 1; j < arrs[i].length; j++) {
                        arrs[i][j - 1] = arrs[i][j];
                    }
                    arrs[i][arrs[i].length - 1] = tmp2;
                    break;
            }
        }
    }
}