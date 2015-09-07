import java.io.*;
import java.util.*;

public class p1155___Ê÷ÉÏ±³°ü {
	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StreamTokenizer in = new StreamTokenizer(br);
	static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));

	static int N = 3500;
	static int head[] = new int[N], tot;
	static edge edge[] = new edge[N * 2];
	static int size[] = new int[N];
	static int dp[][] = new int[N][N];
	static int inf = 1 << 29;

	static class edge {
		int v, val, next;

		edge(int v) {
			this.v = v;
		}

		edge(int v, int val) {
			this.v = v;
			this.val = val;
		}
	}

	static void addEdge(int u, int v) {
		edge[tot] = new edge(v);
		edge[tot].next = head[u];
		head[u] = tot++;
	}

	static void addEdge(int u, int v, int val) {
		edge[tot] = new edge(v, val);
		edge[tot].next = head[u];
		head[u] = tot++;
	}

	static void init() {
		Arrays.fill(head, -1);
		tot = 0;
		for (int i = 0; i < N; i++)
			Arrays.fill(dp[i], -inf);
	}

	static void dfsGet(int u, int f) {
		int v;
		if (u > n - m)
			size[u] = 1;
		else
			size[u] = 0;

		for (int e = head[u]; e != -1; e = edge[e].next) {
			v = edge[e].v;
			if (v == f)
				continue;
			dfsGet(v, u);
			size[u] += size[v];
		}
	}

	static void dfs(int u, int f) {
		int v, val;
		if (u > n - m)
			dp[u][1] = cost[u];
		dp[u][0] = 0;

		for (int e = head[u]; e != -1; e = edge[e].next) {
			v = edge[e].v;
			val = edge[e].val;
			if (v == f)
				continue;
			dfs(v, u);
			for (int i = size[u]; i >= 0; i--)
				for (int j = 0; j <= size[v] && j <= i; j++)
					if (dp[u][i - j] != -inf && dp[v][j] != -inf)
						dp[u][i] = Math.max(dp[u][i], dp[v][j] + dp[u][i - j]
								- val);
		}

	}

	static int n, m;
	static int cost[] = new int[N];

	public static void main(String[] args) throws IOException {
		int k, a, c;

		n = nextInt();
		m = nextInt();
		init();
		for (int i = 1; i <= n - m; i++) {
			k = nextInt();
			while (k-- > 0) {
				a = nextInt();
				c = nextInt();
				addEdge(i, a, c);
				addEdge(a, i, c);
			}
		}
		for (int i = 1; i <= m; i++)
			cost[n - m + i] = nextInt();

		dfsGet(1, -1);
		dfs(1, -1);

		int ans = 0;
		for (int i = m; i >= 0; i--)
			if (dp[1][i] >= 0) {
				ans = i;
				break;
			}
		out.println(ans);

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

	static float nextFloat() throws IOException {
		in.nextToken();
		return (float) in.nval;
	}

	static double nextDouble() throws IOException {
		in.nextToken();
		return in.nval;
	}
}