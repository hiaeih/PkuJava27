public class Solution {
    //����һ�����118��Ŀ��⣻ʱ�临�ӶȺͿռ临�Ӷȶ��ܴ󣬵����Ҳ��ȷ
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
    //�����������ÿռ临�ӶȽ�С�ķ���,û��Ҫ���������˹�����ǣ���һ��������������⣬�ɼ��ٿռ临�Ӷ�
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