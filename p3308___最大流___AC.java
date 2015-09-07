import java.awt.Stroke;
import java.io.BufferedInputStream;
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
import java.util.Iterator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;
import java.util.StringTokenizer;

public class p3308___×î´óÁ÷___AC {
	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StreamTokenizer in = new StreamTokenizer(br);
	static StringTokenizer stoke;
	static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
	static Scanner scan = new Scanner(System.in);

	public static void main(String[] args) throws IOException {
		int l, n, m, test = nextInt();
		int a, b;
		while (test-- > 0) {
			n = nextInt();
			m = nextInt();
			l = nextInt();
			DoubleDinic dinic = new DoubleDinic(m + n + 1);
			int source = 0, destination = n + m + 1;

			for (int i = 1; i <= n; i++)
				dinic.addEdge(source, i, Math.log10(nextDouble()));

			for (int i = 1; i <= m; i++)
				dinic.addEdge(i + n, destination, Math.log10(nextDouble()));

			for (int i = 1; i <= l; i++) {
				a = nextInt();
				b = nextInt();
				dinic.addEdge(a, b + n);
			}

			out.printf("%.4f", Math
					.pow(10, dinic.getDinic(source, destination)));
			out.println();
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

	static class DoubleDinic {

		double cap[][], flow[][], inf = 1e8;
		int n, pre[], queue[];

		DoubleDinic(int nn) {
			this.n = nn;
			cap = new double[n + 1][n + 1];
			flow = new double[n + 1][n + 1];
			pre = new int[n + 1];
			queue = new int[n + 1];
		}

		void addEdge(int u, int v) {
			this.cap[u][v] = inf;
		}

		void addEdge(int u, int v, double cap) {
			this.cap[u][v] = cap;
		}

		double getDinic(int source, int destination) {
			double max_flow = 0, min;
			while (bfs(source, destination)) {
				min = inf;
				for (int i = destination; i != source; i = pre[i]) {
					if (cap[pre[i]][i] < min) {
						min = cap[pre[i]][i];
					}
				}
				for (int i = destination; i != source; i = pre[i]) {
					cap[pre[i]][i] -= min;
					cap[i][pre[i]] += min;
				}
				max_flow += min;
			}
			return max_flow;
		}

		boolean bfs(int source, int destination) {
			Arrays.fill(pre, -1);
			pre[source] = source;

			int temp, start = 0, end = 1;
			queue[start] = source;
			while (start < end) {
				temp = queue[start++];
				for (int i = 0; i <= n; i++) {
					if (pre[i] == -1 && eps(cap[temp][i]) > 0) {
						queue[end++] = i;
						pre[i] = temp;
						if (i == destination)
							return true;
					}
				}
			}
			return false;
		}

		double eps(double ds) {
			return ds < 1e-8 ? 0 : ds;
		}
	}

}