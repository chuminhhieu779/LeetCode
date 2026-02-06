import java.io.*;
import java.util.*;

public class problem_Q1_Contest480 {

	public static int absDifference(int[] nums, int k) {
		Arrays.sort(nums);
		int sumMin = 0 , sumMax = 0 ;
		for (int i = 0 ; i < k ; i++) {
			sumMin += nums[i];
		}
		for (int i = nums.length - 1 ; i >= n - k ; i-- ) {
			sumMax += nums[i];
		}
		return Math.abs(sumMax - sumMin);
	}
	public static void main(String[] args) {
		int[] a = {2, 2, 4};
		int k = 2 ;
		System.out.print(absDifference(a, k));
	}
}