class MyStack {
    Queue<Integer> queue = new LinkedList<Integer>();
    
    // Push element x onto stack.
    public void push(int x) {
        //queue.add(x);
        queue.offer(x);
    }

    // Removes the element on top of the stack.
    public void pop() {
        if(queue.size() == 0)
            return;
        for(int i = 1; i < queue.size(); i++)
            queue.offer(queue.poll());
        queue.remove();
    }

    // Get the top element.
    public int top() {
        if(queue.isEmpty())
            return 0;
        for(int i = 1; i < queue.size(); i++)
            queue.add(queue.poll());
        int top = queue.peek();
        //还要还原顺序
        queue.offer(queue.poll());
        return top;
    }

    // Return whether the stack is empty.
    public boolean empty() {
        return queue.isEmpty()?true:false;
    }
}