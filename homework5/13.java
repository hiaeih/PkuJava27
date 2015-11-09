public class Solution {
    public int romanToInt(String s) {
        /**
         * �ο�˼·��
         * ��ǰֵ����ǰһ��ֵС����ͬ����ֱ�Ӽ��ϼ���
         * ��ǰֵ��ǰһ��ֵ����ζ�Ÿ�ֵ��ǰһλһ�𹹳�һ��ֵ����ǰǰ��ֵ����ǰ��ֵ����ˣ�Ӧ��ȥ��
         * Ȼ����ϣ���λֵ��ȥǰһλ����ֵ
         * */
        char[] roman = { 'I', 'V', 'X', 'L', 'C', 'D', 'M' };
        int[] val = { 1, 5, 10, 50, 100, 500, 1000 };
        Map<Character, Integer> map = new HashMap<Character, Integer>();
        for (int i = 0; i < roman.length; i++){//roman-value
            map.put(roman[i], val[i]);
        }
        int sum = map.get(s.charAt(0));//�������ֵ�һ����ĸ��Ӧ��ֵȡ��
        for(int i=1;i<s.length();i++) {//s.length
            if(map.get(s.charAt(i))<=map.get(s.charAt(i-1))){//��ǰֵ����ǰһ��ֵС����ͬ����ֱ�Ӽ��ϼ���
                sum += map.get(s.charAt(i));
            }else {
                sum = (sum-map.get(s.charAt(i-1)))+(map.get(s.charAt(i))-map.get(s.charAt(i-1)));
            }
        }
        
        return sum;
         
    }
}