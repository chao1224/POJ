import java.io.*;
import java.util.*;

public class p1088___º«“‰ªØdfs {
	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StreamTokenizer in = new StreamTokenizer(br);
	static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));

	static int n, m, arr[][], dp[][], longest;

	public static void main(String[] args) throws IOException {
		while (in.nextToken() != in.TT_EOF) {
			n = (int) in.nval;
			m = nextInt();
			arr = new int[n][m];
			for (int i = 0; i < n; i++)
				for (int j = 0; j < m; j++)
					arr[i][j] = nextInt();
			dp = new int[n][m];
			longest = 0;
			for (int i = 0; i < n; i++)
				for (int j = 0; j < m; j++)
					dfs(i, j);
			out.println(longest);
		}

		out.flush();
		out.close();
	}

	static int dir[][] = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

	static void dfs(int x, int y) {
		if (dp[x][y] > 0)
			return;
		for (int i = 0; i < 4; i++) {
			int tempx = x + dir[i][0];
			int tempy = y + dir[i][1];
			if (tempx >= 0 && tempx < n && tempy >= 0 && tempy < m)
				if (arr[x][y] > arr[tempx][tempy]) {
					dfs(tempx, tempy);
					dp[x][y] = Math.max(dp[x][y], dp[tempx][tempy]);
				}
		}
		dp[x][y]++;
		longest = Math.max(longest, dp[x][y]);
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