class SolutionMaxStack {
	Node head;
	Node tail;
	TreeMap<Integer, List<Node>> map;

	public SolutionMaxStack() {
		head = new Node(0);
		tail = new Node(0);
		head.next = tail;
		tail.prev = head;
		map = new TreeMap<>();
	}

	public void push(int x) {
		Node newNode = new Node(x);
		newNode.next = tail;
		newNode.prev = tail.prev;
		tail.prev.next = newNode;
		tail.prev = newNode;
		if (!map.containsKey(x)) {
			map.put(x, new ArrayList<Node>());
		}
		map.get(x).add(newNode);
	}
	public int pop() {
		int val = tail.prev.val;
		removeNode(tail.prev);
		int listSize = map.get(val).size();
		map.get(val).remove(listSize - 1);
		if (listSize == 1) {
			map.remove(val);
		}
		return val;
	}
	public int top() {
		return map.lastKey();
	}

	public int popMax() {
		int maxVal = map.lastKey();
		int listSize = map.get(maxVal).size();
		Node node = map.get(maxVal).remove(listSize - 1);
		removeNode(node);
		if (listSize == 1) {
			map.remove(maxVal);
		}
		return maxVal;
	}
	public void removeNode(Node n) {
		Node prevNode = n.prev;
		Node nextNode = n.next;
		prevNode.next = nextNode;
		nextNode.prev = prevNode;
	}
	class Node {
		Node prev;
		Node next;
		int val;
		public Node(int val) {
			this.val = val;
			this.prev = null;
			this.next = null;
		}
	}

}
