import java.util.*;
import java.io.*;
 
public class Main {
    public static void main(String...args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        int l=sc.nextInt();
        int[]arr=new int[n];
        for(int i=0;i<n;i++) arr[i]=sc.nextInt();
        
        System.out.printf("%.10f%n", f(arr, n, l));
    }
    
    static double f(int[]arr,int n,int l) {
        Arrays.sort(arr);
        double max=Math.max(arr[0],l-arr[n-1]);
        for(int i=0;i<n-1;i++) {
            double adja=(arr[i+1]-arr[i])/2.0;
            
            max=Math.max(max,adja);
        }
        return max;
    }
}