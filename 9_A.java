import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int k = in.nextInt();
        List<Integer>[] graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
            while (true) {
                int num = in.nextInt();
                if (num == 0) {
                    break;
                }
                graph[i].add(num);
            }
        }
        int[] to = new int[k + 1];
        Arrays.fill(to, -1);
        boolean[] visited = new boolean[n + 1];
        int size = 0;
        for (int u = 1; u <= n; u++) {
            Arrays.fill(visited, false);
            if (dfs(u, graph, to, visited)) {
                size++;
            }
        }
        System.out.println(size);
        for (int v = 1; v <= k; v++) {
            if (to[v] != -1) {
                System.out.println(to[v] + " " + v);
            }
        }
    }

    static boolean dfs(int u, List<Integer>[] graph, int[] to, boolean[] visited) {
        if (visited[u]) {
            return false;
        }
        visited[u] = true;
        for (int i = 0; i < graph[u].size(); i++) {
            int v = graph[u].get(i);
            if (to[v] == -1) {
                to[v] = u;
                return true;
            } else {
                if (dfs(to[v], graph, to, visited)) {
                    to[v] = u;
                    return true;
                }
            }
        }
        return false;
    }
}
