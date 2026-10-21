class Solution {
    public int[] searchRange(int[] nums, int target) {
        int lowerb = lb(nums, target);
        if (lowerb == nums.length || nums[lowerb] != target) {
            return new int[] {-1, -1};
        }
        return new int[] { lowerb, ub(nums, target) - 1 };
    }


    private int lb(int[] nums, int target) {
        int low = 0, high = nums.length - 1;
        int ans = nums.length;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] >= target) {
                ans = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return ans;
    }

    private int ub(int[] nums, int target) {
        int low = 0, high = nums.length - 1;
        int ans = nums.length;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] > target) {
                ans = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return ans;
    }
}
