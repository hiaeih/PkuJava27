import java.util.Scanner;

public class Solution {
    public int lengthOfLastWord(String s) {
        int len = s.length();
        int sumTemp = 0;
        int num = 0;
        
        for(int i=0;i<len;i++){
//        		sumTemp = i;
        		if(s.charAt(i)==' '){
        				if(i==len-1){
        						//sumTemp = i-num;//return (i-num);//
        						break;
//        						return sumTemp;
        				}else{
        							if(s.charAt(i+1)!=' '){
        									sumTemp=0;
        							}
//        							sumTemp = 0;
        						//num = i+1;
//        						sumTemp = i+1;
        				 }
        		}else{
        					sumTemp++;
//        					num = i;
        		 }
//        		sumTemp = i;
        }
        
//        for(int i=0;i<len;i++){
//        		if(s.charAt(i)!=' '){
//        				sumTemp++;
//        				System.out.println("I'm in sumTemp++!!");
//        		}else{
//        				System.out.println("I'm in sumTemp=0!!");
//								if(sumTemp==len-1){
//										sumTemp = sumTemp-num;
//										return sumTemp;
//								}else{
//										num = sumTemp+1;
//										sumTemp++;
//								 }
//        		 		sumTemp = 0;
//        		 		num = i;
//									sumTemp++;
//        		 }
//        }
        System.out.println("sumTemp="+sumTemp+" num="+num);
//        if(sumTemp == 0){
//        		return 0;
//        }else{
//        		return (sumTemp-num-1);
//         }
				return sumTemp;
    }
    public static void main(String[] args){
	    	Scanner sc = new Scanner(System.in);
				String str = sc.nextLine();
			
				Solution sol = new Solution();
				int sum = sol.lengthOfLastWord(str);
				System.out.println(sum);
    }
}
