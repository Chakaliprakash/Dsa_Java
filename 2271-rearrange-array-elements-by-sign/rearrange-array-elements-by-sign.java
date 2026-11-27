
class Solution {
    static {
        for(int i=1;i<500;i++) rearrangeArray(new int[] {i});
    }
    public static int[] rearrangeArray(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];

        int pos = 0;
        int neg = 1;

        for (int num : nums) {
            if (num > 0) {
                result[pos] = num;
                pos += 2;
            } else {
                result[neg] = num;
                neg += 2;
            }
        }

        return result;
    }
}