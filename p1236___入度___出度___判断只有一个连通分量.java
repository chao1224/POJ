import java.awt.Stroke;
import java.io.*;
import java.util.*;

public class p1236___入度___出度___判断只有一个连通分量 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StreamTokenizer in = new StreamTokenizer(br);
	static StringTokenizer stoke;
	static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));

	static int n, m;

	static edge[] edge = new edge[10000];
	static int head[] = new int[101], toll;
	static int dfn[] = new int[101], low[] = new int[101], tempdfn;
	static int stack[] = new int[101], top, inStack[] = new int[101];
	static int cnt, label[] = new int[101], component[] = new int[101];

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
		int u, v, a, b;
		while (in.nextToken() != in.TT_EOF) {
			n = (int) in.nval;
			init();

			for (int i = 1; i <= n; i++) {
				while (true) {
					u = nextInt();
					if (u == 0)
						break;
					else
						addEdge(i, u);
				}
			}

			for (int i = 1; i <= n; i++)
				if (dfn[i] == -1)
					dfsTarjan(i);

			//
			a = 0;
			b = 0;
			int indegree[] = new int[cnt + 1], outdegree[] = new int[cnt + 1];
			for (int i = 0; i < toll; i++) {
				u = label[edge[i].u];
				v = label[edge[i].v];
				if (u != v) {
					outdegree[u]++;
					indegree[v]++;
				}
			}

			for (int i = 1; i <= cnt; i++) {
				if (indegree[i] == 0)
					a++;
				if (outdegree[i] == 0)
					b++;
			}
			b = Math.max(a, b);
			out.println(a);
			if (cnt == 1)
				b = 0;
			out.println(b);

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