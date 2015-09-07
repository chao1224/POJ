import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class p3107___Ê÷µÄÖØÐÄ {
	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StreamTokenizer in = new StreamTokenizer(br);
	static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));

	static int N = 50010;
	static int head[] = new int[N], tot;
	static edge edge[] = new edge[N * 2];
	static int cnt[] = new int[N];
	static int balance[] = new int[N];

	static class edge {
		int v, next;

		edge(int a) {
			v = a;
		}
	}

	static void addEdge(int u, int v) {
		edge[tot].v = v;
		edge[tot].next = head[u];
		head[u] = tot++;
	}

	static void init() {
		Arrays.fill(head, -1);
		tot = 0;
	}

	static void prework() {
		for (int i = 0; i < N * 2; i++)
			edge[i] = new edge(0);
	}

	static void dfs(int u, int f) {
		int v;
		balance[u] = 0;
		cnt[u] = 1;
		for (int e = head[u]; e != -1; e = edge[e].next) {
			v = edge[e].v;
			if (v == f)
				continue;
			dfs(v, u);
			cnt[u] += cnt[v];
			balance[u] = Math.max(balance[u], cnt[v]);
		}
		balance[u] = Math.max(balance[u], n - cnt[u]);
	}

	static int n;

	public static void main(String[] args) throws IOException {
		int u, v;
		prework();
		while (in.nextToken() != in.TT_EOF) {
			n = (int) in.nval;
			init();
			for (int i = 1; i < n; i++) {
				u = nextInt();
				v = nextInt();
				addEdge(u, v);
				addEdge(v, u);
			}

			dfs(1, -1);

			int min = balance[1];
			for (int i = 2; i <= n; i++)
				min = Math.min(min, balance[i]);
			for (int i = 1; i <= n; i++)
				if (balance[i] == min)
					out.print(i + " ");
			out.println();

			// int vert[] = new int[n], head = 0;
			// vert[head++] = 1;
			// for (int i = 2; i <= n; i++) {
			// if (balance[i] < balance[vert[0]]) {
			// head = 0;
			// vert[head++] = i;
			// } else if (balance[i] == balance[vert[0]])
			// vert[head++] = i;
			// }
			//
			// out.print(vert[0]);
			// for (int i = 1; i < head; i++)
			// out.print(" " + balance[vert[i]]);
			// out.println();

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