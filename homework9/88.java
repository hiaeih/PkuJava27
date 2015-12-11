public class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        if(n==0) return;
        if(m==0) {
            for(int i=0;i<n;i++){
                nums1[i]=nums2[i];
            }
        }
        if(m>0&&n>0){
        //nums2[0]>=nums1[m-1]
        if(nums2[0]>=nums1[m-1]){
            for(int i=0;i<n;i++){
                nums1[m]=nums2[i];
                m++;
            }
        }
        //nums2[n-1]<=nums1[0]
        else if(nums2[n-1]<=nums1[0]){
            for(int j=m-1;j>=0;j--){
                nums1[j+n]=nums1[j];
            }
            for(int k=0;k<n;k++){
                nums1[k]=nums2[k];
            }
        }
        //需要交叉着合并到nums1
        else {
            //j记录nums2插入的位置，count记录插入到nums1的元素数
            int j=0,count=0;
            for(int i=0;i<n;i++){
                while(nums2[i]>nums1[j]&&j<m+count) j++;
                for(int k=m-1+count;k>=j;k--){
                    nums1[k+1]=nums1[k];
                }
                nums1[j]=nums2[i];
                j++;
                count++;
            }
        }
        }
    }
}