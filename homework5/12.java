public class Solution {
    public String intToRoman(int num) {
        // char[] roman = {'M', 'D', 'C', 'L','X' ,'V' , 'I'};
        // int[] val = { 1000, 500, 100, 50, 10, 5, 1};
        // Map<Integer, Character> map = new HashMap<Integer, Character>();
        // for (int i = 0; i < roman.length; i++){//val(key)-roman
        //     map.put(val[i], roman[i]);
        // }
        // String r = "";
        // int temp = 0;
        // boolean f = true;
        // for(int i=0;i<val.length;i++){
        //     if((num/val[i])>=0){
        //         temp = num/val[i];
        //         if(temp>5 && temp<9){
        //             //System.out.println("temp>5 Sr="+r);
        //             r=r+map.get(val[i+1]);
        //             //System.out.println("r="+r);
        //             while(f){
        //                 r = r+map.get(val[i]);
        //                 temp--;
        //                 if(temp<=5){
        //                     f=false;
        //                 }
        //             }
        //             //System.out.println("temp>5 Er="+r);
        //             //System.out.println("r="+r);
        //         }else if(temp==9){
        //             r=r+map.get(val[i])+map.get(val[i+2]);
        //         }else if(temp<4){//(temp<5){MMM
        //             //r = r+map.get(i);
        //             //System.out.println("temp<4 Sr="+r);
        //             f=true;
        //             while(f){
        //                 //System.out.println("temp<4 map.get(i)="+map.get(i));
        //                 r += map.get(val[i]);
        //                 temp--;
        //                 if(temp<=0){
        //                     f=false;
        //                 }
        //             }
        //             //System.out.println("temp<4 Er="+r);
        //         }else if(temp==4){
        //           // System.out.println("temp==4 Sr="+r);
        //             r = r+map.get(val[i])+map.get(val[i+1]);
        //             //System.out.println("temp==4 Er="+r);
        //         }else {//temp==5
        //         //System.out.println("else Sr="+r+" temp="+temp);
                
        //             r = r+map.get(val[i+1]);
        //             //System.out.println("else Er="+r);
        //         }
        //     }
        //     num=num-(num/val[i])*val[i];
        // }
        String str="";    
        String symbol[]={"M","CM","D","CD","C","XC","L","XL","X","IX","V","IV","I"};    
        int value[]=    {1000,900,500,400, 100, 90,  50, 40,  10, 9,   5,  4,   1};   
        for(int i=0;num>0;i++)  
        {  
            while(num>=value[i])  
            {  
                num-=value[i];  
                str+=symbol[i];  
            }  
        }  
        return str;
    }
}