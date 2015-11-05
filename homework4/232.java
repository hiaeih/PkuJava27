class MyQueue {
    //算法保证在任何时候都有一栈为空
    Stack<Integer> stack1=new Stack<Integer>();
    Stack<Integer> stack2=new Stack<Integer>();
    // Push element x to the back of queue.
    public void push(int x) {
      
        if(stack1.isEmpty()){
                while(!stack2.isEmpty()){
                    int tmp=stack2.peek();
                    stack2.pop();
                    stack1.push(tmp);
                }
                 stack1.push(x);
        while(!stack1.isEmpty()){
                int tmp=stack1.peek();
                    stack1.pop();
                    stack2.push(tmp);
            }
        }
         else{
             if(stack2.isEmpty()){
                while(!stack1.isEmpty()){
                    int tmp=stack1.peek();
                    stack1.pop();
                    stack2.push(tmp);
                }
                 stack2.push(x);
                while(!stack2.isEmpty()){
                int tmp=stack2.peek();
                    stack2.pop();
                    stack1.push(tmp);
            }
         }
         }

    }

    // Removes the element from in front of queue.
     public void pop() {
        if(!stack1.isEmpty()&&stack2.isEmpty()){
           stack1.pop();
        }
        if(stack1.isEmpty()&&!stack2.isEmpty()){
            stack2.pop();
        }

    }

    // Get the front element.
    public int peek() {
        
        int tmp=0;
       if(!stack1.isEmpty()&&stack2.isEmpty()){
          tmp = stack1.peek();
        }
        if(stack1.isEmpty()&&!stack2.isEmpty()){
           tmp = stack2.peek();
        }
        return tmp;

    }

    // Return whether the queue is empty.
    public boolean empty() {
        return stack1.isEmpty()&&stack2.isEmpty();

    }
}