import java.util.Scanner;
 
public class Main {
 
    static final int MOD = 1_000_000_007;
 
    public static void main(String[] args) {
 
        Scanner sc = new Scanner(System.in);
 
        int n = sc.nextInt();
        int k = sc.nextInt();
        int d = sc.nextInt();
 
        long[][] dp = new long[n + 1][2];
 
        dp[0][0] = 1;
 
        for (int sum = 0; sum <= n; sum++) {
 
            for (int weight = 1; weight <= k; weight++) {
 
                int nextSum = sum + weight;
 
                if (nextSum > n) {
                    break;
                }
 
                if (weight >= d) {
 
                    dp[nextSum][1] =
                            (dp[nextSum][1] + dp[sum][0]) % MOD;
 
                    dp[nextSum][1] =
                            (dp[nextSum][1] + dp[sum][1]) % MOD;
 
                } else {
 
                    dp[nextSum][0] =
                            (dp[nextSum][0] + dp[sum][0]) % MOD;
 
                    dp[nextSum][1] =
                            (dp[nextSum][1] + dp[sum][1]) % MOD;
                }
            }
        }
 
        System.out.println(dp[n][1]);
 
        sc.close();
    }
}