import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class p2127___LCIS___递归输出路径 {
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
				dp[i][j] = dp[i - 1][j];
				if (a[i] > b[j])
					max = Math.max(max, dp[i - 1][j]);
				if (a[i] == b[j])
					dp[i][j] = max + 1;
			}
		}
	}

	static void getLCIS(int n, int[] a, int m, int[] b) {
		int max, I = 0, J = 0, preI = 0, preJ = 0;
		int path[][][] = new int[2][n + 1][m + 1];
		for (int i = 0; i < 2; i++)
			for (int j = 1; j <= n; j++)
				Arrays.fill(path[i][j], 0);

		for (int i = 1; i <= n; i++) {
			max = 0;
			preI = i - 1;
			preJ = 0;

			for (int j = 1; j <= m; j++) {
				if (a[i] > b[j] && max < dp[i - 1][j]) {
					max = dp[i - 1][j];
					preI = i - 1;
					preJ = j;
				}

				if (a[i] == b[j]) {
					dp[i][j] = max + 1;
					path[0][i][j] = preI;
					path[1][i][j] = preJ;
				} else {
					dp[i][j] = dp[i - 1][j];
					path[0][i][j] = i - 1;
					path[1][i][j] = j;
				}

				if (dp[i][j] > dp[I][J]) {
					I = i;
					J = j;
				}
			}
		}

		// for (int i = 1; i <= n; i++) {
		// for (int j = 1; j <= m; j++)
		// out.printf("   <%2d,%2d>    ", path[0][i][j], path[1][i][j]);
		// out.println();
		// }
		// out.println();

		out.println(dp[I][J]);
		if (dp[I][J] == 0) {
			out.println();
			return;
		}

		int i = I, j = J, t1, t2, tot = 0;
		int print[] = new int[n + 1];
		while ((i != 0 && j != 0)) {
			if (a[i] == b[j])
				print[++tot] = a[i];
			t1 = path[0][i][j];
			t2 = path[1][i][j];
			i = t1;
			j = t2;
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