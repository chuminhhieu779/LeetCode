import java.io.*;
import java.util.*;

public class problem_2506 {
	public static  int similarPairs(String[] words) {
		int ans = 0;
		HashMap<Integer, Integer> freq = new HashMap();
		for (var word : words) {
			int mask = 0;
			for (var ch : word.toCharArray())
				mask |= 1 << ch - 'a';
			ans += freq.getOrDefault(mask, 0);
			freq.merge(mask, 1, Integer::sum);
		}
		return ans;
	}
	public static void main(String[] args) {
		String[] words = {"aba", "aabb", "abcd", "bac", "aabc"};
		System.out.print(similarPairs(words));
		// similarPairs(words);
	}
}