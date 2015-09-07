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

public class p3422___费用流 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StreamTokenizer in = new StreamTokenizer(br);
	static StringTokenizer stoke;
	static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
	static Scanner scan = new Scanner(System.in);

	static int n, k;

	public static void main(String[] args) throws IOException {
		int source = 0, destination = 0, val;
		while (in.nextToken() != in.TT_EOF) {
			n = (int) in.nval;
			k = nextInt();

			init();

			for (int i = 1; i <= n; i++)
				for (int j = 1; j <= n; j++) {
					val = nextInt();
					int id = (i - 1) * n + j;
					addEdge(id, id + n * n, 1, val);
					addEdge(id, id + n * n, k, 0);
					if (i < n)
						addEdge(id + n * n, id + n, k, 0);
					if (j < n)
						addEdge(id + n * n, id + 1, k, 0);
				}
			source = n * n * 2 + 1;
			destination = n * n * 2 + 2;
			addEdge(source, 1, k, 0);
			addEdge(n * n * 2, destination, k, 0);
			n = destination;

			out.println(getMin(source, destination));
		}

		out.flush();
		out.close();
	}

	static int N = 5555, M = 55555;
	static int inf = Integer.MAX_VALUE, index, head[] = new int[N], toll;
	static edge edge[] = new edge[M];
	static int dist[] = new int[N], visit[] = new int[N], pre[] = new int[N],
			que[] = new int[N];

	static void init() {
		Arrays.fill(head, -1);
		toll = 0;
	}

	static int getMin(int s, int t) {
		int ans = 0;
		while (spfa(s, t)) {
			ans += argument(s, t);
		}
		return ans;
	}

	static boolean spfa(int source, int destination) {
		Arrays.fill(dist, -1);
		Arrays.fill(visit, 0);
		Arrays.fill(pre, 0);
		Arrays.fill(que, 0);

		int i, h = 0, t = 1;
		dist[source] = 0;
		que[0] = source;
		visit[source] = 1;

		int v;
		while (h != t) {
			int u = que[h++];
			h %= n;
			// 出队
			visit[u] = 0;
			for (i = head[u]; i != -1; i = edge[i].next) {
				v = edge[i].v;
				if (edge[i].cap > 0 && dist[v] < dist[u] + edge[i].cost) {
					dist[v] = dist[u] + edge[i].cost;
					pre[v] = i;
					// 入队
					if (visit[v] == 0) {
						visit[v] = 1;
						que[t++] = v;
						t %= n;
					}
				}
			}
		}
		if (dist[destination] == -1)
			return false;
		return true;
	}

	static int argument(int source, int destination) {
		int u, p, min = inf, ans = 0;
		for (u = destination; u != source; u = edge[p ^ 1].v) {
			p = pre[u];
			min = Math.min(min, edge[p].cap);
		}
		for (u = destination; u != source; u = edge[p ^ 1].v) {
			p = pre[u];
			edge[p].cap -= min;
			edge[p ^ 1].cap += min;
			ans += min * edge[p].cost; // cost记录的为单位流量费用，必须得乘以流量。
		}

		return ans;
	}

	static void addEdge(int u, int v, int cap, int cost) {
		edge[index] = new edge(v, cap, cost);
		edge[index].next = head[u];
		head[u] = index++;

		edge[index] = new edge(u, 0, -cost);
		edge[index].next = head[v];
		head[v] = index++;

	}

	static class edge {
		int v, next;
		int cap, cost;

		edge(int v, int cap, int cost) {
			this.v = v;
			this.cap = cap;
			this.cost = cost;
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

	static int nextInt() throws IOException {
		in.nextToken();
		return (int) in.nval;
	}

	static double nextDouble() throws IOException {
		in.nextToken();
		return in.nval;
	}

}