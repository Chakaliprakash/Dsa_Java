class Solution {
    public int rob(int[] arr) {
        int n=arr.length-1;
        int dp[]=new int[n+1];
        Arrays.fill(dp,-1);
        return roMemo(arr,dp,n);
    }
    static int roMemo(int []arr,int[]dp,int n) {
        if(n==0) return arr[n];
        if(n<0) return 0;

        if(dp[n]!=-1) return dp[n];
        int pick=arr[n]+roMemo(arr,dp,n-2);
        int notPick=roMemo(arr,dp,n-1);
        return dp[n]=Math.max(pick,notPick);
    }
}