class Solution {
    public boolean findSubarrays(int[] arr) {
        HashSet<Integer>set=new HashSet<>();
        for(int i=0;i<arr.length-1;i++) {
            int sum=arr[i]+arr[i+1];
            if(set.contains(sum)) return true;
            set.add(sum);
        }
        return false;
    }
}