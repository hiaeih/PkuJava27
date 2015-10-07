public class Solution {
    public List<Integer> getRow(int rowIndex) {
        List<Integer> list = new ArrayList<Integer>();
        for(int i = 0;i <= rowIndex; i++){
            for(int j = i;j >=0;j--){
				//从后向前比较，i==j时，一定为1，加入集合
                if(i == j)
                    list.add(1);
				//i！=j且j！=0，说明在中间需要依靠list之前的值进行增加，因为j是从后向前的，所以为j和j-1的和
                else if(j != 0)
                    list.set(j,list.get(j-1) + list.get(j));
            }
			//层数遍历结束
        }
        return list;
    }
}