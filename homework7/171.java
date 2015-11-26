public class Solution {
    public int titleToNumber(String s) {
        if(s==null || s.length() == 0){
        throw new IllegalArgumentException("Input is not valid!");
    }
        int sum=0;
        int j=0;
        char[] arr=s.toCharArray();
        for(int i=arr.length-1;i>=0;i--){
            //arr[i]-'A'+1一定不要写成arr[i]-55
            //sum=sum+(int)Math.pow(26,j)*(arr[i]-55);
            sum=sum+(int)Math.pow(26,j)*(arr[i]-'A'+1);
            j++;
        }
        return sum;
    }
}