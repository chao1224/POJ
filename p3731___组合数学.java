import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Stack;
import java.util.StringTokenizer;

public class p3731___组合数学 {
	static StreamTokenizer in = new StreamTokenizer(new BufferedReader(
			new InputStreamReader(System.in)));
	static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
	static int n, m, c[][], mod = 100000007;

	public static void main(String[] args) throws IOException {
		c = new int[2048][2048];
		c[0][0] = 1;
		for (int i = 1; i <= 2000; i++) {
			c[i][0] = 1;
			for (int j = 1; j <= i; j++)
				c[i][j] = ((c[i - 1][j - 1] + c[i - 1][j]) % mod + mod) % mod;
		}

		int test = nextInt(), n, m, x, y, na, nb, nc, nd;
		while (test-- > 0) {
			n = nextInt();
			m = nextInt();
			x = nextInt();
			y = nextInt();

			na = m - y;
			nb = n - x;
			nc = y;
			nd = x - 1;

			long r = 1;
			int len = Math.min(nb, nc);
			if (nd < len)
				len = nd;

			if (len < na)
				len++;
			else
				len = na;

			for (int i = 1; i <= len; i++) {
				r += (long) c[na][i] * c[nb][i - 1] % mod * c[nc][i - 1] % mod
						* c[nd][i - 1] % mod;
				r %= mod;
				r += (long) c[na][i] * c[nb][i] % mod * c[nc][i - 1] % mod
						* c[nd][i - 1] % mod;
				r %= mod;
				r += (long) c[na][i] * c[nb][i] % mod * c[nc][i] % mod
						* c[nd][i - 1] % mod;
				r %= mod;
				r += (long) c[na][i] * c[nb][i] % mod * c[nc][i] % mod
						* c[nd][i] % mod;
				r = (r % mod + mod) % mod;
			}
			out.println(r);
		}

		out.flush();
		out.close();
	}

	static String next() throws IOException {
		in.nextToken();
		return in.sval;
	}

	static double nextDouble() throws IOException {
		in.nextToken();
		return in.nval;
	}

	static int nextInt() throws IOException {
		in.nextToken();
		return (int) in.nval;
	}

	static long nextLong() throws IOException {
		in.nextToken();
		return (long) in.nval;
	}

}
