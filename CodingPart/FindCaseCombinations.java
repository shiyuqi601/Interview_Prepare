package CodingPart;
import java.util.*;
/**
 * Created by yuqishi on 10/31/18.
 */
public class FindCaseCombinations {


    public List<String> findSolution(String s) {
        List<String> res = new ArrayList<>();
        StringBuilder sb = new StringBuilder(s);
        dfs(sb, res, 0, s.length());
        return res;

    }
    //dfs
    private void dfs(StringBuilder sb, List<String> res, int i, int len) {
        if (i == len) {
            res.add(sb.toString());
            return;
        }
        char cur = sb.charAt(i);
        char cLow = Character.toLowerCase(cur);
        char cUp = Character.toUpperCase(cur);

        sb.setCharAt(i, cLow);
        dfs(sb, res, i + 1, len);

        sb.setCharAt(i, cUp);
        dfs(sb, res, i + 1, len);

        sb.setCharAt(i, cur);
    }

    //bit manipulation
    public List<String> findSolution_bitVersion(String s) {
        List<String> res = new ArrayList<>();
        char[] arr = s.toCharArray();
        int n = s.length();
    /*
    Left Shift ( << ): Left shift operator is a binary operator which shift
    the some number of bits, in the given bit pattern, to the left and append
    0 at the end. Left shift is equivalent to multiplying the bit pattern
    with 2k ( if we are shifting k bits ).
    */
        for (int i = 0; i < (1 << n); i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < n; j++) {
        /* Right Shift ( >> ): Right shift operator is a binary operator
        which shift the some number of bits, in the given bit pattern,
        to the right and append 1 at the end. Right shift is equivalent
          to dividing the bit pattern with 2k ( if we are shifting k bits ).*/
                int bit = (i >> j) & 1;
                sb.append(bit == 1 ? Character.toUpperCase(arr[j]) : Character.toLowerCase(arr[j]));
            }
            res.add(sb.toString());
        }
        return res;
    }

    public static void main(String[] argus) {
        FindCaseCombinations fc = new FindCaseCombinations();
        List<String> res = fc.findSolution_bitVersion("abc");
        System.out.println(res);
    }
}
