import java.util.Scanner;
import java.util.Arrays;
 
public class Main {
    static int MOD = 1000000007;
    static long[][] dp;
 
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
 
        dp = new long[k + 1][n + 1];
        for (int i = 0; i <= k; i++) Arrays.fill(dp[i], -1);
        
        System.out.println(Mashmokh_and_ACM_Memo(0, 1, n, k));
    }
 
    static long Mashmokh_and_ACM_Memo(int index, int previous, int n, int k) {
        if (index == k) return 1;
        if (dp[index][previous] != -1) return dp[index][previous];
 
        long count = 0;
        for (int i = previous; i <= n; i += previous) {
            count = (count + Mashmokh_and_ACM_Memo(index + 1, i, n, k)) % MOD;
        }
 
        return dp[index][previous] = count;
    }
}