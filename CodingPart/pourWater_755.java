package CodingPart;

/**
 * Created by yuqishi on 10/22/18.
 */
public class pourWater_755 {

    public int[] pourWater(int[] heights, int V, int K) {
        while (V > 0) {
            //check the left first
            int i = K;
            while (i - 1 >= 0 && heights[i] >= heights[i - 1]) {
                i--;
            }
            //回滚操作
            while (i + 1 <= K && heights[i] == heights[i + 1]) {
                i++;
            }
            if (i < K) {
                heights[i] += 1;
            } else {
                //check the right part
                int j = K;
                while (j + 1 < heights.length && heights[j] >= heights[j + 1]) {
                    j++;
                }
                //回滚操作
                while (j - 1 >= K && heights[j - 1] == heights[j]) {
                    j--;
                }
                if (j > K) {
                    heights[j] += 1;
                } else {
                    heights[K] += 1;
                }
            }
            V--;
        }
        return heights;
    }

    public void pourWater_2(int[] heights, int water, int location) {
        int[] waters = new int[heights.length];
        int pourLocation;

        while (water > 0) {
            int left = location - 1;
            while (left >= 0) {
                if (heights[left] + waters[left] > heights[left + 1] + waters[left + 1]) {
                    break;
                }
                left--;
            }
            if (heights[left + 1] + waters[left + 1] < heights[location] + waters[location]) {
                pourLocation = left + 1;
                waters[pourLocation]++;
                water--;
                continue;
            }

            int right = location + 1;
            while (right < heights.length) {
                if (heights[right] + waters[right] > heights[right - 1] + waters[right - 1]) {
                    break;
                }
                right++;
            }
            if (heights[right - 1] + waters[right - 1] < heights[location] + waters[location]) {
                pourLocation = right - 1;
                waters[pourLocation]++;
                water--;
                continue;
            }

            pourLocation = location;
            waters[pourLocation]++;
            water--;
        }

        print(heights, waters);
    }

    private void print(int[] heights, int[] waters) {
        int n = heights.length;

        int maxHeight = 0;
        for (int i = 0; i < n; i++) {
            maxHeight = Math.max(maxHeight, heights[i] + waters[i]);
        }

        for (int height = maxHeight; height >= 0; height--) {
            for (int i = 0; i < n; i++) {
                if (height <= heights[i]) {
                    System.out.print(". ");
                } else if (height > heights[i] && height <= heights[i] + waters[i]) {
                    System.out.print("w ");
                } else {
                    System.out.print("  ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void main(String[] argus) {
        int[] water = new int[]{1,2,3,4,3,2,1,2,3,4,3,2,1};
        pourWater_755 pw = new pourWater_755();
        int[] res = pw.pourWater(water, 5,5);
        for (int x : res) {
            System.out.print(x + " ");
        }


//        int[] water_2 = new int[]{1,2,3,4,3,2,1,2,3,4,3,2,1};
//        pw.pourWater_2(water_2, 5, 5);


    }
}
