class Solution {
    public int uniquePathsWithObstacles(int[][] arr) {
        int m=arr.length;
        int n=arr[0].length;
        return f(arr,m-1,n-1);
    }
    static int f(int[][]arr,int m,int n) {
        int[][]dp=new int[m+1][n+1];
        for(int i=0;i<=m;i++) {
            for(int j=0;j<=n;j++) {
                if(arr[i][j]==1) dp[i][j]=0;
                else if(i==0&&j==0) dp[i][j]=1;
                else {
                    int u=0,l=0;
                    if(i>0) u=dp[i-1][j];
                    if(j>0) l=dp[i][j-1];
                    dp[i][j]=u+l;
                }
            }
        }
        return dp[m][n];
    }
}