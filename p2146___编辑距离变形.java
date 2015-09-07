import java.awt.*;
import java.io.*;
import java.util.*;

public class p2146___±‡º≠æ‡¿Î±‰–Œ {
	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StreamTokenizer in = new StreamTokenizer(br);
	static StringTokenizer stoke;
	static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
	static Scanner scan = new Scanner(System.in);

	static String name[];

	public static void main(String[] args) throws IOException {
		int n, d;
		while (true) {
			n = nextInt();
			if (n == 0)
				break;
			d = nextInt();
			name = new String[n + 1];
			for (int i = 1; i <= n; i++)
				name[i] = next();
			Arrays.sort(name, 1, n + 1);
			int num = 0;
			for (int i = 1; i <= n; i++) {
				for (int j = i + 1; j <= n; j++) {
					if (distance(i, j) <= d) {
						out.println(name[i] + "," + name[j]);
						num++;
					}
				}
			}
			out.println(num);

		}

		out.flush();
		out.close();
	}

	static int distance(int a, int b) {
		int n = name[a].length(), m = name[b].length();
		int dp[][] = new int[n + 1][m + 1];
		for (int i = 1; i <= n; i++)
			dp[i][0] = i;
		for (int j = 1; j <= m; j++)
			dp[0][j] = j;

		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= m; j++) {
				if (name[a].charAt(i - 1) == name[b].charAt(j - 1))
					dp[i][j] = dp[i - 1][j - 1];
				else
					dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(
							dp[i - 1][j], dp[i][j - 1])) + 1;

				if (i >= 2 && j >= 2
						&& name[a].charAt(i - 2) == name[b].charAt(j - 1)
						&& name[a].charAt(i - 1) == name[b].charAt(j - 2)) {
					dp[i][j] = Math.min(dp[i][j], dp[i - 2][j - 2] + 1);
				}
				if (i >= 2 && j >= 3
						&& name[a].charAt(i - 1) == name[b].charAt(j - 3)
						&& name[a].charAt(i - 2) == name[b].charAt(j - 1)) {
					dp[i][j] = Math.min(dp[i][j], dp[i - 2][j - 3] + 2);
				}
				if (i >= 3 && j >= 2
						&& name[a].charAt(i - 1) == name[b].charAt(j - 2)
						&& name[a].charAt(i - 3) == name[b].charAt(j - 1)) {
					dp[i][j] = Math.min(dp[i][j], dp[i - 3][j - 2] + 2);
				}
			}
		}

		return dp[n][m];
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

	static int nextInt() throws IOException {
		in.nextToken();
		return (int) in.nval;
	}

}