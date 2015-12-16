/*Count the number of prime numbers less than a non-negative number, n.找出数n之内的质数个数*/
public class CountPrimes {
    public int countPrimes(int n) {
      //2,3,5,7,11,13,17   
     //20  5  
     //init check  n从2开始计算到根号n，抛去n的倍数，把n保留
     boolean[] a = new boolean[n];  
     for(int i=2; i*i<n; i++) {
        if(!a[i]) {
            for(int j=i; i*j<n; j++) {
                a[i*j] = true;//表明访问过了
            }
        }
     }
     int c=0;
     for(int i=2; i<n; i++) {
         if(a[i] == false) ++c;
     }
     return c;
    }
}