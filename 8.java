import java.util.Scanner;

public class Solution {
    public int myAtoi(String str) {
        int flag = 1;
//        int end = -1;
        int num = 0;
        int neg = 0;
//        int loc = -1;
        int len = str.length();
        long numL = 0L;
        long maxNegInt = 2147483648L;
        
        if(len==0){
        	num = 0;
        	return num;	
        }
        
//        char c = str.charAt(0);
//        switch(c){
//        	case ' ':flag = 0;loc = 0;break;
//        	case '-':neg = 1;loc = 0;flag=1;break;
//        	case '0':;
//        	case '1':;
//        	case '2':;
//        	case '3':;
//        	case '4':;
//        	case '5':;
//        	case '6':;
//        	case '7':;
//        	case '8':;
//        	case '9':flag = 1;loc = 0;num = num*10+(c-'0');break;
//        	default:num = 0;return num;//break;??
//        }
//        for(int i=1;i<len-1;i++){
//        	if(str.charAt(i)==' '){
//        		loc++;
//        		continue;
//        	}//else{
//	        	if(str.charAt(i)>='0' && str.charAt(i)<='9'){
//	        		if(loc==(i-1)||flag==0){
//	        			num = num*10+(str.charAt(i)-'0');
//	        			loc++;
//	        		}else{
//	        			num = 0;break;
//	        		}
//	        	}else{
//	        		num = 0;break;
//	        	}
        	//}
//        }//for
        
//        for(int i=0;i<str.length();i++){
//        	if(str.charAt(i)=='-' && str.charAt(i+1)>='0' && str.charAt(i+1)<='9'){
//        		neg = 1;
//        	}
//        	if(str.charAt(i)>='0' && str.charAt(i)<='9'){
//        			System.out.println("I'm in 0~9!!");
//        		if(first==-1){//the first integer
//							if(i>0){
//								if(str.charAt(i-1)=='-'){
//									neg = 1;
//								}
//							}
//							first = 1;
//       			  num = str.charAt(i)-'0'+num*10;
//        			System.out.println("num="+num);
//        			first = 1;
//        			first = i;
//        			end = i;//"1"
//        		}else{
//        			break;
//        			end = i;
//        	}
//        	}else{
//        		if(first==1){
        			//first = -1;
//        			break;
//        		}else{
//        			continue;
//        		}
//        		break;
//        	}
//        }
//        System.out.println("first="+first+" end="+end);
//        if(first>=0){
//	        String intStr = str.substring(first,end+1);
//	        num = Integer.parseInt(intStr);//2147483648??
//						return num;
//        }else{
//        	num = 0;
//        }
//        if(num<0){
//        	num = -num;
//        }
    	for(int i=0;i<len;i++){
    		if(str.charAt(i)>='0' && str.charAt(i)<='9'){
    			flag = 0;
    			numL = numL*10+(str.charAt(i)-'0');
    			if(neg==1 && numL>maxNegInt){//8!!
    				System.out.println("I'm in numL>maxNegInt!!  numL="+numL);
    			    num = -2147483648;
    			    return num;
    			}
    			if(neg==0 && numL>2147483647){//neg!=0
    				System.out.println("I'm in neg!=0 && numL>2147483647!!  numL="+numL);
    			    num = 2147483647;
    			    return num;
    			}
//            	if(num>2147483648){
//            		num = 0;
//            	}
    			System.out.println("str.charAt(i)>='0' && str.charAt(i)<='9'  numL="+numL);
    			continue;
		}else if(str.charAt(i)==' ' && flag==1){
						continue;	
		}else if(str.charAt(i)=='-' && flag==1){
					if(str.charAt(i+1)<='9' && str.charAt(i+1)>='0' && i<len-1){
						//num = num*10+(str.charAt(i)-'0');
						System.out.println("str.charAt(i)=='-'  num="+num);
						neg = 1;continue;
					}else{
						num = 0;break;
					}
		}else if(str.charAt(i)=='+' && flag==1){
    			if(str.charAt(i+1)<='9' && str.charAt(i+1)>='0' && i<len-1){
    				continue;
    			}else{
    				num = 0;break;
    			}
		}else{
			//num = 0;
			break;
		}
	}//for
	System.out.println("final numL="+numL);
	num = (int)numL;
	if(neg==1){
		num=num*(-1);
	}
// 	if(num<=(-2147483648) && neg!=1){//num>2147483647
// 		num = 2147483647;
// 		return num;
// 	}
// 	if(num<=(-2147483648) && neg==1){
// 	    num = -2147483648;
// 	    return num;
// 	}
// 	if(num>2147483647){//不存在，直接转为负数
// 	    num = 2147483647;
// 	    return num;
// 	}
    return num;
        
    }
    public static void main(String[] args){
  	Scanner sc = new Scanner(System.in);
		String str = sc.nextLine();
	
		Solution sol = new Solution();
		int num = sol.myAtoi(str);
		System.out.println(num);
}
}