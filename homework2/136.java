import java.util.Hashtable;

public class OneHundredandThirtySix {
    public int singleNumber(int[] nums) {
        //对于负数的不好用，如果直接取绝对值又涉及重复-1和1，舍弃
        /*if(nums.length == 0 || nums == null){  
             return 0;  
         }  
         int flag = 0;
         int[] extr = new int[10000];
         for(int i =  0;i < nums.length; i++){
             extr[Math.abs(nums[i])]++;
         }
        for(int i =  0;i < extr.length; i++){
            if(extr[i] == 1){
                flag = i;
                break;
            }
        }
        return flag;*/
       Map<Integer, Integer> map = new HashMap<Integer, Integer>();  
         if(nums.length == 0 || nums == null){  
             return 0;  
         }  
         for(int i=0;i<nums.length;i++){  
             if(map.containsKey(nums[i])){  
                 int value = map.get(nums[i]);  
                 map.put(nums[i], value+1);  
             }else {  
                 map.put(nums[i], 1);  
             }  
         }  
         for(int i=0;i<nums.length;i++){
             int value = map.get(nums[i]);  
             if(value == 1){  
                 return nums[i];  
             }  
         }  
         return 0;
    }
}