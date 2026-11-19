import java.util.*;
import java.io.*;
 
public class Main {
    public static void main(String...args) {
        Scanner sc=new Scanner(System.in);
        int s=sc.nextInt();
        int n=sc.nextInt();
        int[]p=new int[n];
        int[]b=new int[n];
        for (int i = 0; i < n; i++) {
            p[i] = sc.nextInt();
            b[i] = sc.nextInt();
        }
        
        
        System.out.println(f(p,b,s,n));
    }
    static String f(int[]p,int[]b,int s,int n) {
        int[][]mon=new int[n][2];
        for(int i=0;i<n;i++) {
            mon[i][0]=p[i];
            mon[i][1]=b[i];
        }
        
        Arrays.sort(mon,(a,c)->Integer.compare(a[0],c[0]));
        int count=0;
        for(int i=0;i<n;i++) {
            int power=mon[i][0];
            int bonus=mon[i][1];
            
            if(power>=s) return "NO";
            s+=bonus;
            count++;
        }
        return "YES";
    }
}