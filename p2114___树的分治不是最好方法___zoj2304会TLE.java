import java.io.*;
import java.util.*;

public class p2114___树的分治不是最好方法___zoj2304会TLE {
	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StreamTokenizer in = new StreamTokenizer(br);
	static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));

	static int N = 11000, M = 21000;
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
		edge[tot].v = v;
		edge[tot].val = val;
		edge[tot].next = head[u];
		head[u] = tot++;
	}

	static void prework() {
		for (int i = 0; i < M; i++)
			edge[i] = new edge(i, 0);
	}

	static void init() {
		Arrays.fill(head, -1);
		tot = 0;
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
		int h = 1, t = dfn - 1, temp;

		int ret = 0;
		Arrays.sort(stamp, h, t + 1);
		while (h < t) {
			while (h < t && stamp[h] + stamp[t] > k)
				t--;
			temp = t;
			while (temp >= h && stamp[temp] + stamp[h] == k)
				temp--;
			ret += t - temp;
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
		int d, c;
		prework();

		while (true) {
			n = nextInt();
			if (n == 0)
				break;
			init();
			for (int i = 1; i <= n; i++) {
				while (true) {
					d = nextInt();
					if (d == 0)
						break;
					c = nextInt();
					addEdge(i, d, c);
					addEdge(d, i, c);
				}
			}

			while (true) {
				k = nextInt();
				if (k == 0) {
					out.println(".");
					break;
				}
				ans = 0;
				Arrays.fill(vis, false);
				balance[0] = N;
				Arrays.fill(mark, false);
				dfs(1, -1);
				if (ans > 0)
					out.println("AYE");
				else
					out.println("NAY");
			}
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

	static float nextFloat() throws IOException {
		in.nextToken();
		return (float) in.nval;
	}

	static double nextDouble() throws IOException {
		in.nextToken();
		return in.nval;
	}
}