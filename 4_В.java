import java.util.*;

public class Main {
  public static class Node {
    int value;
    int height;
    Node left;
    Node right;
  }

  static Node root;

  public static int getHeight(Node v) {
    if (v == null) {
      return 0;
    } else {
      return v.height;
    }
  }

  public static void updateHeight(Node v) {
    if (v != null) {
      v.height = 1 + Math.max(getHeight(v.left), getHeight(v.right));
    }
  }

  public static Node rotateRight(Node v) {
    Node node1 = v.left;
    Node node2 = v.left.right;
    node1.right = v;
    v.left = node2;
    updateHeight(v);
    updateHeight(node1);
    return node1;
  }

  public static Node rotateLeft(Node v) {
    Node node1 = v.right;
    Node node2 = node1.left;
    node1.left = v;
    v.right = node2;
    updateHeight(v);
    updateHeight(node1);
    return node1;
  }

  public static Node splay(Node v) {
    updateHeight(v);
    int delta = getHeight(v.left) - getHeight(v.right);
    if (delta > 1) {
      if (getHeight(v.left.left) < getHeight(v.left.right)) {
        v.left = rotateLeft(v.left);
      }
      return rotateRight(v);
    }

    if (delta < -1) {
      if (getHeight(v.right.right) < getHeight(v.right.left)) {
        v.right = rotateRight(v.right);
      }
      return rotateLeft(v);
    }

    return v;
  }

  public static Node insert(Node v, int x) {
    Node nov = new Node();
    nov.value = x;
    nov.height = 1;
    if (v == null) {
      return nov;
    }
    if (x < v.value) {
      v.left = insert(v.left, x);
    } else if (x > v.value) {
      v.right = insert(v.right, x);
    } else {
      return v;
    }

    return splay(v);
  }

  public static int lowerBound(Node v, int val) {
    int answer = -1;
    while (v != null) {
      if (v.value >= val) {
        answer = v.value;
        v = v.left;
      } else {
        v = v.right;
      }
    }
    return answer;
  }

  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    int q = in.nextInt();
    int prev = -1;
    for (int i = 0; i < q; i++) {
      char request = in.next().charAt(0);
      int x = in.nextInt();

      if (request == '+') {
        if (prev != -1) {
          x = (x + prev) % 1000000000;
        }
        root = insert(root, x);
        prev = -1;
      } else {
        int result = lowerBound(root, x);
        System.out.println(result);
        prev = result;
      }
    }
  }
}
