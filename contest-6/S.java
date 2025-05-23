import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    int n = in.nextInt();
    int m = in.nextInt();
    in.nextLine();
    String[] mtx = new String[n];
    for (int i = 0; i < n; ++i) mtx[i] = in.nextLine();
    ArrayList<ArrayList<Integer>> masks = buildMasks(mtx, n, m);
    long[][] dp = new long[2][1 << 15];
    int curr = 0;
    for (int k = 0; k < masks.get(0).size(); ++k) {
      int mask = masks.get(0).get(k);
      dp[curr][mask] = 1;
    }
    for (int i = 0; i < m - 1; ++i) {
      int next = 1 - curr;
      Arrays.fill(dp[next], 0);
      for (int k = 0; k < masks.get(i).size(); ++k) {
        int prev = masks.get(i).get(k);
        if (dp[curr][prev] == 0) continue;
        boolean f = true;
        for (int j = 0; j < n - 1; ++j) {
          int b1 = (prev >> j) & 1;
          int b2 = (prev >> (j + 1)) & 1;
          if (b1 == b2) {
            f = false;
            break;
          }
        }
        int inv = (~prev) & ((1 << n) - 1);
        if (check(prev, inv, n)) dp[next][inv] = (dp[next][inv] + dp[curr][prev]) % module;
        if (f && check(prev, prev, n)) dp[next][prev] = (dp[next][prev] + dp[curr][prev]) % module;
      }
      curr = next;
    }
    long ans = 0;
    for (int k = 0; k < masks.get(m - 1).size(); ++k) {
      int mask = masks.get(m - 1).get(k);
      ans = (ans + dp[curr][mask]) % module;
    }
    System.out.println(ans);
  }
  private static int module = 1000000000 + 7;

  static boolean check(int prev, int curr, int n) {
    for (int i = 0; i < n - 1; ++i) {
      int sum =
          ((prev >> i) & 1) + ((prev >> (i + 1)) & 1) + ((curr >> i) & 1) + ((curr >> (i + 1)) & 1);
      if (sum != 2) return false;
    }
    return true;
  }

  static ArrayList<ArrayList<Integer>> buildMasks(String[] mtx, int n, int m) {
    ArrayList<ArrayList<Integer>> masks = new ArrayList<>();
    for (int i = 0; i < m; ++i) {
      int val = 0;
      int free = 0;
      for (int j = 0; j < n; ++j) {
        char c = mtx[j].charAt(i);
        if (c != '.') {
          if (c == '+') val |= (1 << j);
        } else free |= (1 << j);
      }
      int cnt = Integer.bitCount(free);
      ArrayList<Integer> lst = new ArrayList<>();
      for (int b = 0; b < (1 << cnt); ++b) {
        int mask = val;
        int tmp = b;
        for (int j = 0; j < n; ++j) {
          if ((free & (1 << j)) != 0) {
            mask |= (tmp & 1) << j;
            tmp >>= 1;
          }
        }
        lst.add(mask);
      }
      masks.add(lst);
    }
    return masks;
  }
}
