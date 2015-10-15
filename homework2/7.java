//Reverse Integer
public class Solution {
    public int reverse(int x) {
		 // if (x == Integer.MIN_VALUE){
			// return 0;
		 // }
		 // 设置符号变量
		 // int sign = 1;
		 // if(x<0){
			 // sign=-1;
			 // x=-x;
		 // }
        // int result = 0;
        // while (x!= 0) {
            // if (result > (Integer.MAX_VALUE - x % 10) / 10){
				// if(sign==1){
					// return Integer.MAX_VALUE;
				// }else{
					// return Integer.MIN_VALUE;
				// }
			// }
            // result = result * 10 + x % 10;
            // x =x/10;
        // }
        // return result * sign;
		if (x == Integer.MIN_VALUE)
            return 0;
		int sign = 1;
        if(x<0){
			 sign=-1;
		 }
        int positive = Math.abs(x);
        int result = 0;
        while (positive != 0) {
            if (result > (Integer.MAX_VALUE - positive % 10) / 10)
                return 0;
            result = 10 * result + positive % 10;
            positive =positive/10;
        }
        return result * sign;

    }
}