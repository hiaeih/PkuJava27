public class Solution {
    public int lengthOfLastWord(String s) {
		int length=0;
		s=s.trim();
		int index = s.lastIndexOf(" ");
		//1.第一次错误没有考虑字符串s没有空格的情况   比如输入为"a"
		// s=s.substring(index+1, s.length());
		// length=s.length();
		// return length;
		//2.正确算法   利用lastIndexOf判断最后一个单词的位置并计算长度
		if(index>=0){
		s=s.substring(index+1, s.length());
		length=s.length();
		return length;
		}
		else{
			return s.length();
		}
    }
}