import java.awt.*;
import java.io.*;
import java.util.*;

public class p3356___±‡º≠æ‡¿Î {
	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StreamTokenizer in = new StreamTokenizer(br);
	static StringTokenizer stoke;
	static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
	static Scanner scan = new Scanner(System.in);

	public static void main(String[] args) throws IOException {

		int n, m, dp[][];
		String a, b;
		char cha, chb;

		while (in.nextToken() != in.TT_EOF) {
			n = (int) in.nval;
			a = next();
			m = nextInt();
			b = next();
			dp = new int[n + 1][m + 1];
			for (int i = 1; i <= n; i++)
				dp[i][0] = dp[i - 1][0] + 1;
			for (int j = 1; j <= m; j++)
				dp[0][j] = dp[0][j - 1] + 1;
			for (int i = 1; i <= n; i++) {
				cha = a.charAt(i - 1);
				for (int j = 1; j <= m; j++) {
					chb = b.charAt(j - 1);
					if (cha != chb)
						dp[i][j] = Math.min(dp[i - 1][j - 1] + 1, Math.min(
								dp[i - 1][j] + 1, dp[i][j - 1] + 1));
					else
						dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(
								dp[i - 1][j] + 1, dp[i][j - 1] + 1));
				}
			}
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

	static int nextInt() throws IOException {
		in.nextToken();
		return (int) in.nval;
	}

}