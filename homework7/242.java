public class Solution {
    public boolean isAnagram(String s, String t) {
        /**
         * 思路：
         * 将s、t转换为字符数组，然后进行排序，比较即可判断
         * */
         char[] s1=s.toCharArray();
         char[] t1=t.toCharArray();
         Arrays.sort(s1);
         Arrays.sort(t1);
         if(s1.length!=t1.length){
            return false;
         }else {
             for(int i=0;i<s1.length;i++){
                 if(s1[i]!=t1[i])
                    return false;
             }
             return true;
         }
        
    }
}