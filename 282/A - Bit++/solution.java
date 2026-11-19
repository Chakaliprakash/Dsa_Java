import java.util.*;
import java.io.*;
public class Main {
    public static void main(String args[]) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        sc.nextLine();
        int x=0;
        for(int i=0;i<n;i++) {
            String str=sc.nextLine().trim().toUpperCase();
            if(str.equals("X++")||str.equals("++X")) {
                x++;
        }
        else if(str.equals("X--")||str.equals("--X")) {
            x--;
        }
        
    }
    System.out.println(x);
}
}