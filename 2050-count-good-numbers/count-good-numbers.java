class Solution {
    static final long MOD=1_000_000_007;
    public int countGoodNumbers(long n) {
        long evenpos=(n+1)/2;
        long oddpos=n/2;

        long evenWays=power(5,evenpos);
        long oddWays=power(4,oddpos);

        return (int)((evenWays*oddWays)%MOD);
    }
    static long power(long base,long exp) {
        long result=1;

        while(exp>0) {
            if(exp%2==1) {
                result=(result*base)%MOD;
            }
            base=(base*base)%MOD;
            exp=exp/2;
        }
        return result;
    }
}