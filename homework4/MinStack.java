class MinStack {
    List<Integer> list = new ArrayList<Integer>();  //保存所有元素
    //取得最小元素，因此需要比较每次存入的元素与当前list2中的大小，如果小则存入，否则不存，这样始终保持list2中最新元素为最小的
    List<Integer> list2 = new ArrayList<Integer>(); //保存最小元素
    
    public void push(int x) {
        list.add(x);
        if(list2.size() == 0 || list2.get(list2.size() - 1) >= x)
            list2.add(x);
    }
    
    public void pop() {
        if(list.size() == 0)
            return;
        //说明list2中的最新元素和list中要移除元素一样，所以一起删
        //if(list2.get(list2.size() -1) == list.get(list.size() - 1))//出错？？？
        int elem = list.get(list.size() - 1);  
		if (elem == list2.get(list2.size() - 1))
		    list2.remove(list2.size() - 1);
        list.remove(list.size() - 1);
    }
    
    public int top() {
        if(list.size() == 0)
            return 0;
        return list.get(list.size() - 1);
    }
    
    public int getMin() {
    //常数时间
    if(list2.size() != 0)
        return list2.get(list2.size() - 1);
    return 0;
    //可以通过，但是每次都遍历一遍，貌似不太现实，O(n) and question ask for in constant time
        /*int min = list.get(list.size() - 1);
        for(int i = 0; i < list.size() - 1; i++){
            if(min > list.get(i))
                min = list.get(i);
        }
        return min;*/
    }
}
