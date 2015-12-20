public class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        //System.out.println("nums1[0]="+nums1[0]+" nums1[m-1]="+nums1[m-1]);==>升序
    //     if(nums1[m-1]<=nums2[0]){//nums2整体移动到nums1后
    //         for(int i=0;i<n;i++){
    //             nums1[m+i]=nums2[i];
    //         }
    //     }
    //     if(nums2[n-1]<=nums1[0]){//nums2整体移动到nums1前
    //         for(int i=m+n-1;i>m-1;i++){
    //             nums1[i]=nums1[i-n];//nums1整体后移n位
    //         }
    //         for(int i=0;i<n;i++){
    //             nums1[i]=nums2[i];
    //         }
    //     }
    //   if(nums2[n-1]>nums1[0] && nums2[n-1]<nums1[m-1]) {
    //       //太复杂了！！
    //   } 
    
        int k=m+n-1;
        while((n>=1)&&(m>=1)){
            if(nums2[n-1]>=nums1[m-1]){
                nums1[k--]=nums2[n-1];//nums1[m+n-1]=nums2[n-1];
                n--;
            }else {
                nums1[k--]=nums1[m-1];
                m--;
            }
            //n--;m--;
            //(m+n)=m+n+1;;
        }
        if(n>m){
            while(n>=1){
                nums1[k--]=nums2[n-1];
                n--;
            }
        }else {
            while(m>=1){
                nums1[k--]=nums1[m-1];
                m--;
            }
        }
    }
}