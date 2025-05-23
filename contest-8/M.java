import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    int n = in.nextInt();
    int m = in.nextInt();
    ArrayList<Edge> graph = new ArrayList<>();
    for (int i = 0; i < m; i++) {
      int u = in.nextInt();
      int v = in.nextInt();
      int c = in.nextInt();
      graph.add(new Edge(u, v, c));
    }
    Collections.sort(graph);
    DSU dsu = new DSU(n);
    long totalWeight = 0;
    int edgesAdded = 0;
    for (int i = 0; i < graph.size() && edgesAdded < n - 1; i++) {
      Edge edge = graph.get(i);
      if (dsu.union(edge.u, edge.v)) {
        totalWeight += edge.c;
        edgesAdded++;
      }
    }
    System.out.println(totalWeight);
  }
  
  static class Edge implements Comparable<Edge> {
    int u;
    int v;
    int c;

    Edge(int u, int v, int c) {
      this.u = u;
      this.v = v;
      this.c = c;
    }

    @Override
    public int compareTo(Edge other) {
      return Integer.compare(this.c, other.c);
    }
  }

  static class DSU {
    int[] parent;
    int[] rank;

    DSU(int n) {
      parent = new int[n + 1];
      rank = new int[n + 1];
      for (int i = 1; i <= n; i++) {
        parent[i] = i;
      }
    }

    public int find(int u) {
      if (parent[u] != u) {
        parent[u] = find(parent[u]);
      }
      return parent[u];
    }

    public boolean union(int u, int v) {
      int rootU = find(u);
      int rootV = find(v);
      if (rootU == rootV) {
        return false;
      }
      if (rank[rootU] > rank[rootV]) {
        parent[rootV] = rootU;
      } else if (rank[rootU] < rank[rootV]) {
        parent[rootU] = rootV;
      } else {
        parent[rootV] = rootU;
        rank[rootU]++;
      }
      return true;
    }
  }
}
