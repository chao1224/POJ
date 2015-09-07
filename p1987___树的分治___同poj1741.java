import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class p1987___树的分治___同poj1741 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StreamTokenizer in = new StreamTokenizer(br);
	static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));

	static int N = 41000, M = 81000;
	static int head[] = new int[N], tot;
	static edge[] edge = new edge[M];
	static int stamp[] = new int[N];
	static boolean vis[] = new boolean[N];
	static int Root, countRoot, size[] = new int[N], balance[] = new int[N];
	static boolean mark[] = new boolean[N];

	static class edge {
		int v, val, next;

		edge(int v, int val) {
			this.v = v;
			this.val = val;
		}
	}

	static void addEdge(int u, int v, int val) {
		edge[tot] = new edge(v, val);
		edge[tot].next = head[u];
		head[u] = tot++;
	}

	static void init() {
		Arrays.fill(head, -1);
		tot = 0;
		Arrays.fill(vis, false);
		ans = 0;
		balance[0] = N;
		Arrays.fill(mark, false);
	}

	static void dfsSize(int u, int f) {
		size[u] = 1;
		balance[u] = 0;
		countRoot++;
		int v;
		for (int e = head[u]; e != -1; e = edge[e].next) {
			v = edge[e].v;
			if (v == f || vis[v])
				continue;
			dfsSize(v, u);
			size[u] += size[v];
			balance[u] = Math.max(balance[u], size[v]);
		}
	}

	static void dfsRoot(int u, int f) {
		int v;
		balance[u] = Math.max(balance[u], countRoot - size[u]);
		if (balance[u] < balance[Root])
			Root = u;
		for (int e = head[u]; e != -1; e = edge[e].next) {
			v = edge[e].v;
			if (v == f || vis[v])
				continue;
			dfsRoot(v, u);
		}
	}

	static int findRoot(int u, int f) {
		countRoot = 0;
		Root = 0;
		dfsSize(u, f);
		dfsRoot(u, f);
		vis[Root] = true;
		return Root;
	}

	static void dfsDist(int u, int f, int w) {
		int v, val;
		stamp[dfn++] = w;
		for (int e = head[u]; e != -1; e = edge[e].next) {
			v = edge[e].v;
			val = edge[e].val;
			if (v == f || vis[v])
				continue;
			dfsDist(v, u, w + val);
		}
	}

	static int counter(int u, int weight) {
		dfn = 1;
		dfsDist(u, -1, weight);
		int h = 1, t = dfn - 1;

		int ret = 0;
		Arrays.sort(stamp, h, t + 1);
		while (h < t) {
			while (h < t && stamp[h] + stamp[t] > k)
				t--;
			ret += t - h;
			h++;
		}
		return ret;
	}

	static void dfs(int u, int f) {
		mark[u] = false;
		int v, root = findRoot(u, f);
		ans += counter(root, 0);
		for (int e = head[root]; e != -1; e = edge[e].next) {
			v = edge[e].v;
			if (v == f || vis[v])
				continue;
			ans -= counter(v, edge[e].val);
			dfs(v, root);
		}
	}

	static int n, m, k, dfn, ans;

	public static void main(String[] args) throws IOException {
		int u, v, val;

		n = nextInt();
		m = nextInt();
		init();
		for (int i = 1; i <= m; i++) {
			u = nextInt();
			v = nextInt();
			val = nextInt();
			next();
			addEdge(u, v, val);
			addEdge(v, u, val);
		}
		k = nextInt();
		for (int i = 1; i <= n; i++)
			if (!mark[i])
				dfs(i, -1);
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