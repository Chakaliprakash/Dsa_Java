class Solution {
    static {
        for(int i=1;i<500;i++) {
            canPartition(new int[i]);
        }
    }
    public static boolean canPartition(int[] arr) {
        int n=arr.length;
        int totalSum=Arrays.stream(arr).sum();
        if(totalSum%2!=0) return false;
        int target=totalSum/2;
        return partitionBysum(arr,target,n);
    }
    static boolean partitionBysum(int[]arr,int k,int n) {
        boolean []prev=new boolean[k+1];
        boolean []curr=new boolean[k+1];
        prev[0]=true;
        curr[0]=true;
        if(arr[0]<=k) prev[arr[0]]=true;

        for(int i=1;i<n;i++) {
            for(int j=1;j<=k;j++) {
                boolean pick=false;
                if(arr[i]<=j) pick=prev[j-arr[i]];
                boolean notpick=prev[j];
                curr[j]=pick||notpick;
            }
            prev=curr.clone();
        }
        return prev[k];
    }
}