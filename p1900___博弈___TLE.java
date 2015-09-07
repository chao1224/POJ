import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.Vector;

public class p1900___²©ÞÄ___TLE {
	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StreamTokenizer in = new StreamTokenizer(br);
	static StringTokenizer stoke;
	static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));

	public static void main(String[] args) throws IOException {
		int n = nextInt();
		int m = nextInt();
		m++;
		int[][] F = new int[n + 1][n + 1];

		int deep = 0, cnt, tmp = 0, a, b, x, y, s, p;
		while (true) {
			deep++;
			if (deep == m)
				tmp = 0;
			if ((deep & 1) == 1) {
				for (b = 1; b <= n; b++) {
					for (a = 1; a < b; a++) {
						s = x = a + b;
						y = a * b;
						if (F[a][b] != 0)
							continue;
						cnt = 0;
						int i = 0;
						int j = s;
						while (true) {
							i++;
							j--;
							if (i >= j)
								break;
							if (j <= n && F[i][j] == 0)
								cnt++;
						}
						if (cnt == 1) {
							F[a][b] = deep;
							tmp++;
						}
					}
				}
			} else {
				for (b = 1; b <= n; b++) {
					for (a = 1; a < b; a++) {
						x = a + b;
						y = a * b;
						if (F[a][b] != 0)
							continue;
						cnt = 0;
						int i = 0;
						int j = y;
						while (true) {
							if (i >= j)
								break;
							if (i * j == y && j <= n && F[i][j] == 0)
								cnt++;
							i++;
							while (y % i != 0) {
								i++;
							}
							j = y / i;
						}
						if (cnt == 1) {
							F[a][b] = deep;
							tmp++;
						}
					}
				}
			}
			if (deep >= m)
				break;
		}

		out.println(tmp);
		for (int i = 1; i <= n; i++)
			for (int j = 1; j <= n; j++)
				if (F[i][j] == m)
					out.println(i + " " + j);
		out.flush();
		out.close();
	}

	static String next() throws IOException {
		in.nextToken();
		return in.sval;
	}

	static int nextInt() throws IOException {
		in.nextToken();
		return (int) in.nval;
	}
}
