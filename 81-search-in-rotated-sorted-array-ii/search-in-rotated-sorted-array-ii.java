class Solution {
    public boolean search(int[] arr, int target) {
        return Hacker(arr,target,0,arr.length-1);
    }
    public static boolean Hacker(int []arr,int target,int low,int high) {
        if(low>high) return false;

        int mid=low+(high-low)/2;

        if(arr[mid]==target) return true;

        if(arr[mid]==arr[low]&& arr[mid]==arr[high]) {
            return Hacker(arr,target,low+1,high-1);
        }
        if(arr[mid]>=arr[low]) {
            if(arr[low]<=target && arr[mid]>target) {
                return Hacker(arr,target,low,mid-1);
            }
            else return Hacker(arr,target,mid+1,high);
        }
        else {
            if(arr[mid]<=target && arr[high]>=target) {
                return Hacker(arr,target,mid+1,high);
            }
            else return Hacker(arr,target,low,mid-1);
        }
    }
}