import java.awt.Stroke;
import java.io.*;
import java.util.*;

public class p3160___连通分量___DFS求最大值 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StreamTokenizer in = new StreamTokenizer(br);
	static StringTokenizer stoke;
	static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));

	static int n, m;

	static edge[] edge = new edge[150005];
	static int head[] = new int[30010], toll;
	static int dfn[] = new int[30010], low[] = new int[30010], tempdfn;
	static int stack[] = new int[30010], top, inStack[] = new int[30010];
	static int cnt, label[] = new int[30010], component[] = new int[30010];
	static int max[] = new int[30010], cost[] = new int[30010];
	static int map[][];

	static void init() {
		Arrays.fill(head, (int) -1);
		toll = 0;

		Arrays.fill(dfn, (int) -1);
		tempdfn = 0;

		Arrays.fill(stack, (int) 0);
		top = 0;

		Arrays.fill(label, (int) 0);
		cnt = 0;

		Arrays.fill(max, (int) 0);

	}

	public static void main(String[] args) throws IOException {
		int u, v;
		while (in.nextToken() != in.TT_EOF) {
			n = (int) in.nval;
			m = nextint();
			init();
			for (int i = 1; i <= n; i++)
				cost[i] = nextint();
			for (int i = 1; i <= m; i++) {
				u = (int) (nextint() + 1);
				v = (int) (nextint() + 1);
				addEdge(u, v);
			}

			for (int i = 1; i <= n; i++)
				if (dfn[i] == -1)
					dfsTarjan(i);

			map = new int[cnt + 1][cnt + 1];
			Arrays.fill(head, (int) -1);
			toll = 0;
			for (int e = 0; e < m; e++) {
				u = label[edge[e].u];
				v = label[edge[e].v];
				if (u != v && map[u][v] == 0) {
					map[u][v] = 1;
					addEdge(u, v);
				}
			}
			int ans = 0;
			Arrays.fill(dfn, -1);
			for (int i = 1; i <= cnt; i++) {
				dfn[i] = dfs(i);
				ans = Math.max(ans, dfn[i]);
			}
			out.println(ans);
		}

		out.flush();
		out.close();
	}

	static int dfs(int u) {
		if (dfn[u] != -1)
			return dfn[u];
		int ret = 0;
		int v;
		for (int i = head[u]; i != -1; i = edge[i].next) {
			v = edge[i].v;
			if (dfn[v] == -1) {
				dfn[v] = dfs(v);
			}
			ret = Math.max(ret, dfs(v));
		}
		return ret + max[u];
	}

	static void dfsTarjan(int u) {
		dfn[u] = low[u] = ++tempdfn;
		stack[++top] = u;
		inStack[u] = 1;
		int v;
		for (int e = head[u]; e != -1; e = edge[e].next) {
			v = edge[e].v;
			if (dfn[v] == -1) {
				dfsTarjan(v);
				low[u] = (int) Math.min(low[u], low[v]);
			} else if (inStack[v] == 1) {
				low[u] = (int) Math.min(low[u], dfn[v]);
			}
		}
		if (dfn[u] == low[u]) {
			cnt++;
			do {
				v = stack[top--];
				inStack[v] = 0;
				label[v] = cnt;
				component[cnt]++;
				if (cost[v] > 0)
					max[cnt] += cost[v];
			} while (v != u);
		}
	}

	static void addEdge(int u, int v) {
		edge[toll] = new edge(u, v);
		edge[toll].next = head[u];
		head[u] = toll;
		toll++;
	}

	static class edge {
		int u, v;
		int next;

		edge(int u, int v) {
			this.u = u;
			this.v = v;
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

	static long nextLong() throws IOException {
		in.nextToken();
		return (long) in.nval;
	}

	static int nextint() throws IOException {
		in.nextToken();
		return (int) in.nval;
	}

}