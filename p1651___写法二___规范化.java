import java.io.*;
import java.util.*;

public class p1651___写法二___规范化 {
	static StreamTokenizer in = new StreamTokenizer(new BufferedReader(
			new InputStreamReader(System.in)));
	static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
	static Scanner scan;

	static int n;
	static int dp[][];

	public static void main(String[] args) throws IOException {
		n = nextInt();
		int arr[] = new int[n];
		for (int i = 0; i < n; i++)
			arr[i] = nextInt();
		dp = new int[n][n];
		for (int i = n - 1; i >= 0; i--) {
			for (int j = i + 2; j < n; j++) {
				int temp = 1 << 30;
				for (int k = i + 1; k <= j - 1; k++) {
					temp = Math.min(temp, dp[i][k] + dp[k][j] + arr[i] * arr[k]
							* arr[j]);
				}
				dp[i][j] = temp;
			}
		}

		out.println(dp[0][n - 1]);

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