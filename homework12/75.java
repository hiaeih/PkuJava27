public class Solution {
    //����һ���ǽ�Ϊֱ�ӵķ���
    /*public void sortColors(int[] nums) {
        int count0=0;
        int count1=0;
        int count2=0;
        for(int i=0;i<nums.length;i++){
            if(nums[i]==0) count0++;
            else if(nums[i]==1) count1++;
            else if(nums[i]==2) count2++;
        }
        for(int j=0;j<count0;j++){
            nums[j]=0;
        }
        for(int j=count0;j<count0+count1;j++){
            nums[j]=1;
        }
        for(int j=count0+count1;j<count0+count1+count2;j++){
            nums[j]=2;
        }
    }*/
    //����������3��ָ��������һ��ָ��notred����ʼ�ң�ָ���һ������0����ɫ����λ�ã�һ��ָ��notblue���ҿ�ʼ�����ң�ָ���һ������2����ɫ����λ�á�Ȼ����һ���µ�ָ��iָ��notredָ���λ�ã��������������0���notred����������2���notblue�����������Զ�����
    public void sortColors(int[] nums){
        int notred=0;
        int notblue=nums.length-1;
       // while(notred<=nums.length-1&&nums[notred]==0) notred++;
        while(notred<=notblue&&nums[notred]==0) notred++;
        while(notblue>=0&&nums[notblue]==2) notblue--;
        int i=notred;
        while(i<=notblue){
        if(nums[i]==0) {
            swap(nums,i,notred);
            notred++;
            i++;
        }
        else if(nums[i]==2){
            swap(nums,i,notblue);
            notblue--;
        }
        else i++;
        }
    }
    public void swap(int[] A,int i,int j){
        int temp=A[i];
        A[i]=A[j];
        A[j]=temp;
    }
}