package dp3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class boj_11723 {
    static Set<Integer> set = new HashSet<>();
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++) {
            String[] split = br.readLine().split(" ");

            switch (split[0]) {
                case "add":
                    add(Integer.parseInt(split[1]));
                    break;
                case "check":
                    check(Integer.parseInt(split[1]));
                    break;
                case "remove":
                    remove(Integer.parseInt(split[1]));
                    break;
                case "toggle":
                    toggle(Integer.parseInt(split[1]));
                    break;
                case "all":
                    all();
                    break;
                default:
                    empty();
                    break;
            }
        }
        System.out.println(sb.toString());
    }

    static void add(int x) {
        set.add(x);
    }

    static void remove(int x) {
        set.remove(x);
    }

    static void check(int x) {
        if (set.contains(x)) {
            sb.append(1).append("\n");
        } else {
            sb.append(0).append("\n");
        }
    }

    static void toggle(int x) {
        if (set.contains(x)) {
            set.remove(x);
        } else {
            set.add(x);
        }
    }

    static void all() {
        for (int i = 1; i <= 20; i++) {
            set.add(i);
        }
    }

    static void empty() {
        set.clear();
    }
}