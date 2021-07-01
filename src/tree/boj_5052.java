package tree;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class boj_5052 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder result = new StringBuilder();
        int testcase = Integer.parseInt(br.readLine());
        for (int i = 0; i < testcase; i++) {

            int n = Integer.parseInt(br.readLine());
            boolean same = false;

            String[] lines = new String[n];
            for (int j = 0; j < n; j++) {
                String line = br.readLine();
                lines[j] = line;
            }

            Arrays.sort(lines);

            for(int j=0;j<lines.length-1;j++){
                if(lines[j+1].startsWith(lines[j])){
                    same = true;
                    break;
                }
            }
            if(same){
                result.append("NO").append("\n");
            }else{
                result.append("YES").append("\n");
            }
        }
        System.out.println(result.toString());
    }

}