class MyQueue {
	Stack<Integer> stack1=new Stack<Integer>();
	Stack<Integer> stack2=new Stack<Integer>();
    // Push element x to the back of queue.
    public void push(int x) {
		stack1.push(x);
        
    }

    // Removes the element from in front of queue.
    public void pop() {
		while(stack1.empty()==false){
			stack2.push(stack1.pop());
		}
		stack2.pop();
		while(stack2.empty()==false){
			stack1.push(stack2.pop());
		}
    }

    // Get the front element.
    public int peek() {
		while(stack1.empty()==false){
			stack2.push(stack1.pop());
		}
		int peek=stack2.pop();
		//刚开始忘记加这行代码了
		stack1.push(peek);
		while(stack2.empty()==false){
			stack1.push(stack2.pop());
		}
		return peek;
        
    }

    // Return whether the queue is empty.
    public boolean empty() {
        return stack1.empty();
    }
}