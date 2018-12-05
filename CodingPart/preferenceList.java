package CodingPart;
import java.util.*;
/**
 * Created by yuqishi on 10/28/18.
 */
public class preferenceList {

    //method 1: topological sort
    public List<Integer> getList(List<List<Integer>> preferences) {
        //construct the graph
        Map<Integer, Set<Integer>> graph = new HashMap<>();
        Map<Integer, Integer> inDegree = new HashMap<>();
        for (List<Integer> l : preferences) {
            for (int i = 0; i < l.size() - 1; i++) {
                int from = l.get(i);
                int to = l.get(i + 1);
                if (!graph.containsKey(from)) {
                    graph.put(from, new LinkedHashSet<>()); //use linkedHashSet to handle tie, use the order they enter the queue
                }
                graph.get(from).add(to);

                if (!inDegree.containsKey(from)) {
                    inDegree.put(from, 0);
                }
                inDegree.put(to, inDegree.getOrDefault(to, 0) + 1);
            }
        }

        //for inDegree == 0
        Queue<Integer> q = new LinkedList<>();
        for (Map.Entry<Integer, Integer> entry : inDegree.entrySet()) {
            if (entry.getValue() == 0) {
                q.offer(entry.getKey());
            }
        }

        List<Integer> res = new ArrayList<>();
        while (!q.isEmpty()) {
            int cur = q.poll();
            res.add(cur);
            if (!graph.containsKey(cur)) {
                continue;
            }
            for (int neighbor : graph.get(cur)) {
                inDegree.put(neighbor, inDegree.get(neighbor) - 1);
                if (inDegree.get(neighbor) == 0) {
                    q.offer(neighbor);
                }
            }
        }
        return res;
    }

    public static void main(String[] argus) {
        List<List<Integer>> list = new ArrayList<>();
        list.add(Arrays.asList(3,5,7,9));
        list.add(Arrays.asList(2,3,8));
        list.add(Arrays.asList(5,8));
        preferenceList pl = new preferenceList();
        List<Integer> res = pl.getList(list);
        System.out.println(res);
    }
}
