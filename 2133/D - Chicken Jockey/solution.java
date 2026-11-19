import java.io.*;
import java.util.*;
 
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine().trim());
        while (T-- > 0) {
            int n = Integer.parseInt(br.readLine().trim());
            String[] parts = br.readLine().split(" ");
            long[] h = new long[n];
            for (int i = 0; i < n; i++) {
                h[i] = Long.parseLong(parts[i]);
            }
 
            long sum = 0;
            for (long x : h) sum += x;
            sum -= (n - 1);
 
            long a = 0, b = 0;
            for (int i = 1; i < n; i++) {
                long w = Math.min(i, h[i]) - 2;
                if (w < 0) w = 0;
                long c = Math.max(b, a + w);
                a = b;
                b = c;
            }
 
            System.out.println(sum - b);
        }
    }
}