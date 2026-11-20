import java.util.*;
 
public class Main {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        if (!sc.hasNextInt()) return;
        int n = sc.nextInt();
        
        // Use long to prevent integer overflow
        long[] freq = new long[100001];
        int max = 0;
        long[] dp = new long[100001];
        Arrays.fill(dp, -1);
        
        for (int i = 0; i < n; i++) {
            int val = sc.nextInt();
            freq[val]++;
            max = Math.max(max, val);
        }
        
        System.out.println(f(freq, max, dp));
    }
 
    static long f(long[] freq, int n, long[] dp) {
        if (n <= 0) return 0;
        if (dp[n] != -1) return dp[n];
        
        // Cast n to long to ensure 64-bit multiplication
        long take = f(freq, n - 2, dp) + (long) n * freq[n];
        long not = f(freq, n - 1, dp);
        
        return dp[n] = Math.max(take, not);
    }
}