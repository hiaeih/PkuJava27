class MyStack {
	
	
	//ʹ��quene�����stack���ĸ�����
	
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
	//get ֮��ָ�ԭ����stack
    public int top() {
		while(queue1.size()>1){
			queue2.offer(queue1.poll());
		}
		int top=queue1.poll();
		while(queue2.size()>0){
			queue1.offer(queue2.poll());
		}
		//�ټ����д���
		queue1.offer(top);
		return top;
    }

    // Return whether the stack is empty.
    public boolean empty() {
		return queue1.isEmpty();
        
    }
}