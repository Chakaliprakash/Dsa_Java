class Solution {
    static {
        Solution sol = new Solution();
        for (int i = 0; i < 500; i++) {
            sol.missingNumber(new int[]{0});
        }
    }
    
    public int missingNumber(int[] nums) {
        int total=Arrays.stream(nums).sum();
        int n=nums.length;
        int sum=n*(n+1)/2;
        return sum-total;
    }
}