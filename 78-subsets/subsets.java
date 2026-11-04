class Solution {
    public List<List<Integer>> subsets(int[] arr) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        backtrack(arr,0,list,result);
        return result;
    }
    static void backtrack(int []arr,int i,List<Integer>list,List<List<Integer>>result) {
        if(i==arr.length) {
            result.add(new ArrayList<>(list));
            return;
        }
            list.add(arr[i]);
            backtrack(arr,i+1,list,result);
            list.removeLast();
            backtrack(arr,i+1,list,result);
    }
}