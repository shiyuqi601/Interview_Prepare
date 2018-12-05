package CodingPart;
import java.util.*;

/**
 * Created by yuqishi on 11/13/18.
 */
public class cheapestFlights {
//
//    public int minCost(List<String> lines, String source, String target, int K) {
//        Map<String, Flight> map = new HashMap<>();
//        for (String line : lines) {
//            String[] arr = line.split(" ");
//            if (!map.containsKey(arr[0])) {
//                map.put(arr[0], new Flight(arr[0]));
//            }
//            if (!map.containsKey(arr[1])) {
//                map.put(arr[1], new Flight(arr[1]));
//            }
//            map.get(arr[0]).nodes.put(arr[1], Integer.valueOf(arr[2]));
//        }
//        Queue<String> q = new LinkedList<>();
//        q.offer(source);
//        Queue<Integer> cost = new LinkedList<>();
//        cost.offer(0);
//        boolean find = false;
//        int step = 0;
//        while (!q.isEmpty()) {
//            int size = q.size();
//            for (int i = 0; i < size; i++) {
//                String name = q.poll();
//                Flight cur = map.get(name);
//                int curCost = cost.poll();
//                cur.minCost = Math.min(cur.minCost, curCost);
//
//                for (String next : map.get(name).nodes.keySet()) {
//                    if (next.equals(target) && step <= K) {
//                        find = true;
//                    }
//                    int singleCost = cur.nodes.get(next);
//                    if (map.get(next).minCost > curCost + singleCost &&
//                            step <= K) {
//                        map.get(next).minCost = singleCost + curCost;
//                        cost.offer(singleCost + curCost);
//                        q.offer(next);
//                    }
//                }
//            }
//            step++;
//            if (step > K) {
//                break;
//            }
//        }
//        return find ? map.get(target).minCost : -1;
//    }

    public int findCheapestPrice(int n, List<String> list, String source, String target, int K) {
        Map<String, Set<Flight>> graph = new HashMap<>();
        Map<String, Integer> steps = new HashMap<>();
        Map<String, Integer> prices = new HashMap<>();
        for (String line : list) {
            String[] arr = line.split(" ");
            if (!graph.containsKey(arr[0])) {
                graph.put(arr[0], new HashSet<>());
            }
            graph.get(arr[0]).add(new Flight(arr[1], -1, Integer.valueOf(arr[2])));
            steps.put(arr[0], Integer.MAX_VALUE);
            steps.put(arr[1], Integer.MAX_VALUE);
            prices.put(arr[0], Integer.MAX_VALUE);
            prices.put(arr[1], Integer.MAX_VALUE);
        }

        PriorityQueue<Flight> pq = new PriorityQueue<>(new Comparator<Flight>(){

            public int compare(Flight o1, Flight o2) {
                return o1.cost - o2.cost;
            }
        });

        pq.offer(new Flight(source, -1, 0));
        steps.put(source, -1);
        prices.put(source, 0);

        while (!pq.isEmpty()) {
            Flight cur = pq.poll();
            if (cur.city.equals(target)) {
                return prices.get(target);
            }
            if (cur.step + 1 > K) {
                continue;
            }
            if (!graph.containsKey(cur.city)) {
                continue;
            }
            for (Flight next : graph.get(cur.city)) {
                int nextStep = cur.step + 1;
                int nextPrice = cur.cost + next.cost;
                if (nextPrice < prices.get(next.city) || nextStep < steps.get(next.city)) {
                    pq.offer(new Flight(next.city, nextStep, nextPrice));
                    prices.put(next.city, nextPrice);
                    steps.put(next.city, nextStep);
                }
            }
        }
        return prices.get(target) == Integer.MAX_VALUE ? -1 : prices.get(target);
    }

    public static void main(String[] argus) {
        List<String> lines = new ArrayList<>(Arrays.asList("A B 100", "A C 400",
                "B C 100", "C D 100", "C A 10"));
        cheapestFlights cf = new cheapestFlights();
        System.out.println(cf.findCheapestPrice(4, lines, "A", "D", 0));
        System.out.println(cf.findCheapestPrice(4, lines, "A", "D", 1));
        System.out.println(cf.findCheapestPrice(4, lines, "A", "D", 2));

    }

//    public static class UnitTest {
//        @Test
//        public void test1() {
//            cheapestFlights sol = new cheapestFlights();
//            List<String> lines = new ArrayList<>();
//            lines.add("A B 100");
//            lines.add("A C 400");
//            lines.add("B C 100");
//            lines.add("C D 100");
//            lines.add("C A 10");
//            assertEquals(-1, sol.minCost(lines, "A", "D", 0));
//            assertEquals(500, sol.minCost(lines, "A", "D", 1));
//            assertEquals(300, sol.minCost(lines, "A", "D", 2));
//        }
//
//        @Test
//        public void test2() {
//            cheapestFlights sol = new cheapestFlights();
//            List<String> lines = new ArrayList<>();
//            lines.add("A B 100");
//            lines.add("A C 500");
//            lines.add("B C 100");
//            assertEquals(500, sol.minCost(lines, "A", "C", 0));
//            assertEquals(200, sol.minCost(lines, "A", "C", 1));
//        }
//    }
}

//class Flight{
//    String name;
//    int minCost;
//    Map<String, Integer> nodes;
//    public Flight(String name) {
//        this.name = name;
//        minCost = Integer.MAX_VALUE;
//        nodes = new HashMap<>();
//    }
//}
class Flight {
    String city;
    int step;
    int cost;
    public Flight(String city, int step, int cost) {
        this.city = city;
        this.step = step;
        this.cost = cost;
    }
}