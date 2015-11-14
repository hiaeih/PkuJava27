public class Solution {
     public List<List<Integer>> generate(int numRows) {  
        //用一个大的嵌套链表来存储整个帕斯卡三角形，每一行用一个小的链表来存储
        List<List<Integer>> res=new ArrayList<List<Integer>>();
        if(numRows==0) return res;
        
        List<Integer> list1=new ArrayList<Integer>();
        list1.add(1);
        res.add(list1);
        for(int i=2;i<=numRows;i++){
            List<Integer> list=new ArrayList<Integer>();
            list.add(1);
            
            //取出上一行的值
            List<Integer> pre=res.get(res.size()-1);
            for(int j=1;j<i-1;j++){
                list.add(pre.get(j-1)+pre.get(j));
            }
            list.add(1);
            res.add(list);
        }
        return res;
    }  
}