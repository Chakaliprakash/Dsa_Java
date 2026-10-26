class Solution {
    public int shipWithinDays(int[] arr, int days) {
    int low=Arrays.stream(arr).max().getAsInt(),high=Arrays.stream(arr).sum();
      int ans=0;
      while(low<=high) {
        int mid=low+(high-low)/2;
        int totalDays=CapacityofRequired(arr,mid);
        if(totalDays <= days) {
          ans=mid;
          high=mid-1;
        }
        else low=mid+1;
      }
      // return low;
      return ans;
    }
    public static int CapacityofRequired(int []arr,int k) {
      int days=1,weights=0;

      for(int n:arr) {
        if(weights+n > k) {
          days++;
          weights=n;
        }
        else weights+=n;
      }
      return days;
    }
    }
