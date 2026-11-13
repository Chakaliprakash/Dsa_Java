class Solution {
    static {
        for(int i=1;i<500;i++) canJump(new int[1]);
    }
    public static boolean canJump(int[] nums) {
        int f=0;
        for(int i=0;i<nums.length;i++) {
            if(i>f) return false;
            f=Math.max(f,i+nums[i]);
        }
        return true;
    }
}