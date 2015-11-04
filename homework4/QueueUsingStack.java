class MyQueue {
    Stack<Integer> stack = new Stack<Integer>();
    Stack<Integer> stack2 = new Stack<Integer>();
    
    // Push element x to the back of queue.
    public void push(int x) {
        if(stack.isEmpty())
            stack.push(x);
        else{
            while(!stack.isEmpty()){
                stack2.push(stack.pop());
            }
            stack2.push(x);
            while(!stack2.isEmpty()){
                stack.push(stack2.pop());
            }
        }
    }

    // Removes the element from in front of queue.
    public void pop() {
        stack.pop();
/*        if(stack.isEmpty())
            return;
        stack.removeElementAt(0);*/
        /*for(int i = 1;i < stack.size(); i++){
            stack2.push(stack.pop());
        }
        while(!stack2.isEmpty()){
            stack2.push(stack.pop());
        }*/
    }

    // Get the front element.
    public int peek() {
        return stack.peek();
/*        if(stack.isEmpty())
            return 0;
        while(!stack.isEmpty()){
            stack2.push(stack.peek());
        }
        int top = stack2.peek();
        while(!stack2.isEmpty()){
            stack2.pop();
        }
        return top;*/
    }

    // Return whether the queue is empty.
    public boolean empty() {
        return stack.isEmpty()?true:false;
    }
}