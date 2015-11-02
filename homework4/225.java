class MyStack {
    
    Queue<Integer> q = new LinkedList<Integer>();//Queue<Integer>();//Ö÷queue
    Queue<Integer> q2 = new LinkedList<Integer>();//Queue<Integer>();
    
    // Push element x onto stack.
    public void push(int x) {
        q.offer(x);
    }

    // Removes the element on top of the stack.
    public void pop() {
        
        while(q.size()>1) {
            q2.offer(q.poll());
        }
        q.poll();
        while(q2.size()>0){
            q.offer(q2.poll());
        }
    }

    // Get the top element.
    public int top() {
        while(q.size()>1) {
            q2.offer(q.poll());
        }
        int val = q.element();
        q2.offer(q.poll());
        while(q2.size()>0){
            q.offer(q2.poll());
        }
        return val;
    }

    // Return whether the stack is empty.
    public boolean empty() {
        return q.isEmpty();
    }
}