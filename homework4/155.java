class MinStack {

    
private Stack<Integer> s = new Stack<Integer>();

    private Stack<Integer> minS = new Stack<Integer>();//��ŵ�ǰ�������Խ�Сֵ

    

    public void push(int x) {

	if(s.empty() || x<=minS.peek())//{
		minS.push();
	//}
	s.push(x);
    }

    public void pop() {

	if(minS.peek().equals(s.peek()))//minS.peek()==s.peek()Ϊʲô�ͻ������������!!!!!!
		minS.pop();
	//}
	s.pop();
    }
    public int top() {

        //while(!s.empty()){

            return s.peek();

       // }

    }
    public int getMin() {

        // if(minS.empty()) {

        //     return s.peek();//return s.peek();

        // }else {

        //System.out.println("getMin()  minS.peek()="+minS.peek());

            return minS.peek();

        //}

    }

}