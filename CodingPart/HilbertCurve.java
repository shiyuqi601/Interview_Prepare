package CodingPart;

/**
 * Created by yuqishi on 11/12/18.
 * https://www.cnblogs.com/xuyuan77/archive/2008/10/13/1310269.html
 */
public class HilbertCurve {

    public static int hilbertCurve(int x, int y, int iter) {
        if (iter == 0) return 1;
        int len = 1 << (iter - 1); //边长
        int num = 1 << (2 * (iter - 1)); // how many basic pattern do we have
        //nun = len ^ 2

        if (x >= len && y >= len) {
            return num * 2 + hilbertCurve(x - len, y - len, iter - 1); //第一象限：当前进入点左移len, 下移len 到达上一阶的“进入点”
        } else if (x < len && y >= len) {
            return num + hilbertCurve(x, y - len, iter - 1); //第四象限：当前进入点下移len可以到达上一阶的“进入点”
        } else if (x < len && y < len) {
            return hilbertCurve(y, x, iter - 1); //第三象限，沿y=x翻转即可
        } else {
            return 3 * num + hilbertCurve(len - y - 1, 2 * len - x - 1, iter - 1);
            //第二象限，当前(x,y)先左移2len - 1, 在下移len - 1, 再沿y = -x翻转
            //（x,y）-> (x - 2len + 1, y - len + 1) -> (len - y - 1, 2len - x - 1)
        }
    }

    public static void main(String[] argus) {
        System.out.println(hilbertCurve(1, 1, 2));
        System.out.println(hilbertCurve(0, 1, 1));
        System.out.println(hilbertCurve(2, 2, 2));
        System.out.println(hilbertCurve(3, 3, 2));
        System.out.println(hilbertCurve(3, 0, 2)); //16
    }
}
