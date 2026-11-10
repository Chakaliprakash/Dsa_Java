class Solution {
    public int uniquePaths(int m, int n) {
        int dp[][]=new int[m][n];
        for(int rows[]:dp) Arrays.fill(rows,-1);
        return solve(m-1,n-1,m,n,dp);
        // return UniquePathsTabu(m,n);
    }
    static int solve(int i,int j,int m,int n,int [][]dp) {
        if(i==0 && j==0) return 1;
        if(i<0 || j<0) return 0;
        if(dp[i][j]!=-1) return dp[i][j];
        int up=solve(i-1,j,m,n,dp);
        int down=solve(i,j-1,m,n,dp);
        return dp[i][j]=up+down;
    }
    // static int UniquePathsTabu(int m,int n) {
    //   int[][]dp=new int[m][n];

    //   for(int i=0;i<m;i++) dp[i][0]=1;
    //   for(int j=0;j<n;j++) dp[0][j]=1;

    //   for(int z=1;z<m;z++) {
    //     for(int f=1;f<n;f++) {
    //       dp[z][f]=dp[z-1][f]+dp[z][f-1];
    //     }
    //   }
    //   return dp[m-1][n-1];
    // }
}