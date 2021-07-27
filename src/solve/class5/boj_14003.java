package solve.class5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class boj_14003 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        List<Integer> list = new ArrayList<>();
        int[] replaceArr = new int[arr.length];
        list.add(0);
        int min = 1;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < min) {
                min = arr[i];
            }
            if (arr[i] > list.get(list.size() - 1)) {
                list.add(arr[i]);
                replaceArr[i] = list.size() - 1;
            } else {
                int start = 0;
                int end = list.size() - 1;
                while (start < end) {
                    int mid = (start + end) / 2;

                    if (arr[i] > list.get(mid)) {
                        start = mid + 1;
                    } else {
                        end = mid;
                    }
                }
                replaceArr[i] = start;
                list.set(start, arr[i]);
            }
        }
        int idx = list.size() - 1;
        for (int i = replaceArr.length - 1; i >= 0; i--) {
            if (replaceArr[i] == idx) {
                idx--;
                while (true) {
                    if (idx < (min == 1 ? 1 : 0)) {
                        break;
                    }
                    for (int j = i - 1; j >= 0; j--) {
                        if (replaceArr[j] == idx) {
                            if (arr[j] < list.get(idx + 1)) {
                                list.set(idx, Math.max(list.get(idx), arr[j]));
                                idx--;
                                i = j;
                                break;
                            }
                        }
                    }
                }
                break;
            }
        }
        System.out.println(min == 1 ? list.size() - 1 : list.size());
        StringBuilder result = new StringBuilder();
        for (int i = min == 1 ? 1 : 0; i < list.size(); i++) {
            result.append(list.get(i)).append(" ");
        }
        System.out.println(result.toString().trim());
    }
}