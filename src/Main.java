import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        int[] connectArr = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            connectArr[i] = i;
        }
        for (int i = 1; i <= n; i++) {

            int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            for (int j = 0; j < arr.length; j++) {
                if (arr[j] == 1) {
                    int end = j;

                    union(connectArr, i, j);
                }
            }


        }
        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int result = find(connectArr, arr[0]);
        boolean isSame = true;
        for (int i = 1; i < arr.length; i++) {
            if (result != find(connectArr, arr[i])) {
                isSame = false;
                break;
            }
        }

        if (isSame) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }

    static int find(int[] connectArr, int num) {
        if (connectArr[num] == num) {
            return num;
        }

        connectArr[num] = find(connectArr, connectArr[num]);
        return connectArr[num];
    }

    static void union(int[] arr, int a, int b) {
        a = find(arr, a);
        b = find(arr, b);

        if (a != b) {
            if (a < b) {
                arr[b] = a;
            } else {
                arr[a] = b;
            }
        }
    }
}
