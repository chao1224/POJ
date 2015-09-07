import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class p1135___Dijkstra {
	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StreamTokenizer in = new StreamTokenizer(br);
	static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
	static int n, m;

	public static void main(String[] args) throws IOException {
		int index = 0;
		while (true) {
			n = nextInt();
			m = nextInt();
			if (n == 0 && m == 0)
				break;

			if (index > 0)
				out.println();
			out.println("System #" + (++index));
			Dijkstra SP = new Dijkstra(n);
			for (int i = 1; i <= m; i++)
				SP.addDoubleEdge(nextInt(), nextInt(), nextInt());
			SP.ShortestPath(1);
		}
		out.flush();
		out.close();
	}

	static String next() throws IOException {
		in.nextToken();
		return in.sval;
	}

	static char nextChar() throws IOException {
		in.nextToken();
		return in.sval.charAt(0);
	}

	static int nextInt() throws IOException {
		in.nextToken();
		return (int) in.nval;
	}

	static double nextDouble() throws IOException {
		in.nextToken();
		return in.nval;
	}

	static class Dijkstra {
		static int n, inf = Integer.MAX_VALUE, adj[][];

		Dijkstra(int n) {
			this.n = n;
			adj = new int[n + 1][n + 1];
			for (int i = 1; i <= n; i++)
				Arrays.fill(adj[i], inf);
		}

		void addEdge(int u, int v, int val) {
			adj[u][v] = val;
		}

		void addDoubleEdge(int u, int v, int val) {
			adj[u][v] = adj[v][u] = val;
		}

		void ShortestPath(int v0) {
			int dist[] = new int[n + 1];
			int path[] = new int[n + 1];
			Arrays.fill(path, -1);
			path[v0] = 0;
			int visit[] = new int[n + 1];
			Arrays.fill(visit, 0);
			visit[v0] = 1;
			for (int i = 1; i <= n; i++) {
				dist[i] = adj[v0][i];
				if (dist[i] < inf)
					path[i] = v0;
			}
			dist[v0] = 0;

			int min, u;
			for (int step = 1; step < n; step++) {
				min = inf;
				u = v0;
				for (int i = 1; i <= n; i++) {
					if (visit[i] == 0 && dist[i] < min) {
						u = i;
						min = dist[i];
					}
				}
				visit[u] = 1;
				for (int i = 1; i <= n; i++) {
					if (visit[i] == 0 && adj[i][u] < inf
							&& dist[i] > dist[u] + adj[i][u]) {
						dist[i] = dist[u] + adj[i][u];
						path[i] = u;
					}
				}
			}

			double max1 = 0;
			int last = v0;
			for (int i = 1; i <= n; i++) {
				if (max1 < dist[i]) {
					max1 = dist[i];
					last = i;
				}
			}
			max1 *= 1.0;

			double max2 = 0.0;
			int x = v0, y = v0;
			for (int i = 1; i <= n; i++) {
				for (int j = i + 1; j <= n; j++) {
					if (adj[i][j] < inf) {
						if ((dist[i] + dist[j] + adj[i][j]) / 2.0 > max2
								&& path[i] != j && path[j] != i) {
							max2 = (dist[i] + dist[j] + adj[i][j]) / 2.0;
							x = i;
							y = j;
						}
					}
				}
			}

			if (max1 >= max2) {
				out.print("The last domino falls after ");
				out.printf("%.1f", max1);
				out.println(" seconds, at key domino " + last + ".");
			} else {
				out.print("The last domino falls after ");
				out.printf("%.1f", max2);
				out.println(" seconds, between key dominoes " + x + " and " + y
						+ ".");
			}
		}
	}
}