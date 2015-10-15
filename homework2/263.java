//Ugly Number
public class Solution {
    public boolean isUgly(int num) {
		//负数直接返回flase
        if(num<=0) {
			return false;
		}  
        if(num==1) {
			return true; 
		} 
		//一个数学问题 难度不高
        //把num循环除以所有的2,3,5直到不能整除
        while(num>=2 && num%2==0) {
            num=num/2;
        }
        while(num>=3 && num%3==0){
            num=num/3;
        } 
        while(num>=5 && num%5==0){
            num=num/5;
        }
         if(num ==1){
			 return true;
		 }else{
			 return false;
		 }
 
    }
}