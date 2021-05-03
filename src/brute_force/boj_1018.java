package brute_force;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class boj_1018 {
    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//
//        String line = br.readLine();
//
//        int N = Integer.parseInt(line) - 1;
//
//        List<Integer> list = new ArrayList<>();
//        for (int i = 0; i < 1001; i++) {
//            String s = i + "666";
//
//            for (int j = 0; j < 10; j++) {
//                if(j==6){
//                    continue;
//                }
//                String k = s + j;
//                list.add(Integer.parseInt(k));
//            }
//            list.add(Integer.parseInt(s));
//        }
//
//        list.sort((i1, i2) -> i1 > i2 ? 1 : i1.equals(i2) ? 0 : -1);
//
//        System.out.println(list.get(N));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String line = br.readLine();

        int N = Integer.parseInt(line);

        int num = 665;

        int answer =0 ;
        while(answer < N){

            num++;

            String str = num+"";

            if(str.contains("666")){
                answer++;
            }
        }

        System.out.println(num);
    }

}



//public class boj_1018 {
//    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//
//        String line = br.readLine();
//
//        int N = Integer.parseInt(line);
//
//        int num = 665;
//
//        int answer =0 ;
//        while(answer < N){
//
//            num++;
//
//            String str = num+"";
//
//            if(str.contains("666")){
//                answer++;
//            }
//        }
//
//        System.out.println(num);
//    }
//
//}
