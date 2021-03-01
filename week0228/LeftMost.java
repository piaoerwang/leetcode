class Solution {
    public int leftMostColumnWithOne(BinaryMatrix binaryMatrix) {
        List<Integer> dimen = binaryMatrix.dimensions();
        int m = dimen.get(0), n = dimen.get(1);
        int left = 0, right = n - 1, ans = -1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (existOneInColumn(binaryMatrix, m, mid)) {
                ans = mid;          // record as current ans
                right = mid - 1;    // try to find in the left side
            } else {
                left = mid + 1;     // try to find in the right side
            }
        }
        return ans;
    }
    boolean existOneInColumn(BinaryMatrix binaryMatrix, int m, int c) {
        for (int r = 0; r < m; r++) if (binaryMatrix.get(r, c) == 1) return true;
        return false;
    }
}
