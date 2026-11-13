class Solution {
    static{
        for(int i = 0; i < 500; i++)
            lemonadeChange(new int[0]);
    }
    public static boolean lemonadeChange(int[] arr) {
       int f=0,t=0,i=0;
       while(i<arr.length) {
        if(arr[i]==5) f++;
        else if(arr[i]==10) {
            if(f>0) {
                f--;
                t++;
            }
            else return false;
        }
        else {
            if(t>0 && f>0) {
                t--;
                f--;
            }
            else if(f>=3) f-=3;
            else return false;
        }
        i++;
       } 
       return true;
    }
}