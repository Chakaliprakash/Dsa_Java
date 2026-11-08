class Solution {
    public int rob(int[] arr) {
        int n=arr.length;
        if(n==1) return arr[0];
        return Math.max(HouseRob(arr,0,n-2),HouseRob(arr,1,n-1));
    }
    static int HouseRob(int[]arr,int start,int end) {
        if(start>end) return 0;
        int prev=0,prev2=0;

        for(int i=start;i<=end;i++) {
            int pick=arr[i]+prev2;
            int notpick=prev;

            int curr=Math.max(pick,notpick);
            prev2=prev;
            prev=curr;
        }
        return prev;
    }
}