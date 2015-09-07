import java.awt.Stroke;
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

public class p1156___计算几何入门___最短路 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StreamTokenizer in = new StreamTokenizer(br);
	static StringTokenizer stoke;
	static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
	static Scanner scan = new Scanner(System.in);
	static int n, m;
	static double wX[];
	static double pY[][];
	static point p[];

	public static void main(String[] args) throws IOException {
		double x, a, b, c, d;
		int index = 0;
		while (true) {
			n = nextInt();
			if (n == -1)
				break;
			wX = new double[n + 1];
			pY = new double[n + 1][4];
			p = new point[4 * n + 3];

			index = 0;
			p[++index] = new point(0, 5);
			for (int i = 1; i <= n; i++) {
				x = nextDouble();
				a = nextDouble();
				b = nextDouble();
				c = nextDouble();
				d = nextDouble();
				wX[i] = x;
				pY[i][0] = a;
				pY[i][1] = b;
				pY[i][2] = c;
				pY[i][3] = d;
				p[++index] = new point(x, a);
				p[++index] = new point(x, b);
				p[++index] = new point(x, c);
				p[++index] = new point(x, d);
			}
			p[++index] = new point(10, 5);

			Bellman_Ford sp = new Bellman_Ford(index);
			for (int i = 1; i <= index; i++) {
				for (int j = i + 1; j <= index; j++) {
					if (isOK(p[i], p[j])) {
						sp.addEdge(i, j, dis(p[i], p[j]));
					}
				}
			}
			out.printf("%.2f", sp.ShortestPath(1, index));
			out.println();
		}
		out.flush();
		out.close();
	}

	static String next() throws IOException {
		in.nextToken();
		return in.sval;
	}

	static int nextInt() throws IOException {
		in.nextToken();
		return (int) in.nval;
	}

	static double nextDouble() throws IOException {
		in.nextToken();
		return in.nval;
	}

	static double dis(point a, point b) {
		return Math.sqrt((a.x - b.x) * (a.x - b.x) + (a.y - b.y) * (a.y - b.y));
	}

	static boolean isOK(point a, point b) {
		if (a.x >= b.x)
			return false;
		int i = 0;
		while (i <= n && wX[i] <= a.x)
			i++;
		double temp;
		while (i <= n && wX[i] < b.x) {
			temp = (b.y - a.y) / (b.x - a.x) * (wX[i] - a.x) + a.y;
			if (temp >= pY[i][3] || (temp <= pY[i][2] && temp >= pY[i][1])
					|| temp <= pY[i][0]) {
				return false;
			}
			i++;
		}
		return true;
	}

	static class point {
		double x, y;

		point(double x, double y) {
			this.x = x;
			this.y = y;
		}
	}

	static class Bellman_Ford {
		int n;
		double adj[][], inf = Double.MAX_VALUE;

		Bellman_Ford(int n) {
			this.n = n;
			adj = new double[n + 1][n + 1];
			for (int i = 1; i <= n; i++)
				Arrays.fill(adj[i], inf);
		}

		void addEdge(int u, int v, double val) {
			adj[u][v] = val;
		}

		double ShortestPath(int source, int destination) {
			double dist[] = new double[n + 1];
			Arrays.fill(dist, inf);

			dist[source] = 0;
			for (int step = 1; step < n; step++) {
				for (int u = 1; u <= n; u++) {
					for (int j = 1; j <= n; j++) {
						if (adj[j][u] < inf && dist[j] + adj[j][u] < dist[u])
							dist[u] = dist[j] + adj[j][u];
					}
				}
			}

			return dist[destination];
		}
	}
}
