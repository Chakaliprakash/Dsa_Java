class Solution {
    public int findContentChildren(int[] g, int[] s) {
        Thread t1=new Thread(()->Arrays.sort(g));
        Thread t2=new Thread(()->Arrays.sort(s));
        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch(Exception e) {}
        // Arrays.sort(g);
        // Arrays.sort(s);
        int l=0,r=0;
        while(l<g.length && r<s.length) {
            if(g[l]<=s[r]) l++;
            r++;
        } 
        return l;
    }
}

