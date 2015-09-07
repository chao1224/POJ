import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class p3635___更加巧妙地BFS___充分理解BFS {
	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StreamTokenizer in = new StreamTokenizer(br);
	static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));

	static int N = 1010, M = 10010;
	static int n, m;
	static int head[] = new int[N], tot;
	static edge edge[] = new edge[M * 2];
	static int st, ed;
	static int price[] = new int[N];
	static PriorityQueue<node> queue = new PriorityQueue<node>();

	// 静态开变量不够稳定
	// 这里有一个更加符合bfs的操作
	// 就是每次
	// queue.add(new node(u, used + price[u], oil + 1));

	static class edge {
		int v, next, val;

		edge(int v, int val) {
			this.v = v;
			this.val = val;
		}
	}

	static class node implements Comparable<node> {
		int vert, used, left;

		node(int a, int b, int c) {
			vert = a;
			used = b;
			left = c;
		}

		public int compareTo(node o) {
			return this.used - o.used;
		}
	}

	static void addEdge(int u, int v, int val) {
		edge[tot] = new edge(v, val);
		edge[tot].next = head[u];
		head[u] = tot++;
	}

	static void init() {
		Arrays.fill(head, -1);
		tot = 0;
	}

	public static void main(String[] args) throws IOException {
		int u, v, val;
		int c;
		init();

		n = nextInt();
		m = nextInt();
		for (int i = 0; i < n; i++)
			price[i] = nextInt();

		for (int i = 1; i <= m; i++) {
			u = nextInt();
			v = nextInt();
			val = nextInt();
			addEdge(u, v, val);
			addEdge(v, u, val);
		}

		int q = nextInt(), used, oil;
		boolean vis[][] = new boolean[n][101];
		node temp;
		int inf = 100000, neo;
		loop: while (q-- > 0) {
			c = nextInt();
			st = nextInt();
			ed = nextInt();
			queue.clear();
			for (int i = 0; i < n; i++)
				Arrays.fill(vis[i], false);

			vis[st][0] = true;
			queue.add(new node(st, 0, 0));

			while (!queue.isEmpty()) {
				temp = queue.poll();
				u = temp.vert;
				used = temp.used;
				oil = temp.left;
				vis[u][oil] = true;

				if (u == ed) {
					out.println(used);
					continue loop;
				}

				// this is a pretty important step
				if (oil < c && !vis[u][oil + 1])
					queue.add(new node(u, used + price[u], oil + 1));

				for (int e = head[u]; e != -1; e = edge[e].next) {
					v = edge[e].v;
					val = edge[e].val;
					neo = oil - val;
					if (neo >= 0 && !vis[v][neo])
						queue.add(new node(v, used, neo));
				}
			}

			out.println("impossible");
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

	static int nextint() throws IOException {
		in.nextToken();
		return (int) in.nval;
	}

	static int nextInt() throws IOException {
		in.nextToken();
		return (int) in.nval;
	}

	static long nextLong() throws IOException {
		in.nextToken();
		return (long) in.nval;
	}

	static float nextFloat() throws IOException {
		in.nextToken();
		return (float) in.nval;
	}

	static double nextDouble() throws IOException {
		in.nextToken();
		return in.nval;
	}
}