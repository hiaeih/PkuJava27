class MyStack {
    //�㷨��֤���κ�ʱ��������һ����Ϊ��
    /*ǰ������ʱ�����Ǳ�java.lang.NullPointerException������Ϊ����q1,q2��ʱ��û��new*/
    Queue<Integer>  q1 = new LinkedList<>(); 
    Queue<Integer>  q2 = new LinkedList<>(); 
    // Push element x onto stack.
    public void push(int x) {
        if(q1.isEmpty()){
                q1.add(x);
                while(!q2.isEmpty()){
                    int tmp=q2.poll();
                    q1.add(tmp);
                }
            }
         else{
             if(q2.isEmpty()){
                 q2.add(x);
                 while(!q1.isEmpty()){
                     int tmp=q1.poll();
                     q2.add(tmp);
                 }
             }
         }
    }

    // Removes the element on top of the stack.
    public void pop() {
        if(!q1.isEmpty()&&q2.isEmpty()){
           q1.remove();
        }
        if(q1.isEmpty()&&!q2.isEmpty()){
            q2.remove();
        }
    }

    // Get the top element.
    public int top() {
        int tmp=0;
       if(!q1.isEmpty()&&q2.isEmpty()){
          tmp = q1.peek();
        }
        if(q1.isEmpty()&&!q2.isEmpty()){
           tmp = q2.peek();
        }
        return tmp;
    }

    // Return whether the stack is empty.
    public boolean empty() {
       //return q1.isEmpty()&&q2.isEmpty();
       if(q1.isEmpty()&&q2.isEmpty())
           return true;
       else
           return false;
    }
}
