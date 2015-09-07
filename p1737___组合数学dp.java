import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class p1737___组合数学dp {
	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StreamTokenizer in = new StreamTokenizer(br);
	static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));

	static int N = 51;
	static BigInteger c[][] = new BigInteger[N][N];
	static BigInteger f[] = new BigInteger[N];
	static BigInteger g[] = new BigInteger[N];

	static void init() {
		for (int i = 1; i < N; i++) {
			c[i][i] = c[i][0] = BigInteger.ONE;
			for (int j = 1; j < i; j++)
				c[i][j] = c[i - 1][j - 1].add(c[i - 1][j]);
		}
		f[1] = BigInteger.ONE;
		BigInteger two = BigInteger.ONE.add(BigInteger.ONE);
		for (int i = 1; i < N; i++)
			g[i] = two.pow(i * (i - 1) / 2);
	}

	public static void main(String[] args) throws IOException {
		int n;
		init();

		BigInteger temp;
		for (int i = 2; i < N; i++) {
			temp = BigInteger.ZERO;
			for (int j = 1; j < i; j++) {
				temp = temp.add(c[i - 1][j - 1].multiply(f[j]).multiply(
						g[i - j]));
			}
			f[i] = g[i].subtract(temp);
		}

		while (true) {
			n = nextInt();
			if (n == 0)
				break;
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