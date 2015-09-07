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

public class p1015___dp {
	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StreamTokenizer in = new StreamTokenizer(br);
	static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));

	static short dp[][][], d[], p[], add, inf;
	static byte use[][][];

	public static void main(String[] args) throws IOException {
		short n, m, x, y;
		int jury = 0;
		while (true) {
			n = nextShort();
			m = nextShort();
			if (n == 0 && m == 0)
				break;
			jury++;
			d = new short[n + 1];
			p = new short[n + 1];
			add = (short) (20 * m);
			inf = (short) (40 * m + 30);
			dp = new short[n + 1][m + 1][inf];
			// trace = new short[n + 1][m + 1][inf];
			use = new byte[n + 1][m + 1][inf];
			for (int i = 1; i <= n; i++) {
				d[i] = nextShort();
				p[i] = nextShort();
			}

			for (short i = 0; i <= n; i++)
				for (short j = 0; j <= m; j++)
					for (short k = 0; k < inf; k++)
						dp[i][j][k] = -1;

			int charge;
			dp[0][0][add] = 0;
			for (short i = 1; i <= n; i++) {
				charge = d[i] - p[i];
				for (short j = 0; j <= m && j <= i; j++) {
					for (short k = 0; k < inf; k++) {
						dp[i][j][k] = dp[i - 1][j][k];
						// 注意这一步，是进行从当前行的倒推，倒推出前一行代码的是否符合
						if (j > 0
								&& k - charge < inf
								&& k - charge >= 0
								&& dp[i - 1][j - 1][k - charge] != -1
								&& dp[i - 1][j - 1][k - charge] + d[i] + p[i] > dp[i][j][k]) {
							dp[i][j][k] = (short) (dp[i - 1][j - 1][k - charge]
									+ d[i] + p[i]);
							use[i][j][k] = 2;
						}
					}
				}
			}

			int ans = 0, min = inf;
			for (int i = 0; i < inf; i++) {
				if (dp[n][m][i] >= 0)
					if (Math.abs(i - add) < min) {
						ans = i;
						min = Math.abs(i - add);
					} else if (Math.abs(i - add) == min) {
						if (dp[n][m][ans] < dp[n][m][i])
							ans = i;
					}
			}

			x = (short) ((dp[n][m][ans] + (ans - add)) / 2);
			y = (short) (dp[n][m][ans] - x);
			if (jury > 1)
				out.println();
			out.println("Jury #" + jury);
			out.println("Best jury has value " + x
					+ " for prosecution and value " + y + " for defence:");
			print(n, m, ans);
		}

		out.flush();
		out.close();
	}

	static void print(int i, int j, int k) {
		if (i == 0)
			return;
		if (use[i][j][k] == 2) {
			print(i - 1, j - 1, k - d[i] + p[i]);
			out.print(" " + i);
		} else {
			print(i - 1, j, k);
		}
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

	static short nextShort() throws IOException {
		in.nextToken();
		return (short) in.nval;
	}
}
