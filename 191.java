public class Solution {
//    int sum = 0;
//    int flag = 0;//n的初始值为0
//    int[] binOpp = new int[32];
//    int carry = 0;
    // you need to treat n as an unsigned value
    public int hammingWeight(int n) {
	  int sum = 0;
//        System.out.println(n);
//         if(n==(-2147483648)){
//             sum = 1;
//             return sum;
//         }
//         if(n==(-1)){
//             sum = 32;
//             return sum;
//         }
//         if(n<0 && n>(-2147483648)){//取反加1
//             n = (-1)*n;
//             for(int i=0;i<32;i++){//取反
//                 binOpp[i]=1-n%2;
//                 if(binOpp[i]==1){
//                     sum++;
//                 }
//             }
//             for(int j=0;j<32;j++){//加1
//                 if(binOpp[0]==0){
//                     binOpp[0]=1;
//                     sum=sum+1;
//                 }else{//找到第一个0的下标j，sum=sum-j+1
//                     if(binOpp[j]==0){
//                         sum=sum-j+1;
//                         return sum;
//                     }
// //                    break;
//                 }
//             }
// //            sum = 1;
//             return sum;
//         }//for
        // if(n==0){
        //     sum = 0;
        //     return sum;
        // }
        while(n!=0){
            n = n&(n-1);
//            System.out.println("while(n!=0) n="+n);
//            if((n&(n-1))==1){//if((n&(n-1)))==1)
                sum++;
//            }
//            n = n>>1;//移位
        }
        // if(n==1){
        //     sum++;
        //     return sum;
        // }else{
        //     // if(n==0){//解决n=0的情况
        //     //     return sum;
        //     // }
        //     if(n%2==1){
        //         sum++;
        //     }
        //     n=n/2;
        //     hammingWeight(n);
        // }
        return sum;
    }
