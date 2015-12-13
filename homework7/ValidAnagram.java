/*Given two strings s and t, write a function to determine if t is an anagram of s.
For example,
s = "anagram", t = "nagaram", return true.
s = "rat", t = "car", return false. */
import java.util.*;
import java.util.Iterator;
public class ValidAnagram {
    public static boolean isAnagram(String s, String t) {
        if(s.length() != t.length())
            return false;
		//m1��ͳ��ÿ���ַ�������ĸ���ֵĴ����Ƿ�һ��, �ڱ����������б�дִ��ͨ������leetCode�ϵĽ���ͱ��ز�ͬ������ԭ��
		char[] ch1 = s.toCharArray();
		char[] ch2 = t.toCharArray();
		/*HashMap<Character,Integer> map1 = new HashMap<Character,Integer>();
		HashMap<Character,Integer> map2 = new HashMap<Character,Integer>();
		for (char c : ch1) {
            Integer i = map1.get(c);
            if (i == null) {
                map1.put(c, 1);
            } else {
                map1.put(c, i.intValue() + 1);
            }
        }
        for (char c : ch2) {
            Integer i = map2.get(c);
            if (i == null) {
                map2.put(c, 1);
            } else {
                map2.put(c, i.intValue() + 1);
            }
        }
        Iterator i=map1.entrySet().iterator();
        Iterator j=map2.entrySet().iterator();
        while(i.hasNext() && j.hasNext()){//ֻ����һ��,�ٶȿ�
            Map.Entry e1=(Map.Entry)i.next();
			Map.Entry e2=(Map.Entry)j.next();
			System.out.println("map1: " + e1.getKey() + " " + e1.getValue() + "\tmap2: " + e2.getKey() + " " + e2.getValue());
            if((e1.getKey() != e2.getKey()) || (e1.getValue() != e2.getValue())){
				return false;
			}
        }
		return true;*/
		//m2����ֱ�ӵķ�ʽ�жϣ�����ʹ�ü���
		Arrays.sort(ch1);
		Arrays.sort(ch2);
		return String.valueOf(ch1).equals(String.valueOf(ch2))?true:false;
    }
	public static void main(String[] args){
		System.out.println(ValidAnagram.isAnagram("dgqztusjuu","dqugjzutsu"));
	}
}