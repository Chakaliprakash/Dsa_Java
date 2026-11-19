import java.util.*;
 
public class ContestProblem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
 
        while (t-- > 0) {
            int n = sc.nextInt();
            long[] a = new long[n + 1];
            for (int i = 1; i <= n; i++) a[i] = sc.nextLong();
 
            long[] b = new long[n + 1];
            for (int i = 1; i <= n; i++) b[i] = (i % 2 == 1) ? -a[i] : a[i];
 
            long[] S = new long[n + 1];
            for (int i = 1; i <= n; i++) S[i] = S[i - 1] + b[i];
 
            long ans = 0, curr = 0, prev = 0, next = 0;
            for (int r = 1; r <= n; r++) {
                long base = S[r] + curr;
                long req = (r >= 2) ? next : Long.MIN_VALUE / 2;
                long add = Math.max(0, req - base);
                ans += add;
                curr += add;
                if (add > 0 && r % 2 == 0) prev += add;
                long sNow = base + add;
                next = Math.max(req, prev);
                prev = sNow;
            }
 
            System.out.println(ans);
        }
        sc.close();
    }
}