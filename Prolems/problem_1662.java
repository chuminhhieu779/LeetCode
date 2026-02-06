import java.io.*;
import java.util.*;

public class problem_1662 {

	public boolean arrayStringsAreEqual(String[] word1, String[] word2) {
		String tmp1 = "";
		String tmp2  = "";
		for (String s : word1) {
			tmp1 += s ;
		}
		for (String s : word2) {
			tmp2 += s ;
		}
		return tmp1.equals(tmp2);
	}

	public static void main(String[] args) {
		String[] a = {
			"ab",
			"c"
		};
		String[] b = {
			"a",
			"bc"
		};
		if (arrayStringsAreEqual(a, b)) {
			System.out.print("true");
		} else {
			System.out.print("false");
		}
	}
}