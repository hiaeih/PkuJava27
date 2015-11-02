class MinStack {

    
private Stack<Integer> s = new Stack<Integer>();

    private Stack<Integer> minS = new Stack<Integer>();//存放当前情况下相对较小值

    

    public void push(int x) {

	if(s.empty() || x<=minS.peek())//{
		minS.push();
	//}
	s.push(x);
    }

    public void pop() {

	if(minS.peek().equals(s.peek()))//minS.peek()==s.peek()为什么就会出错？？？？？!!!!!!
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