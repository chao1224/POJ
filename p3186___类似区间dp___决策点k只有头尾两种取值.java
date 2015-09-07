import java.io.*;
import java.util.*;

public class p3186___类似区间dp___决策点k只有头尾两种取值 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StreamTokenizer in = new StreamTokenizer(br);
	static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));

	static int n, arr[], s[];
	static long dp[][];

	public static void main(String[] args) throws IOException {
		int i, j;

		n = nextInt();
		int arr[] = new int[n + 1];
		for (i = 1; i <= n; i++)
			arr[i] = nextInt();
		int s[] = new int[n + 1];
		for (i = 1; i <= n; i++)
			s[i] = s[i - 1] + arr[i];

		int dp[][] = new int[n + 1][n + 1];
		for (i = 1; i <= n; i++)
			dp[i][i] = arr[i];
		for (int len = 1; len <= n; len++) {
			for (i = 1; i + len <= n; i++) {
				j = i + len;
				dp[i][j] = s[j] - s[i - 1]
						+ Math.max(dp[i + 1][j], dp[i][j - 1]);
			}
		}

		out.println(dp[1][n]);

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