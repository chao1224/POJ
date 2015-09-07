import java.io.*;
import java.util.*;

public class p3017___ËõÐ¡Çø¼ädp {
	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StreamTokenizer in = new StreamTokenizer(br);
	static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));

	static int maxl = 1000000000;
	static int n, g[], p;
	static long m, sum, a[], f[], mx;

	public static void main(String[] args) throws IOException {
		int i, j;
		long temp;

		loop: while (in.nextToken() != in.TT_EOF) {
			n = (int) in.nval;
			m = nextLong();
			f = new long[n + 1];
			a = new long[n + 1];
			g = new int[n + 1];
			for (i = 1; i <= n; i++)
				a[i] = nextInt();

			for (i = 1; i <= n; i++)
				if (a[i] > m) {
					out.println(-1);
					continue loop;
				}

			f[1] = a[1];
			g[1] = 1;
			mx = a[1];
			sum = a[1];
			p = 1;

			for (i = 2; i <= n; i++) {
				f[i] = maxl;
				sum += a[i];
				while (sum > m)
					sum -= a[p++];
				if (mx >= a[i] && g[i - 1] >= p) {
					f[i] = f[i - 1];
					g[i] = g[i - 1];
				} else {
					mx = a[i];
					if (g[i - 1] >= p)
						j = g[i - 1];
					else
						j = i;
					for (; j >= p; j--) {
						mx = Math.max(mx, a[j]);
						if (f[i] > mx + f[j - 1]) {
							g[i] = j;
							f[i] = f[j - 1] + mx;
						}
					}
					mx = f[i] - f[g[i] - 1];
				}
			}
			// for (i = 1; i <= n; i++)
			// out.printf("%4d", i);
			// out.println();
			//
			// for (i = 1; i <= n; i++)
			// out.printf("%4d", a[i]);
			// out.println();
			//
			// for (i = 1; i <= n; i++)
			// out.printf("%4d", g[i]);
			// out.println();
			//
			// for (i = 1; i <= n; i++)
			// out.printf("%4d", f[i]);
			// out.println();

			out.println(f[n]);
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