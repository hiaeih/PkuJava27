public class Solution {
    public boolean isPalindrome(String s) {
        if(s==null) return false;
        if(s.length()==0) return true;
        String s1=s.toLowerCase();
        char[] arr=s1.toCharArray();
        Stack st=new Stack();
        for(int i=0;i<arr.length;i++){
            if(arr[i]>='a'&&arr[i]<='z'||arr[i]>='0'&&arr[i]<='9'){
              st.push(arr[i]);
       }
        }
      for(int j=0;j<arr.length;j++){
                if(arr[j]>='a'&&arr[j]<='z'||arr[j]>='0'&&arr[j]<='9'){
                   char a=(char)st.pop();
                   if(arr[j]!=a) return false;
           }
       }
       return true;
    }
}