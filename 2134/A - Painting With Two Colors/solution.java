import java.util.Scanner;
 
public class ContestProblem {
 
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
 
        int t;
        if (!sc.hasNextInt()) return;
        t = sc.nextInt();
 
        while (t-- > 0) {
            long n = sc.nextLong();
            long a = sc.nextLong();
            long b = sc.nextLong();
 
            if ((n - b) % 2 != 0) {
                System.out.println("NO");
                continue;
            }
 
            if (b >= a) {
                System.out.println("YES");
                continue;
            }
 
            if ((a - b) % 2 == 0) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        }
 
        sc.close();
    }
}