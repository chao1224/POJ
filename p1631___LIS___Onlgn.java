import java.io.*;
import java.util.*;

public class p1631___LIS___Onlgn {
	static StreamTokenizer in = new StreamTokenizer(new BufferedReader(
			new InputStreamReader(System.in)));
	static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));

	public static void main(String[] args) throws IOException {
		int test = nextInt();
		int arr[];
		while (test-- > 0) {
			int n = nextInt();
			arr = new int[n + 1];
			for (int i = 1; i <= n; i++)
				arr[i] = nextInt();

			int up[] = new int[n + 1], index = 0, l, r, mid;
			for (int i = 1; i <= n; i++) {
				if (up[index] < arr[i]) {
					up[++index] = arr[i];
				} else {
					l = 1;
					r = index;
					while (l <= r) {
						mid = (l + r) >> 1;
						if (up[mid] < arr[i]) {
							l = mid + 1;
						} else {
							r = mid - 1;
						}
					}
					up[l] = arr[i];
				}
			}

			out.println(index);

		}

		out.flush();
		out.close();
	}

	static String next() throws IOException {
		in.nextToken();
		return in.sval;
	}

	static char nextChar() throws IOException {
		in.nextToken();
		return in.sval.charAt(0);
	}

	static int nextInt() throws IOException {
		in.nextToken();
		return (int) in.nval;
	}

	static long nextLong() throws IOException {
		in.nextToken();
		return (long) in.nval;
	}

	static double nextDouble() throws IOException {
		in.nextToken();
		return in.nval;
	}
}