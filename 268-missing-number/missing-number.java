class Solution {
    public int missingNumber(int[] nums) {
        int total=Arrays.stream(nums).sum();
        int n=nums.length;
        int sum=n*(n+1)/2;
        return sum-total;
    }
}