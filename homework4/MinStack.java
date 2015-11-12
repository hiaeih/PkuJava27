//Min Stack
//Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.
//push(x) -- Push element x onto stack.
//pop() -- Removes the element on top of the stack.
//top() -- Get the top element.
//getMin() -- Retrieve the minimum element in the stack.
class MinStack {
	//1.定义两个栈来完成四个函数
	//利用了额外空间
	private Stack<Integer> stack=new Stack<Integer>();
    private Stack<Integer> min=new Stack<Integer>();
    // public void push(int x) {
        // stack.push(x);
        // if (min.isEmpty()) {
            // min.push(x);
        // } else {
            // min.push(Math.min(x, min.peek()));
        // }
    // }
	// public int top() {
		// return stack.peek();    
    // }


    // public int pop() {
        // min.pop();
        // return stack.pop();
    // }

    // public int getMin() {
        // return min.peek();
    // }
	//2.相比于1减少了一些空间的使用  空间复杂度不变

    public void push(int x) {
        stack.push(x);
        if (min.empty() == true)
            min.push(x);
        else if (Integer.parseInt(min.peek().toString()) >= x)
            min.push(x);
    }
    public int top() {
		return stack.peek();    
    }
    public int pop() {
        if (stack.peek().equals(minStack.peek()) ) 
            minStack.pop();
        return stack.pop();
    }

    public int getMin() {
        return minStack.peek();
    }
}