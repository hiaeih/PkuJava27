/*Count the number of prime numbers less than a non-negative number, n.�ҳ���n֮�ڵ���������*/
public class CountPrimes {
    public int countPrimes(int n) {
      //2,3,5,7,11,13,17   
     //20  5  
     //init check  n��2��ʼ���㵽����n����ȥn�ı�������n����
     boolean[] a = new boolean[n];  
     for(int i=2; i*i<n; i++) {
        if(!a[i]) {
            for(int j=i; i*j<n; j++) {
                a[i*j] = true;//�������ʹ���
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