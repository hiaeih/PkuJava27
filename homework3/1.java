import java.util.*;
public class Solution {
    public int[] twoSum(int[] nums, int target) {
        //����1����ʱ����
        // int[] index = new int[2];
        // int[] nums2 = new int[nums.length];
        // int flag1 = -1;
        // int flag2 = -1;
        // System.arraycopy(nums,0,nums2,0,nums.length);//ԭ���鱸��
        
        //Arrays.sort(nums);//nums = Arrays.sort(nums);  ����ֵ��ı䣡����
        //target<nums[0] || target>nums[length-1]
        
        //ͷβָ����ָ����ӣ���С��target����ͷָ��ǰ������֮βָ��ǰ��
    //     for(int i=0;i<nums.length;i++){
    //         for(int j=nums.length-1;j>i;j--){
    //             System.out.println("I'm in double for!!!");
    //             if((nums[i]+nums[j])>target){//j--(i���䣡��)
    //                 continue;//j--;
    //             }
    //             if((nums[i]+nums[j])<target){//i++(j���䣡��)
    //                 i++;j--;continue;//i++
    //             }
    //             if((nums[i]+nums[j])==target){
    //                 System.out.println("i="+i+" j="+j+" nums[i]="+nums[i]+" nums[j]="+nums[j]);
    //                 flag1=nums[i];
    //                 flag2=nums[j];
    //                 break;//ֻ������һ��ѭ��
    //                 //return index;
    //             }
    //         }
    //         if(flag1!=-1){
    //             break;
    //         }
    //     }
    //     // for(int i=0,j=nums.length-1;i<nums.length,j>i;i++,j--){
    //     //     if((nums[i]+nums[j])>target)
    //     // }
        
    //     //����ԭ����
    //     for(int i=0;i<nums2.length;i++){
    //         if(nums2[i]==flag1){
    //             index[0]=i+1;
    //         }
    //         if(nums2[i]==flag2){
    //             index[1]=i+1;
    //             return index;
    //         }
    //     }
    //     return index;
        
    // }��ʱ������

    /**
     * ��������HashMap
     * key-->number[i],value-->i
     * value map.get(key)
     * ���HashMap�а���target-number[i]��ͨ��map.get(key)������ţ�map->value������ʱi��Ϊ��һ��ţ���ţ�
     * ����number[i]--i+1����map��
     */ 
     int[] index = new int[2];
     Map<Integer,Integer> map = new HashMap<Integer,Integer>();
     
     for(int i=0;i<nums.length;i++){
         if(map.containsKey(target-nums[i])){
             index[0] = map.get(target-nums[i]) + 1;
             index[1] = i + 1;
             break;
         }else{
             map.put(nums[i],i);
         }
     }
     return index;
    }
}