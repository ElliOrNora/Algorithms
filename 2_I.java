import java.util.ArrayDeque;
import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    int n = in.nextInt();
    ArrayDeque<Integer> Queue1 = new ArrayDeque<>();
    ArrayDeque<Integer> Queue2 = new ArrayDeque<>();
    int z;
    for (int i = 1; i <= n; i++) {
      while (Queue1.size() < Queue2.size()) {
        z = Queue2.pollFirst();
        Queue1.addLast(z);
      }
      char ch = in.next().charAt(0);
      if (ch == '-') {
        z = Queue1.pollFirst();
        System.out.println(z);
      } else if (ch == '+') {
        z = in.nextInt();
        Queue2.addLast(z);
      } else {
        z = in.nextInt();
        Queue2.addFirst(z);
      }
    }
  }
}
