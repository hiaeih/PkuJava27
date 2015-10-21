package week2;

public class TwohundredandSixtythree {
	public boolean isUgly(int num) {
        if(num < 1)
            return false;
        while(num%2 == 0)
            num /= 2;
        while(num%3 == 0)
            num /= 3;
        while(num%5 == 0)
            num /= 5;
    return num == 1? true:false;
    //考虑可以用递归，这样计算了多次不是丑数，待实现，或者空间换时间
    }
}
