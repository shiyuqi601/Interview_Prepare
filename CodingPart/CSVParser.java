package CodingPart;
import java.util.*;
/**
 * Created by yuqishi on 11/7/18.
 * /*
 John,Smith,john.smith@gmail.com,Los Angeles,1
 Jane,Roberts,janer@msn.com,"San Francisco, CA",0
 "Alexandra ""Alex""",Menendez,alex.menendez@gmail.com,Miami,1
 """Alexandra Alex"""

 John|Smith|john.smith@gmail.com|Los Angeles|1
 Jane|Roberts|janer@msn.com|San Francisco, CA|0
 Alexandra "Alex"|Menendez|alex.menendez@gmail.com|Miami|1
 "Alexandra Alex"

 Understand the problem:
 For this problem, there are several cases need to consider:
 1. For comma, transform to |
 2. If comma is inside a quote, don't treat the comma as separated. Remove the comma and print the entire token. e.g. "San Francisco, CA" => San Francisco, CA
 3. If there are double quotes, remove one. e.g. "Alexandra ""Alex""" => Alexandra "Alex".
 Note that """Alexandra Alex""" becomes "Alexandra Alex" because we first remove the outer-most quote, and then remove one quote of the double quote.

 */

/*
By default, the escape character is a " (double quote) for CSV-formatted files
https://gpdb.docs.pivotal.io/43200/admin_guide/load/topics/g-escaping-in-csv-formatted-files.html

考虑下面三种情况：
(a)
"San Francisco, CA"

(b)
""San Francisco, CA""

(c)
"""San Francisco, CA"""

其中的a和c比较容易理解：

(1) a的左右双引号是用于quote里面的内容，应当去掉，所以输出就是 San Francisco, CA

(2) 对于c，考虑左边的三个双引号，第一个是用于quote后面的内容，而第二个是用于escape第三个双引号，所以只保留第三个双引号。

然后右边三个双引号也是类似处理：第四个双引号escape第五个双引号，第六个双引号与第一个双引号一起quote其中的内容，所以只保留第五个双引号。

所以输出就是 "San Francisco, CA"

那么现在问题来了，对于b，左边那两个双引号，第一个用于quote，那第二个双引号的语义是什么呢？或者说，期望的输出是什么？

*/
public class CSVParser {

    public static void main(String[] args) {
        System.out.println(parseCSV("John,Smith,john.smith@gmail.com,Los Angeles,1"));
        System.out.println(parseCSV("Jane,Roberts,janer@msn.com,\"San Francisco, CA\",0"));
        System.out.println(parseCSV("\"Alexandra \"\"Alex\"\"\",Menendez,alex.menendez@gmail.com,Miami,1"));
        // "Alexandra ""Alex""",Menendez,alex.menendez@gmail.com,Miami,1
        System.out.println(parseCSV("\"\"\"Alexandra Alex\"\"\""));
        // """Alexandra Alex"""
        System.out.println(parseCSV("\"abc\"\"def\""));
        // "abc""def" -> abc"def
        System.out.println(parseCSV("\"\"San Francisco, CA\"\""));
    }

    public static  String parseCSV(String str) {
        if (str == null || str.isEmpty()) return null;
        List<String> res = new ArrayList<>();
        StringBuilder curr = new StringBuilder();
        boolean inQuote = false;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (inQuote) {
                if (c == '\"') {
                    if (i < str.length() - 1 && str.charAt(i + 1) == '\"') {
                        curr.append("\"");
                        i++;
                    } else {
                        inQuote = false;
                    }
                } else {
                    curr.append(c);
                }
            } else {
                if (c == '\"') {
                    inQuote = true;
                } else if (c == ',') {
                    res.add(curr.toString());
                    curr.setLength(0);
                } else {
                    curr.append(c);
                }
            }
        }

        if (curr.length() > 0)
            res.add(curr.toString());

        return String.join("|", res);
    }
}
