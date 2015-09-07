import java.awt.*;
import java.io.*;
import java.util.*;

public class p1419___图着色___最大团___最大独立集 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StreamTokenizer in = new StreamTokenizer(br);
	static StringTokenizer stoke;
	static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
	static Scanner scan = new Scanner(System.in);

	static int n, m;
	static edge edge[] = new edge[11000];
	static int head[] = new int[200], toll;
	static int map[][];

	static class edge {
		int v, next;

		edge(int v) {
			this.v = v;
		}
	}

	static void init() {
		Arrays.fill(head, -1);
		toll = 0;
	}

	public static void main(String[] args) throws IOException {
		int test = nextInt();
		int u, v;
		while (test-- > 0) {
			n = nextInt();
			m = nextInt();

			map = new int[n + 1][n + 1];
			for (int i = 1; i <= n; i++)
				Arrays.fill(map[i], 1);

			for (int i = 1; i <= m; i++) {
				u = nextInt();
				v = nextInt();
				map[u][v] = map[v][u] = 0;
			}

			choice = new int[n + 1];
			opt = new int[n + 1];
			most = 0;
			dfs(1, 0);

			out.println(most);
			for (int i = 1; i <= n; i++)
				if (opt[i] == 1)
					out.print(i + " ");
			out.println();

		}

		out.flush();
		out.close();
	}

	static int choice[], opt[];
	static int most = 0;

	static void dfs(int dep, int count) {
		if (dep > n) {
			if (most < count) {
				for (int i = 1; i <= n; i++)
					opt[i] = choice[i];
				most = count;
			}
		} else {
			boolean flag = true;

			for (int i = 1; i <= n; i++) {
				if (choice[i] == 1 && map[dep][i] == 0) {
					flag = false;
					break;
				}
			}

			if (flag) {
				choice[dep] = 1;
				dfs(dep + 1, count + 1);
				choice[dep] = 0;
			}

			if (count + n - dep > most)
				dfs(dep + 1, count);

		}
	}

	static void addEdge(int u, int v) {
		edge[toll] = new edge(v);
		edge[toll].next = head[u];
		head[u] = toll++;
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