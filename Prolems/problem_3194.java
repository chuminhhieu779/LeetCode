import java.io.*;
import java.util.*;

public class problem_3194 {
	//  NlogN
	// public static double findMin(List<Double> list) {
	// 	double min = 100000 ;
	// 	for (Double x : list) {
	// 		if (x < min ) {
	// 			min = x ;
	// 		}
	// 	}
	// 	return min ;
	// }

	// public static void minimumAverage(int[] nums) {
	// 	List<Integer> list = new ArrayList();
	// 	for (int i = 0 ; i < nums.length ; i++) {
	// 		list.add(nums[i]);
	// 	}
	// 	Collections.sort(list);



	// 	int t = nums.length / 2 ;
	// 	int max = 0 , min = 800;
	// 	double avg = 0 ;
	// 	List<Double> list2 = new ArrayList<>();
	// 	while (t >= 1) {
	// 		avg = ((double)list.get(0) + list.get(list.size() - 1) ) / (double)2;
	// 		System.out.print(avg + " ");
	// 		list2.add(avg);
	// 		list.remove(0);
	// 		list.remove(list.size() - 1);
	// 		t--;
	// 	}
	// 	return findMin(list2);
	// }
	public static void solve(int[] nums) {
		List<Integer> list = new ArrayList<>();

		double avg = 0 ;
		double result = 100000 ;
		int t = nums.length / 2 ;
		for (int i = 0 ; i < nums.	length ; i++) {
			list.add(nums[i]);
		}
		int min1 = 0 , max = 0 ;
		while (t > 0 ) {
			min1 = list.get(0) ;
			max = list.get(0);
			for (int i = 1 ; i < list.size() ; i++) {
				if (list.get(i) < min1 ) {
					min1 = list.get(i);
				}
				if (list.get(i) > max ) {
					max = list.get(i);
				}
			}
			list.remove(Integer.valueOf(min1));
			list.remove(Integer.valueOf(max));
			System.out.println("min1 :" + min1);
			System.out.println("max : " + max);
			avg = ((double)min1 + max) / (double) 2;
			if (avg < result ) {
				result = avg  ;
			}
			t-- ;
		}

		// return min ;
		System.out.print(result);
	}

	public static void hint(int[] nums) {
		int[] counts = new int[51];

		for (int num : nums) {
			counts[num]++;
		}

		int left = 0;
		int right = counts.length - 1;
		int min = Integer.MAX_VALUE;

		while (left < right) {
			while (left < right && counts[left] == 0) {
				left++;
			}
			counts[left]--;

			while (right > left && counts[right] == 0) {
				right--;
			}
			counts[right]--;

			if (left <= right && left + right < min) {
				min = left + right;
			}
		}

		return min / 2.0;
	}


	public static void main(String[] args) {
		int[] a = {7, 8, 3, 4, 15, 13, 4, 1};
		// minimumAverage(a);
		solve(a);
		// System.out.print(solve(a));
		// System.out.print(minimumAverage(a));
	}
}
