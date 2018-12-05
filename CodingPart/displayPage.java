package CodingPart;
import java.util.*;
/**
 * Created by yuqishi on 11/1/18.
 */
public class displayPage {

    public static void main(String[] argus) {
//        List<String> list = new ArrayList<>(Arrays.asList("1", "4", "20", "6", "6", "1",
//                "6","7","6","2","2","3","2"));
//        List<String> res = displayPages(list, 5);
//        System.out.println(res);
        /*
        *
        *   1,28,310.6,SF 
            4,5,204.1,SF 
            20,7,203.2,Oakland 
            6,8,202.2,SF 
            6,10,199.1,SF 
            1,16,190.4,SF 
            6,29,185.2,SF 
            7,20,180.1,SF 
            6,21,162.1,SF
            2,18,161.2,SF 
            2,30,149.1,SF
            3,76,146.2,SF 
            2,14,141.1,San Jose
        * */


        List<String> lists = new ArrayList<>(Arrays.asList(
                "1,28,310.6,SF",
                "4,5,204.1,SF",
                "20,7,203.2,Oakland",
                "6,8,202.2,SF",
                "6,10,199.1,SF",
                "1,16,190.4,SF",
                "6,29,185.2,SF",
                "7,20,180.1,SF",
                "6,21,162.1,SF",
                "2,18,161.2,SF",
                "2,30,149.1,SF",
                "3,76,146.2,SF",
                "2,14,141.1,San Jose"));
        List<String> res = getPage(lists, 5);
        for (String s : res) {
            System.out.println(s);
        }
    }

    public static List<String> getPage(List<String> lists, int pageSize) {
        List<String> res = new ArrayList<>();
        Set<Integer> set = new HashSet<>();
        int count = 0;
        boolean toEnd = false;
        Iterator<String> iterator = lists.iterator();
        while (iterator.hasNext()) {
            String cur = iterator.next();
            int id = Integer.parseInt(cur.split(",")[0]);
            if (!set.contains(id) || toEnd) {
                res.add(cur);
                set.add(id);
                count++;
                iterator.remove();
            }
            if (count == pageSize) { // 不能用set.size() == pageSize set里面不能添加重复
                res.add("/");
                set.clear();
                count = 0;
                iterator = lists.iterator();
            }
            if (!iterator.hasNext()) {
                toEnd = true;
                iterator = lists.iterator();
            }

        }
        return res;
    }
}
