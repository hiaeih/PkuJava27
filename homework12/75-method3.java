public class Solution {
    //方法三：用快排方法，时间复杂度是O(nlogn),空间复杂度是O(1)
    public void sortColors(int[] nums){
        if(nums==null) return;
        else quickSort(nums,0,nums.length-1);
    }
    public void quickSort(int[] A,int left,int right){
          if (left >= right) {
             return;
         }
        int pivot=A[right];
        int pos=partition(A,left,right,pivot);
        quickSort(A,left,pos-1);
        quickSort(A,pos+1,right);
    }
    public int partition(int[] B,int left,int right,int pivot){
        int leftpoint=left-1;
        int rightpoint=right;
        
        while(true){
            while(B[++leftpoint]<pivot);
            while(leftpoint<rightpoint&&B[--rightpoint]>pivot);
            if(leftpoint>=rightpoint) break;
            swap(B,leftpoint,rightpoint);
        }
        swap(B,leftpoint,right);
        return leftpoint;
    }
    public void swap(int[] c,int i,int j){
        int temp=c[i];
        c[i]=c[j];
        c[j]=temp;
    }
}