class MinStack {
    List<Integer> list = new ArrayList<Integer>();  //��������Ԫ��
    //ȡ����СԪ�أ������Ҫ�Ƚ�ÿ�δ����Ԫ���뵱ǰlist2�еĴ�С�����С����룬���򲻴棬����ʼ�ձ���list2������Ԫ��Ϊ��С��
    List<Integer> list2 = new ArrayList<Integer>(); //������СԪ��
    
    public void push(int x) {
        list.add(x);
        if(list2.size() == 0 || list2.get(list2.size() - 1) >= x)
            list2.add(x);
    }
    
    public void pop() {
        if(list.size() == 0)
            return;
        //˵��list2�е�����Ԫ�غ�list��Ҫ�Ƴ�Ԫ��һ��������һ��ɾ
        //if(list2.get(list2.size() -1) == list.get(list.size() - 1))//��������
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
    //����ʱ��
    if(list2.size() != 0)
        return list2.get(list2.size() - 1);
    return 0;
    //����ͨ��������ÿ�ζ�����һ�飬ò�Ʋ�̫��ʵ��O(n) and question ask for in constant time
        /*int min = list.get(list.size() - 1);
        for(int i = 0; i < list.size() - 1; i++){
            if(min > list.get(i))
                min = list.get(i);
        }
        return min;*/
    }
}
