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

public class p1751___Prime¡⁄Ω”æÿ’Û {
	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StreamTokenizer in = new StreamTokenizer(br);
	static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
	static int n, m;

	public static void main(String[] args) throws IOException {
		double cell[][];
		int a, b;
		n = nextInt();
		cell = new double[n + 1][2];

		for (int i = 1; i <= n; i++) {
			cell[i][0] = nextInt();
			cell[i][1] = nextInt();
		}
		Prime MST = new Prime(n);
		double temp;
		for (int i = 1; i <= n; i++) {
			for (int j = (i + 1); j <= n; j++) {
				temp = Math.sqrt((cell[i][0] - cell[j][0])
						* (cell[i][0] - cell[j][0]) + (cell[i][1] - cell[j][1])
						* (cell[i][1] - cell[j][1]));
				MST.addEdge(i, j, temp);
				MST.addEdge(j, i, temp);
			}
			MST.addEdge(i, i, 0);
		}
		m = nextInt();
		for (int i = 1; i <= m; i++) {
			a = nextInt();
			b = nextInt();
			MST.addEdge(a, b, 0);
			MST.addEdge(b, a, 0);
		}

		MST.solve(1);

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

	static class Prime {
		int n;
		double adj[][];

		Prime(int n) {
			this.n = n;
			adj = new double[n + 1][n + 1];
		}

		void addEdge(int u, int v, double val) {
			adj[u][v] = val;
		}

		void solve(int v0) {
			double dist[] = new double[n + 1];
			int near[] = new int[n + 1];

			double min;
			int u;
			for (int i = 1; i <= n; i++) {
				dist[i] = adj[v0][i];
				near[i] = v0;
			}
			near[v0] = -1;
			for (int time = 1; time < n; time++) {
				min = Integer.MAX_VALUE;
				u = v0;
				for (int i = 1; i <= n; i++) {
					if (near[i] != -1 && dist[i] < min) {
						min = dist[i];
						u = i;
					}
				}

				if (min > 0)
					out.println(u + " " + near[u]);
				near[u] = -1;

				for (int i = 1; i <= n; i++) {
					if (near[i] != -1 && dist[i] > adj[u][i]) {
						dist[i] = adj[u][i];
						near[i] = u;
					}
				}
			}

		}
	}
}