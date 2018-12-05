package CodingPart;
import java.util.*;
/**
 * Created by yuqishi on 10/29/18.
 */
public class tenWizard {

    //BFS
    public static List<Integer> getShortestPath(List<List<Integer>> wizards, int source, int target) {
        int n = wizards.size();
        Map<Integer, Wizard> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.put(i, new Wizard(i, Integer.MAX_VALUE));
        }
        map.get(source).cost = 0;
        Queue<Wizard> q = new LinkedList<>();
        q.offer(map.get(source));

        int[] parent = new int[n];
        while (!q.isEmpty()) {
            Wizard cur = q.poll();
            for (int next : wizards.get(cur.id)) {
                int cost = cur.cost + (int)Math.pow((next - cur.id), 2);
                Wizard nextWizard = map.get(next);
                if (cost < nextWizard.cost) {
                    nextWizard.cost = cost;
                    parent[next] = cur.id;
                }
                q.offer(nextWizard);
            }
        }
        System.out.println("the minimum cost = " + map.get(target).cost);
        List<Integer> res = new ArrayList<>();
        res.add(target);
        int x = target;
        while (x != source) {
            res.add(parent[x]);
            x = parent[x];
        }
        Collections.reverse(res);
        return res;
    }

    public static List<Integer> getMinCost(List<List<Integer>> lists, int source, int target) {
        List<Integer> res = new ArrayList<>();
        Map<Integer, int[]> map = new HashMap<>();
        int[] parent = new int[lists.size()];
        for (int i = 0; i < lists.size(); i++) {
            parent[i] = i;
            map.put(i, new int[]{i, Integer.MAX_VALUE});
        }
        map.put(source, new int[]{source, 0});
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>(){
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1];
            }
        });
        pq.offer(map.get(source));

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            List<Integer> neighbors = lists.get(cur[0]);
            for (int n : neighbors) {
                int[] neighbor = map.get(n);
                int id = neighbor[0];
                int tempCost = cur[1] + (int)Math.pow(id - cur[0], 2);
                if (tempCost < neighbor[1]) {
                    pq.remove(neighbor);
                    map.put(id, new int[]{id, tempCost});
                    pq.offer(map.get(id));
                    parent[id] = cur[0];
                }
            }
        }
        System.out.println("total cost is : " + map.get(target)[1]);
        res.add(target);
        int i = target;
        while (i != source) {
            res.add(parent[i]);
            i = parent[i];
        }
        Collections.reverse(res);
        return res;
    }

    public static void main(String[] argus) {
        tenWizard tw = new tenWizard();
        List<List<Integer>> wizards = new ArrayList<>();
        wizards.add(Arrays.asList(1,2));
        wizards.add(Arrays.asList(3));
        wizards.add(Arrays.asList(3,4));
        wizards.add(Arrays.asList(4));
        wizards.add(new ArrayList<>());
        List<Integer> res = tw.getShortestPath(wizards, 0, 4);
        System.out.print(res);


        List<List<Integer>> lists = new ArrayList<>();
        List<Integer> list_1 = new ArrayList<>(Arrays.asList(1,5,9));
        List<Integer> list_2 = new ArrayList<>(Arrays.asList(2,3,9));
        List<Integer> list_3 = new ArrayList<>(Arrays.asList(4));
        List<Integer> list_4 = new ArrayList<>(Arrays.asList(9));

        lists.add(list_1);
        lists.add(list_2);
        lists.add(list_3);
        lists.add(new ArrayList<>());
        lists.add(new ArrayList<>());
        lists.add(list_4);
        lists.add(new ArrayList<>());
        lists.add(new ArrayList<>());
        lists.add(new ArrayList<>());
        lists.add(new ArrayList<>());

        System.out.println(getMinCost(lists, 0, 9));
    }
}

class Wizard {
    int id;
    int cost;
    public Wizard(int id, int cost) {
        this.id = id;
        this.cost = cost;
    }
}
