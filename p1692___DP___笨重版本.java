import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class p1692___DP___±¿ÖØ°æ±¾ {
	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StreamTokenizer in = new StreamTokenizer(br);
	static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));

	static int n, m, arr[], brr[];
	static int mark[][][];
	static int dp[][];

	public static void main(String[] args) throws IOException {
		int test = nextInt();
		while (test-- > 0) {
			n = nextInt();
			arr = new int[n + 1];
			m = nextInt();
			brr = new int[m + 1];
			for (int i = 1; i <= n; i++)
				arr[i] = nextInt();
			for (int j = 1; j <= m; j++)
				brr[j] = nextInt();

			mark = new int[n + 1][m + 1][2];
			for (int i = 0; i <= n; i++)
				for (int j = 0; j <= m; j++)
					Arrays.fill(mark[i][j], n + m);
			dp = new int[n + 1][m + 1];

			for (int i = 1; i <= n; i++)
				loop: for (int j = 1; j <= m; j++)
					if (arr[i] != brr[j])
						for (int a = i + 1; a <= n; a++)
							if (arr[a] == brr[j])
								for (int b = j + 1; b <= m; b++)
									if (arr[i] == brr[b]) {
										mark[i][j][0] = a;
										mark[i][j][1] = b;
										continue loop;
									}

			// for (int i = 1; i <= n; i++)
			// for (int j = 1; j <= m; j++)
			// if (mark[i][j][0] < m + n)
			// out.println(i + " " + j + "  is ok  when "
			// + mark[i][j][0] + " " + mark[i][j][1]);

			for (int i = 1; i <= n; i++)
				for (int j = 1; j <= m; j++)
					for (int a = 1; a < i; a++)
						for (int b = 1; b < j; b++)
							if (mark[a][b][0] <= i && mark[a][b][1] <= j)
								dp[i][j] = Math.max(dp[i][j],
										dp[a - 1][b - 1] + 2);
							else
								dp[i][j] = Math.max(dp[i][j], dp[a - 1][b - 1]);

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