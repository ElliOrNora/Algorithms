import java.util.Scanner;

public class Main {
  static final int INF = 1000000000;

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    String u = sc.nextLine().trim();
    String v = sc.nextLine().trim();
    int k = Integer.parseInt(sc.nextLine().trim());
    int n = u.length();
    int m = v.length();
    if (Math.abs(n - m) > k) {
      System.out.println(-1);
      return;
    }
    int[][][] dp = new int[n + 1][m + 1][k + 1];
    for (int i = 0; i <= n; i++) {
      for (int j = 0; j <= m; j++) {
        for (int e = 0; e <= k; e++) {
          dp[i][j][e] = INF;
        }
      }
    }
    dp[0][0][0] = 0;
    for (int i = 0; i <= n; i++) {
      for (int j = 0; j <= m; j++) {
        for (int e = 0; e <= k; e++) {
          int cur = dp[i][j][e];
          if (cur != INF) {
            if (i < n && j < m) {
              int cost = (u.charAt(i) == v.charAt(j)) ? 0 : 1;
              dp[i + 1][j + 1][e] = Math.min(dp[i + 1][j + 1][e], cur + cost);
            }
            if (i < n && j < m && e + 1 <= k) {
              dp[i + 1][j + 1][e + 1] = Math.min(dp[i + 1][j + 1][e + 1], cur);
            }
            if (j < m && e + 1 <= k) {
              dp[i][j + 1][e + 1] = Math.min(dp[i][j + 1][e + 1], cur);
            }
            if (i < n && e + 1 <= k) {
              dp[i + 1][j][e + 1] = Math.min(dp[i + 1][j][e + 1], cur);
            }
          }
        }
      }
    }
    int ans = INF;
    for (int e = 0; e <= k; e++) {
      ans = Math.min(ans, dp[n][m][e]);
    }
    System.out.println(ans == INF ? -1 : ans);
  }
}
