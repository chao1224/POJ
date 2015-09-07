import java.io.*;
import java.util.*;

public class p3257___二维背包___单机调度 {
	static StreamTokenizer in = new StreamTokenizer(new BufferedReader(
			new InputStreamReader(System.in)));
	static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));

	static int n, B, L;
	static int dp[][];
	static int inf = 1 << 29;

	static class node implements Comparable<node> {
		int c, x, w, f;

		node(int x, int w, int f, int c) {
			this.x = x;
			this.w = w;
			this.f = f;
			this.c = c;
		}

		public int compareTo(node o) {
			if (this.x < o.x)
				return -1;
			else if (this.x > o.x)
				return 1;
			else if (this.w < o.w)
				return -1;
			else if (this.w > o.w)
				return 1;
			else
				return 0;
		}
	}

	public static void main(String[] args) throws IOException {
		L = nextInt();
		n = nextInt();
		B = nextInt();

		node[] node = new node[n + 1];
		dp = new int[L + 1][B + 1];
		for (int i = 1; i <= L; i++)
			Arrays.fill(dp[i], -1);

		for (int i = 1; i <= n; i++)
			node[i] = new node(nextInt(), nextInt(), nextInt(), nextInt());
		Arrays.sort(node, 1, n + 1);

		for (int i = 1; i <= n; i++)
			for (int v = B; v >= node[i].c; v--)
				if (dp[node[i].x][v - node[i].c] >= 0)
					dp[node[i].x + node[i].w][v] = Math.max(dp[node[i].x
							+ node[i].w][v], dp[node[i].x][v - node[i].c]
							+ node[i].f);

		out.println(dp[L][B]);

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