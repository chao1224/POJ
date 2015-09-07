import java.awt.Stroke;
import java.io.*;
import java.util.*;

public class p3177___求桥___删桥___求连通分支___缩点___构建新图 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StreamTokenizer in = new StreamTokenizer(br);
	static StringTokenizer stoke;
	static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));

	static int n, m, inf = 1 << 29;
	static int dfn[], low[], visit[], head[], index;
	static int stack[], top, father[], bridge[][], cnt;
	static edge edge[];

	public static void main(String[] args) throws IOException {
		int a, b, ra, rb;
		n = nextInt();
		m = nextInt();

		head = new int[n + 1];
		Arrays.fill(head, -1);
		edge = new edge[2 * m];
		index = 0;
		for (int i = 1; i <= m; i++) {
			a = nextInt();
			b = nextInt();
			addEdge(a, b);
		}

		father = new int[n + 1];
		top = 0;
		stack = new int[n + 1];
		for (int i = 1; i <= n; i++)
			father[i] = i;

		bridge = new int[n + 1][2];
		cnt = 0;

		dfn = new int[n + 1];
		low = new int[n + 1];
		visit = new int[n + 1];
		dfn[1] = low[1] = visit[1] = 1;

		dfsTarjan(1, 0);

		int ans = 0;
		int record[] = new int[n + 1];
		for (int i = 1; i <= cnt; i++) {
			a = find(bridge[i][0]);
			b = find(bridge[i][1]);
			record[a]++;
			record[b]++;
		}
		for (int i = 1; i <= n; i++)
			if (record[i] == 1)
				ans++;

		out.println((ans + 1) / 2);

		out.flush();
		out.close();
	}

	static void addEdge(int u, int v) {
		edge[index] = new edge(v);
		edge[index].next = head[u];
		head[u] = index;
		index++;

		edge[index] = new edge(u);
		edge[index].next = head[v];
		head[v] = index;
		index++;
	}

	static void dfsTarjan(int u, int father) {
		stack[top++] = u;
		int v;
		for (int i = head[u]; i != -1; i = edge[i].next) {
			v = edge[i].v;
			if (visit[v] == 0) {
				visit[v] = 1;
				dfn[v] = low[v] = dfn[u] + 1;
				dfsTarjan(v, u);
				low[u] = Math.min(low[u], low[v]);
				if (low[v] <= dfn[u])
					union(u, v);
				if (low[v] > dfn[u]) {
					bridge[++cnt][0] = u;
					bridge[cnt][1] = v;
				}
			} else if (v != father) {
				low[u] = Math.min(low[u], dfn[v]);
			}
		}
	}

	static int find(int x) {
		if (father[x] == x)
			return x;
		else
			father[x] = find(father[x]);
		return father[x];
	}

	static void union(int a, int b) {
		int ra = find(a);
		int rb = find(b);
		if (ra != rb)
			father[ra] = rb;
	}

	static class edge {
		int v, next;

		edge(int vv) {
			this.v = vv;
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