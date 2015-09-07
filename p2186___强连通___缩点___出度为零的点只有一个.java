import java.awt.Stroke;
import java.io.*;
import java.util.*;

public class p2186___强连通___缩点___出度为零的点只有一个 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StreamTokenizer in = new StreamTokenizer(br);
	static StringTokenizer stoke;
	static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));

	static int n, m;

	static edge[] edge = new edge[50001];
	static int head[] = new int[10001], toll;
	static int dfn[] = new int[10001], low[] = new int[10001], tempdfn;
	static int stack[] = new int[10001], top, inStack[] = new int[10001];
	static int cnt, label[] = new int[10001], component[] = new int[10001];

	static void init() {
		Arrays.fill(head, -1);
		toll = 0;

		Arrays.fill(dfn, -1);
		tempdfn = 0;

		Arrays.fill(stack, 0);
		top = 0;

		Arrays.fill(label, 0);
		cnt = 0;
	}

	public static void main(String[] args) throws IOException {
		int u, v;
		while (in.nextToken() != in.TT_EOF) {
			n = (int) in.nval;
			m = nextInt();

			init();

			for (int i = 0; i < m; i++) {
				u = nextInt();
				v = nextInt();
				addEdge(u, v);
			}

			for (int i = 1; i <= n; i++)
				if (dfn[i] == -1)
					dfsTarjan(i);

			Arrays.fill(low, 0);
			for (int i = 0; i < m; i++) {
				u = label[edge[i].u];
				v = label[edge[i].v];
				if (u != v) {
					low[u]++;
				}
			}

			int ans = 0, num = 0;
			for (int i = 1; i <= cnt; i++)
				if (low[i] == 0) {
					ans = component[i];
					num++;
				}
			if (num > 1)
				ans = 0;

			out.println(ans);
		}
		out.flush();
		out.close();
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
				low[u] = Math.min(low[u], low[v]);
			} else if (inStack[v] == 1) {
				low[u] = Math.min(low[u], dfn[v]);
			}
		}
		if (dfn[u] == low[u]) {
			cnt++;
			do {
				v = stack[top--];
				inStack[v] = 0;
				label[v] = cnt;
				component[cnt]++;
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
		int u, v, next;

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

	static int nextInt() throws IOException {
		in.nextToken();
		return (int) in.nval;
	}

}