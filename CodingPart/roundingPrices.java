package CodingPart;
import java.util.*;
/**
 * Created by yuqishi on 11/7/18.
 */
public class roundingPrices {

    public int[] getRoundingPrices(double[] prices, int target) {
        int n = prices.length;
        int[] res = new int[n];

        PriorityQueue<double[]> pq = new PriorityQueue<>(new Comparator<double[]>() {
            @Override
            public int compare(double[] o1, double[] o2) {
                return (int) (o1[0] - o2[0]);
            }
        });
        int sum = 0;
        for (int i = 0; i < n; i++) {
            res[i] = (int) Math.ceil(prices[i]);
            sum += res[i];
            pq.offer(new double[]{prices[i] - res[i], i});
        }
        int diff = sum - target;
        while (diff > 0) {
            int index = (int) pq.poll()[1];
            res[index]--;
            diff--;
        }
        return res;
    }

    public int[] getRound(double[] prices, int target) {
        int n = prices.length;
        int[] res = new int[n];

        List<double[]> list = new ArrayList<>();
        int sum = 0;
        for (int i = 0; i < n; i++) {
            res[i] = (int) Math.ceil(prices[i]);
            sum += res[i];
            list.add(new double[]{res[i] - prices[i], i});
        }
        Collections.sort(list, new Comparator<double[]>() {
            @Override
            public int compare(double[] o1, double[] o2) {
                if (o2[0] - o1[0] > 0) {
                    return 1;
                }
                if (o2[0] - o1[0] < 0) {
                    return -1;
                }
                return 0;
            }
        });
        int diff = sum - target;
        int k = 0;
        while (diff > 0) {
            int index = (int) list.get(k++)[1];
            res[index]--;
            diff--;
        }
        return res;
    }

    static List<Integer> roundPricesToMatchTarget(List<Float> prices, int target) {
        List<Integer> res = new ArrayList<>();
        int n = prices.size();
        List<Float[]> list = new ArrayList<>();
        int sum = 0;
        for (int i = 0; i < n; i++) {
            res.add((int) Math.ceil(prices.get(i)));
            sum += res.get(i);
            list.add(new Float[]{res.get(i) - prices.get(i), (float) i});
        }
        Collections.sort(list, new Comparator<Float[]>() {
            public int compare(Float[] o1, Float[] o2) {
                if (o2[0] - o1[0] > 0) {
                    return 1;
                }
                if (o2[0] - o1[0] < 0) {
                    return -1;
                }
                return 0;
            }
        });
        int diff = sum - target;
        int k = 0;
        while (diff > 0) {
            int index = list.get(k++)[1].intValue();
            res.set(index, res.get(index) - 1);
            diff--;
        }
        return res;
    }

    public static List<Integer> roundNumbers_3(List<Double> list, int target) {
        List<Integer> res = new ArrayList<>();
        List<double[]> helperList = new ArrayList<>();
        double sum = 0;
        for (int i = 0; i < list.size(); i++) {
            double newNum = Math.ceil(list.get(i));
            sum += newNum;
            res.add((int) newNum);
            helperList.add(new double[]{newNum - list.get(i), (double) i});
        }
        Collections.sort(helperList, new Comparator<double[]>() {
            public int compare(double[] o1, double[] o2) {
                if (o1[0] - o2[0] > 0) {
                    return -1;
                } else if (o1[0] - o2[0] < 0) {
                    return 1;
                } else {
                    return 0;
                }
            }
        });

        int diff = (int) sum - target;
        int i = 0;
        while (diff > 0) {
            int index = (int) helperList.get(i++)[1];
            res.set(index, res.get(index) - 1);
            diff--;
        }
        return res;

    }

    public static void main(String[] argus) {
        roundingPrices rp = new roundingPrices();
        List<Double> list1 = new ArrayList<>(Arrays.asList(1.2, 3.7, 2.3, 4.8));
        List<Integer> res = rp.roundNumbers_3(list1, 12);
        for (int x : res) {
            System.out.print(x + " ");
        }


    }
}
