import java.io.*;
import java.util.*;

public class p3186___Çø¼ädp___TLE {
	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StreamTokenizer in = new StreamTokenizer(br);
	static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));

	static int n;

	public static void main(String[] args) throws IOException {
		int i, j, k;

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
				for (k = i; k < j; k++) {
					dp[i][j] = Math.max(dp[i][j], dp[i][k] + dp[k + 1][j]
							+ Math.max(s[k] - s[i - 1], s[j] - s[k]));
				}
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