public class Solution {
    //����һ��
     public void rotate(int[] nums,int k){
        if(k==0) return;
        if(nums.length==1) return;
        
        int n=nums.length;
         k=k%n;
        int[] nums1=new int[n];
        int m=n-k;
        int i=0;
        while(m<n&&i<k){
            nums1[i]=nums[m];
            m++;
            i++;
        }
        int j=0;
        i=k;
        while(j<n-k&&i<n){
            nums1[i]=nums[j];
            j++;
            i++;
        }
        //system.arraycopy������Ҫ��û�������˷����겻��
        System.arraycopy(nums1,0,nums,0,n);
    //�������������һ��Ԫ�ؿ�ʼ����������ײ��壬ֱ���ƶ���k��Ԫ��, Time Limit Exceeded 
   /* public void rotate(int[] nums, int k) {
        if(k==0) return;
        if(nums.length==1) return;
         k=k%nums.length;
        for(int i=0;i<k;i++){
            int temp=nums[nums.length-1];
            for(int j=nums.length-2;j>=0;j--){
                nums[j+1]=nums[j];
            }
            nums[0]=temp;
        }
    }*/
    //����������kΪ�磬�ֱ�������������Ȼ���ٶ����������������
   /* public void rotate(int[] nums,int k){
        if(k==0) return;
        if(nums.length==1) return;
        
        int n=nums.length;
        //���kֵ���������ܳ��ȣ���Ҫ��kȥ�������ܳ��ȣ�k����������
        k=k%n;
        
        reverse(nums,0,n-k-1);
        reverse(nums,n-k,n-1);
        reverse(nums,0,n-1);
    }
    public void reverse(int[] array,int i,int j){
        while(i<j&&i>=0&&j<=array.length){
            int temp=array[i];
            array[i]=array[j];
            array[j]=temp;
            i++;
            j--;
        }*/
    }
}