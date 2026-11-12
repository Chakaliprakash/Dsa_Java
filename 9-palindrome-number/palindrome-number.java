class Solution {
    public boolean isPalindrome(int x) {
        int dub=x;
        int rever=0;

        while(x>0) {
            int d=x%10;
            rever=rever*10+d;
            x=x/10;
        }
        if(rever==dub) return true;
        else return false;
    }
}