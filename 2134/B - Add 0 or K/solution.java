import java.util.*;
 
public class ContestProblem {
    static long extendedGCD(long a, long b, long[] res) {
        if (b == 0) {
            res[0] = 1;
            res[1] = 0;
            return a;
        }
        long[] temp = new long[2];
        long g = extendedGCD(b, a % b, temp);
        res[0] = temp[1];
        res[1] = temp[0] - (a / b) * temp[1];
        return g;
    }
 
    static long modInverse(long a, long m) {
        long[] res = new long[2];
        extendedGCD(a, m, res);
        long x = res[0] % m;
        if (x < 0) x += m;
        return x;
    }
 
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        if (!sc.hasNextInt()) return;
        int t = sc.nextInt();
 
        while (t-- > 0) {
            int n = sc.nextInt();
            long k = sc.nextLong();
            long[] a = new long[n];
            for (int i = 0; i < n; i++) a[i] = sc.nextLong();
 
            long x = k + 1;
            long p = -1;
            for (long d = 2; d * d <= x; d++) {
                if (x % d == 0) {
                    p = d;
                    break;
                }
            }
            if (p == -1) p = x;
 
            long u = (k % p + p) % p;
            long v = modInverse(u, p);
 
            for (int i = 0; i < n; i++) {
                long s = (a[i] % p + p) % p;
                long w = (p - s) % p;
                long m = (w * v) % p;
                a[i] += m * k;
            }
 
            for (int i = 0; i < n; i++) {
                if (i > 0) System.out.print(" ");
                System.out.print(a[i]);
            }
            System.out.println();
        }
 
        sc.close();
    }
}