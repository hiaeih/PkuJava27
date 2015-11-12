class MyStack {
	
	
	//使用quene来完成stack的四个函数
	
	Queue<Integer> queue1 = new LinkedList<Integer>();
	Queue<Integer> queue2 = new LinkedList<Integer>();

 
    // Push element x onto stack.
    public void push(int x) {
		queue1.offer(x);
    }

    // Removes the element on top of the stack.
    public void pop() {
		while(queue1.size()>1){
			queue2.offer(queue1.poll());
		}
		if(queue1.size()==1){
			queue1.poll();
			while(queue2.size()>0){
				queue1.offer(queue2.poll());
			}
		}
    }

    // Get the top element.
	//get 之后恢复原来的stack
    public int top() {
		while(queue1.size()>1){
			queue2.offer(queue1.poll());
		}
		int top=queue1.poll();
		while(queue2.size()>0){
			queue1.offer(queue2.poll());
		}
		//少加下行代码
		queue1.offer(top);
		return top;
    }

    // Return whether the stack is empty.
    public boolean empty() {
		return queue1.isEmpty();
        
    }
}