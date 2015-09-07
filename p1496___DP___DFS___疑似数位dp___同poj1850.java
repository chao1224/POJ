import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class p1496___DP___DFS___ÒÉËÆÊýÎ»dp___Í¬poj1850 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StreamTokenizer in = new StreamTokenizer(br);
	static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));

	static int n;
	static int index;
	static int dp[][] = new int[11][27];

	static void dfs(int a, int b) {
		if (a == 0 || b == 0 || dp[a][b] >= 0)
			return;
		dp[a][b] = 0;
		for (int i = b + 1; i <= 26; i++) {
			dfs(a - 1, i);
			dp[a][b] += dp[a - 1][i];
		}
	}

	public static void main(String[] args) throws IOException {

		Arrays.fill(dp[1], 1);
		for (int i = 2; i <= 10; i++)
			Arrays.fill(dp[i], -1);

		String str;
		while ((str = br.readLine()) != null) {
			n = str.length();
			int ch[] = new int[n + 2];
			int t;
			for (int i = 1; i <= n; i++) {
				t = str.charAt(i - 1) - 'a' + 1;
				ch[n - i + 1] = t;
			}

			boolean flag = true;
			for (int i = 2; i <= n; i++)
				if (ch[i] >= ch[i - 1])
					flag = false;

			if (flag) {
				int ans = 0;
				for (int i = 1; i <= n; i++) {
					int sum = 0;
					for (int j = 1; j <= 26; j++) {
						dfs(i - 1, j);
						sum += dp[i - 1][j];
					}
					ans += sum;
					for (int j = ch[i + 1] + 1; j < ch[i]; j++) {
						dfs(i, j);
						ans += dp[i][j];
					}
				}
				out.println(ans + 1);
			} else
				out.println(0);
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