import java.util.ArrayList;
import java.util.Scanner;

public class Main {
  static class Node {
    int key;
    int value;
  }

  public static int Hashfunc(int key, int n) {
    return key % n;
  }

  public static void add(int x, ArrayList<Node>[] Hash, int n) {
    int ind = Hashfunc(x, n);
    ArrayList<Node> list = Hash[ind];
    Node z = null;
    for (Node i : list) {
      if (i.key == x) {
        z = i;
      }
    }
    if (z == null) {
      Node nov = new Node();
      nov.key = x;
      nov.value = 1;
      list.add(nov);
    } else {
      z.value += 1;
    }
  }

  public static boolean find(int x, ArrayList<Node>[] Hash, int n) {
    int ind = Hashfunc(x, n);
    ArrayList<Node> list = Hash[ind];
    Node z = null;
    for (Node i : list) {
      if (i.key == x) {
        z = i;
      }
    }
    if (z == null || z.value == 0) {
      return false;
    } else {
      z.value -= 1;
      return true;
    }
  }

  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    ArrayList<Integer> answer = new ArrayList<>();
    int n = in.nextInt();
    ArrayList<Node>[] Hash = new ArrayList[n];
    for (int i = 0; i < n; i++) {
      Hash[i] = new ArrayList<>();
    }
    for (int i = 0; i < n; i++) {
      int x = in.nextInt();
      add(x, Hash, n);
    }
    int q = in.nextInt();
    for (int i = 0; i < q; i++) {
      int x = in.nextInt();
      if (find(x, Hash, n)) {
        answer.add(x);
      }
    }
    System.out.println(answer.size());
    for (int i : answer) {
      System.out.print(i + " ");
    }
  }
}
