class Solution {
    public int splitArray(int[] arr, int k) {
        int l=0,h=0;

        for(int n:arr) {
            l=Math.max(l,n);
            h+=n;
        }

        while(l<=h) {
            int m=l+(h-l)/2;

            int countGroups=CountGroups(arr,m);

            if(countGroups > k) {
                l=m+1;
            }
            else h=m-1;
        }
        return l;
    }
    public int CountGroups(int []arr,int mid) {
        int groups=1,sum=0;

        for(int n:arr) {
            if(n+sum <= mid) {
                sum+=n;
            }
            else {
                groups++;
                sum=n;
            }
        }
        return groups;
    }
}