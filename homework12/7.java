public class Solution {
    public int reverse(int x) {
        //|x|<10�����
        if(Math.abs(x)>=0&&Math.abs(x)<10) return x;
        //x�ľ���ֵ����10�����
        else{
        int flag=0;
        String s=String.valueOf(x);
        char[] arr=s.toCharArray();
        if(arr[0]=='-') flag=1;
        else  flag=0;
        int i=arr.length-1;
        while(arr[i]=='0'){
            i--;
        }
        double sum=0;
        int val=10;
        //��������ⷽ��
        if(flag==0){
        for(;i>=0;i--){
            sum=sum*val+arr[i]-'0';
        }
        //Խ�紦��
        if(sum>Integer.MAX_VALUE) return 0;
        else return (int)sum;
        }
        //��������ⷽ��
        else if(flag==1){
            for(;i>=1;i--){
            sum=sum*val+arr[i]-'0';
        }
        //Խ�紦��
        if(sum>Integer.MAX_VALUE) return 0;
        else 
        {
            sum=-sum;
            return (int)sum;
        }
        }
        return 0;
    }
    }
}