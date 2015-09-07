import java.io.*;
import java.util.*;

import javax.sound.sampled.AudioFormat.Encoding;

public class p1947___Ê÷ÉÏ·ºÐÍ±³°ü {
	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StreamTokenizer in = new StreamTokenizer(br);
	static PrintWriter out = new PrintWriter(System.out);

	static int N = 155;
	static int head[] = new int[N], tot;
	static edge[] edge = new edge[N * 2];
	static int inf = 1 << 29;

	static class edge {
		int v, next;

		edge(int v) {
			this.v = v;
		}
	}

	static void addEdge(int u, int v) {
		edge[tot] = new edge(v);
		edge[tot].next = head[u];
		head[u] = tot++;
	}

	static void init() {
		Arrays.fill(head, -1);
		tot = 0;
		for (int i = 0; i < N; i++)
			Arrays.fill(dp[i], inf);
	}

	static void dfs(int u, int f) {
		size[u] = 1;
		int v, s = 0;
		for (int e = head[u]; e != -1; e = edge[e].next) {
			v = edge[e].v;
			if (v == f)
				continue;
			dfs(v, u);
			s++;
			size[u] += size[v];
		}

		dp[u][1] = s;
		int t = size[u];

		for (int e = head[u]; e != -1; e = edge[e].next) {
			v = edge[e].v;
			if (v == f)
				continue;
			for (int i = t + 1; i >= 1; i--)
				for (int j = 1; j < i; j++) {
					if (dp[v][j] != inf && dp[u][i - j] != inf)
						dp[u][i] = Math.min(dp[u][i], dp[v][j] + dp[u][i - j]
								- 1);
				}
		}

	}

	static int n, p;
	static int dp[][] = new int[N][N];
	static int size[] = new int[N];

	public static void main(String[] args) throws IOException {
		int u, v;

		n = nextInt();
		p = nextInt();

		init();

		for (int i = 1; i < n; i++) {
			u = nextInt();
			v = nextInt();
			addEdge(u, v);
			addEdge(v, u);
		}

		dfs(1, -1);

		int ans = dp[1][p];
		for (int i = 2; i <= n; i++)
			ans = Math.min(ans, dp[i][p] + 1);
		out.println(ans);

		out.flush();
		out.close();
	}

	static int nextInt() throws IOException {
		in.nextToken();
		return (int) in.nval;
	}

	static long nextLong() throws IOException {
		in.nextToken();
		return (long) in.nval;
	}

}