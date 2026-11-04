class Solution {
    public List<List<Integer>> subsets(int[] arr) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        backtrack(arr,0,list,result);
        return result;
    }
    static void backtrack(int []arr,int i,List<Integer>list,List<List<Integer>>result) {
        result.add(new ArrayList<>(list));

        for(int z=i;z<arr.length;z++) {
            list.add(arr[z]);
            backtrack(arr,z+1,list,result);
            list.removeLast();
        }
    }
}