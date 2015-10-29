import java.util.Arrays;


public class Solution {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] test={9,9,9,9};
		System.out.println(Arrays.toString(plusOne(test)));
		

	}
    public static int[] plusOne(int[] digits) {
      int temp,carry=1;
      //从最后一位开始进行加1运算
      for (int i=digits.length-1;i>=0;i--)
      {
    	  temp=digits[i];
    	  digits[i]=(temp+carry)%10;
    	  carry=(temp+carry)/10;
    	  //i--;//该行代码会导致越位相加
    	  if(carry==0){
    		  break;
    	  }
      }
      
      if(carry==1){
    	  int[] newdigits=new int[digits.length+1];
    	  //将原来的数组复制到新数组中
          System.arraycopy(digits, 0, newdigits, 1, digits.length);
    	  newdigits[0]=carry;
    	  //newdigits[1]=0;
    	  return newdigits;
      }
      return digits;
    }

}
