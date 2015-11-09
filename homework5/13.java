public class Solution {
    public int romanToInt(String s) {
        /**
         * 参考思路：
         * 当前值若比前一个值小或相同，则直接加上即可
         * 当前值比前一个值大，意味着该值与前一位一起构成一个值，第前前个值将第前个值多加了，应减去，
         * 然后加上（该位值减去前一位）的值
         * */
        char[] roman = { 'I', 'V', 'X', 'L', 'C', 'D', 'M' };
        int[] val = { 1, 5, 10, 50, 100, 500, 1000 };
        Map<Character, Integer> map = new HashMap<Character, Integer>();
        for (int i = 0; i < roman.length; i++){//roman-value
            map.put(roman[i], val[i]);
        }
        int sum = map.get(s.charAt(0));//罗马数字第一个字母对应的值取出
        for(int i=1;i<s.length();i++) {//s.length
            if(map.get(s.charAt(i))<=map.get(s.charAt(i-1))){//当前值若比前一个值小或相同，则直接加上即可
                sum += map.get(s.charAt(i));
            }else {
                sum = (sum-map.get(s.charAt(i-1)))+(map.get(s.charAt(i))-map.get(s.charAt(i-1)));
            }
        }
        
        return sum;
         
    }
}