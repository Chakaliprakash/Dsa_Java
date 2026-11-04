class Solution {
    public int climbStairs(int n) {
        if (n <= 1) {
            return n;
        }
        int []dp=new int[n+1];
        dp[0]=1;
        dp[1]=1;
        for(int i=2;i<=n;i++) {
            dp[i]=dp[i-1]+dp[i-2];
        }
        return dp[n];
        // int prev2 = 1;
        // int prev1 = 2;

        // for (int i = 3; i <= n; i++) {
        //     int current = prev1 + prev2;

        //     prev2 = prev1;
        //     prev1 = current;
        // }

        // return prev1;
    }
}
