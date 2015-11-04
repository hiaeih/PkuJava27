public class MajorityElement{
    public int majorityElement(int[] nums) {
        //һ���Ƚ�O(n^2)
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
        //�����Ѿ������������ҪԪ�أ����Ҵ���n/2��������󣬸�Ԫ��һ�����м䣬�ſ��ܴ���һ�㳤��O(n)
        Arrays.sort(nums);
        return nums[(nums.length+1)/2 - 1];
        //���β��ԣ���Ϊn/2��
        /*int count = 0;
        int k = nums.length;
        int[][] arr = new int[(k+1)/2][2];
        while(k > 3){
            //��Ϊn/2��
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