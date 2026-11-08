class Solution {
    public String minWindow(String s, String t) {
        if(s.length()<t.length()) return "";
        int l=0,minLength=Integer.MAX_VALUE,requi=t.length(),start=0;

        int frq[]=new int[128];
        for(char ch:t.toCharArray()) frq[ch]++;

        for(int r=0;r<s.length();r++) {
            char rC=s.charAt(r);
            if(frq[rC]>0) requi--;
            frq[rC]--;

            while(requi==0) {
                if(r-l+1<minLength) {
                    minLength=r-l+1;
                    start=l;
                }
                char lC=s.charAt(l);
                frq[lC]++;
                if(frq[lC]>0) requi++;
                l++;
            }
        }
        return minLength==Integer.MAX_VALUE?"":s.substring(start,start+minLength);
    }
}