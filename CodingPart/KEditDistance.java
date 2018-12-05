//package Airbnb;
//import java.util.*;
///**
// * Created by yuqishi on 10/26/18.
// * Find all words from a dictionary that are k edit distance away.
// *
// * 1) construct the trie
// *      c
//      / | \
//     b  s  t
//    /
//   y
//
//   2) dp[i]: represents the min edit distance between
//            the prefix of root to current node and target[0~i]
//                    c a t
//                [ 0 1 2 3 ] <- pre_Dp
//           -> c [ 1 0 1 2 ] <- cur_Dp
//
//             cb [ 2 1 1 2 ]
//             cs [ 2 1 1 2 ]
//             ct [ 2 1 1 1 ]
//        if k == 1, notice that edit distance for cb and cs, 2 > k, so we don't need to search cby and cs
//
//     */
//public class KEditDistance {
//
//    public List<String> getKEditDistance(String[] lists, String target, int k) {
//        List<String> res = new ArrayList<>();
//        Trie trie = new Trie();
//        for (String word : lists) {
//            trie.insert(word);
//        }
//        int n = target.length();
//        int[] dp = new int[n + 1];
//        for (int i = 0; i <= n; i++) {
//            dp[i] = i;
//        }
//        searchHelper(trie.root, target, k, dp, res);
//        return res;
//    }
//
//    private void searchHelper(TrieNode root, String target, int k, int[] preDp, List<String> res) {
//        if (root.isWord != null) {
//            if (preDp[target.length()] <= k) {
//                res.add(root.isWord);
//            } else {
//                return;
//            }
//        }
//        for (int i = 0; i < 26; i++) {
//            if (root.children[i] == null) {
//                continue;
//            }
//            int[] dp = new int[target.length() + 1];
//            dp[0] = preDp[0] + 1;
//            for (int j = 1; j <= target.length(); j++) {
//                if (i + 'a' == target.charAt(j - 1)) {
//                    dp[j] = preDp[j - 1];
//                } else {
//                    dp[j] = Math.min(preDp[j - 1] + 1,  //replace
//                            Math.min(dp[j - 1] + 1,     //insert
//                                     preDp[j] + 1));    //delete
//                }
//            }
//            searchHelper(root.children[i], target, k, dp, res);
//        }
//    }
//
//    public static void main(String[] argus) {
//        //["cs", "ct", "cby"]，target word为"cat"，k=1。
//        KEditDistance ke = new KEditDistance();
//        String[] list = new String[]{"cs", "ct", "cby"};
//        List<String> res = ke.getKEditDistance(list, "cat", 1);
//        System.out.print(res);
//    }
//}
//
//
//class TrieNode {
//    String isWord;
//    TrieNode[] children;
//    char value;
//
//    public TrieNode(char value) {
//        children = new TrieNode[26];
//        this.value = value;
//    }
//}
//
//class Trie {
//    TrieNode root;
//
//    public Trie() {
//        root = new TrieNode('0');
//    }
//
//    public void insert(String word) {
//        TrieNode cur = root;
//        for (char c : word.toCharArray()) {
//            if (cur.children[c - 'a'] == null) {
//                cur.children[c - 'a'] = new TrieNode(c);
//            }
//            cur = cur.children[c - 'a'];
//        }
//        cur.isWord = word;
//    }
//}
