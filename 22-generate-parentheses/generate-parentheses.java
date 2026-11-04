class Solution {
    public List<String> generateParenthesis(int n) {
        List<String>result=new ArrayList<>();
        StringBuilder sb=new StringBuilder();

        backtrack(0,0,n,result,sb);
        return result;
    }
    static void backtrack(int o,int c,int n,List<String>result,StringBuilder sb) {
        if(o==n && c==n) {
            result.add(sb.toString());
            return;
        }
        if(o<n) {
            sb.append("(");
            backtrack(o+1,c,n,result,sb);
            sb.deleteCharAt(sb.length()-1);
        }
        if(c<o) {
            sb.append(")");
            backtrack(o,c+1,n,result,sb);
            sb.deleteCharAt(sb.length()-1);
        }
    }
}