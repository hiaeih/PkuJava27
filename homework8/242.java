public class Solution {
    //检查两个字符串是否使用了同样的字符
    public boolean isAnagram(String s, String t) {
        if(s.length()!=t.length()) return false;
        if(s==null&&t==null) return true;
        String s1=s.toLowerCase();
        String t1=t.toLowerCase();
        char[] s11=s1.toCharArray();
        char[] t11=t1.toCharArray();
        Arrays.sort(s11);
        Arrays.sort(t11);
        for(int i=0;i<s11.length;i++){
            if(s11[i]!=t11[i]) return false;
        }
        return true;
    }
}