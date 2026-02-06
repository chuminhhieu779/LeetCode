import java.io.*;
import java.util.*;

public class problem_1313 {

	public static int[] decompressRLElist(int[] nums) {

		List<Integer> list = new ArrayList<>();
		for (int i = 0 ; i <= nums.length - 1 ; i += 2) {
			int fre = nums[i];
			int val = nums[i + 1];
			int[] pair = new int[fre];
			int j = 0 ;
			while ( j < pair.length ) {
				list.add(val); 
				++j ;
			}
		}
		int[] tmp = list.stream().mapToInt(Integer::intValue).toArray();
		return tmp  ;
	}

	public static void main(String[] args) {
		int[] a = {1, 2, 3, 4};
		// decompressRLElist(a);
		for (int x : decompressRLElist(a)) {
			System.out.print(x);
		}
	}
}

