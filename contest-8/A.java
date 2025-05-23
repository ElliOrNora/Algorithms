import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Stack;

public class Main {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    int n = in.nextInt();
    int m = in.nextInt();
    long s = in.nextLong();
    ArrayList<Edge>[] graph = new ArrayList[n + 1];
    for (int i = 1; i <= n; i++) graph[i] = new ArrayList<>();
    for (int i = 0; i < m; i++) {
      int u = in.nextInt();
      int v = in.nextInt();
      long c = in.nextLong();
      long t = in.nextLong();
      graph[u].add(new Edge(v, c, t));
      graph[v].add(new Edge(u, c, t));
    }
    PriorityQueue<Node> heep = new PriorityQueue<>();
    heep.add(new Node(1, 0L, 0L, null));
    long[] minCost = new long[n + 1];
    Arrays.fill(minCost, Long.MAX_VALUE);
    minCost[1] = 0L;
    Node result = null;
    while (!heep.isEmpty()) {
      Node current = heep.remove();
      if (current.vertex == n) { result = current; break; }
      if (current.totalCost <= minCost[current.vertex]) {
        for (int j = 0; j < graph[current.vertex].size(); j++) {
          Edge edge = graph[current.vertex].get(j);
          long newTime = current.totalTime + edge.time;
          if (newTime <= s) {
            long newCost = current.totalCost + edge.cost;
            if (newCost < minCost[edge.to]) {
              minCost[edge.to] = newCost;
              heep.add(new Node(edge.to, newCost, newTime, current));
            }
          }
        }
      }
    }
    if (result == null) {
      System.out.println(-1);
    } else {
      System.out.println(result.totalCost);
      Stack<Integer> path = new Stack<>();
      for (Node node = result; node != null; node = node.parent) path.push(node.vertex);
      System.out.println(path.size());
      while (!path.isEmpty()) System.out.print(path.pop() + " ");
      System.out.println();
    }
  }
  
  static class Edge {
    int to;
    long cost;
    long time;

    Edge(int t, long c, long tm) {
      to = t; cost = c; time = tm;
    }
  }

  static class Node implements Comparable<Node> {
    int vertex;
    long totalCost;
    long totalTime;
    Node parent;

    Node(int v, long c, long t, Node p) { 
      vertex = v;
      totalCost = c;
      totalTime = t;
      parent = p;
    }

    public int compareTo(Node other) { 
      return Long.compare(totalCost, other.totalCost);
    }
  }
}
