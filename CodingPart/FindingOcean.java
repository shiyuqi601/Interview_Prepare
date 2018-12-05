package CodingPart;

import java.util.*;

/**
 * Created by yuqishi on 10/28/18.
 */
public class FindingOcean {

    public String[] findOcean(String[] map, int x, int y) {
        int m = map.length;
        int n = map[0].length();
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{x, y});
        setO(map, x, y);

        int[] dx = new int[]{1,-1,0,0};
        int[] dy = new int[]{0,0,1,-1};
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            for (int k = 0; k < 4; k++) {
                int newX = cur[0] + dx[k];
                int newY = cur[1] + dy[k];
                if (newX >= 0 && newX < m && newY >= 0 && newY < n
                        && map[newX].charAt(newY) == 'W') {
                    setO(map, newX, newY);
                    q.offer(new int[]{newX, newY});
                }
            }
        }
        return map;
    }


    public void setO(String[] map, int x, int y) {
        char[] arr = map[x].toCharArray();
        arr[y] = 'O';
        map[x] = String.valueOf(arr);
    }

    public static void main(String[] argus) {
        FindingOcean fo = new FindingOcean();
        String[] map = new String[]{"WWWLLLW", "WWLLLWW", "WLLLLWW"};
        String[] res = fo.findOcean(map, 0, 1);
        for (String s : res) {
            System.out.println(s);
        }
    }
}
