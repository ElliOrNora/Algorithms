import java.util.ArrayList;
import java.util.Scanner;

public class Main {
  public static int Hashfunc(int key) {
    return Integer.hashCode(key) % 1000;
  }

  public static void add(int x, ArrayList<Integer>[] Hash) {
    int ind = Hashfunc(x);
    ArrayList<Integer> list = Hash[ind];
    boolean f = false;
    for (int i : list) {
      if (i == x) {
        f = true;
      }
    }
    if (!f) {
      list.addFirst(x);
    }
  }

  public static void remove(int key, ArrayList<Integer>[] Hash) {
    int ind = Hashfunc(key);
    ArrayList<Integer> list = Hash[ind];
    list.remove((Integer) key);
  }

  public static boolean find(int x, ArrayList<Integer>[] Hash) {
    int ind = Hashfunc(x);
    ArrayList<Integer> list = Hash[ind];
    boolean f = false;
    for (int i : list) {
      if (i == x) {
        f = true;
      }
    }
    return f;
  }

  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    int n = in.nextInt();
    ArrayList<Integer>[] Hash = new ArrayList[1000];
    for (int i = 0; i < 1000; i++) {
      Hash[i] = new ArrayList<>();
    }

    for (int i = 0; i < n; i++) {
      String line = in.next();
      char request = line.charAt(0);
      int x = in.nextInt();
      switch (request) {
        case '+':
          add(x, Hash);
          break;
        case '-':
          remove(x, Hash);
          break;
        case '?':
          String answer;
          if (find(x, Hash)) {
            answer = "YES";
          } else {
            answer = "NO";
          }
          System.out.println(answer);
          break;
      }
    }
  }
}
