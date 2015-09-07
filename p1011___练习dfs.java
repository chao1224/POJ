import java.io.*;
import java.util.*;

public class p1011___Á·Ï°dfs {
	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StreamTokenizer in = new StreamTokenizer(br);
	static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));

	static int n, stick[], sum;

	public static void main(String[] args) throws IOException {
		while (in.nextToken() != in.TT_EOF) {
			n = (int) in.nval;
			if (n == 0)
				break;
			stick = new int[n];
			sum = 0;
			for (int i = 0; i < n; i++) {
				stick[i] = nextInt();
				sum += stick[i];
			}
			out.println(solve());
		}
		out.flush();
		out.close();
	}

	static int limit;
	static boolean vis[];

	static int solve() {
		Arrays.sort(stick);
		vis = new boolean[n];
		for (limit = stick[n - 1]; limit < sum; limit++) {
			if (sum % limit != 0)
				continue;
			Arrays.fill(vis, false);
			if (dfs(0, n - 1, 0))
				return limit;
		}
		return sum;
	}

	static boolean dfs(int now, int index, int cnt) {
		if (cnt * limit == sum)
			return true;
		for (int i = index; i >= 0; i--) {
			if (!vis[i]) {
				if (now + stick[i] == limit) {
					vis[i] = true;
					if (dfs(0, n - 1, cnt + 1))
						return true;
					vis[i] = false;
					return false;
				} else if (now + stick[i] < limit) {
					vis[i] = true;
					if (dfs(now + stick[i], i - 1, cnt))
						return true;
					vis[i] = false;
					if (now == 0)
						return false;
				}
			}
		}
		return false;
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