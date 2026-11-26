class Solution {
    public int majorityElement(int[] arr) {
        // int max=Arrays.stream(arr).boxed().collect(Collectors.groupingBy(n->n,Collectors.counting())).entrySet().stream().max(Map.Entry.comparingByValue()).get().getKey();

        // return max;
        // int max=0;
        // HashMap<Integer,Integer>map=new HashMap<>();
        // for(int i=0;i<arr.length;i++) {
        //     map.put(arr[i],map.getOrDefault(arr[i],0)+1);
        //     if(map.get(arr[i])>arr.length/2) max=arr[i];
        // }
        // return max;
        int value=0;
        int count=0;
        for(int i=0;i<arr.length;i++) {
            if(count==0) {
                value=arr[i];
                count=1;
            }
            else if(value==arr[i]) count++;
            else count--;
        }
        int c=0;
        for(int i=0;i<arr.length;i++) {
            if(value==arr[i]) c++;
        }
        return c>(arr.length/2) ? value:-1;
    }
}
