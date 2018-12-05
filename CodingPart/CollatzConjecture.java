package CodingPart;

import java.util.*;

/**
 * Created by yuqishi on 10/23/18.
 * 题目是给你公式，比如偶数的话除2，奇数的话就变成3*n+1，
 * 对于任何一个正数，数学猜想是最终他会变成1。
 * 每变一步步数加1，给一个上限，让找出范围内最长步数。
 * 比如7，变换到1是如下顺序：7->22->11->34->17->52->26->13->40->20->10->5->16->8->4->2->1, 总共需要17步。
 */
public class CollatzConjecture {

    public int findLongest(int limit) {
        Map<Integer, Integer> map = new HashMap<>();
        int res = 0;
        for (int i = 1; i <= limit; i++) {
            res = Math.max(res, getSteps(i, map));
        }
        return res;
    }
    private int getSteps(int num, Map<Integer, Integer> map) {
        if (num <= 1) {
            return 1;
        }
        if (map.containsKey(num)) {
            return map.get(num);
        }
        int res = 0;
        if (num % 2 == 0) {
            res = 1 + getSteps(num / 2, map);
        } else {
            res = 1 + getSteps(num * 3 + 1, map);
        }
        map.put(num, res);
        return res;
    }

    public static void main(String[] argus) {
        CollatzConjecture cc = new CollatzConjecture();
        System.out.println(cc.findLongest(7));
        System.out.println(cc.findLongest(5));
        System.out.println(cc.findLongest(1));
        System.out.println(cc.findLongest(0));


    }

}
