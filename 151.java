//Reverse Words in a String
public class Solution {
    public String reverseWords(String s) {
		// 2.利用两个index定位最后一个单词，复杂些，还没有进行优化。
		String s2="";
		s=" "+s+" ";
		//index1是从字符串尾部算空格第一次出现的位置，index2是空格第二次出现的位置
		int index1=s.length();
		int index2=s.length();
		while(index1>=0&&index2>=0){
			index1=s.lastIndexOf(" ");
			index2=s.lastIndexOf(" ",index1-1);
			if(index1>=0&&index2>=0){
				//如果两个相邻index之差等于1说明单词之间连续空格，使用substring移除空格得到新的s
				//index之差大于1则提取出单词添加到输出中，substring移除单词得到新的s
				if(index1-index2>1){
					s2=s2+s.substring(index2,index1);
				}
			s=s.substring(0,index2+1);
			}
		}
		s=s2.trim();
		// 1.第一次错误 missing return statement
		return s;
    }
}