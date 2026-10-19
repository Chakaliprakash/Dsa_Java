class Solution {
    public int searchInsert(int[] arr, int target) {
        int left=0,right=arr.length;

        while(left<right) {
            int mid=left+(right-left)/2;

            if(target==arr[mid]) return mid;
            else if(target>arr[mid]) left=mid+1;
            else right=mid;
        }
        return right;
    }
}