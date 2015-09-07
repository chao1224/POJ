import java.awt.Stroke;
import java.io.*;
import java.util.*;

public class p1144___裸的Tarjan求解割点数目 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StreamTokenizer in = new StreamTokenizer(br);
	static StringTokenizer stoke;
	static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));

	static int n, m, inf = 1 << 29;
	static int dfn[], low[], visit[], subnet[], adj[][];

	public static void main(String[] args) throws IOException {
		int u, v;
		while (true) {
			stoke = new StringTokenizer(br.readLine());
			n = Integer.parseInt(stoke.nextToken());
			if (n == 0)
				break;
			dfn = new int[n + 1];
			Arrays.fill(dfn, -1);
			low = new int[n + 1];
			Arrays.fill(low, -1);
			visit = new int[n + 1];
			adj = new int[n + 1][n + 1];
			subnet = new int[n + 1];

			while (true) {
				stoke = new StringTokenizer(br.readLine());
				u = Integer.parseInt(stoke.nextToken());
				if (u == 0)
					break;
				while (stoke.hasMoreTokens()) {
					v = Integer.parseInt(stoke.nextToken());
					adj[u][v] = adj[v][u] = 1;
				}
			}

			dfn[1] = low[1] = visit[1] = 1;
			dfsTarjan(1);
			int sum = 0;
			if (subnet[1] >= 1)
				subnet[1]--;

			for (int i = 1; i <= n; i++) {
				if (subnet[i] > 0)
					sum++;
			}
			out.println(sum);
		}
		out.flush();
		out.close();
	}

	static void dfsTarjan(int v) {
		for (int i = 1; i <= n; i++) {
			if (adj[v][i] > 0) {
				if (visit[i] == 0) {
					visit[i] = 1;
					dfn[i] = low[i] = dfn[v] + 1;
					dfsTarjan(i);
					low[v] = Math.min(low[v], low[i]);
					if (low[i] >= dfn[v])
						subnet[v]++;
				} else {
					low[v] = Math.min(low[v], dfn[i]);
				}
			}
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

		void clear() {
			for (int i = 1; i <= n; i++)
				Arrays.fill(flow[i], 0);
		}

		int getDinic(int source, int destination) {
			if (cap[source][destination] > 0)
				return inf;
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
				for (int i = 1; i <= n; i++) {
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
			for (int i = 1; i <= n; i++) {
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