import java.util.ArrayList;
import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    int n = in.nextInt();
    int m = in.nextInt();
    int[] W = new int[n + 1];
    int[] S = new int[n + 1];
    for (int i = 1; i <= n; i++) {
      W[i] = in.nextInt();
    }
    for (int i = 1; i <= n; i++) {
      S[i] = in.nextInt();
    }
    int[][] dp = new int[n + 1][m + 1];
    for (int i = 1; i <= n; i++) {
      for (int j = 1; j <= m; j++) {
        if (j - W[i] >= 0) {
          dp[i][j] = Math.max(dp[i - 1][j - W[i]] + S[i], dp[i - 1][j]);
        } else {
          dp[i][j] = dp[i - 1][j];
        }
      }
    }
    ArrayList<Integer> I = new ArrayList<>();
    int i = n;
    int j = m;
    while (i != 0 & j != 0) {
      if ((j - W[i]) < 0) {
        i -= 1;
      } else if (dp[i - 1][j - W[i]] + S[i] >= dp[i - 1][j]) {
        j -= W[i];
        I.add(i);
        i -= 1;
      } else {
        i -= 1;
      }
    }
    for (i = I.size() - 1; i >= 0; i--) {
      System.out.println(I.get(i));
    }
  }
}
