import java.io.*;
import java.util.*;

public class p1180___1D_1D___n2___TLE {
	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StreamTokenizer in = new StreamTokenizer(br);
	static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));

	static int n, s, t[], f[];
	static long dp[], sumT[], sumF[];
	static long inf = 1000000000000000000l;

	public static void main(String[] args) throws IOException {
		while (in.nextToken() != in.TT_EOF) {
			n = (int) in.nval;
			s = nextInt();
			t = new int[n + 2];
			f = new int[n + 2];
			sumT = new long[n + 2];
			sumF = new long[n + 2];
			for (int i = 1; i <= n; i++) {
				t[i] = nextInt();
				f[i] = nextInt();
			}
			for (int i = n; i >= 1; i--) {
				sumT[i] = sumT[i + 1] + t[i];
				sumF[i] = sumF[i + 1] + f[i];
			}

			dp = new long[n + 2];
			Arrays.fill(dp, inf);
			dp[n + 1] = 0;

			for (int i = n; i >= 1; i--) {
				// 两个都可以
				// for (int j = i + 1; j <= n + 1; j++)
				for (int j = n + 1; j > i; j--)
					dp[i] = Math.min(dp[i], dp[j] + (s + sumT[i] - sumT[j])
							* sumF[i]);
			}

			out.println(dp[1]);

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