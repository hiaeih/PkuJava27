public class MergeSortedArray {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
		//¹é²¢ÅÅĞò
        while(m > 0 && n > 0){
            if(nums1[m-1] > nums2[n-1]){
                nums1[m+n-1] = nums1[m-1];
                m--;
            }else{
                nums1[m+n-1] = nums2[n-1];
                n--;
            }
        }
 
        while(n > 0){
            nums1[m+n-1] = nums2[n-1];
            n--;
        }
        /*int i = m - 1;
    	int j = n - 1;
    	int k = m + n - 1;
    	while (k >= 0) {
    		if (j < 0 || (i >= 0 && nums1[i] > nums2[j]))
    			nums1[k--] = nums1[i--];
    		else
    			nums1[k--] = nums2[j--];
        }*/
    }
}