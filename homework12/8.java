public class Solution {
    public int myAtoi(String str) {
        if(str==" "||str.length()==0) return 0;
        //��ȥ�ַ������˵Ŀո�
        String str1=str.trim();
        int flag=0;
        //��ΪҪ�ж��Ƿ񳬳�������Χ����sum���Ͳ���Ϊ���ͣ������޷��ж�
        double sum=0;
        int i=0;
        if(str1.charAt(i)=='+') {flag=0;i++;}
        else if(str1.charAt(i)=='-') {flag=1;i++;}
        else if(str1.charAt(i)<'0'||str1.charAt(i)>'9') return 0;
        while(i<str1.length()){
            //����ַ����г��ַ�0-9֮����ַ�ʱ��������ѭ��
            if(str1.charAt(i)<'0'||str1.charAt(i)>'9') break;
            else sum=sum*10+(str1.charAt(i)-'0');
            i++;
        }
        if(flag==1) sum=-sum;
        if(sum>Integer.MAX_VALUE) sum=Integer.MAX_VALUE;
        if(sum<Integer.MIN_VALUE) sum=Integer.MIN_VALUE;
         //�ú�������ֵΪint����sum����Ϊdouble������Ҫǿ������ת��
        return (int)sum;
    }
}
