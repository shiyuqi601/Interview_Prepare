package CodingPart;

/**
 * Created by yuqishi on 10/31/18.
 */
public class MaxNightAccommodate {


    public int findSolution(int[] nums) {
        if (nums == null) {
            return 0;
        }
        int n = nums.length;
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return nums[0];
        }
    /*
        int[] dp = new int[nums.length + 1];
        dp[0] = 0;
        dp[1] = nums[0];
        for (int i = 2; i <= nums.length; i++) {
                dp[i] = Math.max(dp[i - 2] + nums[i - 1], dp[i - 1]);
        }
        return dp[nums.length];
    */

        int f1 = 0;
        int f2 = nums[0];
        for (int i = 2; i <= n; i++) {
            int f = Math.max(f1 + nums[i - 1], f2);
            f1 = f2;
            f2 = f;
        }
        return f2;

    }


    /*
    * it will automatically contact the police
    * if two adjacent houses were broken into on the same night.
    * */
    // public int rob_1(int[] nums) {
    //     if (nums == null || nums.length == 0) {
    //         return 0;
    //     }
    //     int[] dp = new int[nums.length + 1];
    //     dp[0] = 0;
    //     dp[1] = nums[0];
    //     for (int i = 2; i <= nums.length; i++) {
    //         dp[i] = Math.max(dp[i - 2] + nums[i - 1], dp[i - 1]);
    //     }
    //     return Math.max(dp[nums.length], dp[nums.length - 1]);
    // }

    //更节省空间的写法
    public int rob_1(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int prev = 0, cur = nums[0];
        for (int i = 2; i <= nums.length; i++) {
            int value = Math.max(prev + nums[i - 1], cur);
            prev = cur;
            cur = value;
        }
        return Math.max(prev, cur);
    }


    /*
    * All houses at this place are arranged in a circle.
    * That means the first house is the neighbor of the last one.
    * Meanwhile, adjacent houses have security system connected and
    * it will automatically contact the police if two adjacent
    * houses were broken into on the same night.
    *
    * */

    public int rob_2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        if (nums.length == 2) {
            return Math.max(nums[0], nums[1]);
        }
        int n = nums.length;
        int[] dp = new int[n + 1];
        //rob 1st house
        dp[0] = 0;
        dp[1] = nums[0];
        dp[2] = nums[0];
        for (int i = 3; i < n; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i - 1]);
        }
        int a = dp[n - 1];

        //not rob 1st house
        dp[1] = 0;
        for (int i = 2; i <= n; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i - 1]);
        }
        int b = dp[n];
        return Math.max(a, b);
    }

    /*
    * There is only one entrance to this area, called the "root."
    * Besides the root, each house has one and only one parent house.
    * After a tour, the smart thief realized that "all houses in this place forms a binary tree".
    * It will automatically contact the police if two directly-linked houses were broken into
    * on the same night.
    * */

    public int rob_3(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int[] res = helper(root);
        return Math.max(res[0], res[1]);
    }

    public int[] helper(TreeNode root) {
        if (root == null) {
            return new int[]{0,0};
        }
        int[] left = helper(root.left);
        int[] right = helper(root.right);

        int[] res = new int[2];
        // do not rob the root
        res[0] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
        // rob the root
        res[1] = left[0] + right[0] + root.val;
        return res;
    }

    public static void main(String[] argus) {
        int[] num1 = new int[]{5,1,1,5};
        int[] num2 = new int[]{3,6,4};
        int[] num3 = new int[]{4,10,3,1,5};
        MaxNightAccommodate s = new MaxNightAccommodate();
        System.out.println(s.findSolution(num1));
        System.out.println(s.findSolution(num2));
        System.out.println(s.findSolution(num3));
    }
}


class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    public TreeNode(int val) {
        this.val = val;
    }
}
