package solve.class4;



import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class boj_9935 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


        String str = br.readLine();
        String bomb = br.readLine();

        Stack<Character> stack = new Stack<>();
        StringBuilder sb = new StringBuilder();

        int num = 0;
        for (int i = 0; i < str.length(); i++) {
            char chr = str.charAt(i);
            stack.add(chr);
            if (stack.size() >= bomb.length()) {
                boolean isSame = true;
                for (int j = 0; j < bomb.length(); j++) {
                    if (stack.get(stack.size() - bomb.length() + j) != bomb.charAt(j)) {
                        isSame = false;
                        break;
                    }
                }

                if (isSame) {
                    for (int j = 0; j < bomb.length(); j++) {
                        stack.pop();
                    }
                }
            }
        }

        if(stack.isEmpty()){
            System.out.println("FRULA");
            return;
        }
        for (Character c : stack) {
            sb.append(c);
        }
        System.out.println(sb.toString());

    }
}