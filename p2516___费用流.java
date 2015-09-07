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

public class p2516___费用流 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StreamTokenizer in = new StreamTokenizer(br);
	static StringTokenizer stoke;
	static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
	static Scanner scan = new Scanner(System.in);

	static int n, m, k, cap[][], cost[][], inf = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		int source = 0, destination = 0, val;
		int arr[][], brr[][], crr[][][];
		loop: while (true) {
			n = nextInt();
			m = nextInt();
			k = nextInt();
			if (n == 0 && m == 0 && k == 0)
				break;

			arr = new int[n + 1][k + 1];
			for (int i = 1; i <= n; i++)
				for (int j = 1; j <= k; j++)
					arr[i][j] = nextInt();
			brr = new int[m + 1][k + 1];
			for (int i = 1; i <= m; i++)
				for (int j = 1; j <= k; j++)
					brr[i][j] = nextInt();
			crr = new int[k + 1][n + 1][m + 1];
			for (int r = 1; r <= k; r++)
				for (int i = 1; i <= n; i++)
					for (int j = 1; j <= m; j++)
						crr[r][i][j] = nextInt();

			source = 0;
			destination = n + m + 1;
			cap = new int[n + m + 2][n + m + 2];
			cost = new int[n + m + 2][n + m + 2];
			int outSum, inSum, ans = 0;

			for (int step = 1; step <= k; step++) {
				for (int i = 1; i <= n + m + 1; i++) {
					Arrays.fill(cap[i], 0);
					Arrays.fill(cost[i], 0);
				}
				outSum = inSum = 0;

				for (int i = 1; i <= m; i++) {
					cap[source][i] = brr[i][step];
					cost[source][i] = 0;
					inSum += brr[i][step];
				}
				for (int i = 1; i <= n; i++) {
					cap[m + i][destination] = arr[i][step];
					cost[m + i][destination] = 0;
					outSum += arr[i][step];
				}
				if (inSum < outSum) {
					out.println(-1);
					continue loop;
				} else {
					for (int i = 1; i <= m; i++) {
						for (int j = 1; j <= n; j++) {
							cap[i][j + m] = brr[i][step];
							cost[i][j + m] = crr[step][j][i];
							cost[j + m][i] = -crr[step][j][i];
						}
					}
					ans += maxFlow(source, destination);
				}
			}
			out.println(ans);
		}

		out.flush();
		out.close();
	}

	static int pre[];

	static int maxFlow(int s, int t) {
		int ans = 0;
		while (spfa(s, t))
			ans += argument(s, t);
		return ans;
	}

	static int argument(int s, int t) {
		int min = Integer.MAX_VALUE, flow = 0;
		for (int i = t; i != s; i = pre[i])
			min = Math.min(min, cap[pre[i]][i]);
		for (int i = t; i != s; i = pre[i]) {
			cap[pre[i]][i] -= min;
			cap[i][pre[i]] += min;
			flow += cost[pre[i]][i] * min;
		}
		return flow;
	}

	static boolean spfa(int s, int t) {
		int n = t;
		int inf = Integer.MAX_VALUE;
		int dist[] = new int[n + 1];
		Arrays.fill(dist, inf);
		int visit[] = new int[n + 1];
		int queue[] = new int[n + 1];
		pre = new int[n + 1];

		int begin = 0, end = 1, u, v;
		visit[s] = 1;
		dist[s] = 0;
		while (begin != end) {
			u = queue[begin++];
			begin %= n;
			visit[u] = 0;
			for (int i = 0; i <= n; i++) {
				if (cap[u][i] > 0 && dist[i] > dist[u] + cost[u][i]) {
					dist[i] = dist[u] + cost[u][i];
					pre[i] = u;
					if (visit[i] == 0) {
						visit[i] = 1;
						queue[end++] = i;
						end %= n;
					}
				}
			}
		}

		if (dist[t] == inf)
			return false;
		return true;
	}

	// static int inf = Integer.MAX_VALUE, index, head[];
	// static edge edge[];
	// static int dist[], visit[], pre[], que[];
	//
	// static void init() {
	// edge = new edge[55555];
	// head = new int[5555];
	// Arrays.fill(head, -1);
	// index = 0;
	// }
	//
	// static boolean spfa(int source, int destination) {
	// dist = new int[5555];
	// Arrays.fill(dist, -1);
	// visit = new int[5555];
	// Arrays.fill(visit, 0);
	// pre = new int[5555];
	// que = new int[5555];
	//
	// int i, h = 0, t = 1;
	// dist[source] = 0;
	// que[0] = source;
	// visit[source] = 1;
	//
	// int v;
	// while (h != t) {
	// int u = que[h++];
	// h %= n;
	// // 出队
	// visit[u] = 0;
	// for (i = head[u]; i != -1; i = edge[i].next) {
	// v = edge[i].v;
	// if (edge[i].cap > 0 && dist[v] < dist[u] + edge[i].cost) {
	// dist[v] = dist[u] + edge[i].cost;
	// pre[v] = i;
	// // 入队
	// if (visit[v] == 0) {
	// visit[v] = 1;
	// que[t++] = v;
	// t %= n;
	// }
	// }
	// }
	// }
	// if (dist[destination] == -1)
	// return false;
	// return true;
	// }
	//
	// static int argument(int source, int destination) {
	// int u, p, min = inf, ans = 0;
	// for (u = destination; u != source; u = edge[p ^ 1].v) {
	// p = pre[u];
	// min = Math.min(min, edge[p].cap);
	// }
	// for (u = destination; u != source; u = edge[p ^ 1].v) {
	// p = pre[u];
	// edge[p].cap -= min;
	// edge[p ^ 1].cap += min;
	// ans += min * edge[p].cost; // cost记录的为单位流量费用，必须得乘以流量。
	// }
	// // flow += min;
	//
	// return ans;
	// }
	//
	// static void addEdge(int u, int v, int cap, int cost) {
	// edge[index] = new edge(v, cap, cost);
	// edge[index].next = head[u];
	// head[u] = index++;
	//
	// edge[index] = new edge(u, 0, -cost);
	// edge[index].next = head[v];
	// head[v] = index++;
	//
	// }
	//
	// static class edge {
	// int v, next;
	// int cap, cost;
	//
	// edge() {
	//
	// }
	//
	// edge(int v, int cap, int cost) {
	// this.v = v;
	// this.cap = cap;
	// this.cost = cost;
	// }
	// }

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