public class Solution {
    // you need to treat n as an unsigned value
    public static void main(String[] args) {
		// TODO Auto-generated method stub
        int m=9;
        System.out.println(hammingWeight(m));
	}
	 public static int hammingWeight(int n) {
		String s=Integer.toBinaryString(n);
		int count=0;
	    char[] test=s.toCharArray();
	    for(int i=s.length()-1;i>=0;i--){
	    	if(test[i]=='1') count++;
	    }
	    return count;
	    
	    }
}