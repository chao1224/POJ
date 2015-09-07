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

public class p3735___¾ØÕó¿ìËÙÃÝ {
	static StreamTokenizer in = new StreamTokenizer(new BufferedReader(
			new InputStreamReader(System.in)));
	static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
	static int n, m, k;

	public static void main(String[] args) throws IOException {
		char com;
		int a, b;
		while (true) {
			n = nextInt();
			m = nextInt();
			k = nextInt();
			if (n == 0 && m == 0 && k == 0)
				break;

			long e[][] = new long[n + 2][n + 2], adj[][] = new long[n + 2][n + 2];
			for (int i = 1; i <= n + 1; i++)
				e[i][i] = adj[i][i] = 1;

			long temp;
			for (int ii = 1; ii <= k; ii++) {
				com = nextChar();
				if (com == 'g') {
					a = nextInt();
					adj[n + 1][a]++;
				} else if (com == 'e') {
					a = nextInt();
					for (int i = 1; i <= n + 1; i++)
						adj[i][a] = 0;
				} else {
					a = nextInt();
					b = nextInt();
					for (int i = 1; i <= n + 1; i++) {
						temp = adj[i][a];
						adj[i][a] = adj[i][b];
						adj[i][b] = temp;
					}
				}
			}

			while (m > 0) {
				if ((m & 1) == 1) {
					e = multiply(e, adj);
				}
				adj = multiply(adj, adj);
				m >>= 1;
			}

			for (int i = 1; i < n; i++)
				out.print(e[n + 1][i] + " ");
			out.println(e[n + 1][n]);
		}

		out.flush();
		out.close();
	}

	static int[][] multiply(int[][] a, int b[][]) {
		int c[][] = new int[n + 2][n + 2];
		for (int i = 1; i <= n + 1; i++) {
			for (int k = 1; k <= n + 1; k++) {
				if (a[i][k] > 0)
					for (int j = 1; j <= n + 1; j++) {
						c[i][j] += a[i][k] * b[k][j];
					}
			}
		}
		return c;
	}

	static long[][] multiply(long[][] a, long b[][]) {
		long c[][] = new long[n + 2][n + 2];
		for (int i = 1; i <= n + 1; i++) {
			for (int k = 1; k <= n + 1; k++) {
				if (a[i][k] > 0)
					for (int j = 1; j <= n + 1; j++) {
						c[i][j] += a[i][k] * b[k][j];
					}
			}
		}
		return c;
	}

	static String next() throws IOException {
		in.nextToken();
		return in.sval;
	}

	static char nextChar() throws IOException {
		in.nextToken();
		return in.sval.charAt(0);
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
