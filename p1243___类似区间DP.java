import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class p1243___ÀàËÆÇø¼äDP {
	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StreamTokenizer in = new StreamTokenizer(br);
	static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));

	static int g, l;
	static int dp[][];

	static void dfs(int i, int j) {
		if (dp[i][j] > 0)
			return;
		dfs(i - 1, j);
		dfs(i - 1, j - 1);
		dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j] + 1;
	}

	public static void main(String[] args) throws IOException {
		g = 30;
		l = 30;
		dp = new int[g + 1][l + 1];
		for (int i = 0; i <= g; i++) {
			dp[i][0] = i;
			dp[i][i] = (1 << i) - 1;
		}
		// for (int i = 1; i <= g; i++)
		// for (int j = 1; j < i; j++)
		// dp[i][j] = dp[i - 1][j - 1] + 1 + dp[i - 1][j];
		int test = 1;

		while (true) {
			g = nextInt();
			l = nextInt();
			if (g == 0 && l == 0)
				break;
			out.print("Case " + test++ + ": ");
			if (l > g) {
				dfs(g, g);
				out.println(dp[g][g]);
			} else {
				dfs(g, l);
				out.println(dp[g][l]);
			}
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