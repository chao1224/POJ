import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class p1548___Dilworth {
	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StreamTokenizer in = new StreamTokenizer(br);
	static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));

	static int n;
	static node node[] = new node[1000];

	static class node implements Comparable<node> {
		int x, y;

		node(int a, int b) {
			x = a;
			y = b;
		}

		public int compareTo(node o) {
			if (this.x < o.x)
				return -1;
			else if (this.x > o.x)
				return 1;
			else
				return this.y - o.y;
		}

	}

	public static void main(String[] args) throws IOException {

		int x, y;
		while (true) {
			x = nextInt();
			y = nextInt();
			if (x == -1 && y == -1)
				break;
			int index = 1;
			node[index] = new node(x, y);
			while (true) {
				x = nextInt();
				y = nextInt();
				if (x == 0 && y == 0)
					break;
				node[++index] = new node(x, y);
			}
			Arrays.sort(node, 1, index + 1);
			int arr[] = new int[index + 1];
			for (int i = 1; i <= index; i++)
				arr[i] = node[i].y;

			int dp[] = new int[index + 1];
			int t = 0, l, r, mid;
			for (int i = index; i >= 1; i--) {
				if (arr[i] > dp[t])
					dp[++t] = arr[i];
				else {
					l = 1;
					r = t;
					while (l <= r) {
						mid = (l + r) >> 1;
						if (arr[i] > dp[mid])
							l = mid + 1;
						else
							r = mid - 1;
					}
					dp[l] = arr[i];
				}
			}

			out.println(t);
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

	static short nextShort() throws IOException {
		in.nextToken();
		return (short) in.nval;
	}

	static int nextInt() throws IOException {
		in.nextToken();
		return (int) in.nval;
	}

	static long nextLong() throws IOException {
		in.nextToken();
		return (long) in.nval;
	}

	static float nextFloat() throws IOException {
		in.nextToken();
		return (float) in.nval;
	}

	static double nextDouble() throws IOException {
		in.nextToken();
		return in.nval;
	}
}