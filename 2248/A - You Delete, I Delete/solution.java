import java.util.*;
 
public class Main {
    public static void main(String...args) {
        Scanner sc=new Scanner(System.in);
        int t=sc.nextInt();
        while(t-->0) {
            String s=sc.next();
            f(s);
        }
    }
    public static void f(String s) {
        int zero=s.indexOf('0');
        String remain=s.substring(0,zero)+s.substring(zero+1);
        int one=remain.indexOf('1');
        
        String str=remain.substring(0,one)+remain.substring(one+1);
        System.out.println(str);
    }
}