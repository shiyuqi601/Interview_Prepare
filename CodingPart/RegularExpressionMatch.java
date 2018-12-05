package CodingPart;

/**
 * Created by yuqishi on 11/17/18.
 */
public class RegularExpressionMatch {

    public boolean isMatch(String p, String s) {
        boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];
        dp[0][0] = true;
        for (int i = 1; i <= p.length(); i++) {
            if (i > 1 && p.charAt(i - 1) == '*') {
                dp[0][i] = dp[0][i - 2];
            }
            if (p.charAt(i - 1) == '+') {
                dp[0][i] = dp[0][i - 1];
            }
        }

        for (int i = 1; i <= s.length(); i++) {
            for (int j = 1; j <= p.length(); j++) {
                if (s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '.') {
                    dp[i][j] = dp[i - 1][j - 1];
                }
                if (p.charAt(j - 1) == '*') {
                    if (p.charAt(j - 2) == '.' || p.charAt(j - 2) == s.charAt(i - 1)) {
                        dp[i][j] = dp[i][j - 1] || dp[i - 1][j] || dp[i][j - 2];
                    } else {
                        dp[i][j] = dp[i][j - 2];
                    }
                }
                if (p.charAt(j - 1) == '+')
                    if (p.charAt(j - 2) == '.' || p.charAt(j - 2) == s.charAt(i - 1)) {
                        dp[i][j] = dp[i][j - 1];
                    }
            }
        }
        return dp[s.length()][p.length()];
    }

    public boolean isMatch_2(String s, String p) {
        boolean[][] dp = new boolean[s.length() + 1][p.length()];
        dp[0][0] = true;
        for (int i = 1; i <= p.length(); i++) {
            if (i > 1 && p.charAt(i - 1) == '*') {
                dp[0][i] = dp[0][i - 2];
            }
        }
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 1; j <= p.length(); j++) {
                if (s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '.') {
                    dp[i][j] = dp[i - 1][j - 1];
                }
                if (p.charAt(j - 1) == '*') {
                    if (p.charAt(j - 2) == s.charAt(i - 1) || p.charAt(j - 2) == '.') {
                        dp[i][j] = dp[i][j - 2] || dp[i][j - 1] || dp[i - 1][j];
                    } else {
                        dp[i][j] = dp[i][j - 2];
                    }
                }
            }
        }
        return dp[s.length()][p.length()];
    }

    public static void main(String[] argus) {
        RegularExpressionMatch re = new RegularExpressionMatch();
//        System.out.println(re.isMatch(".+c*.*", "saaaa"));
//        System.out.println(re.isMatch("s+b*", "saaaa"));
//        System.out.println(re.isMatch("s+a*", "saaaab"));
//        System.out.println(re.isMatch(".+b*", "saaaab"));
        System.out.println(re.isMatch_2("aa","a"));
    }
}
