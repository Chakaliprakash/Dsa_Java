// class Solution {
//     public int findContentChildren(int[] g, int[] s) {
//         Arrays.sort(g);
//         Arrays.sort(s);
//         int l=0,r=0;
//         while(l<g.length && r<s.length) {
//             if(g[l]<=s[r]) l++;
//             r++;
//         } 
//         return r;
//     }
// }
class Solution {
    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        
        int l = 0; // Pointer for children (also counts satisfied children)
        int r = 0; // Pointer for cookies
        
        while (l < g.length && r < s.length) {
            if (g[l] <= s[r]) {
                l++; // Child is satisfied, move to the next child
            }
            r++; // Always move to the next cookie
        }
        
        return l;
    }
}
