import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class p1679___Prime___MSTΨһ {
	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StreamTokenizer in = new StreamTokenizer(br);
	static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
	static int n, m;

	public static void main(String[] args) throws IOException {
		int t = nextInt(), u, v, val;
		while (t-- > 0) {
			n = nextInt();
			m = nextInt();
			Prime MST = new Prime(n);
			for (int i = 1; i <= m; i++) {
				u = nextInt();
				v = nextInt();
				val = nextInt();
				MST.addEdge(u, v, val);
				MST.addEdge(v, u, val);
			}
			if (!MST.solve(1))
				out.println("Not Unique!");
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

	static class pair {
		int i, j;

		pair(int i, int j) {
			this.i = i;
			this.j = j;
		}
	}

	static class Prime {
		int n, inf = Integer.MAX_VALUE;
		int adj[][];

		Prime(int n) {
			this.n = n;
			adj = new int[n + 1][n + 1];
			for (int i = 1; i <= n; i++)
				Arrays.fill(adj[i], inf);
		}

		void addEdge(int u, int v, int val) {
			adj[u][v] = val;
		}

		boolean solve(int v0) {
			int dist[] = new int[n + 1];
			int near[] = new int[n + 1];

			for (int i = 1; i <= n; i++) {
				dist[i] = adj[v0][i];
				near[i] = v0;
			}
			dist[v0] = -1;
			int min, sum = 0;
			int u;
			for (int time = 1; time < n; time++) {
				min = Integer.MAX_VALUE;
				u = v0;
				for (int i = 1; i <= n; i++) {
					if (dist[i] != -1 && dist[i] < min) {
						min = dist[i];
						u = i;
					}
				}

				for (int i = 1; i <= n; i++) {
					if (dist[i] == -1) {
						if (adj[i][u] == min && near[u] != i) {
							return false;
						}
					}
				}

				dist[u] = -1;
				sum += min;

				for (int i = 1; i <= n; i++) {
					if (dist[i] > adj[u][i]) {
						dist[i] = adj[u][i];
						near[i] = u;
					}
				}
			}

			out.println(sum);
			return true;
		}
	}
}