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
import java.util.Iterator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;
import java.util.StringTokenizer;

public class p2175___费用流 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StreamTokenizer in = new StreamTokenizer(br);
	static StringTokenizer stoke;
	static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
	static Scanner scan = new Scanner(System.in);

	static int n, m, k, cap[][], flow[][], cost[][], inf = Integer.MAX_VALUE,
			vertex;

	public static void main(String[] args) throws IOException {
		int source = 0, destination = 0;
		int build[][], shelter[][], sum[];

		while (in.nextToken() != in.TT_EOF) {
			n = (int) in.nval;
			m = nextInt();

			build = new int[n + 1][3];
			for (int i = 1; i <= n; i++) {
				build[i][0] = nextInt();
				build[i][1] = nextInt();
				build[i][2] = nextInt();
			}
			shelter = new int[m + 1][3];
			for (int i = 1; i <= m; i++) {
				shelter[i][0] = nextInt();
				shelter[i][1] = nextInt();
				shelter[i][2] = nextInt();
			}

			source = 0;
			destination = n + m + 1;
			flow = new int[n + m + 2][n + m + 2];
			cap = new int[n + m + 2][n + m + 2];
			cost = new int[n + m + 2][n + m + 2];
			for (int i = 1; i < n + m + 2; i++)
				Arrays.fill(cost[i], inf);
			sum = new int[m + 1];

			for (int i = 1; i <= n; i++) {
				for (int j = 1; j <= m; j++) {
					flow[i][j + n] = nextInt();
					cap[i][j + n] = build[i][2];
					// if (flow[i][j + n] < cap[i][j + n])
					cost[i][j + n] = (1 + Math.abs(build[i][0] - shelter[j][0]) + Math
							.abs(build[i][1] - shelter[j][1]));
					cap[j + n][i] = 0;
					flow[j + n][i] = -flow[i][j + n];
					cost[j + n][i] = -(1 + Math
							.abs(build[i][0] - shelter[j][0]) + Math
							.abs(build[i][1] - shelter[j][1]));
					sum[j] += flow[i][j + n];
				}
			}
			for (int i = 1; i <= n; i++) {
				cap[source][i] = build[i][2];
				flow[source][i] = build[i][2];
				cost[source][i] = 0;
				cap[i][source] = 0;
				flow[i][source] = -build[i][2];
				cost[i][source] = 0;
			}
			for (int j = 1; j <= m; j++) {
				cap[j + n][destination] = shelter[j][2];
				flow[j + n][destination] = sum[j];
				cost[j + n][destination] = 0;
				cap[destination][j + n] = 0;
				flow[destination][j + n] = -sum[j];
				cost[destination][j + n] = 0;
			}

			if (spfa(destination, source)) {
				out.println("SUBOPTIMAL");

				int visit[] = new int[n + m + 2], u, v;
				Arrays.fill(visit, 0);
				while (visit[vertex] == 0) {
					visit[vertex] = 1;
					vertex = pre[vertex];
				}

				int start = vertex;
				do {
					u = pre[vertex];
					v = vertex;
					if (u > v) {
						flow[u][v]++;
						flow[v][u]--;
					} else {
						flow[u][v]++;
						flow[v][u]--;
					}
					// System.out.println(u + " -> " + v);
					vertex = pre[vertex];
				} while (vertex != start);

				for (int i = 1; i <= n; i++) {
					for (int j = 1; j < m; j++)
						out.print(flow[i][n + j] + " ");
					out.println(flow[i][n + m]);
				}
			} else {
				out.println("OPTIMAL");
			}
		}
		out.flush();
		out.close();
	}

	static int pre[];

	// spfa判断负圈
	// 即计算某个点入队次数是否超过n
	static boolean spfa(int s, int t) {
		int n = s;
		int inf = Integer.MAX_VALUE;
		int dist[] = new int[n + 1];
		Arrays.fill(dist, inf);
		int visit[] = new int[n + 1];
		int queue[] = new int[n + 1];
		pre = new int[n + 1];
		int count[] = new int[n + 1];

		int begin = 0, end = 1, u;
		visit[s] = 1;
		dist[s] = 0;
		count[s]++;
		queue[begin] = s;
		while (begin != end) {
			u = queue[begin++];
			begin %= n;
			visit[u] = 0;
			for (int i = 0; i <= n; i++) {
				if (cap[u][i] > flow[u][i] && cost[u][i] < inf
						&& dist[i] > dist[u] + cost[u][i]) {
					dist[i] = dist[u] + cost[u][i];
					pre[i] = u;
					if (visit[i] == 0) {
						visit[i] = 1;
						count[i]++;
						if (count[i] >= 1 + n) {
							vertex = i;
							return true;
						}
						queue[end++] = i;
						end %= n;
					}
				}
			}
		}
		return false;
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

}