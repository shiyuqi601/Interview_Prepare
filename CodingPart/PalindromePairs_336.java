package CodingPart;
import java.util.*;
/**
 * Created by yuqishi on 10/21/18.
 */
public class PalindromePairs_336 {

    public List<List<Integer>> palindromePairs(String[] words) {
        List<List<Integer>> res = new ArrayList<>();
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < words.length; i++) {
            map.put(words[i], i);
        }

        for (int i = 0; i < words.length; i++) {
            String cur = words[i];
            //handle empty string
            if (cur.length() == 0) {
                for (int j = 0; j < words.length; j++) {
                    if (i == j) {
                        continue;
                    }
                    if (isPalindrome(words[j])) {
                        res.add(Arrays.asList(i, j));
                        res.add(Arrays.asList(j, i));
                    }
                }
            } else {
                //handle case like "abcd" & "dbca" -> "abcddbca"
                String curReverse = reverse(cur);
                if (map.containsKey(curReverse) && i != map.get(curReverse)) { //avoid "s"
                    res.add(Arrays.asList(i, map.get(curReverse)));
                }
            }

            //handle case like "sscb" & "bc" -> "bcsscb"
            //                 "acqlll" & "qca" -> "acqlllqca"
            for (int j = 1; j < cur.length(); j++) {
                String front = cur.substring(0, j);
                String back = cur.substring(j);
                String frontReverse = reverse(front);
                String backReverse = reverse(back);
                if (isPalindrome(front) && map.containsKey(backReverse)) {
                    res.add(Arrays.asList(map.get(backReverse), map.get(cur)));
                }
                if (isPalindrome(back) && map.containsKey(frontReverse)) {
                    res.add(Arrays.asList(map.get(cur), map.get(frontReverse)));
                }
            }
        }
        return res;
    }

    private boolean isPalindrome(String s) {
        int i = 0, j = s.length() - 1;
        while (i < j) {
            if (s.charAt(i) != s.charAt(j)) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }

    private String reverse(String s) {
        //return new StringBuilder(s).reverse().toString();
        int i = 0, j = s.length() - 1;
        char[] arr = s.toCharArray();
        while (i < j) {
            char temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
            i++;
            j--;
        }
        return String.valueOf(arr);
    }

    public List<List<Integer>> palindromePairs_2(String[] words) {
        List<List<Integer>> res = new ArrayList<>();
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < words.length; i++) {
            map.put(words[i], i);
        }

        for (int i = 0; i < words.length; i++) {
            String cur = words[i];
            for (int j = 0; j <= cur.length(); j++) {
                String front = cur.substring(0, j);
                String back = cur.substring(j);
                String frontReverse = reverse(front);
                String backReverse = reverse(back);
                if (isPalindrome(front) && map.containsKey(backReverse) && map.get(backReverse) != i) {
                    res.add(Arrays.asList(map.get(backReverse), map.get(cur)));
                }
                if (isPalindrome(back) && back.length() != 0 && //avoid "abcd", "dcba", add twice
                        map.containsKey(frontReverse) && map.get(frontReverse) != i) {
                    res.add(Arrays.asList(map.get(cur), map.get(frontReverse)));
                }
            }
        }
        return res;
    }


    public static void main(String[] argus) {
        PalindromePairs_336 pp = new PalindromePairs_336();
        String[] words = new String[]{"abcd", "dcba", "lls","s","sssll"};
        List<List<Integer>> res = pp.palindromePairs_2(words);
        for (List l : res) {
            System.out.println(l);
        }
    }
}
