//Reverse Words in a String
public class Solution {
    public String reverseWords(String s) {
		// 2.��������index��λ���һ�����ʣ�����Щ����û�н����Ż���
		String s2="";
		s=" "+s+" ";
		//index1�Ǵ��ַ���β����ո��һ�γ��ֵ�λ�ã�index2�ǿո�ڶ��γ��ֵ�λ��
		int index1=s.length();
		int index2=s.length();
		while(index1>=0&&index2>=0){
			index1=s.lastIndexOf(" ");
			index2=s.lastIndexOf(" ",index1-1);
			if(index1>=0&&index2>=0){
				//�����������index֮�����1˵������֮�������ո�ʹ��substring�Ƴ��ո�õ��µ�s
				//index֮�����1����ȡ��������ӵ�����У�substring�Ƴ����ʵõ��µ�s
				if(index1-index2>1){
					s2=s2+s.substring(index2,index1);
				}
			s=s.substring(0,index2+1);
			}
		}
		s=s2.trim();
		// 1.��һ�δ��� missing return statement
		return s;
    }
}