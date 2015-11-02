import java.util.Stack;

class MyQueue {
    Stack<Integer> mainStack = new Stack<>();
    Stack<Integer> assistStack = new Stack<>();
    /**
     * 方法一：在需要的时候借助assistStack实现，结果--> TimeOut!!
     * 方法二：参考网上，在需要的时候将mainStack中的元素吐(pop)到assistStack中（注意：这样元素是连续的！）
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
        
        // while(!mainStack.empty()){//将mainStack中元素都pop到assistStack中
        //     assistStack.push(mainStack.pop());
        //     //assistStack.pop();
        // }

        // while(!assistStack.empty()){
        //     if(flag == true){
        //         assistStack.pop();//将第一个元素弹出
        //         flag = false;
        //     }
        //     mainStack.push(assistStack.pop());//将剩余元素放回到mainStack中
        // }
        
        if(!assistStack.empty()) {
            assistStack.pop();
        }else {
           while(!mainStack.empty()) {
                assistStack.push(mainStack.pop());//peek--if,则会出现else问题
            }
            assistStack.pop();
        }
    }

    // Get the front element.
    public int peek() {
        // Stack<Integer> assistStack = new Stack<>();
        
        // while(!mainStack.empty()){//将mainStack中元素都pop到assistStack中
        //     assistStack.push(mainStack.pop());
        // }
        // return assistStack.peek();
        if(!assistStack.empty()) {
            return assistStack.peek();
        }else {
           while(!mainStack.empty()) {
                assistStack.push(mainStack.pop());//peek--if,则会出现else问题
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