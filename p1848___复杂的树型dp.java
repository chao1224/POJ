import java.io.*;
import java.util.*;

public class p1848___¸´ÔÓµÄÊ÷ÐÍdp {
	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StreamTokenizer in = new StreamTokenizer(br);
	static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));

	static int head[], toll;
	static edge edge[];

	static class edge {
		int v, next;

		edge(int v) {
			this.v = v;
		}
	}

	static void addEdge(int u, int v) {
		edge[toll] = new edge(v);
		edge[toll].next = head[u];
		head[u] = toll++;
	}

	static int n, arr[], dp[][], inf = 10000;
	static boolean vis[];

	public static void main(String[] args) throws IOException {
		int u, v;
		while (in.nextToken() != in.TT_EOF) {
			n = (int) in.nval;
			head = new int[n + 1];
			Arrays.fill(head, -1);
			toll = 0;
			edge = new edge[100 + 2 * n];
			for (int i = 1; i < n; i++) {
				u = nextInt();
				v = nextInt();
				addEdge(u, v);
				addEdge(v, u);
			}
			dp = new int[n + 1][3];
			vis = new boolean[n + 1];
			Arrays.fill(vis, false);
			vis[1] = true;
			dfs(1);
			if (dp[1][0] >= inf)
				out.println(-1);
			else
				out.println(dp[1][0]);
		}

		out.flush();
		out.close();
	}

	static void dfs(int u) {
		int v, sum = 0, index = 0, arr[] = new int[n + 1];
		for (int e = head[u]; e != -1; e = edge[e].next) {
			v = edge[e].v;
			if (!vis[v]) {
				vis[v] = true;
				dfs(v);
				sum += dp[v][0];
				arr[++index] = v;
			}
		}
		if (index == 0) {
			dp[u][0] = inf;
			dp[u][1] = 0;
			dp[u][2] = inf;
			return;
		}
		dp[u][1] = Math.min(sum, inf);
		dp[u][0] = inf;
		dp[u][2] = inf;
		for (int i = 1; i <= index; i++) {
			v = arr[i];
			dp[u][2] = Math.min(dp[u][2], sum - dp[v][0]
					+ Math.min(dp[v][1], dp[v][2]));
			dp[u][0] = Math.min(dp[u][0], sum - dp[v][0] + dp[v][2] + 1);
		}
		int k;
		for (int i = 1; i <= index; i++) {
			v = arr[i];
			for (int j = 1; j <= index; j++) {
				if (i == j)
					continue;
				k = arr[j];
				dp[u][0] = Math.min(dp[u][0], sum - dp[k][0] - dp[v][0]
						+ Math.min(dp[k][1], dp[k][2])
						+ Math.min(dp[v][1], dp[v][2]) + 1);
			}
		}
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