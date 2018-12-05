package CodingPart;
import java.util.*;
/**
 * Created by yuqishi on 10/22/18.
 */
public class textJustification_68 {

    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> res = new ArrayList<>();
        int len = 0, start = 0, count = 0; //count the # of space
        //this    is    an
        //example  of text
        for (int i = 0; i < words.length; i++) {
            String cur = words[i];
            len += cur.length();
            if (len < maxWidth) {
                count++;
                len++;
                continue;
            }
            StringBuilder sb = new StringBuilder();
            //if is exactly suitable
            if (len == maxWidth) {
                for (int j = start; j <= start + count; j++) {
                    sb.append(words[j]);
                    sb.append(" ");
                }
                sb.deleteCharAt(sb.length() - 1);
                start = i + 1;
            } else {
                len -= cur.length();
                len--;
                count--;
                // if we only get one word
                if (count == 0) {
                    sb.append(words[start]);
                    for (int k = words[start].length(); k < maxWidth; k++) {
                        sb.append(" ");
                    }
                } else {
                    int sum = maxWidth - len;
                    int mod = sum % count;
                    int num = sum / count;
                    for (int j = start; j <= start + count; j++) {
                        sb.append(words[j]);
                        //we don't need space after the last word
                        if (j == start + count) {
                            break;
                        }
                        if (mod != 0) {
                            for (int k = 0; k <= num + 1; k++) {
                                sb.append(" ");
                            }
                            mod--;
                        } else {
                            for (int k = 0; k <= num; k++) {
                                sb.append(" ");
                            }
                        }
                    }
                }
                start = i;
                i--;
            }
            res.add(sb.toString());
            len = 0;
            count = 0;
        }
        //if the last line is exactly suitable
        if (len == 0) {
            return res;
        }
        //handle the last line
        StringBuilder sb = new StringBuilder();
        for (int j = start; j < start + count; j++) {
            sb.append(words[j]);
            sb.append(" ");
        }
        for (int i = 0; i < maxWidth - len; i++) {
            sb.append(" ");
        }
        res.add(sb.toString());
        return res;
    }

    public static void main(String[] argus) {
        textJustification_68 tj = new textJustification_68();
        String[] words = new String[]{"This", "is", "an", "example", "of", "text", "justification."};
        List<String> res = tj.fullJustify(words, 16);
        for (String s : res) {
            System.out.println(s);
        }
    }
}
