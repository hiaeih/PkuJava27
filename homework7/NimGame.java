//Nim Game
//You are playing the following Nim Game with your friend: There is a heap of stones on the table, each time one of you take turns to remove 1 to 3 stones. The one who 

//removes the last stone will be the winner. You will take the first turn to remove the stones.

//Both of you are very clever and have optimal strategies for the game. Write a function to determine whether you can win the game given the number of stones in the heap.

//For example, if there are 4 stones in the heap, then you will never win the game: no matter 1, 2, or 3 stones you remove, the last stone will always be removed by your friend
public class Solution {
    public boolean canWinNim(int n) {
		//尼姆游戏
		//1.1个石子，全部拿走；
		//2.2个石子，全部拿走；
		//3.3个石子，全部拿走；
		//4.4个石子，对手面对的是的第1，2，3情况，对手必胜；
		//5.5个石子，拿走1个让对手面对第4种情况，对手必败；
		//6.6个石子，拿走2个让后手面对第4种情况，对手必败
		//...
		//...
		//当n是4的倍数时，不管你做什么操作你都会输掉这个游戏
		//当n不是4的倍数时，你可以赢
		if(n%4==0)
			return false;
		else
			return true;
		
        
    }
}