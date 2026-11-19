import java.util.*;
 
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
 
        int t = sc.nextInt();
        while (t-- > 0) {
            int n = sc.nextInt();
            long[] g = new long[n];
 
            for (int i = 0; i < n; i++) {
                g[i] = sc.nextLong();
            }
 
            Arrays.sort(g);
 
            long sum = 0;
            int start = (n % 2 == 0) ? 1 : 0;
 
            for (int i = start; i < n; i += 2) {
                sum += g[i];
            }
 
            System.out.println(sum);
        }
    }
}