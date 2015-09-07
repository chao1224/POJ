import java.awt.*;
import java.io.*;
import java.util.*;

public class p1692___类似编辑距离DP {
	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StreamTokenizer in = new StreamTokenizer(br);
	static StringTokenizer stoke;
	static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
	static Scanner scan = new Scanner(System.in);

	static int n;

	public static void main(String[] args) throws IOException {
		int test = nextInt();
		int n, m, arr[], brr[], dp[][], down[][], up[][];
		while (test-- > 0) {
			n = nextInt();
			m = nextInt();
			arr = new int[n + 1];
			brr = new int[m + 1];
			for (int i = 1; i <= n; i++)
				arr[i] = nextInt();
			for (int i = 1; i <= m; i++)
				brr[i] = nextInt();
			dp = new int[n + 1][m + 1];

			for (int i = 1; i <= n; i++)
				if (arr[i] == brr[1])
					dp[i][1] = 1;
				else
					dp[i][1] = 0;

			for (int i = 2; i <= m; i++)
				if (brr[i] == arr[1])
					dp[1][i] = 1;
				else
					dp[1][i] = 0;

			up = new int[n + 1][m + 1];
			down = new int[n + 1][m + 1];
			for (int i = 0; i <= n; i++) {
				Arrays.fill(up[i], -1);
				Arrays.fill(down[i], -1);
			}

			// up[i][j]表示第一行前i-1个和第二行第j个相等的最后编号
			// down[i][j]表示第二行前j-1个数字和第一行第1个相等 的最后编号
			for (int i = 2; i <= n; i++) {
				for (int j = 2; j <= m; j++) {
					if (arr[i - 1] == brr[j])
						up[i][j] = i - 1;
					else
						up[i][j] = up[i - 1][j];
					if (arr[i] == brr[j - 1])
						down[i][j] = j - 1;
					else
						down[i][j] = down[i][j - 1];
				}
			}

			for (int i = 1; i <= n; i++) {
				for (int j = 1; j <= m; j++) {
					dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
					if (arr[i] != brr[j] && up[i][j] != -1 && down[i][j] != -1)
						dp[i][j] = Math.max(dp[i][j],
								dp[up[i][j] - 1][down[i][j] - 1] + 2);
				}
			}

			// System.out.println("UP:");
			// for (int i = 1; i <= n; i++) {
			// for (int j = 1; j <= m; j++)
			// System.out.print(up[i][j] + " ");
			// System.out.println();
			// }
			// System.out.println();
			//
			// System.out.println("Down:");
			// for (int i = 1; i <= n; i++) {
			// for (int j = 1; j <= m; j++)
			// System.out.print(down[i][j] + " ");
			// System.out.println();
			// }
			// System.out.println();
			// System.out.println();

			out.println(dp[n][m]);
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

	static long nextLong() throws IOException {
		in.nextToken();
		return (long) in.nval;
	}

	static double nextDouble() throws IOException {
		in.nextToken();
		return in.nval;
	}

	static int nextInt() throws IOException {
		in.nextToken();
		return (int) in.nval;
	}

}