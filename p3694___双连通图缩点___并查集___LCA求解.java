import java.awt.Stroke;
import java.io.*;
import java.util.*;

public class p3694___双连通图缩点___并查集___LCA求解 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StreamTokenizer in = new StreamTokenizer(br);
	static StringTokenizer stoke;
	static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));

	static int n, m;

	static int head[], index;
	static edge edge[];

	static int dfn[], low[], visit[], bridgeNum, bridge[][];

	static int father[], rank[];

	static int root[], lca[], depth[];

	static void init() {
		father = new int[n + 1];
		for (int i = 1; i <= n; i++)
			father[i] = i;
		rank = new int[n + 1];
		Arrays.fill(rank, 1);

		dfn = new int[n + 1];
		low = new int[n + 1];
		visit = new int[n + 1];
		bridgeNum = 0;
		bridge = new int[m + 1][2];

		head = new int[n + 1];
		Arrays.fill(head, -1);
		edge = new edge[m * 2];
		index = 0;
	}

	public static void main(String[] args) throws IOException {
		int u, v, ru, rv;
		int casenum = 0;
		while (true) {
			n = nextInt();
			m = nextInt();
			if (n == 0 && m == 0)
				break;
			casenum++;
			if (casenum > 1)
				out.println();
			out.println("Case " + casenum + ":");

			init();
			loop: for (int i = 1; i <= m; i++) {
				u = nextInt();
				v = nextInt();
				for (int e = head[u]; e != -1; e = edge[e].next) {
					if (edge[e].v == v) {
						edge[e].more = edge[e ^ 1].more = 1;
						addEdge(u, v, 1);
						continue loop;
					}
				}
				addEdge(u, v);
			}

			dfn[1] = low[1] = visit[1] = 1;
			dfsTarijan(1, 0);

			Arrays.fill(head, -1);
			index = 0;
			for (int i = 1; i <= bridgeNum; i++) {
				u = find(bridge[i][0]);
				v = find(bridge[i][1]);
				addEdge(u, v);
			}

			root = new int[n + 1];
			depth = new int[n + 1];
			Arrays.fill(visit, 0);
			int r = find(1);
			root[r] = 0;
			depth[r] = 0;
			visit[r] = 1;
			dfs(r);

			int q = nextInt();
			while (q-- > 0) {
				u = nextInt();
				v = nextInt();
				ru = find(u);
				rv = find(v);
				if (ru == rv) {
					out.println(bridgeNum);
				} else {
					// 如果是桥，即为加了一条桥的重边，则桥的数量减一
					// 如果不是桥，则加了这一条边，会形成一个环
					// 发现二者可以合二为一，就是一种情况：加边之后找环
					if (depth[ru] > depth[rv]) {
						u = rv;
						v = ru;
					} else {
						u = ru;
						v = rv;
					}
					while (depth[v] > depth[u]) {
						// System.out.println("short");
						if (depth[v] > 0) {
							bridgeNum--;
							depth[v] = 0;
						}
						v = root[v];
					}
					rv = v;
					ru = u;
					while (v != u) {
						if (depth[v] > 0) {
							bridgeNum--;
							depth[v] = 0;
						}
						if (depth[u] > 0) {
							bridgeNum--;
							depth[u] = 0;
						}
						v = root[v];
						u = root[u];
					}
					out.println(bridgeNum);
				}
			}

		}

		out.flush();
		out.close();
	}

	static void dfs(int u) {
		int v;
		for (int i = head[u]; i != -1; i = edge[i].next) {
			v = edge[i].v;
			if (visit[v] == 0) {
				visit[v] = 1;
				depth[v] = depth[u] + 1;
				root[v] = u;
				dfs(v);
			}
		}
	}

	static void dfsTarijan(int u, int father) {
		int v;
		for (int e = head[u]; e != -1; e = edge[e].next) {
			v = edge[e].v;
			if (visit[v] == 0) {
				visit[v] = 1;
				dfn[v] = low[v] = dfn[u] + 1;
				dfsTarijan(v, u);
				low[u] = Math.min(low[u], low[v]);
				if (low[v] <= dfn[u] || edge[e].more == 1) {
					union(v, u);
				}
				if (low[v] > dfn[u] && edge[e].more == 0) {
					bridgeNum++;
					bridge[bridgeNum][0] = u;
					bridge[bridgeNum][1] = v;
				}
			} else if (v != father) {
				low[u] = Math.min(low[u], dfn[v]);
			}
		}
	}

	static int find(int x) {
		if (father[x] != x)
			father[x] = find(father[x]);
		return father[x];
	}

	static void union(int a, int b) {
		int ra = find(a);
		int rb = find(b);
		if (ra != rb) {
			if (rank[ra] > rank[rb]) {
				father[rb] = ra;
			} else {
				if (rank[a] == rank[rb])
					rank[rb]++;
				father[ra] = rb;
			}
		}
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

	static void addEdge(int u, int v, int m) {
		edge[index] = new edge(v);
		edge[index].more = m;
		edge[index].next = head[u];
		head[u] = index;
		index++;

		edge[index] = new edge(u);
		edge[index].more = m;
		edge[index].next = head[v];
		head[v] = index;
		index++;

	}

	static class edge {
		int v, next, more;

		edge(int v) {
			this.v = v;
		}

	}

	static class Dinic {
		int cap[][], flow[][], inf = 1 << 29;
		int n, level[], queue[];

		Dinic(int nn) {
			this.n = nn;
			cap = new int[n + 1][n + 1];
			flow = new int[n + 1][n + 1];
			level = new int[n + 1];
			queue = new int[n + 1];
		}

		int getDinic(int source, int destination) {
			int sum = 0, temp;
			while (bfs(source, destination)) {
				temp = dfs(source, destination, inf);
				sum += temp;
			}
			return sum;
		}

		void addEdge(int u, int v) {
			this.cap[u][v] = inf;
		}

		void addEdge(int u, int v, int cap) {
			this.cap[u][v] = cap;
		}

		boolean bfs(int source, int destination) {
			Arrays.fill(level, -1);
			level[source] = 0;

			int temp, start = 0, end = 1;
			queue[start] = source;
			while (start < end) {
				temp = queue[start];
				for (int i = 0; i <= n; i++) {
					if (level[i] == -1 && cap[temp][i] > flow[temp][i]) {
						queue[end++] = i;
						level[i] = level[temp] + 1;
					}
				}
				start++;
			}
			return level[destination] != -1;
		}

		int dfs(int vert, int destination, int sum) {
			if (vert == destination)
				return sum;
			int s = sum, temp;
			for (int i = 0; i <= n; i++) {
				if (cap[vert][i] > flow[vert][i] && level[i] == level[vert] + 1) {
					temp = dfs(i, destination, Math.min(sum, cap[vert][i]
							- flow[vert][i]));
					flow[vert][i] += temp;
					flow[i][vert] -= temp;
					sum -= temp;
				}
			}
			return s - sum;
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