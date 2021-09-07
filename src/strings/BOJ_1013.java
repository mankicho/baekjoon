package strings;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BOJ_1013 {

    static int[][] nodeNumber;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int testcase = Integer.parseInt(br.readLine());
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < testcase; i++) {
            String line = br.readLine();

            Pattern pattern = Pattern.compile("^(10(0)+(1)+|(01))+$");

            Matcher matcher = pattern.matcher(line);

            if (matcher.matches()) {
                result.append("YES").append("\n");
            } else {
                result.append("NO").append("\n");
            }
        }
        System.out.println(result.toString());

    }

}
