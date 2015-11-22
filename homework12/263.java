/*根据丑数的定义，丑数只能被2、3和5整除。也就是说如果一个数如果它能被2整除，我们把它连续除以2；如果能被3整除，就连续除以3；如果能被5整除，就除以连续5。如果最后我们得到的是1，那么这个数就是丑数，否则不是。*/

public class Solution {
    public boolean isUgly(int num) {
        if(num<=0) return false;
        else if(num==1) return true;
        else {
            while(num%2==0&&num/2!=1){
                num=num/2;
            }
            if(num/2==1&&num%2==0) return true;
            else if(num%2!=0) {
                while(num%3==0&&num/3!=1){
                    num=num/3;
                }
                if(num/3==1&&num%3==0) return true;
                else if(num%3!=0) {
                while(num%5==0&&num/5!=1){
                    num=num/5;
                }
                 if(num/5==1&&num%5==0) return true;
            }
            
        }
        
    }
    return false;
}
}