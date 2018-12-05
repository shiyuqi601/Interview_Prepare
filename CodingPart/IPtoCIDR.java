package CodingPart;
import java.util.*;
/**
 * Created by yuqishi on 11/1/18.
 */
public class IPtoCIDR {

    public List<String> ipToCIDR(String ip, int n) {
        //思路，找到这些IPs中从右往左第一位相同的二进制位
        // x & -x ;-x是x的补码，返回x与2^64的最大公约数，
        //即x最多能被n个2整除就返回2^n,如果x是奇数返回1;返回值为0 ，说明x=0;为其他数，表示x为x与2^64的最大公约数
        //一言以蔽之就是获取32位二进制表示中从右往左首次出现1的位置
        long x = 0;
        //以"."划分每个IP
        String[] ipsegment = ip.split("\\.");
        for (int i = 0; i < ipsegment.length; i++){
            x = Integer.parseInt(ipsegment[i]) + x * 256;
        }
        List<String> res = new ArrayList<>();
        while (n > 0) {
            long temp = x & -x;//求得该IP用32位二进制表示中从右往左首次出现1的位置
            //-x才是x的补码，~x为反码
            //temp如果为奇数，则该IP为第一个CIDR块
            //如果偶数，则该IP用二进制表示下的最低有效位的位数能表示的地址的数量
            while (temp > n) {
                temp = temp / 2;
            }
            //到这里temp肯定是小于n的，这告诉我们包括此IP在内的temp个IPs可以用一个ICDR来表示
            //接下来求出这些IPs所处的CIDR
            res.add(longToIP(x, (int)temp));
            //x加上temp;
            x += temp;//temp个ips考虑好了，接下来考虑从x+temp考虑
            n -= temp;//还有几个IPs要求ICDR的
        }
        return res;
    }

    public String longToIP(long x, int k) {
        int[] res = new int[4];
        for (int i = 3; i >= 0; i--) {
            res[i] = (int)x & 255;
            x >>= 8;
        }
        int netID = 33;
        while (k > 0) {
            k /= 2;
            netID--;
        }
        return res[0] + "." + res[1] + "." + res[2] + "." + res[3] + "/" + netID;
    }

    public static void main(String[] argus) {
        IPtoCIDR ic = new IPtoCIDR();
        System.out.println(ic.ipToCIDR("255.0.0.7", 10));
        System.out.println(255 & 255);
    }
}
