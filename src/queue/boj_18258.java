package queue;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class boj_18258 {
    public static void main(String[] args) throws Exception {
        MyQueue myQueue = new MyQueue();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            String line = br.readLine();

            if (line.contains("push")) {
                String[] split = line.split(" ");
                myQueue.push(Integer.parseInt(split[1]));
            } else if (line.contains("front")) {
                sb.append(myQueue.front()).append("\n");
            } else if (line.contains("back")) {
                sb.append(myQueue.back()).append("\n");
            } else if (line.contains("size")) {
                sb.append(myQueue.size()).append("\n");
            } else if (line.contains("pop")) {
                sb.append(myQueue.pop()).append("\n");
            } else {
                sb.append(myQueue.empty()).append("\n");
            }
        }
        System.out.println(sb.toString());
    }

    private static class MyQueue {

        int head = 0;
        int tail = 0;
        int[] arr;

        public MyQueue() {
            arr = new int[10];
        }

        void push(int val) {
            if (head == arr.length) {
                int length = arr.length * 2;
                int[] tmp = new int[arr.length];

                for (int i = 0; i < tmp.length; i++) {
                    tmp[i] = arr[i];
                }

                arr = new int[length];

                for (int i = 0; i < tmp.length; i++) {
                    arr[i] = tmp[i];
                }
            }
            arr[head++] = val;
        }

        int pop() {
            if (tail == head) {
                return -1;
            }
            return arr[tail++];
        }

        int size() {
            return head - tail;
        }

        int empty() {
            if (head == tail) {
                return 1;
            }
            return 0;
        }

        int front() {
            if (head == tail) {
                return -1;
            }

            return arr[tail];
        }

        int back() {
            if (head == tail) {
                return -1;
            }

            return arr[head - 1];
        }
    }
}
