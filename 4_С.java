import java.util.*;

public class Main {
  public static class Node {
    String key;
    String value;
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

  public static Node insert(Node v, String key, String value) {
    Node nov = new Node();
    nov.key = key;
    nov.value = value;
    nov.height = 1;
    if (v == null) {
      return nov;
    }
    if (key.compareTo(v.key) < 0) {
      v.left = insert(v.left, key, value);
    } else if (key.compareTo(v.key) > 0) {
      v.right = insert(v.right, key, value);
    } else {
      return v;
    }

    return splay(v);
  }

  public static Node find(Node v, String key) {
    if (key.compareTo(v.key) < 0) {
      return find(v.left, key);
    } else if (key.compareTo(v.key) > 0) {
      return find(v.right, key);
    } else {
      return v;
    }
  }

  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    int n = in.nextInt();
    for (int i = 0; i < n; i++) {
      String login = in.next();
      String password = in.next();
      root = insert(root, login, password);
      root = insert(root, password, login);
    }
    int q = in.nextInt();
    for (int i = 0; i < q; i++) {
      String request = in.next();
      Node answer = find(root, request);
      System.out.println(answer.value);
    }
  }
}
