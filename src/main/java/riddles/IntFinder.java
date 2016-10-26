package riddles;

public class IntFinder {

    public int getIndex(int[] sortedArray, int number) {
        if (sortedArray == null || sortedArray.length == 0) {
            return -1;
        }

        return doBinarySearch(sortedArray, number, 0, sortedArray.length - 1);
    }

    private int doBinarySearch(int[] sortedArray, int number, int start, int end) {
        int midPoint = (end - start) / 2 + start;

        if (end < 0 || start >= sortedArray.length) {
            return -1;
        }

        if (sortedArray[midPoint] == number) {
            return midPoint;
        } else if (sortedArray[midPoint] < number) {
            return doBinarySearch(sortedArray, number, midPoint + 1, end);
        } else {
            return doBinarySearch(sortedArray, number, start, midPoint - 1);
        }
    }
}
