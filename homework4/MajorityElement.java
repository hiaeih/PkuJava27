public class MajorityElement{
    public int majorityElement(int[] nums) {
        //一点点比较O(n^2)
        /*Arrays.sort(nums);
        int result = 0;
        for(int i = 0; i < nums.length; i++)
        {
            int count = 1;
            int temp = nums[i];
            for(int j = i+1; j < nums.length; j++){
                if(temp == nums[j])
                    count++;
                else
                    break;
            }
            if(count >= (nums.length+1)/2){
                    result = temp;
                    break;
            }
        }
        return result;*/
        //首先已经存在了这个主要元素，并且大于n/2，则排序后，该元素一定在中间，才可能大于一般长度O(n)
        Arrays.sort(nums);
        return nums[(nums.length+1)/2 - 1];
        //分治策略，分为n/2组
        /*int count = 0;
        int k = nums.length;
        int[][] arr = new int[(k+1)/2][2];
        while(k > 3){
            //分为n/2组
            arr[][0]
            for(int i = 0; i < k/2; i++){
                if(arr[i][0] == arr[i][1]){
                    count++;
                }
            }
        }
        if(k == 3)
             */
    }
}