
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.Vector;

public class p2138___LIS___LCS___Ãÿ≈–dp {
	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StreamTokenizer in = new StreamTokenizer(br);
	static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));

	public static void main(String[] args) throws IOException {
		int n = nextInt();
		String dic = next();
		p2138___newString[] arr = new p2138___newString[n + 1];
		arr[0] = new p2138___newString(dic, dic.length());
		String t;
		for (int i = 1; i <= n; i++) {
			t = next();
			arr[i] = new p2138___newString(t, t.length());
		}

		Arrays.sort(arr, 1, n + 1);

		int[] depth = new int[n + 1];
		int temp = 0, longest = 0;
		String ans = dic;
		int begin = 0;
		for (; begin <= n; begin++) {
			if (arr[begin].l > dic.length() + 1) {
				break;
			}
			if (contain(dic, arr[begin].str)) {
				arr[begin].root = dic;
				depth[begin] = 1;
				ans = arr[begin].str;
			} else
				arr[begin].root = null;
		}
		// for (int i = 1; i <= n; i++)
		// System.out.print(depth[i] + "  ");
		// System.out.println();

		p2138___newString check;
		for (int i = begin; i <= n; i++) {
			temp = 0;
			check = arr[0];
			for (int j = 1; j < i; j++) {
				if (contain(arr[j].str, arr[i].str) && temp < depth[j]) {
					temp = depth[j];
					check = arr[j];
				}
			}
			if (check.root != null) {
				depth[i] = temp + 1;
				if (depth[i] > longest) {
					longest = depth[i];
					ans = arr[i].str;
				} else if (depth[i] == longest) {
					if (ans.length() < arr[i].l)
						ans = arr[i].str;
				}
				arr[i].root = dic;
			} else {
				depth[i] = 0;
				arr[i].root = null;
			}
		}

		// for (int i = 1; i <= n; i++)
		// System.out.print(depth[i] + "  ");
		// System.out.println();

		out.println(ans);
		out.flush();
		out.close();
	}

	static boolean contain(String a, String b) {
		if (a.length() + 1 != b.length())
			return false;
		char chx, chy;
		int n = a.length();
		int m = b.length();
		int[][] c = new int[n + 1][m + 1];
		for (int i = 1; i <= n; i++) {
			chx = a.charAt(i - 1);
			for (int j = 1; j <= m; j++) {
				chy = b.charAt(j - 1);
				if (chx == chy) {
					c[i][j] = c[i - 1][j - 1] + 1;
				} else {
					c[i][j] = Math.max(c[i - 1][j], c[i][j - 1]);
				}
			}
		}
		if (c[n][m] != n)
			return false;
		else
			return true;
	}

	static int nextInt() throws IOException {
		in.nextToken();
		return (int) in.nval;
	}

	static String next() throws IOException {
		in.nextToken();
		return in.sval;
	}

	static double nextDouble() throws IOException {
		in.nextToken();
		return in.nval;
	}

}

class p2138___newString implements Comparable<p2138___newString> {
	String str, root;
	int l;

	p2138___newString(String s, int l) {
		this.str = s;
		this.l = l;
		this.root = null;
	}

	public int compareTo(p2138___newString o) {
		if (this.l < o.l)
			return -1;
		else if (this.l > o.l)
			return 1;
		else
			return this.str.compareTo(o.str);
	}

}
