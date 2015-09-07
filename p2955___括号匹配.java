import java.io.*;
import java.util.*;

public class p2955___¿®∫≈∆•≈‰ {
	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StreamTokenizer in = new StreamTokenizer(br);
	static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));

	static int n;

	public static void main(String[] args) throws IOException {
		String str;
		int i, j, k;
		while (true) {
			str = br.readLine();
			if (str.equals("end"))
				break;
			n = str.length();
			int arr[] = new int[n];
			for (i = 0; i < n; i++)
				if (str.charAt(i) == '(')
					arr[i] = 0;
				else if (str.charAt(i) == ')')
					arr[i] = 1;
				else if (str.charAt(i) == '[')
					arr[i] = 2;
				else
					arr[i] = 3;

			int dp[][] = new int[n][n];
			for (i = 0; i < n - 1; i++)
				if ((arr[i] == 0 && arr[i + 1] == 1)
						|| (arr[i] == 2 && arr[i + 1] == 3))
					dp[i][i + 1] = 2;

			for (int step = 2; step <= n; step++)
				for (i = 0; i + step < n; i++) {
					j = i + step;
					if (arr[i] == 0 && arr[j] == 1)
						dp[i][j] = Math.max(dp[i][j], 2 + dp[i + 1][j - 1]);
					if (arr[i] == 2 && arr[j] == 3)
						dp[i][j] = Math.max(dp[i][j], 2 + dp[i + 1][j - 1]);
					for (k = i; k < j; k++)
						dp[i][j] = Math.max(dp[i][j], dp[i][k] + dp[k + 1][j]);
				}

			out.println(dp[0][n - 1]);
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

	static double nextDouble() throws IOException {
		in.nextToken();
		return in.nval;
	}
}