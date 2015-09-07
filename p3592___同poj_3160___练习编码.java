import java.awt.Stroke;
import java.io.*;
import java.util.*;

public class p3592___同poj_3160___练习编码 {
	// static BufferedReader br = new BufferedReader(new InputStreamReader(
	// System.in));
	// static StreamTokenizer in = new StreamTokenizer(br);
	// static StringTokenizer stoke;
	static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
	static Scanner scan = new Scanner(System.in);

	static int n, m;

	static edge[] edge = new edge[100000];
	static int head[] = new int[1610], toll;
	static int dfn[] = new int[1610], low[] = new int[1610], tempdfn;
	static int stack[] = new int[1610], top, inStack[] = new int[1610];
	static int cnt, label[] = new int[1610];
	static int ans[] = new int[1610];// 对于val，0-9表示数值，0表示可跳跃，-1表示无法走通
	static point point[] = new point[1610];
	static int max[] = new int[1610];

	static void init() {
		Arrays.fill(head, -1);
		toll = 0;

		Arrays.fill(dfn, -1);
		tempdfn = 0;

		Arrays.fill(inStack, 0);
		top = 0;

		Arrays.fill(label, 0);
		cnt = 0;

		Arrays.fill(max, 0);
	}

	static class point implements Comparable<point> {
		int i, j, val;

		point(int ii, int jj) {
			this.i = ii;
			this.j = jj;
		}

		public int compareTo(point o) {
			if (this.i < o.i)
				return -1;
			else if (this.i > o.i)
				return 1;
			else if (this.j < o.j)
				return -1;
			else if (this.j > o.j)
				return 1;
			return 0;
		}

	}

	public static void main(String[] args) throws IOException {
		Scanner scan = new Scanner(System.in);
		int test = scan.nextInt(), ti, tj, u, v;
		String str;
		char ch;
		point temp;

		while (test-- > 0) {
			n = scan.nextInt();
			m = scan.nextInt();
			scan.nextLine();
			init();

			int num = 0;
			point record[] = new point[1610];
			for (int i = 0; i < n; i++) {
				str = scan.nextLine();
				for (int j = 0; j < m; j++) {
					ch = str.charAt(j);
					temp = new point(i, j);
					if (ch == '*') {
						record[++num] = temp;
						temp.val = 0;
					} else if (ch == '#')
						temp.val = -1;
					else
						temp.val = ch - '0';

					point[i * m + j] = temp;
				}
			}

			if (num >= 1)
				Arrays.sort(record, 1, num);
			for (int i = 1; i <= num; i++) {
				ti = scan.nextInt();
				tj = scan.nextInt();
				addEdge(record[i].i * m + record[i].j, ti * m + tj);
			}

			for (int i = 0; i < n * m; i++) {
				temp = point[i];
				if (temp.val == -1)
					continue;
				if ((i + 1) % m != 0 && (i + 1) < n * m
						&& point[i + 1].val >= 0)
					addEdge(i, i + 1);
				if (i + m < n * m && point[i + m].val >= 0)
					addEdge(i, i + m);
			}

			for (int i = 0; i < m * n; i++)
				if (dfn[i] == -1)
					dfsTarjan(i);

			Arrays.fill(head, -1);
			int line = toll;
			toll = 0;
			for (int i = 0; i < line; i++) {
				u = label[edge[i].u];
				v = label[edge[i].v];
				if (u != v)
					addEdge(u, v);
			}

			out.println(dfs(label[0]));

		}

		out.flush();
		out.close();
	}

	static int dfs(int u) {
		int ret = 0, v;
		for (int e = head[u]; e != -1; e = edge[e].next) {
			v = edge[e].v;
			if (u != v)
				ret = Math.max(ret, dfs(v));
		}
		// System.out.println("max from " + u + " :    " + max[u]);
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
				if (point[v].val > 0)
					max[cnt] += point[v].val;
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

	// static String next() throws IOException {
	// in.nextToken();
	// return in.sval;
	// }
	//
	// static char nextChar() throws IOException {
	// in.nextToken();
	// return in.sval.charAt(0);
	// }
	//
	// static long nextLong() throws IOException {
	// in.nextToken();
	// return (long) in.nval;
	// }
	//
	// static int nextInt() throws IOException {
	// in.nextToken();
	// return (int) in.nval;
	// }

}