package brute_force;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ_1011 {

    static int[][] nodeNumber;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int testcase = Integer.parseInt(br.readLine());
        StringBuilder re = new StringBuilder();
        for (int i = 0; i < testcase; i++) {
            String[] split = br.readLine().split(" ");
            int a = Integer.parseInt(split[0]);
            int b = Integer.parseInt(split[1]);

            int distance = b - a;

            if(distance<=3){
                re.append(distance).append("\n");
                continue;
            }
            Double sqrt = Math.sqrt(distance);

            int left = sqrt.intValue() * 2 - 1;
            int right = (sqrt.intValue() + 1) * 2 - 1;

            if (sqrt == sqrt.intValue()) {
                re.append(left).append("\n");
                continue;
            }
            if (Math.abs(distance - (sqrt.intValue() * sqrt.intValue())) > Math.abs(distance - ((sqrt.intValue()+ 1) * (sqrt.intValue()+ 1)))) {
                re.append(right).append("\n");
            } else {
                re.append(left + 1).append("\n");
            }
        }
        System.out.println(re.toString());

    }

}
