class Solution {
    public Node treeToDoublyList(Node root) {
        
        if(root == null) {
            return null;
        }
        
        Stack<Node> stack = new Stack<>();
        Node node = root;
        while(node != null) {
            stack.push(node);
            node = node.left;
        }
        
        Node start = stack.peek();
        Node finish = null;
        
        while(!stack.isEmpty()) {
            node = stack.pop();
            
            //connect the predecessor to the current node
            if(finish != null) {
                finish.right = node;
            }
            
            //connect current node to its predecessor
            node.left = finish; 
            
            //make the previous predecessor the current node
            finish = node; 
            
            node = node.right;
            if(node != null) {
                while(node != null) {
                    stack.push(node);
                    node = node.left;
                }
            }
        }
        
        //connect ends of the list
        start.left = finish;
        finish.right = start;
        
        return start;
    }
}
