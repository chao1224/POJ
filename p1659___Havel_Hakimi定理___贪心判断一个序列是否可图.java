import java.awt.Stroke;
import java.io.*;
import java.util.*;

public class p1659___Havel_Hakimi定理___贪心判断一个序列是否可图 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StreamTokenizer in = new StreamTokenizer(br);
	static StringTokenizer stoke;
	static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
	static Scanner scan = new Scanner(System.in);

	static int n, m, zeroNum;
	static point[] p;
	static int map[][] = new int[11][11];

	public static void main(String[] args) throws IOException {
		int test = nextInt(), index = 0, val;

		while (test-- > 0) {
			if (index == 0)
				index = 1;
			else
				out.println();
			n = nextInt();
			zeroNum = 0;
			for (int i = 1; i <= n; i++)
				Arrays.fill(map[i], 0);
			p = new point[n + 1];
			for (int i = 1; i <= n; i++) {
				val = nextInt();
				if (val == 0)
					zeroNum++;
				p[i] = new point(val, i);
			}
			// 左闭右开
			Arrays.sort(p, 1, n + 1);

			if (havel_hakimi()) {
				out.println("YES");
				for (int i = 1; i <= n; i++) {
					for (int j = 1; j < n; j++)
						out.print(map[i][j] + " ");
					out.println(map[i][n]);
				}
			} else
				out.println("NO");
		}

		out.flush();
		out.close();
	}

	static boolean havel_hakimi() {
		int min;
		for (int head = 1; head <= n; head++) {
			Arrays.sort(p, head, n + 1);
			min = p[head].val;
			zeroNum++;
			p[head].val = 0;

			if (min + head > n)
				return false;
			for (int j = head + 1; j <= head + min; j++) {
				p[j].val--;
				map[p[head].index][p[j].index] = map[p[j].index][p[head].index] = 1;
				if (p[j].val == 0)
					zeroNum++;
				else if (p[j].val < 0)
					return false;
			}
			if (zeroNum == n)
				return true;
		}
		return true;
	}

	static class point implements Comparable<point> {
		int val, index;

		point(int val, int index) {
			this.val = val;
			this.index = index;
		}

		public int compareTo(point o) {
			if (this.val < o.val)
				return 1;
			else if (this.val > o.val)
				return -1;
			return 0;
		}
	}

	static class Tarjan {
		int n, m;
		int head[], toll;
		edge edge[];
		int dfn[], low[], tempdfn;
		int stack[], inStack[], top;
		int cnt, label[], max[];

		Tarjan(int nn, int mm) {
			this.n = nn;
			this.m = mm;
			head = new int[n + 1];
			edge = new edge[m];
			dfn = new int[n + 1];
			low = new int[n + 1];
			stack = new int[n + 1];
			inStack = new int[n + 1];
			label = new int[n + 1];
			max = new int[n + 1];
		}

		void dfsTarjan(int u) {
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
				} while (v != u);
			}
		}

		int getComponentNum(int n) {
			for (int i = 1; i <= n; i++)
				if (dfn[i] == -1)
					dfsTarjan(i);
			return cnt;
		}

		void init() {
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

		void addEdge(int u, int v) {
			edge[toll] = new edge(u, v);
			edge[toll].next = head[u];
			head[u] = toll;
			toll++;
		}

		class edge {
			int u, v;
			int next;

			edge(int u, int v) {
				this.u = u;
				this.v = v;
			}

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