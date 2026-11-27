class Solution {
    public int[] rearrangeArray(int[] arr) {
        int z=arr.length;
        int[]res=new int[z];
        int p=0,n=1;
        for(int i=0;i<z;i++) {
            if(arr[i]>0) {
                res[p]=arr[i];
                p+=2;
            } 
            else {
                res[n]=arr[i];
                n+=2;
            }
         }
         return res;
    }
}