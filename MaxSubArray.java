import java.util.ArrayList;
import java.util.List;

public class MaxSubArray {
    int sum;
    int left;
    int right;

    MaxSubArray(int sum, int left, int right) {
        this.sum = sum;
        this.left = left;
        this.right = right;
    }

    static MaxSubArray maxSubArray(List<Integer> arr) {
        if (arr.size() == 1) {
            return new MaxSubArray(arr.get(0), 0, 0);
        }

        int mid = arr.size() / 2;
        List<Integer> leftHalf = arr.subList(0, mid);
        List<Integer> rightHalf = arr.subList(mid, arr.size());
        MaxSubArray leftResult = maxSubArray(leftHalf);
        MaxSubArray rightResult = maxSubArray(rightHalf);
        MaxSubArray crossResult = maxCrossingSubArray(arr, mid);
        if (leftResult.sum >= rightResult.sum && leftResult.sum >= crossResult.sum) {
            return new MaxSubArray(leftResult.sum, leftResult.left, leftResult.right);
        } else if (rightResult.sum >= leftResult.sum && rightResult.sum >= crossResult.sum) {
            return new MaxSubArray(rightResult.sum, rightResult.left, rightResult.right);
        } else {
            return crossResult;
        }

    }

    // Helper function to find the max subarray to cross the middle
    static MaxSubArray maxCrossingSubArray(List<Integer> arr, int mid) {

        // Find the max sum on left side of the mid
        int leftSum = Integer.MIN_VALUE;
        int currSum = 0;
        int leftIdx = mid;
        for (int i = mid - 1; i >= 0; i--) {
            currSum += arr.get(i);
            if (currSum > leftSum) {
                leftSum = currSum;
                leftIdx = i;
            }
        }
        // Find the max sum on right side of the mid

        int rightSum = Integer.MIN_VALUE;
        currSum = 0;
        int rightIdx = mid;
        for (int i = mid; i < arr.size(); i++) {
            currSum += arr.get(i);
            if (currSum > rightSum) {
                rightSum = currSum;
                rightIdx = i;
            }
        }
        return new MaxSubArray(rightSum + rightSum, leftIdx, rightIdx);

    }

    public static void main(String[] args) {
        List<Integer> arr = List.of(-2, 1, 3, -9, 7, 9, 4);
        MaxSubArray result = maxSubArray(arr);
        System.out.println("The array is " + arr);
        System.out.println("The max subarray is: " + arr.subList(result.left, result.right) + " " + result.sum);

    }

}
