import java.util.Arrays;


public class Solution {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] test={9,9,9,9};
		System.out.println(Arrays.toString(plusOne(test)));
		

	}
    public static int[] plusOne(int[] digits) {
      int temp,carry=1;
      //�����һλ��ʼ���м�1����
      for (int i=digits.length-1;i>=0;i--)
      {
    	  temp=digits[i];
    	  digits[i]=(temp+carry)%10;
    	  carry=(temp+carry)/10;
    	  //i--;//���д���ᵼ��Խλ���
    	  if(carry==0){
    		  break;
    	  }
      }
      
      if(carry==1){
    	  int[] newdigits=new int[digits.length+1];
    	  //��ԭ�������鸴�Ƶ���������
          System.arraycopy(digits, 0, newdigits, 1, digits.length);
    	  newdigits[0]=carry;
    	  //newdigits[1]=0;
    	  return newdigits;
      }
      return digits;
    }

}
