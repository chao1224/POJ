import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.util.Arrays;

public class p4045___Ê÷ÐÎdp_DFS {
	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StreamTokenizer in = new StreamTokenizer(br);
	static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));

	static int N = 50100;
	static int tot;
	static int head[] = new int[N];
	static edge edge[] = new edge[2 * N];
	static int n, I, R;
	static int up[] = new int[N], down[] = new int[N];
	static long sum[] = new long[N];
	static boolean vis[] = new boolean[N];
	static int s;

	static class edge {
		int v, val, next;

		edge(int a, int c) {
			v = a;
			val = c;
		}
	}

	static void init() {
		Arrays.fill(head, -1);
		Arrays.fill(up, 0);
		Arrays.fill(down, 0);
		Arrays.fill(sum, 0);
		tot = 0;
	}

	static void addEdge(int u, int v) {
		edge[tot] = new edge(v, 1);
		edge[tot].next = head[u];
		head[u] = tot++;
	}

	public static void main(String[] args) throws IOException {
		int ttt = nextInt(), u, v;
		while (ttt-- > 0) {
			n = nextInt();
			I = nextInt();
			R = nextInt();
			init();
			for (int i = 1; i < n; i++) {
				u = nextInt();
				v = nextInt();
				addEdge(u, v);
				addEdge(v, u);
			}
			Arrays.fill(vis, false);
			s = 0;
			prepare(1, 0);
			up[1] = 0;

			Arrays.fill(vis, false);
			dfs(1, s);

			int queue[] = new int[n + 1], h = 0;
			long min = sum[1];
			queue[++h] = 1;
			for (int i = 2; i <= n; i++) {
				if (sum[i] < min) {
					min = sum[i];
					h = 0;
					queue[++h] = i;
				} else if (sum[i] == min) {
					queue[++h] = i;
				}
			}
			out.println((long) (min * I * I * R));
			for (int i = 1; i < h; i++)
				out.print(queue[i] + " ");
			out.println(queue[h]);

			out.println();
		}
		out.flush();
		out.close();
	}

	static void dfs(int u, int s) {
		vis[u] = true;
		int v;
		sum[u] = s;
		for (int e = head[u]; e != -1; e = edge[e].next) {
			v = edge[e].v;
			if (vis[v])
				continue;
			dfs(v, s + up[v] - down[v]);
		}
	}

	static void prepare(int u, int d) {
		vis[u] = true;
		s += d;
		int v;
		for (int e = head[u]; e != -1; e = edge[e].next) {
			v = edge[e].v;
			if (vis[v])
				continue;
			prepare(v, d + 1);
			down[u] += down[v] + 1;
		}
		up[u] = n - 2 - down[u];
	}

	static String next() throws IOException {
		in.nextToken();
		return in.sval;
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