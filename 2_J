import java.util.Scanner;

public class Main {
  public static void push(int x, int[][] stack, int l) {
    stack[l][0] = x;
    if (l != 0) {
      stack[l][1] = Math.min(x, stack[l - 1][1]);
    } else {
      stack[l][1] = x;
    }
  }

  public static void restack(int[][] originStack, int[][] targetStack, int l) {
    for (int i = 0; i < l; i++) {
      targetStack[i][0] = originStack[l - 1 - i][0];
      if (i != 0) {
        targetStack[i][1] = Math.min(targetStack[i - 1][1], targetStack[i][0]);
      } else {
        targetStack[i][1] = targetStack[i][0];
      }
    }
  }

  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    int n = in.nextInt();
    int[][] stack1 = new int[n][2];
    int[][] stack2 = new int[n][2];
    int l1 = 0;
    int l2 = 0;
    for (int i = 0; i < n; i++) {
      String s = in.next();
      switch (s) {
        case "enqueue":
          int x = in.nextInt();
          push(x, stack1, l1);
          l1 += 1;
          System.out.println("ok");
          break;
        case "dequeue":
        case "front":
          if (l2 == 0) {
            if (l1 == 0) {
              System.out.println("error");
              break;
            } else {
              restack(stack1, stack2, l1);
              l2 = l1;
              l1 = 0;
            }
          }
          System.out.println(stack2[l2 - 1][0]);
          if (s.equals("dequeue")) {
            l2 -= 1;
          }
          break;
        case "size":
          System.out.println(l1 + l2);
          break;
        case "clear":
          l1 = 0;
          l2 = 0;
          System.out.println("ok");
          break;
        case "min":
          if (l1 == 0) {
            if (l2 == 0) {
              System.out.println("error");
            } else {
              System.out.println(stack2[l2 - 1][1]);
            }
          } else {
            if (l2 == 0) {
              System.out.println(stack1[l1 - 1][1]);
            } else {
              System.out.println(Math.min(stack1[l1 - 1][1], stack2[l2 - 1][1]));
            }
          }
          break;
      }
    }
  }
}
