package biaobiaoqi.algorithm.string;

/**
 * Some test cases:
 * 
 * To test next method: dest:abcabaefabcabc
 * 
 * To test kmp. dest:abcaba str:abcabcaba
 * 
 * @author biaobiaoqi
 * 
 */
public class StringMatching {
	public static int[] next;

	public static void main(String[] args) {
		String str = "BBC ABCDAB ABCDABCDABDE";
		String destStr = "ABCDABD";

		System.out.println("bruteforce: "
				+ (bruteforce(str, destStr) ? "YES" : "NO"));

		next = kmpNext(destStr);
		System.out.println("kmp: " + (kmp(str, destStr) ? "YES" : "NO"));
	}

	public static boolean kmp(String str, String dest) {
		// i stands for index of str string, j stands for index in dest string.
		// At the beginning of each loop process, j is the new position of dest
		// taht should be compared.
		for (int i = 0, j = 0; i < str.length(); i++) {
			while (j > 0 && str.charAt(i) != dest.charAt(j))
				// This loop is to get a matching character recursively. Another
				// stop condition is when particial match value meets end.
				j = next[j - 1];// As i in str and j in dest is comparing,
								// recomputing of j should be in the former
								// character substring, which is next[j-1]

			if (str.charAt(i) == dest.charAt(j))
				j++;

			if (j == dest.length())
				return true;
		}

		return false;
	}

	public static int[] kmpNext(String str) {
		int[] next = new int[str.length()];
		next[0] = 0;
		for (int i = 1, j = 0; i < str.length(); i++) {// j == 0 means the
														// cursor points to
														// nothing.
			while (j > 0 && str.charAt(j) != str.charAt(i))
				// the j here stands for the number of same characters for
				// postfix and prefix, instead of
				// the index of the end of prefix.
				j = next[j - 1]; // watch out here! it's j - 1 here, instead of
									// j
			if (str.charAt(i) == str.charAt(j))
				j++;
			next[i] = j;
		}
		return next;
	}

	public static boolean bruteforce(String str1, String str2) {
		for (int i = 0, j = 0; i != str1.length();) {
			if (str1.charAt(i) == str2.charAt(j)) {
				j++;
				i++;
				if (j == str2.length())
					return true;
			} else {
				i = i - j + 1;
				j = 0;
			}
		}
		return false;
	}
}