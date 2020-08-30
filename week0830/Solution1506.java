package leetcodeweek0830;

/*
//Definition for a Node.
class Node {
 public int val;
 public List<Node> children;

 
 public Node() {
     children = new ArrayList<Node>();
 }
 
 public Node(int _val) {
     val = _val;
     children = new ArrayList<Node>();
 }
 
 public Node(int _val,ArrayList<Node> _children) {
     val = _val;
     children = _children;
 }
};
*/

class Solution1506 {
	public Node findRoot(List<Node> tree) {
		int allSum = 0;
		int allChildrenSum = 0;
		for (Node node : tree) {
			allSum += node.val;
			for (Node child : node.children) {
				allChildrenSum += child.val;
			}
		}
		for (Node node : tree) {
			if (node.val == allSum - allChildrenSum) {
				return node;
			}
		}
		return null;
	}
}
