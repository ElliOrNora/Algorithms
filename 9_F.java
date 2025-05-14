import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    private static long maxFlow(List<List<Edge>> graph, int s, int t) {
        long flow = 0;
        int[] level = new int[graph.size()];
        while (bfs(graph, s, t, level)) {
            int[] ptr = new int[graph.size()];
            while (true) {
                long pushed = dfs(graph, ptr, level, s, t, Long.MAX_VALUE);
                if (pushed == 0) {
                    break;
                }
                flow += pushed;
            }
        }
        return flow;
    }

    private static boolean bfs(List<List<Edge>> graph, int s, int t, int[] level) {
        Arrays.fill(level, -1);
        level[s] = 0;
        Queue<Integer> q = new LinkedList<>();
        q.add(s);
        while (!q.isEmpty()) {
            int u = q.poll();
            for (Edge e : graph.get(u)) {
                if (e.capacity > 0 && level[e.to] == -1) {
                    level[e.to] = level[u] + 1;
                    q.add(e.to);
                }
            }
        }
        return level[t] != -1;
    }

    private static long dfs(List<List<Edge>> graph, int[] ptr, int[] level, int u, int t, long flow) {
        if (u == t) {
            return flow;
        }
        for (; ptr[u] < graph.get(u).size(); ptr[u]++) {
            Edge e = graph.get(u).get(ptr[u]);
            if (e.capacity > 0 && level[e.to] == level[u] + 1) {
                long pushed = dfs(graph, ptr, level, e.to, t, Math.min(flow, e.capacity));
                if (pushed > 0) {
                    e.capacity -= pushed;
                    e.rev.capacity += pushed;
                    return pushed;
                }
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        List<OriginalEdge> originalEdges = new ArrayList<>();
        List<List<Edge>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < m; i++) {
            int u = in.nextInt();
            int v = in.nextInt();
            int c = in.nextInt();
            originalEdges.add(new OriginalEdge(u, v, c, i + 1));
            Edge e1 = new Edge(v, c, i + 1);
            Edge e1Rev = new Edge(u, 0, i + 1);
            e1.rev = e1Rev;
            e1Rev.rev = e1;
            graph.get(u).add(e1);
            graph.get(v).add(e1Rev);
            Edge e2 = new Edge(u, c, i + 1);
            Edge e2Rev = new Edge(v, 0, i + 1);
            e2.rev = e2Rev;
            e2Rev.rev = e2;
            graph.get(v).add(e2);
            graph.get(u).add(e2Rev);
        }
        maxFlow(graph, 1, n);
        boolean[] reachable = new boolean[n + 1];
        Queue<Integer> q = new LinkedList<>();
        q.add(1);
        reachable[1] = true;
        while (!q.isEmpty()) {
            int u = q.poll();
            for (Edge e : graph.get(u)) {
                if (e.capacity > 0 && !reachable[e.to]) {
                    reachable[e.to] = true;
                    q.add(e.to);
                }
            }
        }
        List<Integer> cutEdges = new ArrayList<>();
        long sum = 0;
        for (OriginalEdge e : originalEdges) {
            if (reachable[e.u] && !reachable[e.v] || !reachable[e.u] && reachable[e.v]) {
                cutEdges.add(e.index);
                sum += e.c;
            }
        }
        Collections.sort(cutEdges);
        System.out.println(cutEdges.size() + " " + sum);
        if (cutEdges.isEmpty()) {
            System.out.println();
        } else {
            StringBuilder sb = new StringBuilder();
            for (Integer idx : cutEdges) {
                sb.append(idx).append(' ');
            }
            sb.deleteCharAt(sb.length() - 1);
            System.out.println(sb);
        }
    }

    static class Edge {
        int to;
        Edge rev;
        long capacity;
        int originalEdgeIndex;

        Edge(int to, long capacity, int originalEdgeIndex) {
            this.to = to;
            this.capacity = capacity;
            this.originalEdgeIndex = originalEdgeIndex;
        }
    }

    static class OriginalEdge {
        int u;
        int v;
        int c;
        int index;

        OriginalEdge(int u, int v, int c, int index) {
            this.u = u;
            this.v = v;
            this.c = c;
            this.index = index;
        }
    }
}
