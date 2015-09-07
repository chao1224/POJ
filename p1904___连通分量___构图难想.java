import java.awt.Stroke;
import java.io.*;
import java.util.*;

public class p1904___连通分量___构图难想 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StreamTokenizer in = new StreamTokenizer(br);
	static StringTokenizer stoke;
	static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));

	static int n, m;

	static edge[] edge = new edge[300100];
	static int head[] = new int[4010], toll;
	static int dfn[] = new int[4010], low[] = new int[4010], tempdfn;
	static int stack[] = new int[4010], top, inStack[] = new int[4010];
	static int cnt, label[] = new int[4010];
	static int ans[] = new int[4010];

	static void init() {
		Arrays.fill(head, -1);
		toll = 0;

		Arrays.fill(dfn, -1);
		tempdfn = 0;

		Arrays.fill(inStack, 0);
		top = 0;

		Arrays.fill(label, 0);
		cnt = 0;
	}

	public static void main(String[] args) throws IOException {
		int k, v, jjj = 0;
		while (in.nextToken() != in.TT_EOF) {
			n = (int) in.nval;
			if (jjj == 0)
				jjj = 1;
			else
				out.println();
			init();
			for (int i = 1; i <= n; i++) {
				k = nextInt();
				while (k-- > 0) {
					v = nextInt();
					addEdge(i, v + n);
				}
			}
			for (int i = 1; i <= n; i++) {
				v = nextInt();
				addEdge(v + n, i);
			}

			for (int i = 1; i <= n; i++)
				if (dfn[i] == -1)
					dfsTarjan(i);

			int num;
			for (int i = 1; i <= n; i++) {
				num = 0;
				for (int j = head[i]; j != -1; j = edge[j].next) {
					v = edge[j].v;
					if (label[v] == label[i])
						ans[++num] = v - n;
				}
				Arrays.sort(ans, 0, num + 1);

				out.print(num);
				for (int j = 1; j <= num; j++)
					out.print(" " + ans[j]);
				out.println();
			}
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
				// out.print(v + " ");
			} while (v != u);
			// out.println();
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

	static int nextInt() throws IOException {
		in.nextToken();
		return (int) in.nval;
	}

}