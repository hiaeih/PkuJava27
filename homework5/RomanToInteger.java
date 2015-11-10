public class RomanToInteger {
    public int romanToInt(String s) {
        if(s.length() == 0 || s == "")
            return 0;
        Map<Character,Integer> map = new HashMap<Character,Integer>();
        char[] roman = {'M','D','C','L','X','V','I'};
        int[] romanNum = {1000,500,100,50,10,5,1};
        int[] number = new int[100];
        for(int i = 0; i < roman.length; i++){
            number[roman[i]] = romanNum[i];
            //map.put(roman[i],romanNum[i]);  //键值对直接匹配
        }
        char[] romanChar = s.toCharArray();
        int Num = number[romanChar[0]];
        //int Num = 0;
        //int right = 0;
        //从小到大检查，匹配，左大右小进行加法，左小右大减法
        //for(int i = s.length() - 1; i >= 0; i--){
        for(int i = 0; i < s.length() - 1; i++){
            /*int left = map.get(romanChar[i]);
            Num += left < right? -left:left;
            right = left;*/
            if(number[romanChar[i]] < number[romanChar[i+1]])
                Num += number[romanChar[i+1]] - 2*number[romanChar[i]];
            else
                Num += number[romanChar[i+1]];
        }
        return Num;
    }
}