import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    int n = in.nextInt();
    int[] a = new int[n];
    for (int i = 0; i < n; i++) {
      a[i] = in.nextInt();
    }
    int[] dp = new int[n];
    int[] prev = new int[n];
    int len = 0;
    for (int i = 0; i < n; i++) {
      int l = 0, r = len;
      while (l < r) {
        int mid = l + (r - l) / 2;
        if (a[dp[mid]] >= a[i]) {
          l = mid + 1;
        } else {
          r = mid;
        }
      }
      if (l < len) {
        dp[l] = i;
      } else {
        dp[len] = i;
        len++;
      }
      prev[i] = (l > 0) ? dp[l - 1] : -1;
    }
    List<Integer> indices = new ArrayList<>();
    int i = dp[len - 1];
    while (i != -1) {
      indices.add(i + 1);
      i = prev[i];
    }
    System.out.println(len);
    for (i = indices.size() - 1; i >= 0; i--) {
      System.out.print(indices.get(i) + " ");
    }
  }
}
