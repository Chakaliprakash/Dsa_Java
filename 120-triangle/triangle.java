class Solution {
    public int minimumTotal(List<List<Integer>> list) {
        // return f(list,0,0);
        // int n=list.size();
        // int[][]dp=new int[n][n];
        // for(int g[]:dp) Arrays.fill(g,-1);
        // return fMemo(list,0,0,dp);
        return Ftabu(list);
    }
    static int f(List<List<Integer>> list,int i,int j) {
        int n=list.size();
        if(i==n) return 0;
        int d=f(list,i+1,j);
        int dr=f(list,i+1,j+1);
        return list.get(i).get(j) + Math.min(d,dr);
    }
    static int fMemo(List<List<Integer>> list,int i,int j,int[][]dp) {
        int n=list.size();
        if(i==n) return 0;
        if(dp[i][j]!=-1) return dp[i][j];
        int d=fMemo(list,i+1,j,dp);
        int dr=fMemo(list,i+1,j+1,dp);
        return dp[i][j]=list.get(i).get(j) + Math.min(d,dr);
    }
    static int Ftabu(List<List<Integer>> triangle) {
    int n = triangle.size();
    int[] dp = new int[n];

    for (int j = 0; j < n; j++) dp[j] = triangle.get(n - 1).get(j);

    for (int i = n - 2; i >= 0; i--) {
        for (int j = 0; j <= i; j++) {
            dp[j] = triangle.get(i).get(j) + Math.min(dp[j], dp[j + 1]);
        }
    }
    return dp[0];
    }
}

    