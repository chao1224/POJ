import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class p2127___LCIS___利用性质循环输出路径 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StreamTokenizer in = new StreamTokenizer(br);
	static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));

	static int n, m, a[], b[];
	static int N = 510, M = 510;
	static int dp[][] = new int[N][M];

	// 仅仅只是求出任意两点F[i][j]的前ai，bj并且以bj为结尾的LCIS
	static void LCIS(int n, int[] a, int m, int[] b) {
		int max;
		for (int i = 1; i <= n; i++) {
			max = 0;
			for (int j = 1; j <= m; j++) {
				if (a[i] > b[j])
					max = Math.max(max, dp[i - 1][j]);
				if (a[i] == b[j])
					dp[i][j] = max + 1;
				else
					dp[i][j] = dp[i - 1][j];
			}
		}
	}

	static void getLCIS(int n, int[] a, int m, int[] b) {
		int max, I = 0, J = 0, preJ = 0;
		int path[][] = new int[n + 1][m + 1];
		for (int i = 1; i <= n; i++)
			Arrays.fill(path[i], 0);

		for (int i = 1; i <= n; i++) {
			max = 0;
			preJ = 0;
			for (int j = 1; j <= m; j++) {
				if (a[i] > b[j] && max < dp[i - 1][j]) {
					max = dp[i - 1][j];
					preJ = j;
				}
				if (a[i] == b[j]) {
					dp[i][j] = max + 1;
					path[i][j] = preJ;
				} else
					dp[i][j] = dp[i - 1][j];

				if (dp[i][j] > dp[I][J]) {
					I = i;
					J = j;
				}
			}
		}

		out.println(dp[I][J]);
		if (dp[I][J] == 0) {
			out.println();
			return;
		}

		int i = I, j = J;
		int print[] = new int[N], tot = 0;
		while (i != 0 && j != 0) {
			if (a[i] == b[j]) {
				print[++tot] = a[i];
				j = path[i][j];
			}
			i--;
		}

		for (i = tot; i > 1; i--)
			out.print(print[i] + " ");
		out.println(print[1]);

	}

	public static void main(String[] args) throws IOException {
		while (in.nextToken() != in.TT_EOF) {
			n = (int) in.nval;
			a = new int[n + 1];
			for (int i = 1; i <= n; i++)
				a[i] = nextInt();
			m = nextInt();
			b = new int[m + 1];
			for (int i = 1; i <= m; i++)
				b[i] = nextInt();
			getLCIS(n, a, m, b);
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

	static float nextFloat() throws IOException {
		in.nextToken();
		return (float) in.nval;
	}

	static double nextDouble() throws IOException {
		in.nextToken();
		return in.nval;
	}
}