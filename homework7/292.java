public class Solution {
    public boolean canWinNim(int n) {
        /**
         * 分析：
         * 1->1+0,win
         * 2->2+0,win
         * 3->3+0,win
         * 4->1+3;2+2;3+1,lose
         * 5->1+"4",win
         * 6->2+"4",win
         * 7->3+"4",win
         * 8->1+"7",lose
         * ...
         * 总结：4的倍数会输
         * */
         if(n%4==0) return false;
         else return true;
    }
}