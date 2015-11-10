public class IntegerToRoman{
    public String intToRoman(int num) {
        if(num < 1 && num > 3999)
            return "";
        String romanNum = "";
        //�Ӵ�С��飬ƥ�䣬ֻ�����������С��Ϊʵ����С�Ҵ�����
        int[] number = {1000,900,500,400,100,90,50,40,10,9,5,4,1};
        String[] roman = {"M","CM","D","CD","C","XC","L","XL","X","IX","V","IV","I"};
        for(int i = 0; i < number.length; i++){
            while(num >= number[i]){
                num -= number[i];
                romanNum += roman[i];
            }
        }
        return romanNum;
    }
}