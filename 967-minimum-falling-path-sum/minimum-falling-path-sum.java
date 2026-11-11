class Solution {
    public int minFallingPathSum(int[][] arr) {
        int n=arr.length;
        int[][]dp=new int[n][n];
        for(int l[]:dp) Arrays.fill(l,Integer.MAX_VALUE);
        int ans=Integer.MAX_VALUE;
        for(int i=0;i<n;i++) {
            ans=Math.min(ans,f(arr,0,i,n,dp));
        }
        return ans;
    }
    static int f(int[][]arr,int i,int j,int n,int[][]dp) {
        if(j<0 || j>n-1) return Integer.MAX_VALUE;
        if(i==n-1) return arr[i][j];
        if(dp[i][j]!=Integer.MAX_VALUE) return dp[i][j];
        int down=f(arr,i+1,j,n,dp);
        int left=f(arr,i+1,j-1,n,dp);
        int right=f(arr,i+1,j+1,n,dp);
        return dp[i][j]=arr[i][j]+Math.min(down,Math.min(left,right));
    }
}