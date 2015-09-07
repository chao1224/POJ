import java.awt.Stroke;
import java.io.*;
import java.util.*;

public class p1966___连通度求解 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StreamTokenizer in = new StreamTokenizer(br);
	static StringTokenizer stoke;
	static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));

	static int n, m, inf = 1 << 29;

	public static void main(String[] args) throws IOException {
		Scanner scan = new Scanner(System.in);
		String str;
		int a, b, stop1, stop;
		while (scan.hasNext()) {
			n = scan.nextInt();
			m = scan.nextInt();
			Dinic dinic = new Dinic(n + n);
			for (int ii = 1; ii <= m; ii++) {
				str = scan.next();
				stop = 0;
				for (; stop < str.length(); stop++)
					if (str.charAt(stop) == ',')
						break;
				stop1 = stop;
				a = Integer.parseInt(str.substring(1, stop)) + 1;
				b = Integer
						.parseInt(str.substring(stop1 + 1, str.length() - 1)) + 1;
				dinic.addEdge(b + n, a);
				dinic.addEdge(a + n, b);
			}
			for (int i = 1; i <= n; i++)
				dinic.addEdge(i, i + n, 1);

			int ans = inf;
			for (int i = 2; i <= n; i++) {
				dinic.clear();
				ans = Math.min(ans, dinic.getDinic(n + 1, i));
			}
			if (ans == inf)
				ans = n;
			System.out.println(ans);
		}
		out.flush();
		out.close();
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