class Solution {
    static {
        Solution sol=new Solution();
        for(int i=1;i<500;i++) sol.findMaxConsecutiveOnes(new int[]{i});
    }
    public int findMaxConsecutiveOnes(int[] nums) {
        int count=0,max=0;

        for(int i:nums) {
            if(i==1) {
                count++;
                max=Math.max(max,count);
            }
            else count=0;
        }
        return max;
    }
}