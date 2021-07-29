package devide_conquest;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class boj_6549 {

    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String line;

        while (!(line = br.readLine()).equals("0")) {
            int[] arr = Arrays.stream(line.split(" ")).mapToInt(Integer::parseInt).toArray();

            int n = arr[0];

            int[] histogram = new int[n];
            for (int i = 1; i < arr.length; i++) {
                histogram[i - 1] = arr[i];
            }
            System.out.println(getPartArea(histogram, 0, histogram.length - 1));
        }

    }

    static long getPartArea(int[] arr, int start, int end) {

        if (start == end) {
            return arr[start];
        }
        int mid = (start + end) / 2;

        long leftArea = getPartArea(arr, start, mid);
        long rightArea = getPartArea(arr, mid + 1, end);

        long midArea = getMidArea(arr, start, end, mid);

        return Math.max(leftArea, Math.max(rightArea, midArea));
    }

    static long getMidArea(int[] arr, int s, int e, int mid) {
        long max = arr[mid];

        int left = mid - 1;
        int right = mid + 1;

        long height = arr[mid];
        int width = 1;
        while (s <= left && right <= e) {
            if (arr[left] > arr[right]) {
                height = Math.min(arr[left], height);
                width++;
                max = Math.max(max, height * width);
                left--;
            } else {
                height = Math.min(arr[right], height);
                width++;
                max = Math.max(max, height * width);
                right++;
            }
        }

        while (s <= left) {
            height = Math.min(arr[left], height);
            width++;
            max = Math.max(max, height * width);
            left--;
        }
        while (right <= e) {
            height = Math.min(arr[right], height);
            width++;
            max = Math.max(max, height * width);
            right++;
        }
        return max;
    }

}

