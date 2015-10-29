public class Solution {
    public static void main(String[] args) {
		// TODO Auto-generated method stub
          int j=12;
          System.out.println(isHappy(j));
	}
    public static boolean isHappy(int n) {  
        if(n<=0) return false;  
        long ln = n;  
        Set<Long> set = new HashSet<Long>();  
          
        while(ln<=Integer.MAX_VALUE) {  
            if(set.contains(ln) ) return false; else set.add(ln);  
            //ln = digitSquare(ln);  
            if(digitSquare(ln) == 1) return true;  
            ln = digitSquare(ln); 
        }  
        return false;  
    }  
      
    private static long digitSquare(long ln) {  
        long sum = 0;  
        while(ln!=0) {  
            sum += Math.pow(ln%10, 2);  
            ln /= 10;  
        }  
        return sum;  
    }  
}