package CodingPart;

/**
 * Created by yuqishi on 10/31/18.
 */
public class findMedian {
    /*
    * Binary search: any integer will between Integer.MIN_VALUE ~ MAX_VALUE, which means we know the upper bound and lower bound.
        We can guess a median be “guess = lower + (upper - lower) / 2”,
        in order to verify whether our guess is correct or not,
        we can iterate all the numbers, to see if there are exactly half of the numbers are smaller than the guess median.
        If so, we can return smallest element in the file that is larger than the guess.
        If we found more than half of numbers were smaller than the guess,
        for next step, we can move the upper bound to guess - 1.
        Just use binary search, we can scan the file at most 32 times.
    * */

    public long findMedianInLargeStream(int[] nums) {
        int len = nums.length;
        if (len % 2 != 0) {
            return findMedianHelper(nums, len / 2 + 1, Integer.MIN_VALUE, Integer.MAX_VALUE);
        }
        return (findMedianHelper(nums, len / 2, Integer.MIN_VALUE, Integer.MAX_VALUE) +
                findMedianHelper(nums, len / 2 + 1, Integer.MIN_VALUE, Integer.MAX_VALUE)) / 2;
    }

    private long findMedianHelper(int[] nums, int k, long left, long right) {
        if (left >= right) {
            return left;
        }
        long res = left;
        long guess = left + (right - left) / 2;
        int count = 0;
        for (int num : nums) {
            if (num <= guess) {
                count++;
                res = Math.max(res, num);
            }

        }
        if (count < k) {
            return findMedianHelper(nums, k, Math.max(res + 1, guess), right);
        } else if (count > k) {
            return findMedianHelper(nums, k, left, res);
        }
        return res;
    }



    public static void main(String[] argus) {
        int[] nums = new int[]{1,3,5,7,2,4,6,8,10,11,45,64};
        //1 2 3 4 5 6 7 8 10,11,45,64

        int[] nums_1 = new int[]{1,3,5,7}; //4
        int[] nums_2 = new int[]{2,4,6}; //4
        int[] nums_3 = new int[]{1,2,2,5,2}; //2
        findMedian fm = new findMedian();
        System.out.println(fm.findMedianInLargeStream(nums_1));
        System.out.println(fm.findMedianInLargeStream(nums_2));
        System.out.println(fm.findMedianInLargeStream(nums_3));
    }
}
