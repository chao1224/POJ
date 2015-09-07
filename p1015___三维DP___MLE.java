import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.Vector;

public class p1015___ÈýÎ¬DP___MLE {
	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StreamTokenizer in = new StreamTokenizer(br);
	static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));

	static int trace[][][], trace2[][][], dp[][][];
	static boolean use[][][];

	public static void main(String[] args) throws IOException {
		int n, m, d[], p[], inf = 500 * 2 + 1, x, y, way[], number, val;
		int jury = 0;
		while (true) {
			n = nextInt();
			m = nextInt();
			if (n == 0 && m == 0)
				break;
			jury++;
			d = new int[n + 1];
			p = new int[n + 1];
			dp = new int[n + 1][m + 1][inf];
			trace = new int[n + 1][m + 1][inf];
			trace2 = new int[n + 1][m + 1][inf];
			use = new boolean[n + 1][m + 1][inf];
			for (int i = 1; i <= n; i++) {
				d[i] = nextInt();
				p[i] = nextInt();
			}

			for (int i = 0; i <= n; i++)
				for (int j = 0; j <= m; j++)
					Arrays.fill(dp[i][j], -1);

			int charge;
			dp[1][0][500] = 0;
			dp[1][1][d[1] - p[1] + 500] = d[1] + p[1];
			trace[1][1][d[1] - p[1] + 500] = 1;
			trace2[1][1][d[1] - p[1] + 500] = 0;
			for (int i = 1; i < n; i++) {
				charge = d[i + 1] - p[i + 1];
				for (int j = 0; j <= m && j <= i; j++) {
					for (int k = 0; k < inf; k++) {
						if (dp[i][j][k] >= 0) {
							//
							if (dp[i][j][k] > dp[i + 1][j][k]) {
								dp[i + 1][j][k] = dp[i][j][k];
								trace[i + 1][j][k] = j;
								trace2[i + 1][j][k] = k;
							}
							if (j < m
									&& dp[i][j][k] + d[i + 1] + p[i + 1] > dp[i + 1][j + 1][k
											+ charge]) {
								dp[i + 1][j + 1][k + charge] = dp[i][j][k]
										+ d[i + 1] + p[i + 1];
								trace[i + 1][j + 1][k + charge] = j;
								trace2[i + 1][j + 1][k + charge] = k;
								use[i + 1][j + 1][k + charge] = true;
							}
						}
					}
				}
			}

			int ans = 0, min = inf;
			for (int i = 0; i < inf; i++) {
				if (dp[n][m][i] >= 0)
					if (Math.abs(i - 500) < min) {
						ans = i;
						min = Math.abs(i - 500);
					} else if (Math.abs(i - 500) == min) {
						if (dp[n][m][ans] < dp[n][m][i])
							ans = i;
					}
			}
			// System.out.println(dp[4][2][ans] + "  " + trace[4][2][ans] + "  "
			// + trace2[4][2][ans]);
			// System.out.println(dp[3][2][502] + "  " + trace[3][2][502] + "  "
			// + trace2[3][2][502]);
			// System.out.println(dp[2][1][499] + "  " + trace[2][1][499] + "  "
			// + trace2[2][1][499]);
			// System.out.println(dp[1][0][500] + "  " + trace[1][0][500] + "  "
			//		+ trace2[1][0][500]);
			// System.out.println(dp[3][1][499] + "  " + trace[3][1][499] + "  "
			// + trace2[3][1][499]);
			// System.out.println(dp[2][1][499] + "  " + trace[2][1][499] + "  "
			// + trace2[2][1][499]);
			// System.out.println(dp[1][0][500] + "  " + trace[1][0][500] + "  "
			// + trace2[1][0][500]);

			// way = new int[m + 1];
			// number = trace[n][m][ans];
			// val = trace2[n][m][ans];
			// int t1, t2;
			// way[number] = n;
			// for (int i = n; i >= 1; i--) {
			// if (dp[i][number][val] >= 0) {
			// way[number] = i;
			// out.println(i + " :  " + number + " " + val);
			// t1 = trace[i][number][val];
			// t2 = trace2[i][number][val];
			// number = t1;
			// val = t2;
			// out.println(number + " " + val + " -> " + i);
			// }
			// }

			x = (dp[n][m][ans] + (ans - 500)) / 2;
			y = dp[n][m][ans] - x;
			if (jury > 1)
				out.println();
			out.println("Jury #" + jury);
			out.println("Best jury has value " + x
					+ " for prosecution and value " + y + " for defence:");
			// for (int i = 1; i < m; i++)
			// out.print(way[i] + " ");
			// out.println(way[m]);
			print(n, m, ans);
		}

		out.flush();
		out.close();
	}

	static void print(int i, int j, int k) {
		if (i == 0)
			return;
		print(i - 1, trace[i][j][k], trace2[i][j][k]);
		if (use[i][j][k])
			out.print(" " + i);
	}

	static String next() throws IOException {
		in.nextToken();
		return in.sval;
	}

	static long nextLong() throws IOException {
		in.nextToken();
		return (long) in.nval;
	}

	static int nextInt() throws IOException {
		in.nextToken();
		return (int) in.nval;
	}
}
