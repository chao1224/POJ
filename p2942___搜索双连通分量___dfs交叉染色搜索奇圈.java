import java.awt.Stroke;
import java.io.*;
import java.util.*;

public class p2942___搜索双连通分量___dfs交叉染色搜索奇圈 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StreamTokenizer in = new StreamTokenizer(br);
	static StringTokenizer stoke;
	static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));

	static int n, m, inf = 1 << 29;
	static int dfn[], low[], visit[], head[], index;
	static edge edge[];
	static int used[], stack[], top;
	static int block[][], blockNum[], cnt;
	static boolean inBlock[];

	public static void main(String[] args) throws IOException {
		int a, b;
		while (true) {
			n = nextInt();
			m = nextInt();
			if (n == 0 && m == 0)
				break;

			head = new int[n + 1];
			Arrays.fill(head, -1);
			edge = new edge[n * n];

			int map[][] = new int[n + 1][n + 1];
			for (int i = 0; i < m; i++) {
				a = nextInt();
				b = nextInt();
				map[a][b] = map[b][a] = 1;
			}
			// 这么做是为了防止在最后二分染色的时候，该图的补图出现环，那么任何双连通图就一定会判断成为奇圈了
			for (int i = 1; i <= n; i++)
				map[i][i] = 1;
			index = 0;
			for (int i = 1; i <= n; i++) {
				for (int j = i + 1; j <= n; j++)
					if (map[i][j] == 0) {
						addEdge(i, j);
					}
			}

			used = new int[n + 1];
			stack = new int[n + 1];
			visit = new int[n + 1];
			dfn = new int[n + 1];
			low = new int[n + 1];
			top = 0;

			block = new int[n + 1][n + 1];
			blockNum = new int[n + 1];
			cnt = 0;

			for (int i = 1; i <= n; i++) {
				if (visit[i] == 0) {
					dfn[i] = low[i] = visit[i] = 1;
					dfsTarjan(i, 0);
				}
			}

			int u, v;
			inBlock = new boolean[n + 1];
			int color[] = new int[n + 1];
			boolean can[] = new boolean[n + 1];
			loop: for (int blo = 1; blo <= cnt; blo++) {
				// 实际就是当前分量的顶点数目大于等于3，因为我从0开始计数
				if (blockNum[blo] >= 2) {
					Arrays.fill(inBlock, false);
					Arrays.fill(color, 0);
					for (int i = 0; i <= blockNum[blo]; i++)
						inBlock[block[blo][i]] = true;
					top = 0;
					stack[++top] = block[blo][0];
					color[stack[top]] = 1;
					while (top > 0) {
						u = stack[top--];
						for (int i = head[u]; i != -1; i = edge[i].next) {
							v = edge[i].v;
							if (inBlock[v]) {
								if (color[v] == 0) {
									color[v] = 3 - color[u];
									stack[++top] = v;
								} else {
									if (color[v] == color[u]) {
										for (int j = 1; j <= n; j++)
											if (inBlock[j])
												can[j] = true;
										continue loop;
									}
								}
							}
						}
					}
				}
			}

			int ans = 0;
			for (int i = 1; i <= n; i++)
				if (!can[i])
					ans++;
			out.println(ans);
		}

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
				if (low[v] >= dfn[u]) {
					int t;
					++cnt;
					block[cnt][0] = u;
					blockNum[cnt] = 0;
					do {
						t = stack[--top];
						block[cnt][++blockNum[cnt]] = t;
					} while (t != v);
				}
			} else if (v != father) {
				low[u] = Math.min(low[u], dfn[v]);
			}
		}
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