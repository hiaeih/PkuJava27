/*���ݳ����Ķ��壬����ֻ�ܱ�2��3��5������Ҳ����˵���һ����������ܱ�2���������ǰ�����������2������ܱ�3����������������3������ܱ�5�������ͳ�������5�����������ǵõ�����1����ô��������ǳ����������ǡ�*/

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