import java.io.*;
import java.util.*;

public class p3186___方法二___记忆化dp {
	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StreamTokenizer in = new StreamTokenizer(br);
	static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));

	static int n;
	static long arr[], s[];
	static long dp[][];

	public static void main(String[] args) throws IOException {
		int i, j;

		n = nextInt();
		arr = new long[n + 1];
		for (i = 1; i <= n; i++)
			arr[i] = nextInt();
		s = new long[n + 1];
		for (i = 1; i <= n; i++)
			s[i] = s[i - 1] + arr[i];

		dp = new long[n + 1][n + 1];
		for (i = 1; i <= n; i++)
			dp[i][i] = arr[i];

		out.println(dfs(1, n));

		out.flush();
		out.close();
	}

	static long dfs(int a, int b) {
		if (dp[a][b] > 0)
			return dp[a][b];
		dp[a][b] = Math.max(dfs(a + 1, b), dfs(a, b - 1)) + s[b] - s[a - 1];
		return dp[a][b];
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