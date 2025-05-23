import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    int n = in.nextInt();
    ArrayList<Edge> edges = new ArrayList<>();
    for (int i = 1; i <= n; i++) {
      edges.add(new Edge(0, i, 0));
    }
    for (int i = 1; i <= n; i++) {
      for (int j = 1; j <= n; j++) {
        int a = in.nextInt();
        if (a != 100000) {
          edges.add(new Edge(i, j, a));
        }
      }
    }
    int infinity = Integer.MAX_VALUE;
    int[] dist = new int[n + 1];
    Arrays.fill(dist, infinity);
    dist[0] = 0;
    int[] prev = new int[n + 1];
    Arrays.fill(prev, -1);
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < edges.size(); j++) {
        Edge e = edges.get(j);
        if (dist[e.from] != infinity && dist[e.to] > dist[e.from] + e.weight) {
          dist[e.to] = dist[e.from] + e.weight;
          prev[e.to] = e.from;
        }
      }
    }
    Integer cycleNode = null;
    for (int i = 0; i < edges.size(); i++) {
      Edge e = edges.get(i);
      if (dist[e.from] != infinity && dist[e.to] > dist[e.from] + e.weight) {
        cycleNode = e.to;
        break;
      }
    }
    if (cycleNode == null) {
      System.out.println("NO");
    } else {
      for (int i = 0; i < n; i++) {
        cycleNode = prev[cycleNode];
      }
      ArrayList<Integer> cycle = new ArrayList<>();
      int u = cycleNode;
      do {
        cycle.add(u);
        u = prev[u];
      } while (u != cycleNode);
      cycle.add(cycleNode);
      System.out.println("YES");
      System.out.println(cycle.size());
      for (int j = cycle.size() - 1; j >= 0; j--) {
        System.out.print(cycle.get(j) + " ");
      }
    }
  }
  
  static class Edge {
    int from;
    int to;
    int weight;

    Edge(int from, int to, int weight) {
      this.from = from;
      this.to = to;
      this.weight = weight;
    }
  }
}
