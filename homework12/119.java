public class Solution {
    //方法一：借鉴118题目求解；时间复杂度和空间复杂度都很大，但结果也正确
    /*public List<Integer> getRow(int rowIndex) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        List<Integer> list = new ArrayList<Integer>();
        list.add(1);
        res.add(list);
        
        for(int i=2;i<=rowIndex+1;i++){
            List<Integer> list1 = new ArrayList<Integer>();
            list1.add(1);
            List<Integer> pre=res.get(res.size()-1);
            for(int j=1;j<i-1;j++){
                list1.add(pre.get(j-1)+pre.get(j));
            }
            list1.add(1);
            res.add(list1);
        }
        return res.get(rowIndex);
    }*/
    //方法二：采用空间复杂度较小的方法,没必要求出整个帕斯卡三角，用一个链表来覆盖求解，可减少空间复杂度
    public List<Integer> getRow(int rowIndex){
        List<Integer> list = new ArrayList<Integer>();
        list.add(1);
        for(int i=1;i<=rowIndex;i++){
            int temp=1;
            for(int j=1;j<i;j++){
                int inner=list.get(j);
                list.set(j,temp+inner);
                temp=inner;
            }
            list.add(1);
        }
        return list;
    }
}