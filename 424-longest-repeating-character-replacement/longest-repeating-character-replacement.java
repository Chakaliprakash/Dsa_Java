class Solution {
    public int characterReplacement(String s, int k) {
        int ans=0,maxfreq=0,l=0,n=s.length();
        int freq[]=new int[26];

        for(int r=0;r<n;r++) {
            char ch=s.charAt(r);
            freq[ch-'A']++;
            maxfreq=Math.max(maxfreq,freq[ch-'A']);

            int window=r-l+1;
            while(window-maxfreq>k) {
                freq[s.charAt(l)-'A']--;
                l++;
                window=r-l+1;
            }
            ans=Math.max(ans,window);
        }
        return ans;
    }
}