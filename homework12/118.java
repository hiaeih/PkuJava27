public class Solution {
     public List<List<Integer>> generate(int numRows) {  
        //��һ�����Ƕ���������洢������˹�������Σ�ÿһ����һ��С���������洢
        List<List<Integer>> res=new ArrayList<List<Integer>>();
        if(numRows==0) return res;
        
        List<Integer> list1=new ArrayList<Integer>();
        list1.add(1);
        res.add(list1);
        for(int i=2;i<=numRows;i++){
            List<Integer> list=new ArrayList<Integer>();
            list.add(1);
            
            //ȡ����һ�е�ֵ
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