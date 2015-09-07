import java.awt.Stroke;
import java.io.*;
import java.util.*;

public class p3031___ÂãµÄDijkstra {
	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StreamTokenizer in = new StreamTokenizer(br);
	static StringTokenizer stoke;
	static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
	static Scanner scan = new Scanner(System.in);

	static int n, m;
	static long cost[];

	public static void main(String[] args) throws IOException {
		int test = nextInt(), u, v, val;
		Dijkstra dijk;
		while (test-- > 0) {
			n = nextInt();
			m = nextInt();

			if (n == 0 || n == 1) {
				for (int i = 1; i <= n; i++)
					nextInt();
				out.println(0);
				for (int i = 1; i <= m; i++) {
					u = nextInt();
					v = nextInt();
					val = nextInt();
				}
				continue;
			}

			dijk = new Dijkstra(n, 2 * m + 10);
			cost = new long[n + 1];
			for (int i = 1; i <= n; i++)
				cost[i] = nextInt();
			for (int i = 1; i <= m; i++) {
				u = nextInt();
				v = nextInt();
				val = nextInt();
				dijk.addEdge(u, v, val);
				dijk.addEdge(v, u, val);
			}
			long ans = dijk.ShortestPath(1);
			if (ans == -1)
				out.println("No Answer");
			else
				out.println(ans);

		}
		out.flush();
		out.close();
	}

	static class Dijkstra {
		int n, m, toll = 0;
		int head[];
		edge edge[];
		PriorityQueue<node> queue = new PriorityQueue();

		Dijkstra(int nn, int mm) {
			this.n = nn;
			this.m = mm;
			head = new int[n + 1];
			Arrays.fill(head, -1);
			edge = new edge[m];
			toll = 0;
		}

		void addEdge(int u, int v, int val) {
			edge[toll] = new edge(u, v, val);
			edge[toll].next = head[u];
			head[u] = toll;
			toll++;
		}

		long ShortestPath(int source) {
			int visit[] = new int[n + 1];
			long dist[] = new long[n + 1];
			queue.add(new node(source, source, 0));

			node temp;
			int v;
			while (!queue.isEmpty()) {
				temp = queue.poll();
				v = temp.v;
				if (visit[v] == 1)
					continue;
				visit[v] = 1;
				dist[v] = temp.val;
				for (int i = head[v]; i != -1; i = edge[i].next) {
					if (visit[edge[i].v] == 0)
						queue
								.add(new node(v, edge[i].v, edge[i].val
										+ dist[v]));
				}
			}

			long sum = 0;
			for (int i = 2; i <= n; i++)
				if (dist[i] == 0)
					return -1;
				else
					sum += dist[i] * cost[i];
			return sum;
		}

		class edge {
			int v, next = -1;
			long val;

			edge(int u, int v, long val) {
				this.v = v;
				this.val = val;
			}
		}

		class node implements Comparable<node> {
			int u, v;
			long val;

			node(int u, int v, long val) {
				this.u = u;
				this.v = v;
				this.val = val;
			}

			public int compareTo(node o) {
				if (this.val < o.val)
					return -1;
				else if (this.val > o.val)
					return 1;
				return 0;
			}
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