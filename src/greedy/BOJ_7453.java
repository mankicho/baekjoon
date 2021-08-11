package greedy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_7453 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int[] a = new int[n];
        int[] b = new int[n];
        int[] c = new int[n];
        int[] d = new int[n];
        for (int i = 0; i < n; i++) {
            int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            a[i] = arr[0];
            b[i] = arr[1];
            c[i] = arr[2];
            d[i] = arr[3];
        }

        int[] list = new int[n * n];
        int[] reverseList = new int[n * n];
        int idx = 0;
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < b.length; j++) {
                list[idx] = a[i] + b[j];
                reverseList[idx] = c[i] + d[j];
                idx++;
            }
        }
        Arrays.sort(list);
        Arrays.sort(reverseList);
        int startPointer = 0;
        int secondPointer = reverseList.length - 1;

        long ans = 0;
        while (startPointer < list.length && secondPointer >= 0) {
            if (list[startPointer] + reverseList[secondPointer] == 0) {
                int firstTmp = list[startPointer];
                int secondTmp = reverseList[secondPointer];

                long firstNum = 0;
                long secondNum = 0;
                while (startPointer < list.length && firstTmp == list[startPointer]) {
                    startPointer++;
                    firstNum++;
                }

                while (secondPointer >= 0 && secondTmp == reverseList[secondPointer]) {
                    secondPointer--;
                    secondNum++;
                }
                ans += (firstNum * secondNum);
            } else {
                if (list[startPointer] + reverseList[secondPointer] < 0) {
                    startPointer++;
                } else {
                    secondPointer--;
                }
            }
//            System.out.println("now ans = " + ans);
        }
//        System.out.println(Arrays.toString(list));
//        System.out.println(Arrays.toString(reverseList));
        System.out.println(ans);
    }
}