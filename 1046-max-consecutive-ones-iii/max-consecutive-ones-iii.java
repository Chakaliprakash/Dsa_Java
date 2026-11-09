// class Solution {
//     public int longestOnes(int[] nums, int k) {
//         int ans=0,l=0,count=0;

//         for(int i=0;i<nums.length;i++) {
//             if(nums[i]==0) count++;
//             while(count>k) {
//                 if(nums[l]==0) count--;
//                 l++;
//             }
//             ans=Math.max(ans,i-l+1);
//         }
//         return ans;
//     }
// }
class Solution {
    public int longestOnes(int[] nums, int k) {
        int n = nums.length;
        int start=0,end=0;
        int zeroes = 0;
        while(end<n){
            if(nums[end] == 0){
                zeroes++;
            }
            end++;
            if(zeroes>k){
                if(nums[start] == 0){
                    zeroes--;
                }
                start++;
            }
        }
        return end-start;
    }
}