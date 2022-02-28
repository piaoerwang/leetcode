class SolutionMovingAverage {
	public int maxSize;
	public double prevSum = 0.0;
	Queue<Integer> queue;

	public SolutionMovingAverage(int size) {
		this.maxSize = size;
		queue = new LinkedList<>();
	}
	public double next(int val) {
		if (queue.size() == maxSize) {
			prevSum -= queue.remove();
		}
		prevSum += val;
		queue.add(val);
		return prevSum/queue.size();
	}

}
