package CodingPart;
import java.util.*;
/**
 * Created by yuqishi on 10/22/18.
 */
public class CheapestFlights_KStops_787 {

    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
        int inf = 0x3f3f3f3f;
        int[] ds = new int[n];
        Arrays.fill(ds, inf);
        ds[src] = 0;

        int ans = ds[dst];
        for (int k = 0; k <= K; ++k) {
            int[] nds = ds.clone();
            for (int[] e : flights) {
                nds[e[1]] = Math.min(nds[e[1]], ds[e[0]] + e[2]);
            }
            ds = nds;
            ans = Math.min(ans, ds[dst]);
        }
        System.out.println(inf);
        System.out.println(Integer.MAX_VALUE);
        return ans == inf ? -1 : ans;
    }


    public int findCheapestPrice_2(int n, int[][] flights, int src, int dst, int K) {
        if (src == dst) {
            return 0;
        }
        int[][] graph = new int[n][n];
        for (int[] f : flights) {
            graph[f[0]][f[1]] = f[2];
        }

        int[] prices = new int[n];
        int[] steps = new int[n];
        Arrays.fill(prices, Integer.MAX_VALUE);
        Arrays.fill(steps, Integer.MAX_VALUE);

        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                return a[1] - b[1];
            }
        });
        pq.add(new int[]{src, 0, -1});
        prices[src] = 0;
        steps[src] = -1;
        while (!pq.isEmpty()) {
            int[] curr = pq.poll();

            if (curr[0] == dst) {
                return prices[dst];
            }
            for (int i = 0; i < n; i++) {
                if (graph[curr[0]][i] == 0 || curr[2] + 1 > K) {
                    continue;
                }
                int nextPrice = curr[1] + graph[curr[0]][i];
                int nextStep = curr[2] + 1;
                if (nextPrice >= prices[i] && nextStep >= steps[i]) {
                    continue;
                }
                pq.add(new int[]{i, nextPrice, nextStep});
                if (nextPrice < prices[i]) {
                    prices[i] = nextPrice;
                    steps[i] = nextStep;
                }
            }
        }
        return -1;
    }

    public static void main(String[] argus) {
//        n = 3, edges = [[0,1,100],[1,2,100],[0,2,500]]
//        src = 0, dst = 2, k = 1
        int[][] edges = new int[3][3];
        edges[0] = new int[]{0,1,100};
        edges[1] = new int[]{1,2,100};
        edges[2] = new int[]{0,2,500};
        CheapestFlights_KStops_787 cf = new CheapestFlights_KStops_787();
        System.out.print(cf.findCheapestPrice(3, edges, 0, 2, 0));
    }
}

