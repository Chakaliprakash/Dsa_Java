class Solution {
    public int minEatingSpeed(int[] piles, int h) {
        int low=1,high=Arrays.stream(piles).max().getAsInt();

        while(low<=high) {
            int mid=low+(high-low)/2;

            long totalHours=function(piles,mid);

            if(totalHours <= h) high=mid-1;
            else low=mid+1;
        }
        return low;
    }

    public long function(int []piles,int h) {
        long total=0;

        for(int i=0;i<piles.length;i++) {
            total+=(piles[i]+h-1)/h;
        }
        return total;
    }
}