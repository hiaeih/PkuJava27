import java.math.BigInteger;
import java.util.Scanner;

public class Solution {
    public String addBinary(String a, String b) {
    // 	BigInteger aBInt=new BigInteger(a);//int aInt=Integer.parseInt(a.trim());//Integer.parseInt(a);
    // 	BigInteger bBInt=new BigInteger(b);//int bInt=Integer.parseInt(b.trim());//Integer.parseInt(b);
    
    // 	int maxLength=Math.max(a.length(),b.length());
    //     int[] aNum=new int[maxLength];
    // 	int[] bNum=new int[maxLength]; 
    // 	int carry=0;
    // 	String sumStr="";
    // 	BigInteger sum=new BigInteger("0");
    
    // 	for(int i=maxLength-1;i>=0;i--){
    // 		aNum[i]=(aBInt.intValue())%2;
    // 		aBInt=BigInteger.valueOf((aBInt.intValue())/10);
    // 		bNum[i]=(bBInt.intValue())%2;
    // 		bBInt=BigInteger.valueOf((bBInt.intValue())/10);
    
    // 		sum=BigInteger.valueOf(carry+aNum[i]+bNum[i]); //1+1+1??
    // 		if((sum.intValue())>=2){//sum==2
    // 			carry=1;
    // 			//bNum[i]=0;
    // 			bNum[i]=(sum.intValue())%2;
    //         }else{
    //             carry=0;
    //         	bNum[i]=sum.intValue();
    //         	//carry=0;
    //         }
    //         sum=BigInteger.valueOf(0);
    //     }//for
    //     if(carry==1){//溢出
    //         int[] bNum2=new int[maxLength+1];
    //         bNum2[0]=carry;
    //         for(int j=0;j<maxLength;j++){
    //             bNum2[j+1]=bNum[j];
    //         }
            
    //         sumStr=Arrays.toString(bNum2);
    //         //for
    //     }else{
    //         sumStr=Arrays.toString(bNum);//list.toString();String.valueOf(bNum);
    //     }
    //     sumStr=sumStr.replaceAll("\\[","");
    //     sumStr=sumStr.replaceAll("\\]","");
    //     sumStr=sumStr.replaceAll("\\,","");
    //     sumStr=sumStr.replaceAll("\\ ","");
    //     return sumStr;
    
        int carry=0;
        String sumStr="";
        
        int aLen=a.length();
        int bLen=b.length();
	int minLen=Math.min(aLen,bLen);
        
	System.out.println("a="+a);
	System.out.println("b="+b);
	System.out.println("a.charAt(0)="+a.charAt(0));

//	for(int i=minLen-1;i>=0;i--){
//	    int sum=(a.charAt(i)-'0')+(b.charAt(i)-'0')+carry;
//	    System.out.println("a.charAt(i)="+(a.charAt(i)-'0')+"b.charAt(i)="+(b.charAt(i)-'0'));
//	    if(sum>=2){
//	        carry=1;
//		sumStr=String.valueOf(sum%2)+sumStr;//sumStr+String.valueOf(sum%2);
//		System.out.println("carry=1:sumStr="+sumStr);
//	    }else{
//		carry=0;
//		sumStr=String.valueOf(sum)+sumStr;//sumStr+String.valueOf(sum%2);
//		System.out.println("carry=0:sumStr="+sumStr);
//	     }
//	    aLen--;
//	    bLen--;
//	}
        while(aLen>0 && bLen>0){
	    System.out.println("a.charAt(aLen-1)="+(a.charAt(aLen-1)-'0')+"  b.charAt(bLen-1)="+(b.charAt(bLen-1)-'0'));
            int sum=(a.charAt(aLen-1)-'0')+(b.charAt(bLen-1)-'0')+carry;//从高位开始相加？？
            if(sum>=2){
                carry=1;
                sumStr=String.valueOf(sum%2)+sumStr;//sumStr+String.valueOf(sum%2);
		System.out.println("I'm in sum>=2!!! sumStr="+sumStr);
            }else{
                carry=0;
                sumStr=sumStr+String.valueOf(sum);
		System.out.println("I'm in else!!! sumStr="+sumStr);
            }
            aLen--;
            bLen--;
        }
        while(aLen>0){
	    System.out.println("I'm while(aLen>0)!!! sumStr="+sumStr);
            int sum=(a.charAt(aLen-1)-'0')+carry;
            if(sum==2){
                carry=1;
                sumStr=String.valueOf(sum%2)+sumStr;
            }else{
                carry=0;
                sumStr=String.valueOf(sum)+sumStr;//直接将剩下的加上？？
            }
            aLen--;
        }
        while(bLen>0){
	    System.out.println("I'm while(bLen>0)!!!");
            int sum=(b.charAt(bLen-1)-'0')+carry;
            if(sum==2){
                carry=1;
                sumStr=String.valueOf(sum%2)+sumStr;
            }else{
                carry=0;
                sumStr=String.valueOf(sum)+sumStr;//直接将剩下的加上？？
            }
            bLen--;
        }
        //溢出
        if(carry==1){
            sumStr="1"+sumStr;//sumStr+"1";
	    System.out.println("I'm overflow!!");
        }
        return sumStr;
    }
    public static void main(String[] args){
    	Scanner sc=new Scanner(System.in);
	String str1=sc.nextLine();
	String str2=sc.nextLine();

	Solution s=new Solution();
	String sumStr=s.addBinary(str1,str2);
	System.out.println(sumStr);
    }
}