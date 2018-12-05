package CodingPart;
import java.util.*;
/**
 * Created by yuqishi on 11/18/18.
 */
public class PyramidTransitionMatrix {

    public boolean pyramidTransition(String bottom, List<String> allowed) {
        Map<String, Set<String>> map = new HashMap<>();
        for (String s : allowed) {
            String leaf = s.substring(0, 2);
            String parent = s.substring(2);
            if (!map.containsKey(leaf)) {
                map.put(leaf, new HashSet<>());
            }
            map.get(leaf).add(parent);
        }
        return dfs(bottom, map);
    }

    public boolean dfs(String bottom, Map<String, Set<String>> map) {
        if (bottom.length() == 1) {
            return true;
        }
        for (int i = 0; i < bottom.length() - 1; i++) {
            if (!map.containsKey(bottom.substring(i, i + 2))) {
                return false;
            }
        }
        List<String> nextLevels = new ArrayList<>();
        getNextLevels(bottom, "", map, nextLevels, 0);
        for (String next : nextLevels) {
            if (dfs(next, map)) {
                return true;
            }
        }
        return false;
    }

    public void getNextLevels(String bottom, String cur, Map<String, Set<String>> map, List<String> res, int startIndex) {
        if (startIndex == bottom.length() - 1) {
            res.add(cur);
            return;
        }
        String key = bottom.substring(startIndex, startIndex + 2);
        for (String next : map.get(key)) {
            cur += next;
            getNextLevels(bottom, cur, map, res, startIndex + 1);
            cur = cur.substring(0, cur.length() - 1);
        }
    }

    public static void main(String[] argus) {
        PyramidTransitionMatrix pt = new PyramidTransitionMatrix();
        List<String> list = new ArrayList<>(Arrays.asList("XYD", "YZE", "DEA", "FFF"));
        System.out.println(pt.pyramidTransition("XYZ", list));
    }
}
