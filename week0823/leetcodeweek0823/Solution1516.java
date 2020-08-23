package leetcodeweek0823;

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

class Solution {
	public Node moveSubTree(Node root, Node p, Node q) {
		// p is already a direct child of q
		if (q.children.contains(p)) {
			return root;
		}
		// find parents of p and q
		Node[] parents = new Node[2];
		findParents(root, p, q, parents);
		// node p is in the sub-tree of node q
		if (inSubtree(q, p)) {
			// move node p (with all of its sub-tree except for node q) and add
			// it as a child to node q
			parents[1].children.remove(q);
			q.children.add(p);
			// p is the root node, return q as the new root
			if (parents[0] == null) {
				return q;
			}
			// reconnect node q to replace node p
			int index = parents[0].children.indexOf(p);
			parents[0].children.set(index, q);
			return root;
		}
		// otherwise, simply move p as the last child to q
		q.children.add(p);
		parents[0].children.remove(p);
		return root;
	}

	private void findParents(Node root, Node p, Node q, Node[] parents) {
		if (root == null) {
			return;
		}
		for (Node next : root.children) {
			if (next.val == p.val) {
				parents[0] = root;
			}
			if (next.val == q.val) {
				parents[1] = root;
			}
			findParents(next, p, q, parents);
		}
	}

	private boolean inSubtree(Node node, Node root) {
		if (root == null) {
			return false;
		}
		if (root.val == node.val) {
			return true;
		}
		for (Node next : root.children) {
			if (inSubtree(node, next)) {
				return true;
			}
		}
		return false;
	}
}
