import java.io.*;
import java.util.*;

public class p1180___2D_1D___n3___TLE {
	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StreamTokenizer in = new StreamTokenizer(br);
	static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));

	static int n, s, t[], f[];
	static long dp[][], sumT[], sumF[];
	static long inf = 2100000000;

	public static void main(String[] args) throws IOException {
		while (in.nextToken() != in.TT_EOF) {
			n = (int) in.nval;
			s = nextInt();
			t = new int[n + 1];
			f = new int[n + 1];
			sumT = new long[n + 1];
			sumF = new long[n + 1];
			for (int i = 1; i <= n; i++) {
				t[i] = nextInt();
				f[i] = nextInt();
				sumT[i] = sumT[i - 1] + t[i];
				sumF[i] = sumF[i - 1] + f[i];
			}

			dp = new long[n + 1][n + 1];

			for (int i = 1; i <= n; i++)
				Arrays.fill(dp[i], inf);

			for (int i = 1; i <= n; i++) {
				dp[i][1] = (s + sumT[i]) * (sumF[i]);
				for (int j = 1; j <= i; j++)
					for (int k = 1; k < i; k++) {
						dp[i][j] = Math.min(dp[i][j], dp[k][j - 1]
								+ (s * j + sumT[i]) * (sumF[i] - sumF[k]));
					}
			}

			long max = inf;
			for (int j = 1; j <= n; j++)
				max = Math.min(max, dp[n][j]);

			out.println(max);

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