import java.util.*;
 
public class Main {
 
    public static void main(String... args) {
 
        Scanner sc = new Scanner(System.in);
 
        int t = sc.nextInt();
 
        while (t-- > 0) {
 
            int n = sc.nextInt();
 
            int[] arr = new int[n];
 
            for (int i = 0; i < n; i++) {
                arr[i] = sc.nextInt();
            }
 
            System.out.println(f(arr, n));
        }
 
        sc.close();
    }
 
    static int f(int[] arr, int n) {
 
        int[] freq = new int[n + 1];
 
        for (int j : arr) {
            freq[j]++;
        }
 
        int ans = 0;
        for (int i = 2; i <= 2 * n; i++) {
 
            int boats = 0;
 
            for (int j = 1; j <= i / 2; j++) {
 
                int eachO = i - j;
 
                if (eachO > n) continue;
 
                if (eachO == j) {
                    boats += freq[j] / 2;
                } else {
                    boats += Math.min(freq[j], freq[eachO]);
                }
            }
 
            ans = Math.max(boats, ans);
        }
 
        return ans;
    }
}