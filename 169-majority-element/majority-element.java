class Solution {
    public int majorityElement(int[] arr) {
        int max=Arrays.stream(arr).boxed().collect(Collectors.groupingBy(n->n,Collectors.counting())).entrySet().stream().max(Map.Entry.comparingByValue()).get().getKey();

        return max;
    }
}
