package CodingPart;
import java.util.*;
/**
 * Created by yuqishi on 10/29/18.
 */
public class MinVerticeTraverDirectedGraph {

    public List<Integer> getMin(int[][] edges, int n) {
        Map<Integer, Set<Integer>> graph = new HashMap<>();
        for (int i = 1; i <= n; i++) {
            graph.put(i, new HashSet<>());
        }
        for (int[] e : edges) {
            graph.get(e[0]).add(e[1]);
        }
        boolean[] visited = new boolean[n + 1];
        Set<Integer> res = new HashSet<>();
        for (int i = 1; i <= n; i++) {
            if (!visited[i]) {
                res.add(i);
                visited[i] = true;
                dfs(graph, visited, i, i, new HashSet<>(), res);
            }
        }
        return new ArrayList<>(res);
    }

    public void dfs(Map<Integer, Set<Integer>> graph, boolean[] visited, int cur, int start,
                    Set<Integer> curVisited, Set<Integer> res) {
        curVisited.add(cur);
        visited[cur] = true;
        for (int neighbor : graph.get(cur)) {
            if (res.contains(neighbor) && neighbor != start) {
                res.remove(neighbor);
            }
            if (!curVisited.contains(neighbor)) {
                dfs(graph, visited, neighbor, start, curVisited, res);
            }
        }
    }


    public static void main(String args[]) {
        MinVerticeTraverDirectedGraph mv = new MinVerticeTraverDirectedGraph();
        int[][] edges = {{2,2},{3,7},{4,8},{5,8},{6,3},{6,9},{7,4},{8,7},{9,2}, {9,6}};
        int[][] edges_2 = {{1,3},{1,2},{2,4},{4,1},{4,5},{5,6},{6,5}};
        int[][] edges_3 = {{1,2},{2,3},{3,4},{4,5},{5,6},{5,7},{6,1}};
        List<Integer> result = mv.getMin(edges_3, 7);
        for (int num : result) {
            System.out.print(num + " ");
        }
    }
}
