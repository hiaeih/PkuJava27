import java.util.Stack;

class MyQueue {
    Stack<Integer> mainStack = new Stack<>();
    Stack<Integer> assistStack = new Stack<>();
    /**
     * ����һ������Ҫ��ʱ�����assistStackʵ�֣����--> TimeOut!!
     * ���������ο����ϣ�����Ҫ��ʱ��mainStack�е�Ԫ����(pop)��assistStack�У�ע�⣺����Ԫ���������ģ���
     * */
     // Prepare for assistStack
    //  public void prepareAssist() {
    //      while(!mainStack.empty()) {
    //          assistStack.push(mainStack.peek());//pop()-->peek
    //      }
    //  }
    // Push element x to the back of queue.
    public void push(int x) {
        mainStack.push(x);
    }

    // Removes the element from in front of queue.
    public void pop() {
        //boolean flag = true;
        //Stack<Integer> assistStack = new Stack<>();
        
        // while(!mainStack.empty()){//��mainStack��Ԫ�ض�pop��assistStack��
        //     assistStack.push(mainStack.pop());
        //     //assistStack.pop();
        // }

        // while(!assistStack.empty()){
        //     if(flag == true){
        //         assistStack.pop();//����һ��Ԫ�ص���
        //         flag = false;
        //     }
        //     mainStack.push(assistStack.pop());//��ʣ��Ԫ�طŻص�mainStack��
        // }
        
        if(!assistStack.empty()) {
            assistStack.pop();
        }else {
           while(!mainStack.empty()) {
                assistStack.push(mainStack.pop());//peek--if,������else����
            }
            assistStack.pop();
        }
    }

    // Get the front element.
    public int peek() {
        // Stack<Integer> assistStack = new Stack<>();
        
        // while(!mainStack.empty()){//��mainStack��Ԫ�ض�pop��assistStack��
        //     assistStack.push(mainStack.pop());
        // }
        // return assistStack.peek();
        if(!assistStack.empty()) {
            return assistStack.peek();
        }else {
           while(!mainStack.empty()) {
                assistStack.push(mainStack.pop());//peek--if,������else����
            }
            return assistStack.peek();
        }
    }

    // Return whether the queue is empty.
    public boolean empty() {
        // if(mainStack.empty() || assistStack.empty()){
        //     return true;
        // }else {
        //     return false;
        // }
        return (mainStack.empty() && assistStack.empty());
    }
}