package brute_force;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BlackJack{

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String firstLine = br.readLine();
        String secondLine = br.readLine();

        String[] firstLineSplit = firstLine.split(" ");
        String[] secondLineSplit = secondLine.split(" ");

        int N = Integer.parseInt(firstLineSplit[0]);
        int M = Integer.parseInt(firstLineSplit[1]);

        int[] arr = Arrays.stream(secondLineSplit).mapToInt(Integer::parseInt).toArray();

        int size = arr.length;

        int max = 0;
        for (int i = 0; i < size; i++) {
            int first = arr[i];
            for (int j = i + 1; j < size; j++) {
                int second = arr[j];
                for (int k = j + 1; k < size; k++) {
                    int third = arr[k];

                    int plus = first + second + third;

                    if (plus > M) {
                        continue;
                    }
                    if (plus > max) {
                        max = plus;
                    }
                }
            }
        }

        System.out.println(max);
    }
}
