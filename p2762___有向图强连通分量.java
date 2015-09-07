import java.awt.Stroke;
import java.io.*;
import java.util.*;

public class p2762___有向图强连通分量 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StreamTokenizer in = new StreamTokenizer(br);
	static StringTokenizer stoke;
	static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));

	static int n, m;

	static edge[] edge;
	static int head[], index;
	static int dfn[], low[], dfnNum;
	static int stack[], top, inStack[];
	static int component[], cnt;
	static int inDegree[];

	static void init() {
		head = new int[n + 1];
		Arrays.fill(head, -1);
		index = 0;
		edge = new edge[m + 1];

		dfn = new int[n + 1];
		Arrays.fill(dfn, -1);
		low = new int[n + 1];
		dfnNum = 0;

		stack = new int[n + 1];
		inStack = new int[n + 1];
		top = 0;

		component = new int[n + 1];
		cnt = 0;
	}

	public static void main(String[] args) throws IOException {
		int test = nextInt(), u, v;
		while (test-- > 0) {
			n = nextInt();
			m = nextInt();

			init();
			for (int i = 1; i <= m; i++) {
				u = nextInt();
				v = nextInt();
				addEdge(u, v);
			}

			for (int i = 1; i <= n; i++)
				if (dfn[i] == -1)
					dfsTarjan(i);

			index = 0;
			Arrays.fill(head, -1);
			inDegree = new int[cnt + 1];
			int mark[][] = new int[cnt + 1][cnt + 1];
			for (int i = 1; i <= m; i++) {
				u = component[edge[i - 1].u];
				v = component[edge[i - 1].v];
				if (u != v && mark[u][v] == 0) {
					mark[u][v] = 1;
					inDegree[v]++;
					addEdge(u, v);
				}
			}

			if (topo())
				out.println("Yes");
			else
				out.println("No");
		}

		out.flush();
		out.close();
	}

	static boolean topo() {
		int count = 0, cur = 0;
		for (int i = 1; i <= cnt; i++) {
			if (inDegree[i] == 0) {
				cur = i;
				count++;
			}
		}
		if (count > 1)
			return false;
		int v;
		while (cnt-- > 0) {
			count = 0;
			for (int e = head[cur]; e != -1; e = edge[e].next) {
				v = edge[e].v;
				inDegree[v]--;
				if (inDegree[v] == 0) {
					cur = v;
					count++;
				}
			}
			if (count > 1)
				return false;
		}

		return true;
	}

	static void dfsTarjan(int u) {
		dfn[u] = low[u] = ++dfnNum;
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
		if (low[u] == dfn[u]) {
			++cnt;
			do {
				v = stack[top--];
				inStack[v] = 0;
				component[v] = cnt;
			} while (u != v);
		}
	}

	static void addEdge(int u, int v) {
		edge[index] = new edge(u, v);
		edge[index].next = head[u];
		head[u] = index;
		index++;
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